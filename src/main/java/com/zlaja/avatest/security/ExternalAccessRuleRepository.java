package com.zlaja.avatest.security;

import com.zlaja.avatest.organization.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ExternalAccessRuleRepository extends MongoRepository<ExternalAccessRule, String> {

    Optional<ExternalAccessRule> findById(String id);

    List<ExternalAccessRule> findByToOrganizationAndIsApprovedByMainOrganization(Organization toOrganization, boolean isApprovedByMainOrganization);

    List<ExternalAccessRule> findByToOrganizationAndFromOrganizationAndIsApprovedByMainOrganization(Organization toOrganization, Organization fromOrganization, boolean isApprovedByMainOrganization);
}
