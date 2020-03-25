package lab2.validator;

import lab2.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class TaskValidator extends AbstractValidator<Task> {

    public TaskValidator() {
        super(Task.class);
    }

    @Override
    public void validateTarget(Task target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", REQUIRED_FIELD, getDefaultRequiredMessage());
    }
}
