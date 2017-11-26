package com.korobko;

import com.korobko.cases.Flood;
import com.korobko.policies.GuaranteedCostInsurance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vova Korobko
 */
class FloodTest {
    @Test
    void cost_add_success() {
        double expected = Constants.COST_GUARANTEED + Constants.COST_FLOOD;

        assertEquals(expected, new Flood(new GuaranteedCostInsurance()).cost());
    }

}