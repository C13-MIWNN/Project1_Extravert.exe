package nl.mitw.extrovert.exe.demo.recipesdemo.controller;

import nl.mitw.extrovert.exe.demo.recipesdemo.dtos.CulinaryCompanionUserFormDTO;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.CulinaryCompanionUser;
import nl.mitw.extrovert.exe.demo.recipesdemo.services.CulinaryCompanionUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Nadine Beck
 * Omschrijving
 */

@Controller
public class CulinaryCompanionUserController {
    private final CulinaryCompanionUserService culinaryCompanionUserService;

    public CulinaryCompanionUserController(CulinaryCompanionUserService culinaryCompanionUserService) {
        this.culinaryCompanionUserService = culinaryCompanionUserService;
    }

    @GetMapping("/user/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new CulinaryCompanionUserFormDTO());
        return "userForm";
    }

    @PostMapping("/user/new")
    public String processUserForm(@ModelAttribute("user") CulinaryCompanionUserFormDTO culinaryCompanionUserFormDTO,
                                  BindingResult bindingResult) {
        if (culinaryCompanionUserService.userExists(culinaryCompanionUserFormDTO.getName())) {
            bindingResult.rejectValue("name", "duplicate",
                    "this username is not available");
        }

        if (!culinaryCompanionUserFormDTO.getPassword().equals(culinaryCompanionUserFormDTO.getConfirmPassword())) {
            bindingResult.rejectValue("password", "no.match",
                    "The passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            return "userForm";
        }

        culinaryCompanionUserService.saveUser(culinaryCompanionUserFormDTO);
        return "redirect:/user/new";
    }
}
