package br.com.furb;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.furb.model.ApiRequest;
import br.com.furb.model.User;
import br.com.furb.service.ApiRequestService;
import br.com.furb.service.UserService;

@RestController
@EnableAutoConfiguration
public class ApiRequestController {

	@Autowired
	private ApiRequestService apiRequestService;
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(ApiRequestController.class);

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getHome() {
		logger.info("Api request received");

		Map<String, String> response = new HashMap<String, String>();
		try {
			ApiRequest apiRequest = new ApiRequest(new Date());
			apiRequestService.create(apiRequest);
			response.put("status", "success");
		} catch (Exception e) {
			logger.error("Error occurred while trying to process api request", e);
			response.put("status", "fail");
		}

		return response;
	}

	@RequestMapping(value = "/Users", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getUsers() throws ClassNotFoundException, SQLException {
		Map<String, String> response = new HashMap<String, String>();

		for (User user : userService.findAll()) {
			response.put("name", user.getName());
		}

		return response;
	}

}