package com.portfolio.Util;

import java.util.UUID;

public class Util {

  public static String UPLOAD_DIRECTORY =  System.getProperty("user.dir") + "/src/main/resources/static/images/";
  public static String IMAGE_URL = "/images/";

  public static String generateString() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
