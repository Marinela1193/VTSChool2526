package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @Autowired
    serviceStudent serviceStudent;

    @GetMapping("/index")
    public String login() { return "login"; }

    /*@GetMapping("/admin/dashboard")
    public String adminDashboard() { return "admin/dashboard"; }*/

}

