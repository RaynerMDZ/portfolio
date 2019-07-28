package com.portfolio.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Rayner MDZ
 */
@Configuration
@PropertySource("classpath:azure.properties")
public class AzureConnection {

  @Value("${azure.storageConnectionString}")
  public String storageConnectionString;

  @Value("${azure.containerName}")
  public String containerName;

}
