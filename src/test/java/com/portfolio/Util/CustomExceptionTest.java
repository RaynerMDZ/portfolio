package com.portfolio.Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rayner MDZ
 */
class CustomExceptionTest {

  @DisplayName("Custom Exception with = Exception")
  @Test
  void getMessage() {

    CustomException exception = new CustomException("Exception");

    Assertions.assertEquals(exception.getMessage(), "Exception");
  }
}
