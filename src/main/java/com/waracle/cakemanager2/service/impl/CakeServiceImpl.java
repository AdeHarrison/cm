package com.waracle.cakemanager2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.repository.CakeRepository;
import com.waracle.cakemanager2.service.CakeService;

@Service
public class CakeServiceImpl implements CakeService {

    private final CakeRepository cakeRepository;

    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;

        //todo find better place for this
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

        //todo remove but check how run from original project first
        //        cakeRepository.save(new Cake("Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"));
        //        cakeRepository.save(new Cake("victoria sponge", "sponge with jam", "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"));
        //        cakeRepository.save(new Cake("Carrot cake", "Bugs bunnys favourite", "http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg"));
        //        cakeRepository.save(new Cake("Banana cake", "Donkey kongs favourite", "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"));
        //        cakeRepository.save(new Cake("Birthday cake", "a yearly treat", "http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg"));
        //        cakeRepository.save(new Cake("Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"));
        //        cakeRepository.save(new Cake("victoria sponge", "sponge with jam", "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"));
        //        cakeRepository.save(new Cake("Carrot cake", "Bugs bunnys favourite", "http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg"));
        //        cakeRepository.save(new Cake("Banana cake", "Donkey kongs favourite", "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"));
        //        cakeRepository.save(new Cake("Birthday cake", "a yearly treat", "http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg"));
        //        cakeRepository.save(new Cake("Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"));
        //        cakeRepository.save(new Cake("victoria sponge", "sponge with jam", "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"));
        //        cakeRepository.save(new Cake("Carrot cake", "Bugs bunnys favourite", "http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg"));
        //        cakeRepository.save(new Cake("Banana cake", "Donkey kongs favourite", "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"));
        //        cakeRepository.save(new Cake("Birthday cake", "a yearly treat", "http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg"));
    }

    public List<CakeDTO> getAllCakes() {
        List<CakeDTO> cakeDTOS = new ArrayList<>();

        for (Cake cake : cakeRepository.findAll()) {
            cakeDTOS.add(new CakeDTO(cake));
        }

        return cakeDTOS;
    }

    public Cake createCake(CakeDTO cakeDTO) {
        return cakeRepository.save(new Cake(cakeDTO.getTitle(), cakeDTO.getDescription(), cakeDTO.getImage()));
    }
}
