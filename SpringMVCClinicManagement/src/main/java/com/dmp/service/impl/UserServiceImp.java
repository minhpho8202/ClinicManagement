/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dmp.pojo.User;
import com.dmp.repository.RoleRepository;
import com.dmp.repository.UserRepository;
import com.dmp.service.UserService;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author minhp
 */
@Service("userDetailsService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        return this.userRepository.getUsers(params);
    }

    @Override
    public Long countUser() {
        return this.userRepository.countUser();
    }

    @Override
    public boolean addOrUpdate(User user) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        user.setCreatedDate(timestamp);
        String password = user.getPassword();
        user.setPassword(this.passwordEncoder.encode(password));
        
        if(user.getRoleId() == null) {
            user.setRoleId(this.roleRepository.getRoleByName("ROLE_PATIENT"));
        }

        if (!user.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        user.setFirstName("ee");
//        user.setLastName("sad");
//        user.setDateOfBirth(date);
//        user.setPhoneNumber("21");
//        user.setEmail("ewqwq");
//        user.setGender("male");
//        user.setUsername("22");
//        user.setPassword("22");
//        user.setAddress("22");
//        user.setRoleId(this.roleRepository.getRoles().get(1));
        return this.userRepository.addOrUpdate(user);
    }

    @Override
    public User getUserById(int id) {
        return this.userRepository.getUserById(id);
    }

    @Override
    public boolean deleteUserById(int id) {
        return this.userRepository.deleteUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepository.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRoleId().getName()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepository.authUser(username, password);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(params.get("dateOfBirth"));
        } catch (ParseException ex) {
            Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        u.setFirstName(params.get("firstName"));
        u.setLastName(params.get("lastName"));
        u.setDateOfBirth(dateOfBirth);
        u.setGender(params.get("gender"));
        u.setEmail(params.get("email"));
        u.setPhoneNumber(params.get("phoneNumber"));
        u.setAddress(params.get("address"));
        u.setUsername(params.get("username"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setConfirmPassword(this.passwordEncoder.encode(params.get("confirmPassword")));
        u.setCreatedDate(timestamp);
        u.setRoleId(this.roleRepository.getRoleByName("ROLE_PATIENT"));
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.userRepository.addUser(u);
        return u;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return this.userRepository.isUsernameUnique(username);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return this.userRepository.isEmailUnique(email);
    }

}
