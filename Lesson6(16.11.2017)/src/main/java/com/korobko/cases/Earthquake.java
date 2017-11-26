package com.korobko.cases;

import com.korobko.policies.InsurancePolicy;

import static com.korobko.Constants.COST_EARTHQUAKE;

/**
 * @author Vova Korobko
 */

public class Earthquake extends InsuranceCase {

    InsurancePolicy insurancePolicy;

    public Earthquake(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getDescription() {
        return insurancePolicy.getDescription() + ", Earthquake";
    }

    public double cost() {
        return COST_EARTHQUAKE + insurancePolicy.cost();
    }
}
