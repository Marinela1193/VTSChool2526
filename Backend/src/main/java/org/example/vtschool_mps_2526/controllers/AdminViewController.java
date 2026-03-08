package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.service.serviceCourse;
import org.example.vtschool_mps_2526.service.serviceEnrollment;
import org.example.vtschool_mps_2526.service.serviceStudent;
import org.example.vtschool_mps_2526.service.serviceSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
class AdminViewController {
    @Autowired
    serviceStudent serviceStudent;
    @Autowired
    serviceCourse serviceCourse;
    @Autowired
    serviceEnrollment serviceEnrollment;
    @Autowired
    serviceSubject serviceSubject;


    @GetMapping("/home")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/enroll")
    public String showEnrollForm(Model model) {
        model.addAttribute("students", serviceStudent.getStudents());
        model.addAttribute("courses", serviceCourse.getCourses());
        return "admin/enrollView";
    }

    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam String studentIdCard,
                                @RequestParam int courseCode,
                                Model model) {

        boolean enrolled = serviceEnrollment.enrollStudent(studentIdCard, courseCode);

        if (!enrolled) {
            model.addAttribute("students", serviceStudent.getStudents());
            model.addAttribute("courses", serviceCourse.getCourses());
            model.addAttribute("errorMessage", "The student cannot be enrolled in this course.");
            return "admin/enrollView";
        }

        return "redirect:/admin/home";
    }

    @GetMapping("/qualify")
    public String showQualifyForm(@RequestParam(required = false) String studentIdCard,
                                  Model model) {
        model.addAttribute("students", serviceStudent.getStudents());

        if (studentIdCard == null || studentIdCard.isBlank()) {
            model.addAttribute("subjects", new ArrayList<>());
        } else {
            model.addAttribute("subjects",
                    serviceSubject.getPendingSubjectsByStudent(studentIdCard));

        }

        return "admin/qualifyView";
    }
}
