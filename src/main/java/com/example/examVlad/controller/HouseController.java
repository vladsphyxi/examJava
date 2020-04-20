package com.example.examVlad.controller;

import com.example.examVlad.entity.House;
import com.example.examVlad.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HouseController {

    @Autowired
    HouseRepository houseRepository;

    @PostMapping("/houses")
    public ResponseEntity<Long> saveHouse(House house) {
        House newHouse = (House) houseRepository.save(house);
        return new ResponseEntity<Long>(newHouse.getHouseId(), HttpStatus.OK);
    }

    @PutMapping("/houses/{id}")
    ResponseEntity<Long> updateHouse(@RequestBody House newHouse, @PathVariable Long id) {
        return houseRepository.findById(id).map(house -> {
            house.setHouseId(newHouse.getHouseId());
            house.setHouseName(newHouse.getHouseName());
            house.setPrice(newHouse.getPrice());
            house.setRentPrice(newHouse.getRentPrice());
            house.setNoOfBeds(newHouse.getNoOfBed());
            houseRepository.save(house);
            return new ResponseEntity<Long>(house.getHouseId(), HttpStatus.OK);
        }).orElseGet(() -> {
            newHouse.setHouseId(id);
            houseRepository.save(newHouse);
            return new ResponseEntity<Long>(newHouse.getHouseId(), HttpStatus.OK);
        });
    }
    @DeleteMapping("/houses/{id}")
    ResponseEntity<Long> deleteHouse(@PathVariable Long id) {
        houseRepository.deleteById(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
    @GetMapping("/houses/{id}")
    ResponseEntity<House> getHousesById(@PathVariable Long id) {
        return new ResponseEntity<House>(houseRepository.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping("/houses")
    public ResponseEntity<List<House>> getAllHouses() {
        List<House> list = (List<House>) houseRepository.findAll();
        return new ResponseEntity<List<House>>(list, HttpStatus.OK);
    }

}
