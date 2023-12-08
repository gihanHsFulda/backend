//author - Gihan Kaushal
//date - 2023-11-05
//Matriculation number - 1455441

package com.bmicalculator.bmi.controller;

import com.bmicalculator.bmi.BmiBean;
import com.bmicalculator.bmi.service.BmiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800)
public class BmiApplicationController {

    BmiService bmiService;

    public BmiApplicationController(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public BmiBean bmiCalculator(@RequestBody BmiBean bmiBean) {

        double bmi = calculateBmiValue(bmiBean.getHeight(), bmiBean.getWeight());
        bmiBean.setBmiValue(bmi);
        return bmiService.saveUser(bmiBean);

    }

    @GetMapping("/users")
    public List<BmiBean> getAllUsers() {
        return bmiService.getAllUser();

    }

    private double calculateBmiValue(double height, double weight) {
        // BMI formula: weight (kg) / (height (m) * height (m))
        return weight / (height * height);
    }

    private String determineBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal Weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
