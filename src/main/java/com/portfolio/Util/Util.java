package com.portfolio.Util;

import java.util.UUID;

public class Util {

  public static String UPLOAD_DIRECTORY =  System.getProperty("user.dir") + "/src/main/resources/static/img/";
  public static String IMAGE_URL = "/img/";


  public static String generateString() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
