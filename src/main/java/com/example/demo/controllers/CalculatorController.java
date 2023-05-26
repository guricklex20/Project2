package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.text.DecimalFormat;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/calculate")
    public ModelAndView calculate(
            @RequestParam("initialInvestment") double initialInvestment,
            @RequestParam("annualContribution") double annualContribution,
            @RequestParam("annualContributionIncrease") double annualContributionIncrease,
            @RequestParam("investmentYears") int investmentYears,
            @RequestParam("investmentReturn") double investmentReturn) {


        double totalAmount = initialInvestment;
        double annualContributionAmount = annualContribution;
        double accumulatedInterest = 0;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for (int i = 1; i <= investmentYears; i++) {
            double interest = (totalAmount + annualContributionAmount) * (investmentReturn / 100);
            totalAmount += annualContributionAmount + interest;
            accumulatedInterest += interest;

            System.out.println("Año " + i + ": Monto acumulado = $" + decimalFormat.format(totalAmount) +
                    ", Interés acumulado = $" + decimalFormat.format(accumulatedInterest));

            annualContributionAmount += (annualContributionAmount * (annualContributionIncrease / 100));
        }

        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("totalAmount", totalAmount);
        modelAndView.addObject("accumulatedInterest", accumulatedInterest);
        return modelAndView;
    }
}
