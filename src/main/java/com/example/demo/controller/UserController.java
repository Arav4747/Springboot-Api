package com.example.demo.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repo.Productrepo;
import com.example.demo.repo.Userepo;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final Userepo userepo;
    private final Productrepo productrepo;
    private final PasswordEncoder passwordEncoder;

    public UserController(Userepo userepo, Productrepo productrepo, PasswordEncoder passwordEncoder)
    {
        this.userepo = userepo;
        this.productrepo = productrepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userepo.save(user);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productrepo.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id)
    {
        return productrepo.findById(id).get();
    }

    @GetMapping("/product/{category}")
    public List<Product> getProductByCategory(@PathVariable String category)
    {
        return productrepo.findByCategory(category);
    }

    @GetMapping("/products/price")
    public List<Product> getProductByPriceRange(
            @RequestParam double min,
            @RequestParam double max)
    {
        return productrepo.findByPriceBetween(min, max);
    }
}