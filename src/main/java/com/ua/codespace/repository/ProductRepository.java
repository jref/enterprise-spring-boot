package com.ua.codespace.repository;

import com.ua.codespace.model.Product;
import com.ua.codespace.model.projection.InlineDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = InlineDescription.class)
public interface ProductRepository extends JpaRepository<Product, Long> {
}
