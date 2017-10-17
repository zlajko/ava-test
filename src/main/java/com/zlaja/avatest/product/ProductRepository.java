package com.zlaja.avatest.product;

import com.zlaja.avatest.organization.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findById(String id);

    List<Product> findByOrganizationIn(Set<Organization> organizations);

}
