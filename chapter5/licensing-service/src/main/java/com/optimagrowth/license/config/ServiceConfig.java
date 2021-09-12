package com.optimagrowth.license.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "ostock")
@Getter @Setter
public class ServiceConfig{

  // together with the configured prefix "ostock" this property can be read from the configuration server as
  // ostock.environment
  private String environment;

  //Some extra property to check encryption / decryption functionality in the Config server
  private String secret;
    
}