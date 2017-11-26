package com.korobko.policies;
/**
 * @author Vova Korobko
 */

public abstract class InsurancePolicy {
    String description = "Unknown policy";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
