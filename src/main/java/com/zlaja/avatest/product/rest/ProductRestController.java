package com.zlaja.avatest.product.rest;

import com.zlaja.avatest.product.Product;
import com.zlaja.avatest.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/products", produces = "application/json")
@Api(value = "Product Rest Controller", authorizations = {@Authorization(value="basicAuth")})
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = GET)
    public List<Product> findAll() {
        return productService.findAll();
    }

    @RequestMapping(path = "/{id}", method = GET)
    public Product findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @RequestMapping(method = POST)
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public Product update(@PathVariable("id") String id, @RequestBody Product product) {
        return productService.update(product);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public void delete(@PathVariable("id") String id) {
        productService.delete(productService.findById(id));
    }
}
