package com.korobko;

import com.korobko.cases.Earthquake;
import com.korobko.cases.Flood;
import com.korobko.cases.Hurricane;
import com.korobko.policies.InsurancePolicy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vova Korobko
 */
class MainTest {
    InsurancePolicy fullPolicy;
    InsurancePolicy mediumPolicy;
    InsurancePolicy minPolicy;
    InsuranceDerivative insuranceDerivative;
    double fullCost;
    double mediumCost;
    double minCost;

    @BeforeEach
    void setUp() {
        fullCost = Constants.COST_GUARANTEED
                        + Constants.COST_EARTHQUAKE
                        + Constants.COST_FLOOD
                        + Constants.COST_HURRICANE;

        mediumCost = Constants.COST_GUARANTEED
                            + Constants.COST_FLOOD
                            + Constants.COST_HURRICANE;

        minCost = Constants.COST_GUARANTEED
                       + Constants.COST_HURRICANE;

        fullPolicy = mock(Earthquake.class);
        when(fullPolicy.cost()).thenReturn(fullCost);
        mediumPolicy = mock(Flood.class);
        when(mediumPolicy.cost()).thenReturn(mediumCost);
        minPolicy = mock(Hurricane.class);
        when(minPolicy.cost()).thenReturn(minCost);

        insuranceDerivative = new InsuranceDerivative();
        insuranceDerivative.addInsurancePolicy(minPolicy);
        insuranceDerivative.addInsurancePolicy(fullPolicy);
        insuranceDerivative.addInsurancePolicy(mediumPolicy);
    }

    @AfterEach
    void tearDown() {
        fullPolicy = null;
        mediumPolicy = null;
        minPolicy = null;

    }

    @Test
    void searchPolicyMinRisk_threePolicies_positive() {
        assertEquals(minCost, Main.searchPolicyMinRisk(insuranceDerivative).get().cost());
    }

    @Test
    void searchPolicyMinRisk_nullPolicies_throwsException() {
        assertThrows(NullPointerException.class, () -> Main.searchPolicyMinRisk(null));
    }

    @Test
    void sortPoliciesByDecreasingRisk_threePolicies_positive() {
        double[] expected = {fullCost, mediumCost, minCost};
        double[] actual = Main.sortPoliciesByDecreasingRisk(insuranceDerivative).stream().mapToDouble(InsurancePolicy::cost).toArray();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
    }

    @Test
    void sortPoliciesByDecreasingRisk_nullPolicies_throwsException() {
        assertThrows(NullPointerException.class, () -> Main.sortPoliciesByDecreasingRisk(null));
    }

    @Test
    void calculateDerivativeCost_threePolicies_positive() {
        double expected = fullCost + mediumCost + minCost;
        assertEquals(expected, Main.calculateDerivativeCost(insuranceDerivative));
    }

    @Test
    void calculateDerivativeCost_nullPolicies_throwsException() {

        assertThrows(NullPointerException.class, () -> Main.calculateDerivativeCost(null));
    }
}