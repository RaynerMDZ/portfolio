package com.portfolio.Util;

import java.util.UUID;

public class Util {

  public static String UPLOAD_DIRECTORY =  System.getProperty("user.dir") + "/src/main/resources/static/images/uploads/";
  public static String IMAGE_URL = "../images/uploads/";


  public static String generateString() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
