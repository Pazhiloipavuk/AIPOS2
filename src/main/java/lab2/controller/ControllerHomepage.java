package lab2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static lab2.controller.ControllerConstants.HOMEPAGE_URL;

@Controller
@RequestMapping(HOMEPAGE_URL)
public class ControllerHomepage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHomepage.class.getName());

    private static final String HOMEPAGE_VIEW = "homepage";

    @GetMapping
    public String getHomepage() {
        LOGGER.trace("Homepage");
        return HOMEPAGE_VIEW;
    }
}