package edu.ucsb.cs156.happiercows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HappierCowsApplication {

  public static void main(String[] args) {
    SpringApplication.run(HappierCowsApplication.class, args);
  }

}
