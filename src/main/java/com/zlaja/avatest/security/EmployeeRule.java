package com.zlaja.avatest.security;

public class EmployeeRule implements Rule {

    @Override
    public boolean enforceRule(Object entity) {
        return true;
    }
}
