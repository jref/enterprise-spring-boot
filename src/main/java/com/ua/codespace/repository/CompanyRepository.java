package com.ua.codespace.repository;

import com.ua.codespace.model.Company;
import com.ua.codespace.model.projection.NameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = NameProjection.class)
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @RestResource(exported = false)
    @Override
    void delete(Long aLong);

    @RestResource(exported = false)
    @Override
    void delete(Company entity);
}
