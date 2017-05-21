package com.ua.codespace.model.projection;


import com.ua.codespace.model.Company;
import com.ua.codespace.model.Product;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "name", types = {Product.class, Company.class})
public interface NameProjection {

    String getName();
}
