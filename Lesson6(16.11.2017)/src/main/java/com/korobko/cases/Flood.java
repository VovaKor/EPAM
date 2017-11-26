package com.korobko.cases;

import com.korobko.policies.InsurancePolicy;

import static com.korobko.Constants.COST_FLOOD;

/**
 * @author Vova Korobko
 */

public class Flood extends InsuranceCase {

    InsurancePolicy insurancePolicy;

    public Flood(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getDescription() {
        return insurancePolicy.getDescription() + ", Flood";
    }

    public double cost() {
        return COST_FLOOD + insurancePolicy.cost();
    }
}
