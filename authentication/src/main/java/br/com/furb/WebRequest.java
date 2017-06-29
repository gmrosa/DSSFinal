package br.com.furb;

import java.util.ArrayList;
import java.util.List;

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
import br.com.furb.model.dto.UserDTO;

@RestController
@EnableAutoConfiguration
/**
 * Extensão do chrome para testar os comandos
 * https://chrome.google.com/webstore/detail/fhbjgbiflinjbdggehcddcbncdddomop?utm_source=chrome-app-launcher-info-dialog
 */
public class WebRequest {

    @Autowired
    private UserController userController;

    /**
     * GET https://localhost:8075/
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus getHome() {
        return HttpStatus.OK;
    }

    /**
     * GET https://localhost:8075/
     */
    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMovies( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password) {

        if (userController.login(name, password)) {
            List<String> movies = new ArrayList<>();
            movies.add("Meu Malvado Favorito 3");
            movies.add("The House");
            movies.add("13 Minutos");
            movies.add("Transformers");
            movies.add("Carros 3");

            return movies;
        }
        throw new RuntimeException(HttpStatus.BAD_REQUEST.name());
    }

    /**
     * GET https://localhost:8075/user?name=admin&password=admin&id=3
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("id") Long id) {

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
     * DELETE https://localhost:8075/user?name=admin&password=admin&id=3
     */
    @RequestMapping(value = "/user", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUser( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("id") Long id) {

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
     * PUT https://localhost:8075/userrole?name=admin&password=admin&id=4&role=1
     */
    @RequestMapping(value = "/userrole", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updateUserRole( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("id") Long id, //
            @RequestParam("role") Integer role) {

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
     * PUT https://localhost:8075/usercard?name=admin&password=admin&id=4&card=1234123412341234
     */
    @RequestMapping(value = "/usercard", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updateUserCard( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password, //
            @RequestParam("id") Long id, //
            @RequestParam("card") String card) {

        if (userController.login(name, password)) {
            if (userController.isAdmin(name)) {
                if (userController.updateUserCard(id, card)) {
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
     * GET https://localhost:8075/users?name=admin&password=admin
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getUsers( //
            @RequestParam("name") String name, //
            @RequestParam("password") String password) {

        if (userController.login(name, password)) {
            Role role = userController.getUserRole(name);
            if (role == Role.ADMIN || role == Role.EMPLOYEE) {
                return userController.findAll(role);
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

        if (userController.login(name, password)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
