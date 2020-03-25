package lab2.controller.description;

import lab2.model.Comment;
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

import java.util.Optional;

import static lab2.controller.ControllerConstants.*;
import static lab2.controller.task.ControllerAllTasks.TASKS_ATTRIBUTE;
import static lab2.controller.user.ControllerAllUsers.USERS_ATTRIBUTE;

@Controller
@RequestMapping(FULL_INFORMATION_ABOUT_DESCRIPTION_URL)
@Slf4j
public class ControllerDescription {

    private static final String DESCRIPTION_DETAILS_VIEW = "descriptionDetails";
    private static final String MODEL_ATTRIBUTE = "description";

    private DescriptionService descriptionService;

    private UserService userService;

    private TaskService taskService;

    private AbstractValidator<Description> validator;

    @InitBinder(MODEL_ATTRIBUTE)
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String findById(@PathVariable Long id, Model model) {
        log.trace("Find description by id = {}", id);
        descriptionService.findById(id).ifPresent(description -> {
            fillModel(model);
            model.addAttribute(description);
        });
        return Optional.ofNullable(model.getAttribute(MODEL_ATTRIBUTE))
                .map(attribute -> DESCRIPTION_DETAILS_VIEW)
                .orElse(ERROR);
    }

    private void fillModel(Model model) {
        model.addAttribute(new Comment());
        model.addAttribute(USERS_ATTRIBUTE, userService.findAll());
        model.addAttribute(TASKS_ATTRIBUTE, taskService.findTasksWithoutDescription());
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Description description, BindingResult errors, Model model) {
        log.trace("Update description with id = {}", description.getId());
        if (errors.hasErrors()) {
            fillModel(model);
            return DESCRIPTION_DETAILS_VIEW;
        } else {
            var updatedDescription = descriptionService.save(description);
            return redirect(FULL_INFORMATION_ABOUT_DESCRIPTION_URL, updatedDescription.getId());
        }
    }

    @DeleteMapping
    public String delete(@PathVariable Long id) {
        log.trace("Delete description with id = {}", id);
        descriptionService.deleteById(id);
        return redirect(DESCRIPTIONS_URL);
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

