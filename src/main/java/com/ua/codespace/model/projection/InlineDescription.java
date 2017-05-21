package com.ua.codespace.model.projection;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.codespace.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineDescription", types = Product.class)
public interface InlineDescription extends NameProjection {

    NameProjection getProducer();

    @JsonProperty("desc")
    @Value("Product #{target.name}, produced by #{target.producer.name}")
    String getDescription();
}
