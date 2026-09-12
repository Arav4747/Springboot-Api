package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repo.Productrepo;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    private final Productrepo productrepo;

    public AdminController(Productrepo productrepo)
    {
        this.productrepo = productrepo;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product)
    {
        return this.productrepo.save(product);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@RequestBody Product product, @PathVariable int id)
    {
        Product product2 = this.productrepo.getById(id);

        product2.setName(product.getName());
        product2.setCategory(product.getCategory());
        product2.setDiscount(product.getDiscount());
        product2.setPrice(product.getPrice());
        product2.setQuantity(product.getQuantity());

        this.productrepo.save(product2);

        return "updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id)
    {
        this.productrepo.deleteById(id);

        return "product deleted successfully";
    }
    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productrepo.findAll();
    }
}