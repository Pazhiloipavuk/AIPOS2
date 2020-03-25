package lab2.validator;

import lab2.model.Description;
import lab2.model.Task;
import lab2.model.User;
import lab2.service.DescriptionService;
import lab2.service.TaskService;
import lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@PropertySource("classpath:messages.properties")
public abstract class AbstractValidator<T> implements Validator {

    protected static final String REQUIRED_FIELD = "required.field";
    private static final String DESCRIPTION_NOT_EXISTING = "description.not.existing";
    private static final String TASK_NOT_EXISTING = "task.not.existing";
    private static final String USER_NOT_EXISTING = "user.not.existing";

    @Value("${description.not.existing}")
    private String articleNotExistingMessage;
    @Value("${task.not.existing}")
    private String productNotExistingMessage;
    @Value("${user.not.existing}")
    private String userNotExistingMessage;
    @Value("${required.field}")
    private String defaultRequiredMessage;

    private Class<T> targetClass;

    private DescriptionService descriptionService;

    private TaskService taskService;

    private UserService userService;

    public AbstractValidator(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return targetClass.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Optional.ofNullable(object)
                .filter(target -> supports(object.getClass()))
                .map(targetClass::cast)
                .ifPresent(target -> validateTarget(target, errors));
    }

    protected abstract void validateTarget(T target, Errors errors);

    protected Optional<Description> isExistDescription(Description description, Errors errors) {
        Optional<Description> loadedArticle = Optional.ofNullable(description)
                .stream()
                .map(Description::getId)
                .filter(Objects::nonNull)
                .map(descriptionService::findById)
                .flatMap(Optional::stream)
                .findAny();
        if (loadedArticle.isEmpty()) {
            errors.rejectValue(combine("description", "id"), DESCRIPTION_NOT_EXISTING, articleNotExistingMessage);
        }
        return loadedArticle;
    }

    protected Optional<Task> isExistTask(Task task, Errors errors) {
        Optional<Task> loadedProduct = Optional.ofNullable(task)
                .stream()
                .map(Task::getId)
                .filter(Objects::nonNull)
                .map(taskService::findById)
                .flatMap(Optional::stream)
                .findAny();
        if (loadedProduct.isEmpty()) {
            errors.rejectValue(combine("task", "id"), TASK_NOT_EXISTING, productNotExistingMessage);
        }
        return loadedProduct;
    }

    protected Optional<User> isExistUser(User user, Errors errors) {
        Optional<User> loadedUser = Optional.ofNullable(user)
                .stream()
                .map(User::getId)
                .filter(Objects::nonNull)
                .map(userService::findById)
                .flatMap(Optional::stream)
                .findAny();
        if (loadedUser.isEmpty()) {
            errors.rejectValue(combine("author", "id"), USER_NOT_EXISTING, userNotExistingMessage);
        }
        return loadedUser;
    }

    public String getDefaultRequiredMessage() {
        return defaultRequiredMessage;
    }

    public String combine(String... paths) {
        return String.join(".", paths);
    }

    @Autowired
    public void setDescriptionService(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    public DescriptionService getDescriptionService() {
        return descriptionService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
