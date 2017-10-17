package com.zlaja.avatest.security.rest;

import com.zlaja.avatest.security.ExternalAccessRule;
import com.zlaja.avatest.security.ExternalAccessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/dataSharingRules", produces = "application/json")
public class ExternalAccessRuleController {

    private final ExternalAccessRuleService externalAccessRuleService;

    @Autowired
    public ExternalAccessRuleController(ExternalAccessRuleService externalAccessRuleService) {
        this.externalAccessRuleService = externalAccessRuleService;
    }

    @RequestMapping(method = GET)
    public List<ExternalAccessRule> findAll() {
        return externalAccessRuleService.findAll();
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ExternalAccessRule findById(@PathVariable("id") String id) {
        return externalAccessRuleService.findById(id);
    }

    @RequestMapping(method = POST)
    public ExternalAccessRule create(@RequestBody ExternalAccessRule externalAccessRule) {
        return externalAccessRuleService.create(externalAccessRule);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public ExternalAccessRule update(@PathVariable("id") String id, @RequestBody ExternalAccessRule externalAccessRule) {
        return externalAccessRuleService.update(externalAccessRule);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public void delete(@PathVariable("id") String id) {
        externalAccessRuleService.delete(externalAccessRuleService.findById(id));
    }
}
