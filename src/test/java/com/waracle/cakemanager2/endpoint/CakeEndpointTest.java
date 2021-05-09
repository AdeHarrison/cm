package com.waracle.cakemanager2.endpoint;

//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.hamcrest.core.Is.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.initMocks;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static uk.gov.ons.census.caseapisvc.utility.DataUtils.TEST_CCS_QID;
//import static uk.gov.ons.census.caseapisvc.utility.DataUtils.createCcsUacQidLink;
//import static uk.gov.ons.census.caseapisvc.utility.DataUtils.createMultipleCasesWithEvents;
//import static uk.gov.ons.census.caseapisvc.utility.DataUtils.createSingleCaseWithEvents;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.waracle.cakemanager2.service.CakeService;
//import com.waracle.cakemanager2.service.impl.CakeServiceImpl;
//
//import ma.glasnost.orika.MapperFacade;
//import ma.glasnost.orika.impl.DefaultMapperFactory;
//import uk.gov.ons.census.caseapisvc.exception.CaseIdNotFoundException;
//import uk.gov.ons.census.caseapisvc.exception.CaseReferenceNotFoundException;
//import uk.gov.ons.census.caseapisvc.exception.QidNotFoundException;
//import uk.gov.ons.census.caseapisvc.exception.UPRNNotFoundException;
//import uk.gov.ons.census.caseapisvc.model.entity.Case;
//import uk.gov.ons.census.caseapisvc.model.entity.UacQidLink;
//import uk.gov.ons.census.caseapisvc.service.CaseService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.service.CakeService;

import io.restassured.http.ContentType;

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

    //  @After
    //  public void tearDown() {
    //    reset(caseService);
    //  }

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

