package br.com.furb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.furb.controller.UserController;
import br.com.furb.model.User;

@RestController
@EnableAutoConfiguration
/**
 * Extensão do chrome para testar os comandos
 * https://chrome.google.com/webstore/detail/fhbjgbiflinjbdggehcddcbncdddomop?utm_source=chrome-app-launcher-info-dialog
 */
public class ApiRequestController {

    @Autowired
    private UserController userController;

    private static final Logger logger = LoggerFactory.getLogger(ApiRequestController.class);

    /**
     * GET http://localhost:8080/
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus getHome() {
        logger.info("getHome");

        return HttpStatus.OK;
    }

    /**
     * GET http://localhost:8080/User/1
     */
    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable Long id) {
        logger.info("getUser");

        User user = userController.find(id);
        return user;
    }

    /**
     * DELETE http://localhost:8080/User/1
     */
    @RequestMapping(value = "/User/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUser(@PathVariable Long id) {
        logger.info("deleteUser");

        //userController.delete(id);
        return HttpStatus.OK;
    }

    /**
     * PUT http://localhost:8080/User?id=1&role=1
     */
    @RequestMapping(value = "/User", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updateUserRole(@RequestParam("id") Long id, @RequestParam("role") Integer role) {
        logger.info("updateUserRole");
        return userController.updateUserRole(id, role);
    }

    /**
     * POST http://localhost:8080/User?name=teste&role=1&salt=1&password=1
     */
    @RequestMapping(value = "/User", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus createNewUser( //
            @RequestParam("name") String name, //
            @RequestParam("role") Integer role, //
            @RequestParam("password") String password) {

        logger.info("createNewUser");
        return userController.createNewUser(name, role, password);
    }

    /**
     * GET http://localhost:8080/Users
     */
    @RequestMapping(value = "/Users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        logger.info("getUsers");
        return userController.findAll();
    }

}
