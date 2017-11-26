package com.korobko.policies;

import static com.korobko.Constants.COST_GUARANTEED;

/**
 * @author Vova Korobko
 */

public class GuaranteedCostInsurance extends InsurancePolicy {

    public GuaranteedCostInsurance() {
        description = "GuaranteedCostInsurance = ";
    }

    public double cost() {
        return COST_GUARANTEED;
    }
}
