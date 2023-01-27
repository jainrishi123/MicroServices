package com.onlineshopping.supplierservice.service;

import com.onlineshopping.supplierservice.entity.Supplier;
import com.onlineshopping.supplierservice.exception.SupplierException;
import com.onlineshopping.supplierservice.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Supplier findById(Integer id){
        return supplierRepository.findById(id).orElseThrow(()->new SupplierException("Supplier Not Found With Supplier Id"+id));

    }

}
