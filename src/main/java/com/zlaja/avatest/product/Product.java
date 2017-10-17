package com.zlaja.avatest.product;

import com.zlaja.avatest.organization.Organization;
import com.zlaja.avatest.security.AccessRight;
import com.zlaja.avatest.security.ProductRule;
import com.zlaja.avatest.security.ProtectedResource;
import com.zlaja.avatest.security.Rule;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Set;

public class Product implements ProtectedResource {

    @Id
    private String id;
    private String name;
    private long price;
    private long stock;
    private Organization organization;

    @Transient
    private Set<AccessRight> rights;

    public Product() {
    }

    public Product(String name, long price, long stock, Organization organization) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public Class<? extends Rule> applicableRule() {
        return ProductRule.class;
    }

    public Set<AccessRight> getRights() {
        return rights;
    }

    public void setRights(Set<AccessRight> rights) {
        this.rights = rights;
    }
}
