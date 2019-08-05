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
import java.util.Optional;
import java.util.UUID;

/**
 * @author Rayner MDZ
 */
@Slf4j
@Service
public class AzurePictureServiceImpl implements PictureService {

  private final PictureRepository pictureRepository;
  private final PostRepository postRepository;
  private final AzureConnection azureConnection;

  public AzurePictureServiceImpl(PictureRepository pictureRepository,
                                 PostRepository postRepository,
                                 AzureConnection azureConnection) {
    this.pictureRepository = pictureRepository;
    this.postRepository = postRepository;
    this.azureConnection = azureConnection;
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

    File newFile = convertFile(file, postId);

    if (newFile == null) {
      return false;
    }

    try {
      // Parse the connection string and create a blobReference client to interact with Blob storage
      container = azureContainerConnection(azureConnection.containerName, azureConnection.storageConnectionString);

      if (container == null) {
        return false;
      }

      // Create the container if it does not exist with public access.
      System.out.println("Creating container: " + container.getName());
      createContainer(container);

      //Getting a blobReference reference
      CloudBlockBlob blobReference = container.getBlockBlobReference(newFile.getName());

      //Creating blobReference and uploading file to it
      System.out.println("Uploading the file");
      URI = uploadFile(URI, newFile, blobReference);


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
   * @param postId
   * @param files
   * @return
   */
  @Override
  public boolean savePictures(Long postId, MultipartFile[] files) {

    CloudBlobContainer container;
    String URI = "";

    try {
      // Parse the connection string and create a blobReference client to interact with Blob storage
      container = azureContainerConnection(azureConnection.containerName, azureConnection.storageConnectionString);

      if (container == null) {
        return false;
      }

      // Create the container if it does not exist with public access.
      createContainer(container);

      // Upload files to the Azure storage.
      for (MultipartFile file : files) {

        File newFile = convertFile(file, postId);

        if (newFile == null) {
          return false;
        }

        //Getting a blobReference reference
        CloudBlockBlob blobReference = container.getBlockBlobReference(newFile.getName());

        //Creating blobReference and uploading file to it
        System.out.println();
        System.out.println("Uploading the file");
        URI = uploadFile(URI, newFile, blobReference);
        System.out.println(URI);

        boolean success = saveImageWithUri(postId, URI);

        // if the image was not saved into the database, the blob is deleted.
        if (!success) {
          blobReference.delete();
          return false;
        }

        newFile.delete();
      }

      return true;

    } catch (URISyntaxException | IOException e) {
      e.printStackTrace();
      return false;

    } catch (StorageException ex) {
      System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
      return false;
    }
  }

  /**
   *
   * @param URI
   * @param newFile
   * @param blob
   * @return
   * @throws StorageException
   * @throws IOException
   */
  private String uploadFile(String URI, File newFile, CloudBlockBlob blob) throws StorageException, IOException {
    blob.uploadFromFile(newFile.getAbsolutePath());
    URI = blob.getUri().toString();
    return URI;
  }

  /**
   *
   * @param container
   * @throws StorageException
   */
  private void createContainer(CloudBlobContainer container) throws StorageException {
    container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
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

      container = azureContainerConnection(azureConnection.containerName, azureConnection.storageConnectionString);

      if (container == null) {
        return false;
      }

      // Separates the URI into an array.
      String[] name = picture.getPictureString().split("/");
//      String nameNoArray = picture.getPictureString().substring(picture.getPictureString().lastIndexOf("/"));
//
//
//      System.out.println();
//      System.out.println("nameNoArray " + nameNoArray.substring(1));
//      System.out.println();

      // Gets the last element in the array. This will be the name of the blob.
      // Looks for that name inside the container.
      CloudBlockBlob blob = container.getBlockBlobReference(name[name.length-1]);

      if (blob.exists()) {

        blob.delete();
        System.out.println("Blob with name: " + name[name.length-1] + " Deleted!");

      } else {
        throw new CustomException("Blob with name " + name[name.length -1] + " does not exist");
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
  private File convertFile(MultipartFile file, Long postId) {

    File newFile = new File("postId_" + postId + "_fileName_" + generateString() + getFileExtension(file.getOriginalFilename()));

    try (FileOutputStream fos = new FileOutputStream(newFile)) {
      fos.write(file.getBytes());
      return newFile;

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Creates a random name.
   * @return a random generated string.
   */
  @Override
  public String generateString() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * Removes all characters before the last 'DOT' from the name.
   * @param name as the file name
   * @return the extension of the file.
   */
  @Override
  public String getFileExtension(String name) {

    String extension;
    try {
      extension = name.substring(name.lastIndexOf("."));

    } catch (Exception e) {
      extension = "";
    }
    return extension;
  }
}
