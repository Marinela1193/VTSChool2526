package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.service.serviceCourse;
import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
class AdminViewController {
    @Autowired
    serviceStudent serviceStudent;
    serviceCourse serviceCourse;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/enroll")
    public String showEnrollForm(Model model) {
        model.addAttribute("students", serviceStudent.getStudents());
        model.addAttribute("courses", serviceCourse.getCourses());
        return "admin/enroll";
    }

    @GetMapping("/qualify")
    public String showQualifyForm(Model model) {
        model.addAttribute("students", serviceStudent.getStudents());
        return "admin/qualify";
    }
}
