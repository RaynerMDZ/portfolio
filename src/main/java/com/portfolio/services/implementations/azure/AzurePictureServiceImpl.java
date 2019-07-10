package com.portfolio.services.implementations.azure;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import com.portfolio.Util.AzureConnection;
import com.portfolio.Util.CustomException;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Rayner MDZ
 */
@Slf4j
@Service
public class AzurePictureServiceImpl implements PictureService {

  private final PictureRepository pictureRepository;
  private final PostRepository postRepository;

  public AzurePictureServiceImpl(PictureRepository pictureRepository, PostRepository postRepository) {
    this.pictureRepository = pictureRepository;
    this.postRepository = postRepository;
  }

  /**
   *
   * @param id
   * @return
   */
  @Override
  public List<Picture> getAllPictures(Long id) {
    if (postRepository.findById(id).isPresent()) {
      return postRepository.findById(id).get().getPictures();
    }
    return null;
  }

  /**
   *
   * @param id
   * @return
   */
  @Override
  public Picture getPictureById(Long id) {

    if (id != null) {
      try {
        Optional<Picture> picture = pictureRepository.findById(id);

        if (picture.isPresent()) {
          return picture.get();
        }

      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
    return null;
  }

  /**
   *
   * @param postId
   * @param file
   * @return
   */
  @Override
  public boolean savePicture(Long postId, MultipartFile file) {

    CloudBlobContainer container;
    String URI = "";

    File newFile = convertFile(file);

    if (newFile == null) {
      return false;
    }

    try {
      // Parse the connection string and create a blob client to interact with Blob storage
      container = azureContainerConnection(AzureConnection.containerName, AzureConnection.storageConnectionString);

      if (container == null) {
        return false;
      }

      // Create the container if it does not exist with public access.
      System.out.println("Creating container: " + container.getName());
      container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

      //Getting a blob reference
      CloudBlockBlob blob = container.getBlockBlobReference(newFile.getName());

      //Creating blob and uploading file to it
      System.out.println("Uploading the file");
      blob.uploadFromFile(newFile.getAbsolutePath());
      URI = blob.getUri().toString();


    } catch (URISyntaxException | IOException e) {
      e.printStackTrace();

    } catch (StorageException ex) {
      System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
    }

    boolean success = saveImageWithUri(postId, URI);

    if (!success) {
      return false;
    }
    // Deletes the image saved to the server.
    newFile.delete();

    return true;
  }

  /**
   *
   * @param id
   * @return
   */
  @Override
  public boolean deletePictureById(Long id) {

    Picture picture = getPictureById(id);
    CloudBlobContainer container;

    try {

      container = azureContainerConnection(AzureConnection.containerName, AzureConnection.storageConnectionString);

      if (container == null) {
        return false;
      }

      // Separates the URI into an array.
      String[] name = picture.getPictureString().split("/");

      // Gets the last element in the array. This will be the name of the blob.
      // Looks for that name inside the container.
      CloudBlockBlob blob = container.getBlockBlobReference(name[name.length-1]);

      if (blob.exists()) {
        blob.delete();
        System.out.println("Blob with name: " + name[name.length-1] + " Deleted!");

      } else {
        throw new CustomException("Blob does not exist");
      }

      pictureRepository.delete(picture);

      return true;

    } catch (URISyntaxException | CustomException e) {
      e.printStackTrace();

    } catch (StorageException ex) {
      System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
    }
    return false;
  }

  /**
   *
   * @param id
   * @return
   */
  @Override
  public boolean hidePicture(Long id) {

    Picture picture = null;
    if (id != null) {
      picture = getPictureById(id);
    }

    // If picture is not null.
    if (picture != null) {
      // If isHidden is true then switch it to false.
      if (picture.getHidden()) {
        picture.setHidden(false);
        return false;

      } else {
        // If isHidden is false then switch it to true.
        picture.setHidden(true);
        return true;
      }
    }
    return false;
  }

  /**
   *
   * @param postId
   * @param URI
   * @return
   */
  private boolean saveImageWithUri(Long postId, String URI) {
    Post post = null;
    if (postRepository.findById(postId).isPresent()) {
      post = postRepository.findById(postId).get();
    }

    if (post == null) return false;

    Picture picture = new Picture();
    picture.setHidden(false);
    picture.setPictureString(URI);
    picture.setPost(post);

    try {
      pictureRepository.save(picture);
      return true;
    } catch (DataException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   *
   * @param azureContainerName
   * @param azureStorageConnectionString
   * @return
   */
  private CloudBlobContainer azureContainerConnection(String azureContainerName, String azureStorageConnectionString)  {

    CloudStorageAccount storageAccount;
    CloudBlobClient blobClient;
    CloudBlobContainer container;

    try {

      storageAccount = CloudStorageAccount.parse(azureStorageConnectionString);
      blobClient = storageAccount.createCloudBlobClient();
      container = blobClient.getContainerReference(azureContainerName);
      return container;

    } catch (URISyntaxException | InvalidKeyException e) {
      e.printStackTrace();

    } catch (StorageException ex) {
      System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
    }
    return null;
  }

  /**
   *
   * @param file
   * @return
   */
  private File convertFile(MultipartFile file) {

    File newFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

    try (FileOutputStream fos = new FileOutputStream(newFile)) {
      fos.write( file.getBytes());
      return newFile;

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
