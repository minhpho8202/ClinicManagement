/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.repository;

import com.dmp.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface UserRepository {
    List<User> getUsers(Map<String, String> params);
    Long countUser();
    boolean addOrUpdate(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    boolean deleteUserById(int id);
    boolean authUser(String username, String password);
    User addUser(User user);
    public boolean isUsernameUnique(String username);
    public boolean isEmailUnique(String email);
}
