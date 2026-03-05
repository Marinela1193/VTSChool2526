package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.StudentsDAO;
import org.example.vtschool_mps_2526.models.entities.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class loginService implements UserDetailsService {

    @Autowired
    StudentsDAO studentsDAO;

    private final serviceStudent serviceStudent;

    public loginService(serviceStudent serviceStudent) {
        this.serviceStudent = serviceStudent;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals("admin")) {
            return User.builder()
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN")
                    .build();
        }

        /*StudentEntity student = serviceStudent
                .getStudentByEmail(username);
                if(student != null) {
                    throw new UsernameNotFoundException("User not found: " + username);
                }*/
        StudentEntity student = studentsDAO
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(student.getEmail())
                .password(student.getIdcard())
                .roles("STUDENT")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

