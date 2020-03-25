package lab2.controller.task;

import lab2.model.Task;
import lab2.service.TaskService;
import lab2.validator.AbstractValidator;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static lab2.controller.ControllerConstants.*;

@Controller
@RequestMapping(TASKS_URL)
@NoArgsConstructor
public class ControllerAllTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAllTasks.class.getName());

    private static final String TASKS_VIEW = "tasks";
    public static final String TASKS_ATTRIBUTE = "tasks";

    private TaskService taskService;

    private AbstractValidator<Task> validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public ControllerAllTasks(TaskService taskService, AbstractValidator<Task> validator) {
        this.taskService = taskService;
        this.validator = validator;
    }

    @GetMapping
    public String findAll(Model model) {
        LOGGER.trace("Find all tasks");
        model.addAttribute(new Task());
        model.addAttribute(TASKS_ATTRIBUTE, taskService.findAll());
        return TASKS_VIEW;
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Task product, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            LOGGER.trace("Error during saving task");
            model.addAttribute(TASKS_ATTRIBUTE, taskService.findAll());
            return TASKS_VIEW;
        } else {
            var savedProduct = taskService.save(product);
            LOGGER.trace("Save task with id = {}", savedProduct.getId());
            return redirect(TASKS_URL);
        }
    }
}
