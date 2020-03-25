package lab2.controller.user;

import lab2.model.User;
import lab2.service.UserService;
import lab2.validator.AbstractValidator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static lab2.controller.ControllerConstants.*;

@Controller
@RequestMapping(USERS_URL)
@NoArgsConstructor
@Slf4j
public class ControllerAllUsers {

    private static final String USERS_VIEW = "users";
    public static final String USERS_ATTRIBUTE = "users";

    private UserService userService;

    private AbstractValidator<User> validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public ControllerAllUsers(UserService userService, AbstractValidator<User> validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping
    public String findAll(Model model) {
        log.trace("Find all users");
        model.addAttribute(new User());
        model.addAttribute(USERS_ATTRIBUTE, userService.findAll());
        return USERS_VIEW;
    }

    @PutMapping
    public String save(@Validated @ModelAttribute User user, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            log.trace("Error during saving user");
            model.addAttribute(USERS_ATTRIBUTE, userService.findAll());
            return USERS_VIEW;
        } else {
            var savedUser = userService.save(user);
            log.trace("Save user with id = {}", savedUser.getId());
            return redirect(USERS_URL);
        }
    }
}
