package com.zlaja.avatest.security;

import com.zlaja.avatest.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExternalAccessRuleService {

    private final ExternalAccessRuleRepository externalAccessRuleRepository;

    @Autowired
    public ExternalAccessRuleService(ExternalAccessRuleRepository externalAccessRuleRepository) {
        this.externalAccessRuleRepository = externalAccessRuleRepository;
    }

    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<ExternalAccessRule> findAll() {
        return externalAccessRuleRepository.findAll();
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public ExternalAccessRule findById(String id) {
        Optional<ExternalAccessRule> externalAccessRule = externalAccessRuleRepository.findById(id);
        if (externalAccessRule.isPresent()) {
            return externalAccessRule.get();
        }
        throw new ProductNotFoundException();
    }

    @PreAuthorize("hasPermission(#externalAccessRule, 'CREATE')")
    public ExternalAccessRule create(ExternalAccessRule externalAccessRule) {
        return externalAccessRuleRepository.save(externalAccessRule);
    }

    @PreAuthorize("hasPermission(#externalAccessRule, 'UPDATE')")
    public ExternalAccessRule update(ExternalAccessRule externalAccessRule) {
        return externalAccessRuleRepository.save(externalAccessRule);
    }

    @PreAuthorize("hasPermission(#externalAccessRule, 'DELETE')")
    public void delete(ExternalAccessRule externalAccessRule) {
        externalAccessRuleRepository.delete(externalAccessRule);
    }
}
