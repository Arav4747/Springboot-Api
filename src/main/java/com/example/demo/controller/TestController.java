package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repo.Productrepo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController
{
	
	public TestController(Productrepo productrepo)
	{
	    this.productrepo = productrepo;
	}
	private final Productrepo productrepo;
	
	@GetMapping("/arav")
	public String demo()
	{
		return "Hello good morning arav";
	}
	
	//getting products in db
	@GetMapping("/products")
	public List<Product> getProduct()
	{
		List<Product> products = this.productrepo.findAll();
	return products;
	}
	
	
	//adding products in db
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product)
	{
		return this.productrepo.save(product);
	}
	
	 
	//for deleting itmes in db
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id)
	{
		this.productrepo.deleteById(id);
		return "product deleted successfully";
	}
	
	//forupdating items in databases
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
	
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable int id)
	{
		return this.productrepo.findById(id).get();
	}
	
	@GetMapping("/product/{category}")
	public List<Product> getProductByCategory(@PathVariable String category)
	{
		return this.productrepo.findByCategory(category);
	}
	
	@GetMapping("/products/price")
	public List<Product> getProdcutByPriceRange(@RequestParam(value = "min",required = false) double min,@RequestParam(value = "max",required = false)double max)
	{
		return this.productrepo.findByPriceBetween(min, max);
	}
}