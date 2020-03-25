package lab2.validator;

import lab2.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator extends AbstractValidator<User> {

    public UserValidator() {
        super(User.class);
    }

    @Override
    public void validateTarget(User target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", REQUIRED_FIELD, getDefaultRequiredMessage());
    }
}
