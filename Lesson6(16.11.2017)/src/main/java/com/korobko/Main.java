package com.korobko;

import com.korobko.cases.Earthquake;
import com.korobko.cases.Flood;
import com.korobko.cases.Hurricane;
import com.korobko.policies.GuaranteedCostInsurance;
import com.korobko.policies.InsurancePolicy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Создать консольное приложение, удовлетворяющее следующим требованиям:
 *
 * 1. Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция.
 * 2. Каждый класс должен иметь исчерпывающее смысл название и информативный состав.
 * 3. Наследование должно применяться только тогда, когда это имеет смысл.
 * 4. При кодировании должны быть использованы соглашения об оформлении кода java code convention.
 * 5. Классы должны быть грамотно разложены по пакетам.
 * 6. Работа с консолью или консольное меню должно быть минимальным.
 * 7. Для хранения параметров инициализации можно использовать файлы.
 *
 * 11. Страхование. Определить иерархию страховых обязательств. Собрать из обязательств дериватив.
 * Посчитать стоимость. Провести сортировку обязательств в деривативе на основе уменьшения степени риска.
 * Найти обязательство в деривативе, соответствующее заданному диапазону параметров.
 *
 * @author Vova Korobko
 */
public class Main {
    public static void main(String[] args) {
        InsurancePolicy fullCost = new GuaranteedCostInsurance();
        fullCost = new Hurricane(fullCost);
        fullCost = new Flood(fullCost);
        fullCost = new Earthquake(fullCost);

        InsurancePolicy minCost = new GuaranteedCostInsurance();
        minCost = new Hurricane(minCost);

        InsurancePolicy mediumCost = new GuaranteedCostInsurance();
        mediumCost = new Hurricane(mediumCost);
        mediumCost = new Flood(mediumCost);

        // Collecting derivative from insurance liabilities
        InsuranceDerivative insuranceDerivative = new InsuranceDerivative();
        insuranceDerivative.addInsurancePolicy(fullCost);
        insuranceDerivative.addInsurancePolicy(minCost);
        insuranceDerivative.addInsurancePolicy(mediumCost);

        // Calculating cost
        double cost = calculateDerivativeCost(insuranceDerivative);
        System.out.println("Derivative cost = " + cost + "$");

        // Sorting by decreasing a risk
        sortPoliciesByDecreasingRisk(insuranceDerivative).forEach(p -> {
                    System.out.println("Risk cost = " + p.cost() + "$");
                });

        // Searching liability with min risk
        searchPolicyMinRisk(insuranceDerivative).ifPresent(p -> {
                    System.out.println("Min risk cost = " + p.cost() + "$");
                });
    }

    public static Optional<InsurancePolicy> searchPolicyMinRisk(InsuranceDerivative derivative) {
        return derivative.getPolicies().stream()
                .min(Comparator.comparingDouble(InsurancePolicy::cost));

    }

    public static List<InsurancePolicy> sortPoliciesByDecreasingRisk(InsuranceDerivative derivative) {
        return derivative.getPolicies().stream()
                .sorted(Comparator.comparingDouble(InsurancePolicy::cost).reversed())
                .collect(Collectors.toList());
    }

    public static double calculateDerivativeCost(InsuranceDerivative derivative) {
        return derivative.getPolicies().stream().mapToDouble(InsurancePolicy::cost).sum();
    }
}
