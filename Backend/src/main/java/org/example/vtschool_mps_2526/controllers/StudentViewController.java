package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
class StudentViewController {
    @Autowired
    serviceStudent serviceStudent;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "student/dashboard";
    }

    @GetMapping("/profile")
    public String editProfile(Model model) {
        return "student/profile";
    }

    @GetMapping("/scores")
    public String checkScores(Model model) {
        return "student/scores";
    }
}
