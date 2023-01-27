package com.onlineshopping.productservice.controller;


import com.onlineshopping.productservice.service.ProductService;
import com.onlineshopping.productservice.vo.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;


@CrossOrigin
@RestController
@Validated
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/v1/products")
    ResponseEntity<List<com.onlineshopping.productservice.entity.Product>> getProducts() {

        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }


    @GetMapping("/v1/products/{pid}")
    ResponseEntity<Product> getById(@PathVariable Integer pid) {
        return new ResponseEntity<>(productService.getById(pid), HttpStatus.OK);
    }

    @PostMapping("/v1/products")
    ResponseEntity<String> saveProduct(@RequestBody @Valid List<Product> products) {
        return new ResponseEntity<>(productService.saveProduct(products), HttpStatus.OK);
    }

    @PutMapping("/v1/products")
    ResponseEntity<String> updateProduct(@RequestBody @Valid List<Product> products) {
        for (Product p : products) {
            productService.updateProduct(p);
        }
        return new ResponseEntity<>("Products Updated", HttpStatus.OK);
    }

    @DeleteMapping("/v1/products/{pid}")
    ResponseEntity<String> deleteProduct(@PathVariable Integer pid) {
        return new ResponseEntity<>(productService.deleteProduct(pid), HttpStatus.OK);
    }

}
