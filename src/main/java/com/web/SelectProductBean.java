package com.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.model.ProductModel;
import com.spring.service.ProductService;

import lombok.Data;

@ManagedBean(name = "selectProductBean", eager = true)
@SessionScoped
@Component
public @Data class SelectProductBean implements Serializable {

	private static final long serialVersionUID = 1549481937223946546L;
	
	private String name;
	private String description;
	private String price;
	private String type;
	private String color;
	private byte[] foto;

	@Autowired
	private ProductService productService;
	
	private List<ProductModel> products = new ArrayList<>();
	
	public String showProductsByName(String name){
		this.name = name;
		setProducts(productService.findProductByName(name));

		return "/pages/secure/productCases?faces-redirect=true"; 
	}
	
	public String showProductsByColor(String color){
		this.color = color;
		setProducts(productService.findProductByColor(color));

		return "/pages/secure/productCases?faces-redirect=true"; 
	}
	
	public String showProductsByType(String type){
		this.type = type;
		setProducts(productService.findProductByType(type));

		return "/pages/secure/productCases?faces-redirect=true"; 
	}
	
	public String showAll(){
		setProducts(productService.getAllProducts());

		return "/pages/secure/productCases?faces-redirect=true"; 
	}
}
