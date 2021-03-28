package com.rowen.currency.Controller;

import com.rowen.currency.Exception.UserAlreadyExistException;
import com.rowen.currency.Model.User;
import com.rowen.currency.Repo.UserRepo;
import com.rowen.currency.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(Model model, @ModelAttribute @Valid User userFromForm) {
        try {
            User user = userService.registerNewUser(userFromForm);
            userRepo.save(user);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("message", "An account with that email already exists");
        }

        return "redirect:/login";
    }

}
