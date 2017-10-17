package com.zlaja.avatest.product;

import com.zlaja.avatest.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private final ProductRepository productRepository;
    private final OrganizationService organizationService;

    @Autowired
    public ProductService(ProductRepository productRepository, OrganizationService organizationService) {
        this.productRepository = productRepository;
        this.organizationService = organizationService;
    }

    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<Product> findAll() {
        return productRepository.findByOrganizationIn(organizationService.getOrganizationsForCurrentUser());
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException();
    }

    @PreAuthorize("hasPermission(#product, 'CREATE')")
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @PreAuthorize("hasPermission(#product, 'UPDATE')")
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @PreAuthorize("hasPermission(#product, 'DELETE')")
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
