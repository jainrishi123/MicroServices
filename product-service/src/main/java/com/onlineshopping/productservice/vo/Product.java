package com.onlineshopping.productservice.vo;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
public class Product {

    private int productID;
    @Min(value = 1, message = "Minimum 1 Unit is required")
    private Integer unit;

    @NotNull
    private Integer price;
    @NotNull
    private Integer supplierId;
    @NotBlank(message = "productName cannot be empty")
    private String productName;
    @NotBlank(message = "productName cannot be empty")
    String productImage;



}



