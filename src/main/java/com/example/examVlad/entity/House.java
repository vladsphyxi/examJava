package com.example.examVlad.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private String bedrooms;
    private Integer price;
    private Integer rentPrice;

    public House(Long id, String name, String bedrooms, Integer price, Integer rentPrice) {
        this.id = id;
        this.name = name;
        this.bedrooms = bedrooms;
        this.price = price;
        this.rentPrice = rentPrice;
    }

    public House() {
    }
    public Long getHouseId() {
        return id;
    }

    public void setHouseId(Long id) {
        this.id = id;
    }

    public String getHouseName() {
        return name;
    }

    public void setHouseName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getNoOfBed() {
        return bedrooms;
    }

    public void setNoOfBeds(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

}
