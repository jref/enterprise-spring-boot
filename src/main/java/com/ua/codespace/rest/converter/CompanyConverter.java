package com.ua.codespace.rest.converter;

import com.ua.codespace.model.Company;
import com.ua.codespace.rest.dto.CompanyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter implements Converter<Company, CompanyDto> {


    @Override
    public CompanyDto convert(Company source) {
        return new CompanyDto(source.getName());
    }
}
