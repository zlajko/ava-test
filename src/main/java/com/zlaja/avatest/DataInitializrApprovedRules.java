package com.zlaja.avatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"approvedRules", "default"})
class DataInitializrApprovedRules {

    @Autowired
    DataInitializrApprovedRules(DataInitializr dataInitializr) {
        dataInitializr.initData(true);
    }
}
