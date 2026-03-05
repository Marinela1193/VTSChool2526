package org.example.vtschool_mps_2526.controllers;
import org.example.vtschool_mps_2526.models.dto.StudentsDTO;
import org.example.vtschool_mps_2526.service.serviceScore;
import org.example.vtschool_mps_2526.service.serviceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
class StudentViewController {
    @Autowired
    serviceStudent serviceStudent;
    @Autowired
    private serviceScore serviceScore;

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

//    @GetMapping("/scores")
//    public String checkScores(Authentication authentication, Model model) {
//        model.addAttribute("scores", serviceScore.getScoresByEmail(authentication.getName()));
//
//        return "student/scores";
//    }

    @PostMapping("/update")
    public String updateStudentProfile(@ModelAttribute StudentsDTO studentDto) {

        serviceStudent.updateStudent(studentDto);

        return "redirect:/student/home";
    }
}
