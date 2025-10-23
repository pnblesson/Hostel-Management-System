package com.example.hostelmanagement.config;

import com.example.hostelmanagement.repository.AdministratorRepository;
import com.example.hostelmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        var student = studentRepository.findByUsername(username);
        if (student.isPresent()) {
            return student.get();
        }

       
        var admin = administratorRepository.findByUsername(username);
        if (admin.isPresent()) {
            return admin.get();
        }

     
        throw new UsernameNotFoundException("User not found: " + username);
    }
}