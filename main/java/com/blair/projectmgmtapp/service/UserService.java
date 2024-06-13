//package com.blair.projectmgmtapp.service;
//
//import com.example.GlabSP13.model.User;
//import com.example.GlabSP13.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public List<User> getAllUsers() {
//        return (List<User>) userRepository.findAll();
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    public void deleteUserById(Long id) {
//        userRepository.deleteById(id);
//    }
//}
