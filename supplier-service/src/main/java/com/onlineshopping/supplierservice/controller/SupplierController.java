package com.onlineshopping.supplierservice.controller;


import com.onlineshopping.supplierservice.entity.Supplier;
import com.onlineshopping.supplierservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("api/v1/supplier/{id}")
    ResponseEntity<Supplier> findById( @PathVariable Integer id){
    return new ResponseEntity<>(supplierService.findById(id), HttpStatus.OK);
    }
}
