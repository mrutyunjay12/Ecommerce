package com.Mrutyunjay.ProductService.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GenericProductDTO {
    private long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
