package com.example.examVlad.web;

import com.example.examVlad.controller.HouseController;
import com.example.examVlad.entity.House;
import com.example.examVlad.repository.HouseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@RunWith(SpringRunner.class)
@WebMvcTest(HouseController.class)
public class HouseControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HouseRepository houseRepository;

    @Test
    public void saveHouse() throws Exception {
        House testHouse = new House(1L, "Street A", "2 bedroom", 9, 9);
        mockMvc.perform(post("/Houses", testHouse)).andExpect(status().isOk());
        verify(houseRepository, times(1)).findAll();
    }

    @Test
    public void updateHouse() throws Exception {
        House testHouse = new House(2L, "Street B ", "2 bedroom", 123000, 1300);
        mockMvc.perform(put("/houses/" + testHouse.getHouseId(), testHouse)).andExpect(status().isOk());
        verify(houseRepository, times(1)).findAll();
    }

    @Test
    public void deleteHouse() throws Exception {
        long testId = 1L;
        mockMvc.perform(put("/houses/" + testId)).andExpect(status().isOk());
        verify(houseRepository, times(1)).findAll();
    }

    @Test
    public void getHouseById() throws Exception {
        long testId = 2L;
        mockMvc.perform(get("/house/" + testId)).andExpect(status().isOk());
        verify(houseRepository, times(1)).findAll();
    }

    @Test
    public void getAllHouses() throws Exception {
        mockMvc.perform(get("/houses")).andExpect(status().isOk());
        verify(houseRepository, times(1)).findAll();
    }

}
