package lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static lab2.controller.ControllerConstants.ERROR;

@Controller
public class ControllerError {

    @ExceptionHandler({Exception.class})
    public String handleException() {
        return ERROR;
    }
}
