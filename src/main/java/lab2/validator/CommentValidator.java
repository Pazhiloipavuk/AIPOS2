package lab2.validator;

import lab2.model.Comment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class CommentValidator extends AbstractValidator<Comment> {

    public CommentValidator() {
        super(Comment.class);
    }

    @Override
    public void validateTarget(Comment target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", REQUIRED_FIELD, getDefaultRequiredMessage());
        isExistDescription(target.getDescription(), errors);
        isExistUser(target.getAuthor(), errors);
    }
}