//    @Test
//    public void shouldCreateCakeWithEmployeeIdId() throws Exception {
//        String testCakeBody = "{\"title\": \"Lemon cheesecake\", \"description\": \"description1\", \"image\": \"image1\"}";
//        CakeDTO expected = new CakeDTO(1, "title1", "description1", "image1");
//
//        when(cakeService.createCake(any(CakeDTO.class))).thenReturn(expected);
//
//        // @formatter:off
//        mockMvc
//                .perform(
//                        post("/cases")
////                                .contentType(ContentType.JSON.toString())
//                                .content(testCakeBody)
//                            .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].employeeId", is(1)))
//                .andExpect(jsonPath("$[0].title", is("title1")))
//                .andExpect(jsonPath("$[0].description", is("description1")))
//                .andExpect(jsonPath("$[0].image", is("image1")));
//        // @formatter:on
////        String testCakeBody = "{\"title\": \"title1\", \"description\": \"description1\", \"image\": \"image1\"}";
////        CakeDTO expected = new CakeDTO(1, "title1", "description1", "image1");
////
////        // @formatter:off
////    String bodyAsString =
////            given()
////                    .when()
////                    .contentType(ContentType.JSON)
////                    .body(testCakeBody)
////                    .post(URL + "/cakes")
////                    .then()
////                    .contentType(ContentType.JSON)
////                    .and()
////                    .statusCode(201)
////                    .and()
////                    .extract()
////                    .body()
////                    .asString();
////    // @formatter:on
////
////        ObjectMapper mapper = new ObjectMapper();
////        CakeDTO actual = mapper.readValue(bodyAsString, CakeDTO.class);
////
////        assertThat(actual, equalTo(expected));
//    }

    @Test
    public void shouldRejectWith500WhenTitleAlreadyExists() {
//        String testCakeBody = "{\"title\": \"Lemon cheesecake\", \"description\": \"description1\", \"image\": \"image1\"}";
//
//        loadTestCake1();
//
//        // @formatter:off
//    given()
//            .when()
//            .contentType(ContentType.JSON)
//            .body(testCakeBody)
//            .post(URL + "/cakes")
//            .then()
//            .statusCode(500);
//    // @formatter:on
    }

    @Test
    public void shouldReturnJsonFileContainingCakes() throws IOException {
//        int expectedSize = 2;
//
//        loadTestCakes();
//
//        // @formatter:off
//    byte[] image =
//            given()
//                    .when()
//                    .get(URL + "/cakes")
//                    .then()
//                    .statusCode(200)
//                    .and()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cakes.json")
//                    .extract()
//                    .asByteArray();
//    // @formatter:on
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<CakeDTO> actual = mapper.readValue(image, new TypeReference<>() {
//        });
//
//        assertThat(actual.size(), equalTo(expectedSize));
    }

    //
    //  @Test
    //  public void getMultipleCasesWithoutEventsByUPRN() throws Exception {
    //    when(caseService.findByUPRN(anyString())).thenReturn(createMultipleCasesWithEvents());
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/uprn/%s", TEST_UPRN))
    //                .param("caseEvents", "false")
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASES_BY_UPRN))
    //        .andExpect(jsonPath("$[0].id", is(TEST1_CASE_ID)))
    //        .andExpect(jsonPath("$[0].caseEvents", hasSize(0)))
    //        .andExpect(jsonPath("$[1].id", is(TEST2_CASE_ID)))
    //        .andExpect(jsonPath("$[1].caseEvents", hasSize(0)));
    //  }
    //
    //  @Test
    //  public void getMultipleCasesWithoutEventsByDefaultByUPRN() throws Exception {
    //    when(caseService.findByUPRN(anyString())).thenReturn(createMultipleCasesWithEvents());
    //
    //    mockMvc
    //        .perform(get(createUrl("/cases/uprn/%s", TEST_UPRN)).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASES_BY_UPRN))
    //        .andExpect(jsonPath("$[0].id", is(TEST1_CASE_ID)))
    //        .andExpect(jsonPath("$[0].caseEvents", hasSize(0)))
    //        .andExpect(jsonPath("$[1].id", is(TEST2_CASE_ID)))
    //        .andExpect(jsonPath("$[1].caseEvents", hasSize(0)));
    //  }
    //
    //  @Test
    //  public void receiveNotFoundExceptionWhenUPRNDoesNotExist() throws Exception {
    //    when(caseService.findByUPRN(any())).thenThrow(new UPRNNotFoundException("a uprn"));
    //
    //    mockMvc
    //        .perform(get(createUrl("/cases/uprn/%s", TEST_UPRN)).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isNotFound());
    //  }
    //
    //  @Test
    //  public void getACaseWithEventsByCaseId() throws Exception {
    //    when(caseService.findByCaseId(any())).thenReturn(createSingleCaseWithEvents());
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/%s", TEST1_CASE_ID))
    //                .param("caseEvents", "true")
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASE_BY_ID))
    //        .andExpect(jsonPath("$.id", is(TEST1_CASE_ID)))
    //        .andExpect(jsonPath("$.caseEvents", hasSize(1)));
    //  }
    //
    //  @Test
    //  public void getACaseWithoutEventsByCaseId() throws Exception {
    //    when(caseService.findByCaseId(any())).thenReturn(createSingleCaseWithEvents());
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/%s", TEST1_CASE_ID))
    //                .param("caseEvents", "false")
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASE_BY_ID))
    //        .andExpect(jsonPath("$.id", is(TEST1_CASE_ID)))
    //        .andExpect(jsonPath("$.caseEvents", hasSize(0)))
    //        .andExpect(jsonPath("$.surveyType", is("CENSUS")));
    //  }
    //
    //  @Test
    //  public void getACaseWithoutEventsByCaseIdForCCSCase() throws Exception {
    //    Case testCase = createSingleCaseWithEvents();
    //    testCase.setCcsCase(true);
    //    when(caseService.findByCaseId(any())).thenReturn(testCase);
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/%s", TEST1_CASE_ID))
    //                .param("caseEvents", "false")
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASE_BY_ID))
    //        .andExpect(jsonPath("$.id", is(TEST1_CASE_ID)))
    //        .andExpect(jsonPath("$.caseEvents", hasSize(0)))
    //        .andExpect(jsonPath("$.surveyType", is("CCS")));
    //  }
    //
    //  @Test
    //  public void getACaseWithoutEventsByDefaultByCaseId() throws Exception {
    //    when(caseService.findByCaseId(any())).thenReturn(createSingleCaseWithEvents());
    //
    //    mockMvc
    //        .perform(get(createUrl("/cases/%s", TEST1_CASE_ID)).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASE_BY_ID))
    //        .andExpect(jsonPath("$.id", is(TEST1_CASE_ID)))
    //        .andExpect(jsonPath("$.caseEvents", hasSize(0)));
    //  }
    //
    //  @Test
    //  public void getCaseFromQidId() throws Exception {
    //    when(caseService.findCaseByQid(TEST_QID)).thenReturn(createSingleCaseWithEvents());
    //
    //    mockMvc
    //        .perform(get(createUrl("/cases/qid/%s", TEST_QID)).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(jsonPath("$.id", is(TEST1_CASE_ID)));
    //  }
    //
    //  @Test
    //  public void receiveNotFoundExceptionWhenCaseIdDoesNotExist() throws Exception {
    //    when(caseService.findByCaseId(any())).thenThrow(new CaseIdNotFoundException("a case id"));
    //
    //    mockMvc
    //        .perform(get(createUrl("/cases/%s", TEST1_CASE_ID)).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isNotFound());
    //  }
    //
    //  @Test
    //  public void getACaseWithEventsByCaseReference() throws Exception {
    //    when(caseService.findByReference(anyInt())).thenReturn(createSingleCaseWithEvents());
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ref/%s", TEST1_CASE_REFERENCE_ID))
    //                .param("caseEvents", "true")
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASE_BY_REFERENCE))
    //        .andExpect(jsonPath("$.caseRef", is(TEST1_CASE_REFERENCE_ID)))
    //        .andExpect(jsonPath("$.caseEvents", hasSize(1)));
    //  }
    //
    //  @Test
    //  public void getACaseWithoutEventsByCaseReference() throws Exception {
    //    when(caseService.findByReference(anyInt())).thenReturn(createSingleCaseWithEvents());
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ref/%s", TEST1_CASE_REFERENCE_ID))
    //                .param("caseEvents", "false")
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASE_BY_REFERENCE))
    //        .andExpect(jsonPath("$.caseRef", is(TEST1_CASE_REFERENCE_ID)))
    //        .andExpect(jsonPath("$.caseEvents", hasSize(0)));
    //  }
    //
    //  @Test
    //  public void getACaseWithoutEventsByDefaultByCaseReference() throws Exception {
    //    when(caseService.findByReference(anyInt())).thenReturn(createSingleCaseWithEvents());
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ref/%s", TEST1_CASE_REFERENCE_ID))
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(handler().methodName(METHOD_NAME_FIND_CASE_BY_REFERENCE))
    //        .andExpect(jsonPath("$.caseRef", is(TEST1_CASE_REFERENCE_ID)))
    //        .andExpect(jsonPath("$.caseEvents", hasSize(0)));
    //  }
    //
    //  @Test
    //  public void receiveNotFoundExceptionWhenCaseReferenceDoesNotExist() throws Exception {
    //    when(caseService.findByReference(anyInt())).thenThrow(new CaseReferenceNotFoundException(0));
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ref/%s", TEST1_CASE_REFERENCE_ID))
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isNotFound());
    //  }
    //
    //  @Test
    //  public void getCcsQidByCaseId() throws Exception {
    //    UacQidLink ccsUacQidLink = createCcsUacQidLink(TEST_CCS_QID, true);
    //    when(caseService.findUacQidLinkByCaseId(any())).thenReturn(ccsUacQidLink);
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ccs/%s/qid", TEST1_CASE_ID)).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(jsonPath("$.qid", is(TEST_CCS_QID)))
    //        .andExpect(jsonPath("$.active", is(true)));
    //  }
    //
    //  @Test
    //  public void getInactiveCcsQidByCaseId() throws Exception {
    //    UacQidLink ccsUacQidLink = createCcsUacQidLink(TEST_CCS_QID, false);
    //    when(caseService.findUacQidLinkByCaseId(any())).thenReturn(ccsUacQidLink);
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ccs/%s/qid", TEST1_CASE_ID)).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(handler().handlerType(CaseEndpoint.class))
    //        .andExpect(jsonPath("$.qid", is(TEST_CCS_QID)))
    //        .andExpect(jsonPath("$.active", is(false)));
    //  }
    //
    //  @Test
    //  public void getCcsQidByCaseIdCcsCaseNotFound() throws Exception {
    //    when(caseService.findUacQidLinkByCaseId(any())).thenThrow(new CaseIdNotFoundException("test"));
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ccs/%s/qid", TEST1_CASE_REFERENCE_ID))
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isNotFound());
    //  }
    //
    //  @Test
    //  public void getCcsQidByCaseIdCcsQIDNotFound() throws Exception {
    //    when(caseService.findUacQidLinkByCaseId(any())).thenThrow(new QidNotFoundException("test"));
    //
    //    mockMvc
    //        .perform(
    //            get(createUrl("/cases/ccs/%s/qid", TEST1_CASE_REFERENCE_ID))
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isNotFound());
    //  }
    //
    //  private String createUrl(String urlFormat, String param1) {
    //    return String.format(urlFormat, param1);
    //  }
}
