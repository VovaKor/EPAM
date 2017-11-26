package com.korobko;

import com.korobko.cases.Earthquake;
import com.korobko.policies.GuaranteedCostInsurance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vova Korobko
 */
class EarthquakeTest {
    @Test
    void cost_add_success() {
        double expected = Constants.COST_GUARANTEED + Constants.COST_EARTHQUAKE;

        assertEquals(expected, new Earthquake(new GuaranteedCostInsurance()).cost());
    }

}