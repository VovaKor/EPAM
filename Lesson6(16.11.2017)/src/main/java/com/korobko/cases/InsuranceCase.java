package com.korobko.cases;

import com.korobko.policies.InsurancePolicy;

/**
 * @author Vova Korobko
 */

public abstract class InsuranceCase extends InsurancePolicy {
    public abstract String getDescription();
}
