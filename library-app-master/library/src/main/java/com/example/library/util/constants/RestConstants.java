package com.example.library.util.constants;

public interface RestConstants {

    String ROOT = "/api";

    String ID = "id";

    String ID_PATH = "/{"+ ID + "}";

    String DEFAULT_PAGE_NUMBER = "0";

    String DEFAULT_PAGE_SIZE = "10";

    String BEARER_TOKEN = "Bearer";

    String AUTHORIZATION_HEADER = "Authorization";

    interface UserController {

        String BASE = ROOT + "/user";

        String SIGN_UP = "/sign-up";

        String UPDATE = "/update" + ID_PATH;

        String PASSWORD_CHANGE = "/password" + ID_PATH;

        String ACTIVATE_USER = "/activate" + ID_PATH;

        String SIGNUP_FULL_PATH = BASE + SIGN_UP;

    }

    interface BookController {

        String BASE = ROOT + "/book";

        String ADD = "/add";

        String UPDATE = "/update" + ID_PATH;
    }

    interface AuthController {

        String BASE = ROOT + "/auth";

        String LOG_IN = "/log-in";

        String LOGIN_FULL_PATH = BASE + LOG_IN;
    }

    interface LibraryController {

        String BASE = ROOT + "/library";

        String ADD = "/add";

        String UPDATE = "/update" + ID_PATH;

        String DELETE = "/delete" + ID_PATH;

        String LIBRARIES_FOR_USER = "/for-user";

        String LIBRARIES = "/libraries";

        String SEARCH = "/search";

    }


}
