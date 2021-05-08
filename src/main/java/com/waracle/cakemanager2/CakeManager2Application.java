package com.waracle.cakemanager2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.repository.CakeRepository;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CakeManager2Application implements CommandLineRunner {

    @Autowired
    ApplicationContextProvider applicationContextProvider;

    public static void main(String[] args) {
        SpringApplication.run(CakeManager2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //todo Not pretty as creates data for ITs as well but a quick "technical fix" for now

        CakeRepository cakeRepository = applicationContextProvider.getApplicationContext()
                .getBean(CakeRepository.class);

        cakeRepository.save(new Cake("Lemon cheesecake", "A cheesecake made of lemon",
                "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"));
        cakeRepository.save(new Cake("victoria sponge", "sponge with jam",
                "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"));
        cakeRepository.save(new Cake("Carrot cake", "Bugs bunnys favourite",
                "http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg"));
        cakeRepository.save(new Cake("Banana cake", "Donkey kongs favourite",
                "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"));
        cakeRepository.save(new Cake("Birthday cake", "a yearly treat",
                "http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg"));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
