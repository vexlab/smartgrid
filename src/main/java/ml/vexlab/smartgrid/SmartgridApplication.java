package ml.vexlab.smartgrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SmartgridApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmartgridApplication.class, args);
  }
}
