package com.waracle.cakemanager2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.repository.CakeRepository;

@Component
@Profile("!test")
public class Utils implements CommandLineRunner {

    @Autowired
    CakeRepository cakeRepository;

    @Override
    public void run(String... args) {
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
}
