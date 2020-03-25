package lab2.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerConstants {

    public static final String HOMEPAGE_URL = "/";

    public static final String TASKS_URL = "/tasks";
    public static final String FULL_INFORMATION_ABOUT_TASK_URL = "/task/{id}";

    public static final String DESCRIPTIONS_URL = "/descriptions";
    public static final String FULL_INFORMATION_ABOUT_DESCRIPTION_URL = "/description/{id}";

    public static final String USERS_URL = "/users";
    public static final String FULL_INFORMATION_ABOUT_USER_URL = "/user/{id}";

    public static final String COMMENTS_URL = "/comments";
    public static final String FULL_INFORMATION_ABOUT_COMMENT_URL = "/comment/{id}";

    public static final String ERROR = "error";

    public static final String REDIRECT_PATTERN = "redirect:%s";
    public static final String ID_PATTERN = "{id}";

    public static String redirect(String url) {
        return String.format(REDIRECT_PATTERN, url);
    }

    public static String redirect(String url, Long id) {
        String resolvedUrl = url.replace(ID_PATTERN, id.toString());
        return String.format(REDIRECT_PATTERN, resolvedUrl);
    }
}
