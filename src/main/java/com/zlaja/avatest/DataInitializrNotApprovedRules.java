package com.zlaja.avatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"notApprovedRules"})
class DataInitializrNotApprovedRules {

    @Autowired
    DataInitializrNotApprovedRules(DataInitializr dataInitializr) {
        dataInitializr.initData(false);
    }
}
