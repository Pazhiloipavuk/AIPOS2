package lab2.controller.comment;

import lab2.model.Comment;
import lab2.service.CommentService;
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
@RequestMapping(FULL_INFORMATION_ABOUT_COMMENT_URL)
@NoArgsConstructor
@Slf4j
public class ControllerComment {

    private static final String COMMENT_DETAILS_VIEW = "commentDetails";
    private static final String MODEL_ATTRIBUTE = "comment";

    private CommentService commentService;

    private AbstractValidator<Comment> validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public ControllerComment(CommentService commentService, AbstractValidator<Comment> validator) {
        this.commentService = commentService;
        this.validator = validator;
    }

    @GetMapping
    public String findById(@PathVariable Long id, Model model) {
        log.trace("Find comment by id = {}", id);
        commentService.findById(id).ifPresent(model::addAttribute);
        return Optional.ofNullable(model.getAttribute(MODEL_ATTRIBUTE))
                .map(attribute -> COMMENT_DETAILS_VIEW)
                .orElse(ERROR);
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Comment comment, BindingResult errors) {
        log.trace("Update comment with id = {}", comment.getId());
        if (errors.hasErrors()) {
            return COMMENT_DETAILS_VIEW;
        } else {
            var updatedComment = commentService.save(comment);
            return redirect(FULL_INFORMATION_ABOUT_COMMENT_URL, updatedComment.getId());
        }
    }

    @DeleteMapping
    public String delete(@PathVariable Long id) {
        log.trace("Delete comment with id = {}", id);
        commentService.deleteById(id);
        return redirect(COMMENTS_URL);
    }
}
