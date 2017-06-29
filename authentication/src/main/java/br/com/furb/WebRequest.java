package br.com.furb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.furb.controller.UserController;
import br.com.furb.enumeration.Role;
import br.com.furb.model.User;

@RestController
@EnableAutoConfiguration
/**
 * Extensão do chrome para testar os comandos
 * https://chrome.google.com/webstore/detail/fhbjgbiflinjbdggehcddcbncdddomop?utm_source=chrome-app-launcher-info-dialog
 */
public class WebRequest {

    @Autowired
    private UserController userController;

    private static final Logger logger = LoggerFactory.getLogger(WebRequest.class);

    /**
     * GET https://localhost:8075/
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus getHome() {
        logger.info("getHome");

        return HttpStatus.OK;
    }

    /**
     * GET https://localhost:8075/user?name=admin&password=admin&id=3
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("id") Long id) {
        logger.info("getUser");

        if (userController.login(name, password)) {
            Role role = userController.getUserRole(name);
            if (role == Role.ADMIN || role == Role.EMPLOYEE) {
                User user = userController.find(id);
                if (user != null) {
                    return user;
                }
            }
        }
        throw new RuntimeException(HttpStatus.BAD_REQUEST.name());
    }

    /**
     * TODO DELETE https://localhost:8075/user?name=admin&password=admin&id=3
     */
    @RequestMapping(value = "/user", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUser( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("id") Long id) {

        logger.info("deleteUser");

        if (userController.login(name, password)) {
            if (userController.isAdmin(name)) {
                if (userController.delete(id)) {
                    return HttpStatus.OK;
                }
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    /**
     * TODO PUT https://localhost:8075/user?name=admin&password=admin&id=1&role=1
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updateUserRole( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("id") Long id, //
            @RequestParam("role") Integer role) {

        logger.info("updateUserRole");

        if (userController.login(name, password)) {
            if (userController.isAdmin(name)) {
                if (userController.updateUserRole(id, role)) {
                    return HttpStatus.OK;
                }
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    /**
     * POST https://localhost:8075/user?name=admin&password=admin&newName=newuser&newRole=2&newPassword=newuser
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus createNewUser( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("newName") String newName, //
            @RequestParam("newRole") Integer newRole, //
            @RequestParam("newPassword") String newPassword) {

        logger.info("createNewUser");
        if (userController.login(name, password)) {
            if (userController.isAdmin(name)) {
                if (userController.createNewUser(newName, newRole, newPassword)) {
                    return HttpStatus.OK;
                }
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    /**
     * GET https://localhost:8075/users?name=emp&password=emp
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password) {

        logger.info("getUsers");

        if (userController.login(name, password)) {
            Role role = userController.getUserRole(name);
            if (role == Role.ADMIN || role == Role.EMPLOYEE) {
                return userController.findAll();
            }
        }
        throw new RuntimeException(HttpStatus.BAD_REQUEST.name());
    }

    /**
     * GET https://localhost:8075/login?name=emp&password=emp
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus login( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password) {

        logger.info("login");
        if (userController.login(name, password)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
