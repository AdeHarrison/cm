package com.waracle.cakemanager2.service.impl;

import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.repository.CakeRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;

    @InjectMocks
    private CakeServiceImpl cakeService;

    @Test
    public void getCakes() {
        List<Cake> cakes = asList(createCake1(), createCake2());
        List<CakeDTO> expected = asList(new CakeDTO(createCake1()), new CakeDTO(createCake2()));

        when(cakeRepository.findAll()).thenReturn(cakes);

        List<CakeDTO> actual = cakeService.getCakes();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void createCake() {
        Cake testCake = createCake1();
        CakeDTO expected = new CakeDTO(createCake1());

        when(cakeRepository.save(any(Cake.class))).thenReturn(testCake);

        CakeDTO actual = cakeService.createCake(expected);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldThrowConstraintViolationExceptionWhenTitleAlreadyExists() {

        when(cakeRepository.save(any(Cake.class))).thenThrow(ConstraintViolationException.class);

        Assertions.assertThrows(ConstraintViolationException.class, () -> cakeService.createCake(new CakeDTO()));
    }

    private Cake createCake1() {
        return new Cake("Lemon cheesecake", "A cheesecake made of lemon",
                "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
    }

    private Cake createCake2() {
        return new Cake("victoria sponge", "sponge with jam",
                "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg");
    }
}