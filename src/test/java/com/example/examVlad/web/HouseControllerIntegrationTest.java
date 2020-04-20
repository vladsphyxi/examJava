package com.example.examVlad.web;

import com.example.examVlad.entity.House;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HouseControllerIntegrationTest {

    @LocalServerPort
    private int port = 8010;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveHouse(House house) {
        ResponseEntity<Long> response = this.restTemplate.postForEntity("http://localhost:" + port + "/houses", house, Long.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void updateHouse(House newHouse, Long id) {
        this.restTemplate.put("http://localhost:" + port + "/houses/" + id, newHouse, Long.class);
        assertThat(HttpStatus.OK, equalTo(HttpStatus.OK));
    }

    @Test
    public void deleteHouse(Long id) {
        this.restTemplate.delete("http://localhost:" + port + "/houses/" + id, Long.class);
        assertThat(HttpStatus.OK, equalTo(HttpStatus.OK));
    }

    @Test
    public void getHousesById(Long id) {
        ResponseEntity<House> response = this.restTemplate.getForEntity("http://localhost:" + port + "/houses/" + id, House.class);
        assertThat(response, equalTo(HttpStatus.OK));
    }

    @Test
    public void getAllHouses() {
        ResponseEntity<List> response = this.restTemplate.getForEntity("http://localhost:" + port + "/houses", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
