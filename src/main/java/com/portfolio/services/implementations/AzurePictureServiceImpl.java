package com.portfolio.services.implementations;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import com.portfolio.Util.CustomException;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
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

  private static final String storageConnectionString =
          "DefaultEndpointsProtocol=https;AccountName=raynerdevblobstorage;" +
                  "AccountKey=xzw4mmEStHdG8PIflLLawesgyvdmLJ2STMGJRELNgoDUelNCj/9v87eBv7bh75vk3UIOQH7OrqTUp05EIpPVNw==;" +
                  "EndpointSuffix=core.windows.net";

  private static final String containerName = "portfolioimages";

  private final PictureRepository pictureRepository;
  private final PostRepository postRepository;
  private final PostService postService;
  private final ResourceLoader resourceLoader;

  public AzurePictureServiceImpl(PictureRepository pictureRepository,
                            PostRepository postRepository,
                            PostService postService,
                            ResourceLoader resourceLoader) {
    this.pictureRepository = pictureRepository;
    this.postRepository = postRepository;
    this.postService = postService;
    this.resourceLoader = resourceLoader;
  }

  /**
   *
   * @param id
   * @return
   */
  @Override
  public List<Picture> getAllPictures(Long id) {

    return postService.getPostById(id).getPictures();
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

    CloudStorageAccount storageAccount;
    CloudBlobClient blobClient;
    CloudBlobContainer container;
    String URI = "";

    File newFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream( newFile );
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      fos.write( file.getBytes() );
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      // Parse the connection string and create a blob client to interact with Blob storage
      storageAccount = CloudStorageAccount.parse(storageConnectionString);
      blobClient = storageAccount.createCloudBlobClient();
      container = blobClient.getContainerReference(containerName);

      // Create the container if it does not exist with public access.
      System.out.println("Creating container: " + container.getName());
      container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

      //Getting a blob reference
      CloudBlockBlob blob = container.getBlockBlobReference(newFile.getName());

      //Creating blob and uploading file to it
      System.out.println("Uploading the file");
      blob.uploadFromFile(newFile.getAbsolutePath());
      URI = blob.getUri().toString();


    } catch (URISyntaxException | InvalidKeyException | IOException e) {
      e.printStackTrace();

    } catch (StorageException ex) {
      System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
    }

    Post post = postService.getPostById(postId);

    Picture picture = new Picture();
    picture.setHidden(false);
    picture.setPictureString(URI);
    picture.setPost(post);

    pictureRepository.save(picture);

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
    CloudStorageAccount storageAccount;
    CloudBlobContainer container = null;
    CloudBlobClient blobClient = null;

    try {

      storageAccount = CloudStorageAccount.parse(storageConnectionString);
      blobClient = storageAccount.createCloudBlobClient();
      container = blobClient.getContainerReference(containerName);

      // Separates the URI into an array.
      String[] name = picture.getPictureString().split("/");

      // Gets the last element in the array. This will be the name of the blob.
      // Looks for that name inside the container.
      CloudBlockBlob blob = container.getBlockBlobReference(name[name.length-1]);

      // If the blob exist then it erases it.
      if (blob.exists()) {
        blob.delete();
        System.out.println("Blob with name: " + name[name.length-1] + " Deleted!");
      } else {
        throw new CustomException("Blob does not exist");
      }

      pictureRepository.delete(picture);

      return true;

    } catch (URISyntaxException | InvalidKeyException | CustomException e) {
      e.printStackTrace();
      return false;

    } catch (StorageException ex) {
      System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
      return false;
    }
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
}
