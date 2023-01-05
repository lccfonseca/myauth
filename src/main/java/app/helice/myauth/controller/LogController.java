package app.helice.myauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.helice.myauth.repository.LogRepository;

/**
 *
 * @author lccf
 */
@Controller
@RequestMapping({"/logs"})
public class LogController {

    @Autowired
    private LogRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("logs", repository.findAll());
        return "/logs/index";
    }
    
}
