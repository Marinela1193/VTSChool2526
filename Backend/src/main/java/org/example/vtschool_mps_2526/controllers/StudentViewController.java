package org.example.vtschool_mps_2526.controllers;

import org.example.vtschool_mps_2526.models.dto.StudentsDTO;
import org.example.vtschool_mps_2526.service.serviceCourse;
import org.example.vtschool_mps_2526.service.serviceScore;
import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/student")
class StudentViewController {
    @Autowired
    serviceStudent serviceStudent;
    @Autowired
    private serviceScore serviceScore;
    @Autowired
    private serviceCourse serviceCourse;

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        model.addAttribute("alumno", serviceStudent.getStudentByEmail(authentication.getName()));
        return "student/home";
    }

    @GetMapping("/profile")
    public String editProfile(Authentication authentication, Model model) {
        model.addAttribute("alumno", serviceStudent.getStudentByEmail(authentication.getName()));
        return "student/profile";
    }

    @GetMapping("/scores")
    public String checkScores(Authentication authentication, @RequestParam(required = false) Integer course, Model model) {

        StudentsDTO alumno = serviceStudent.getStudentByEmail(authentication.getName());

        model.addAttribute("alumno", alumno);
        model.addAttribute("courses", serviceCourse.getCourses());
        model.addAttribute("scores", serviceScore.getScoresByStudentIdcard(String.valueOf(alumno.getIdcard())));

        if (course == null) {
            model.addAttribute("scores", new ArrayList<>());
        } else {
            model.addAttribute("scores",
                    serviceScore.getScoresByStudentIdcardAndCourse(
                            String.valueOf(alumno.getIdcard()), course));
        }

        return "student/scores";
    }

@PostMapping("/update")
public String updateStudentProfile(@ModelAttribute StudentsDTO studentDto) {

    serviceStudent.updateStudent(studentDto);

    return "redirect:/student/home";
}
}
