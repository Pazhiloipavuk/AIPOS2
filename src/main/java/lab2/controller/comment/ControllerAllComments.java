package lab2.controller.comment;

import lab2.controller.description.ControllerDescription;
import lab2.model.Comment;
import lab2.service.CommentService;
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

@Controller
@RequestMapping(COMMENTS_URL)
@Slf4j
public class ControllerAllComments {

    private static final String COMMENTS_VIEW = "comments";
    private static final String COMMENTS_ATTRIBUTE = "comments";
    private static final String MODEL_ATTRIBUTE = "comment";

    private CommentService commentService;

    private AbstractValidator<Comment> validator;

    private ControllerDescription controllerDescription;

    @InitBinder(MODEL_ATTRIBUTE)
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String findAll(Model model) {
        log.trace("Find all comments");
        model.addAttribute(new Comment());
        model.addAttribute(COMMENTS_ATTRIBUTE, commentService.findAll());
        return COMMENTS_VIEW;
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Comment comment, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            log.trace("Error during saving comment");
            String page = controllerDescription.findById(comment.getDescription().getId(), model);
            model.addAllAttributes(errors.getModel());
            return page;
        } else {
            var savedComment = commentService.save(comment);
            log.trace("Save comment with id = {}", comment.getId());
            return redirect(FULL_INFORMATION_ABOUT_DESCRIPTION_URL, savedComment.getDescription().getId());
        }
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setValidator(AbstractValidator<Comment> validator) {
        this.validator = validator;
    }

    @Autowired
    public void setArticleDetailsController(ControllerDescription controllerDescription) {
        this.controllerDescription = controllerDescription;
    }
}
