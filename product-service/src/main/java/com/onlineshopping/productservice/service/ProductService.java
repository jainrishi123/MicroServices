package com.onlineshopping.productservice.service;

//Static import getEntityProduct Java 5

import com.netflix.discovery.converters.Auto;
import com.onlineshopping.productservice.entity.Product;
import com.onlineshopping.productservice.entity.Supplier;
import com.onlineshopping.productservice.exception.ProductException;
import com.onlineshopping.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service  //Annotation java 5
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    SupplierRepository supplierRepository;

//    byte b1 = 0b101; //Binary Literal Java 8

    public List<Product> getProducts() {
        return productRepository.findAll();
//        List<com.onlineshopping.productservice.vo.Product> list = new ArrayList<>();// Generics Java 5
//        for (Product p : productRepository.findAll()) {    //For Each loop Java 5
//            list.add(getVoProduct(p));
//        }
//        return list;
    }

    public com.onlineshopping.productservice.vo.Product getById(int pid) {
        return getVoProduct(productRepository.findById(pid).
                orElseThrow(() -> new ProductException("Product Not Found with ProductId :" + pid)));

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String saveProduct(List<com.onlineshopping.productservice.vo.Product> products) {
        List<Product> pro = new ArrayList<>();
        for (com.onlineshopping.productservice.vo.Product product1 : products) {
            pro.add(getEntityProduct(product1));
        }
        productRepository.saveAll(pro);
        return "Product Saved";

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String updateProduct(com.onlineshopping.productservice.vo.Product product1) {
        Product product = getEntityProduct(product1);
        if (productRepository.findById(product.getProductID()).isEmpty()) {
            throw new ProductException("Product Not Found with ProductId :" + product.getProductID());
        } else {
            productRepository.save(product);
        }
        return "Product Updated";
    }

    public String deleteProduct(int pid) {
        if (productRepository.findById(pid).isEmpty()) {
            throw new ProductException("Product Not Found with ProductId :" + pid);
        } else {
            productRepository.deleteById(pid);
            return "Product Deleted Having ProductId: " + pid;
        }
    }

    public com.onlineshopping.productservice.vo.Product getVoProduct(Product p1) {
        return com.onlineshopping.productservice.vo.Product.builder().productID(p1.getProductID()).productName(p1.getProductName()).
                price(p1.getPrice()).unit(p1.getUnit()).supplierId(p1.getSupplierId()).productImage(p1.getProductImage()).build();
    }


    public com.onlineshopping.productservice.entity.Product getEntityProduct(com.onlineshopping.productservice.vo.Product p1) {
        return Product.builder().productID(p1.getProductID()).productName(p1.getProductName()).
                unit(p1.getUnit()).productImage(p1.getProductImage()).price(p1.getPrice()).supplier(restTemplate.getForObject
                        ("http://localhost:9001/api/v1/supplier/"+p1.getSupplierId(), Supplier.class))
                        .build();
    }
}
