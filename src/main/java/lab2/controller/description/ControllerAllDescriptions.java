package lab2.controller.description;

import lab2.model.Description;
import lab2.service.DescriptionService;
import lab2.service.TaskService;
import lab2.service.UserService;
import lab2.validator.AbstractValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static lab2.controller.ControllerConstants.*;
import static lab2.controller.task.ControllerAllTasks.TASKS_ATTRIBUTE;
import static lab2.controller.user.ControllerAllUsers.USERS_ATTRIBUTE;

@Controller
@RequestMapping(DESCRIPTIONS_URL)
@Slf4j
public class ControllerAllDescriptions {

    private static final String DESCRIPTIONS_VIEW = "descriptions";
    private static final String DESCRIPTIONS_ATTRIBUTE = "descriptions";

    private DescriptionService descriptionService;

    private UserService userService;

    private TaskService taskService;

    private AbstractValidator<Description> validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String findAll(Model model) {
        log.trace("Find all descriptions");
        model.addAttribute(new Description());
        fillModel(model);
        return DESCRIPTIONS_VIEW;
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Description description, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            log.trace("Error during saving description");
            fillModel(model);
            return DESCRIPTIONS_VIEW;
        } else {
            var savedDescription = descriptionService.save(description);
            log.trace("Save description with id = {}", savedDescription.getId());
            return redirect(DESCRIPTIONS_URL);
        }
    }

    private void fillModel(Model model) {
        model.addAttribute(USERS_ATTRIBUTE, userService.findAll());
        model.addAttribute(TASKS_ATTRIBUTE, taskService.findTasksWithoutDescription());
        model.addAttribute(DESCRIPTIONS_ATTRIBUTE, descriptionService.findAll());
        model.addAttribute(descriptionService.findAll());
    }

    @Autowired
    public void setArticleService(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setValidator(AbstractValidator<Description> validator) {
        this.validator = validator;
    }
}
