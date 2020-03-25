package lab2.controller.task;

import lab2.model.Task;
import lab2.service.TaskService;
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

import java.util.Optional;

import static lab2.controller.ControllerConstants.*;

@Controller
@RequestMapping(FULL_INFORMATION_ABOUT_TASK_URL)
@NoArgsConstructor
@Slf4j
public class ControllerTask {

    private static final String TASK_DETAILS_VIEW = "taskDetails";
    private static final String MODEL_ATTRIBUTE = "task";

    private TaskService taskService;

    private AbstractValidator<Task> validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public ControllerTask(TaskService taskService, AbstractValidator<Task> validator) {
        this.taskService = taskService;
        this.validator = validator;
    }

    @GetMapping
    public String findById(@PathVariable Long id, Model model) {
        log.trace("Find task by id = {}", id);
        taskService.findById(id).ifPresent(model::addAttribute);
        return Optional.ofNullable(model.getAttribute(MODEL_ATTRIBUTE))
                .map(attribute -> TASK_DETAILS_VIEW)
                .orElse(ERROR);
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Task task, BindingResult errors) {
        log.trace("Update task with id = {}", task.getId());
        if (errors.hasErrors()) {
            return TASK_DETAILS_VIEW;
        } else {
            var updatedComment = taskService.save(task);
            return redirect(FULL_INFORMATION_ABOUT_TASK_URL, updatedComment.getId());
        }
    }

    @DeleteMapping
    public String delete(@PathVariable Long id) {
        log.trace("Delete task with id = {}", id);
        taskService.deleteById(id);
        return redirect(TASKS_URL);
    }
}
