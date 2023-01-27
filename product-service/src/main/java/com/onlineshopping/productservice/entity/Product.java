package com.onlineshopping.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "Product_10709086")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;

    @ManyToOne()
    @JoinColumn(name = "supplierId", insertable = false, updatable = false)
    Supplier supplier;
    int unit, price, supplierId;
    String productName;
    String productImage;


}
