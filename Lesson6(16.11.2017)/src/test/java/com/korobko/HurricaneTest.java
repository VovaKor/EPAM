package com.korobko;

import com.korobko.cases.Hurricane;
import com.korobko.policies.GuaranteedCostInsurance;
import com.korobko.policies.InsurancePolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Vova Korobko
 */
class HurricaneTest {
    @Test
    void cost_add_success() {
        double expected = Constants.COST_GUARANTEED + Constants.COST_HURRICANE;

        assertEquals(expected, new Hurricane(new GuaranteedCostInsurance()).cost());
    }

}