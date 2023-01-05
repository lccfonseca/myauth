/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helice.myauth.controller;

import app.helice.myauth.model.Role;
import app.helice.myauth.model.User;
import app.helice.myauth.repository.RoleRepository;
import app.helice.myauth.repository.UserRepository;
import java.util.HashSet;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lccf
 */
@Controller
@RequestMapping({"/users"})
public class UserController {
    
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository repositoryRole;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", repository.findAll());
        return "/users/index";
    }
    
    @GetMapping("/add")
    public String add(User user, Model model) {
        user = new User();
        model.addAttribute("user", user);
        return "/users/add";
    }
    
    @PostMapping("/create")
    public String create(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/users/add";
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
               
        Role r = repositoryRole.findById(Integer.toUnsignedLong(2)).get();
        HashSet<Role> hsr = new HashSet<Role>();
        hsr.add(r);
        user.setRoles(hsr);
        
        repository.save(user);
        
        return "redirect:/users/";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("user", user);
        return "/users/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid User user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "/users/update";
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);
        return "redirect:/users/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        repository.delete(user);
        return "redirect:/users/";
    }
    
}
