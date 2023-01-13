package app.helice.myauth.controller;

import app.helice.myauth.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.helice.myauth.repository.LogRepository;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") long id, Model model) {
        Log log = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("log", log);
        return "/logs/view";
    }

}
