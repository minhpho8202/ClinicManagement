/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author minhp
 */
public interface UserService extends UserDetailsService{
    List<User> getUsers(Map<String, String> params);
    Long countUser();
    boolean addOrUpdate(User user);
    User getUserById(int id);
    boolean deleteUserById(int id);
    User getUserByUsername(String username);
    boolean authUser(String username, String password);
    User addUser(Map<String, String> params, MultipartFile avatar);
    public boolean isUsernameUnique(String username);
    public boolean isEmailUnique(String email);
}
