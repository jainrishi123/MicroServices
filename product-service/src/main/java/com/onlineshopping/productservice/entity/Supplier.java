package com.onlineshopping.productservice.entity;


import jakarta.persistence.*;
import lombok.Data;

//import javax.persistence.*;


@Data
@Entity
@Table(name = "Supplier_10709086")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int supplierId;
    String supplierName, address, city;
    int postalCode;
    long phone;


}
