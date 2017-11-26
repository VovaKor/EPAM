package com.korobko;

import com.korobko.policies.InsurancePolicy;

import java.util.ArrayList;
import java.util.List;

public class InsuranceDerivative {
    private List<InsurancePolicy> list;

    public InsuranceDerivative() {
        this.list = new ArrayList<>();
    }

    public void addInsurancePolicy(InsurancePolicy policy) {
        this.list.add(policy);
    }

    public List<InsurancePolicy> getPolicies() {
        return list;
    }
}
