package com.oneifm.challenge.wos;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneifm.challenge.wos.wo.model.CleaningJob;
import com.oneifm.challenge.wos.wo.model.CleaningTeam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WOIntegrationTests {

    @Autowired
    private WebTestClient webClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetCleaningTeamAPI() {
        List<CleaningTeam> expectedCleaningTeams = Arrays.asList(
                new CleaningTeam(3, 1),
                new CleaningTeam(1, 2),
                new CleaningTeam(2, 0),
                new CleaningTeam(1, 3)
        );
        this.webClient.post()
                      .uri("api/wo/team")
                      .body(BodyInserters.fromObject(new CleaningJob(Arrays.asList(35, 21, 17, 28), 10, 6)))
                      .exchange()
                      .expectStatus()
                      .isOk()
                      .expectBody()
                      .consumeWith(response -> {
                          try {
                              Assert.assertEquals(
                                      objectMapper.readValue(response.getResponseBodyContent(), new TypeReference<List<CleaningTeam>>() {
                                      }),
                                      expectedCleaningTeams
                              );
                          } catch (JsonParseException e) {
                              Assert.fail(e.getMessage());
                          } catch (JsonMappingException e) {
                              Assert.fail(e.getMessage());
                          } catch (IOException e) {
                              Assert.fail(e.getMessage());
                          }
                      });
    }

    @Test
    public void testGetCleaningTeamAPIBadRequest() {
        ArrayList rooms = new ArrayList();

        for (int i = 0; i < 110; i++) {
            rooms.add(i);
        }

        this.webClient.post()
                      .uri("api/wo/team")
                      .body(BodyInserters.fromObject(new CleaningJob(rooms, 10, 6)))
                      .exchange()
                      .expectStatus()
                      .isBadRequest();
    }
}
