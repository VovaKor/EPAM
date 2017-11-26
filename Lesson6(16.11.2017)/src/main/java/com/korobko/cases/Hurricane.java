package com.korobko.cases;

import com.korobko.policies.InsurancePolicy;

import static com.korobko.Constants.COST_HURRICANE;
/**
 * @author Vova Korobko
 */

public class Hurricane extends InsuranceCase {

    InsurancePolicy insurancePolicy;

    public Hurricane(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getDescription() {
        return insurancePolicy.getDescription() + ", Hurricane";
    }

    public double cost() {
        return COST_HURRICANE + insurancePolicy.cost();
    }


}
