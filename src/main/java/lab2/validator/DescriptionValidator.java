package lab2.validator;

import lab2.model.Description;
import lab2.model.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@PropertySource("classpath:messages.properties")
public class DescriptionValidator extends AbstractValidator<Description> {

    private static final String TASK_NOT_FREE = "task.not.free";

    @Value("${task.not.free}")
    private String defaultTaskNotFreeMessage;

    public DescriptionValidator() {
        super(Description.class);
    }

    @Override
    public void validateTarget(Description description, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", REQUIRED_FIELD, getDefaultRequiredMessage());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", REQUIRED_FIELD, getDefaultRequiredMessage());
        validateProduct(description, errors);
        isExistUser(description.getAuthor(), errors);
    }

    private void validateProduct(Description description, Errors errors) {
        Optional<Task> product = isExistTask(description.getTask(), errors);
        product.map(Task::getDescription)
                .map(Description::getId)
                .filter(Predicate.not(id -> description.getId().equals(id)))
                .ifPresent(id -> rejectProductNotFreeError(errors));
    }

    private void rejectProductNotFreeError(Errors errors) {
        errors.rejectValue(combine("task", "id"), TASK_NOT_FREE, defaultTaskNotFreeMessage);
    }
}

