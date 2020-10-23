package com.cepheid.cloud.skel;

import com.cepheid.cloud.skel.MockDb.MockDB;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.cepheid.cloud.skel.controller.MovieController;
import com.cepheid.cloud.skel.repository.MovieRepository;

@SpringBootApplication(scanBasePackageClasses = {MovieController.class, SkelApplication.class})
@EnableJpaRepositories(basePackageClasses = {MovieRepository.class})
public class SkelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkelApplication.class, args);
    }

    @Bean
    ApplicationRunner initItems(MovieRepository repository, DescriptionRepository descriptionRepository) {
        return args -> {
            new MockDB().creatMockData(repository, descriptionRepository);
        };
    }
}


