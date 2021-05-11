package com.waracle.cakemanager2.endpoint;

import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.service.CakeService;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CakeEndpointTest {

    //  @Autowired
    private MockMvc mockMvc;

    @Mock
    private CakeService cakeService;

    @InjectMocks
    private CakeEndpoint underTest;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnAllCakes() throws Exception {
        List<CakeDTO> expected = Arrays.asList(new CakeDTO(1, "title1", "description1", "image1"),
                new CakeDTO(2, "title2", "description2", "image2"));

        when(cakeService.getCakes()).thenReturn(expected);

        // @formatter:off
        mockMvc
            .perform(
                get("/")
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].employeeId", is(1)))
            .andExpect(jsonPath("$[0].title", is("title1")))
            .andExpect(jsonPath("$[0].description", is("description1")))
            .andExpect(jsonPath("$[0].image", is("image1")))
            .andExpect(jsonPath("$[1].employeeId", is(2)))
            .andExpect(jsonPath("$[1].title", is("title2")))
            .andExpect(jsonPath("$[1].description", is("description2")))
            .andExpect(jsonPath("$[1].image", is("image2")));
        // @formatter:on
    }

    @Test
    public void shouldCreateCakeWithEmployeeIdId() throws Exception {
        String testCakeBody = "{\"title\": \"title1\", \"description\": \"description1\", \"image\": \"image1\"}";
        CakeDTO expected = new CakeDTO(1, "title1", "description1", "image1");

        when(cakeService.createCake(any(CakeDTO.class))).thenReturn(expected);

        // @formatter:off
        mockMvc
                .perform(
                        post("/cakes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(testCakeBody)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeId", is(1)))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.description", is("description1")))
                .andExpect(jsonPath("$.image", is("image1")));
        // @formatter:on
    }

    @Test
    public void shouldThrowConstraintViolationExceptionWhenTitleAlreadyExists() {
        String testCakeBody = "{\"title\": \"title1\", \"description\": \"description1\", \"image\": \"image1\"}";
        CakeDTO expected = new CakeDTO(1, "title1", "description1", "image1");

        when(cakeService.createCake(any(CakeDTO.class))).thenThrow(ConstraintViolationException.class);

        // @formatter:off
        Exception nse = Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc
                .perform(
                    post("/cakes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(testCakeBody));
        });

        assertThat(nse.getCause(), instanceOf(ConstraintViolationException.class));
    }

    @Test
    public void shouldReturnJsonFileContainingCakes() throws Exception {
        List<CakeDTO> expected = Arrays.asList(new CakeDTO(1, "title1", "description1", "image1"),
                new CakeDTO(2, "title2", "description2", "image2"));

        when(cakeService.getCakes()).thenReturn(expected);

        // @formatter:off
        mockMvc
                .perform(
                        get("/")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].employeeId", is(1)))
                .andExpect(jsonPath("$[0].title", is("title1")))
                .andExpect(jsonPath("$[0].description", is("description1")))
                .andExpect(jsonPath("$[0].image", is("image1")))
                .andExpect(jsonPath("$[1].employeeId", is(2)))
                .andExpect(jsonPath("$[1].title", is("title2")))
                .andExpect(jsonPath("$[1].description", is("description2")))
                .andExpect(jsonPath("$[1].image", is("image2")));
        // @formatter:on
    }
}
