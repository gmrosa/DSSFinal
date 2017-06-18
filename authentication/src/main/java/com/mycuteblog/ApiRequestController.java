package com.mycuteblog;

import com.mycuteblog.model.ApiRequest;
import com.mycuteblog.model.Test;
import com.mycuteblog.service.ApiRequestService;
import com.mycuteblog.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class ApiRequestController {

	@Autowired
	private ApiRequestService apiRequestService;
	@Autowired
	private TestService testService;

	private static final Logger logger = LoggerFactory.getLogger(ApiRequestController.class);

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getHome() {
		logger.info("Api request received");

		Map<String, String> response = new HashMap<String, String>();
		try {
			ApiRequest apiRequest = new ApiRequest(new Date());
			apiRequestService.create(apiRequest);
			Test tst = new Test();
			tst.setDsc("oi");
			testService.create(tst);
			Test tst2 = testService.getById(1L);
			// response.put("status", "success");
			response.put("status", tst2.getDsc());
		} catch (Exception e) {
			logger.error("Error occurred while trying to process api request", e);
			response.put("status", "fail");
		}

		return response;
	}

	@RequestMapping(value = "/Users", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getUsers() throws ClassNotFoundException, SQLException {
		Map<String, String> response = new HashMap<String, String>();

		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:../database/data", "sa", "");
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select name from user");
		while (rset.next()) {
			String name = rset.getString(1);
			response.put("name", name);
		}

		conn.close();

		return response;
	}

	@RequestMapping(value = "/Tests", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getTests() throws ClassNotFoundException, SQLException {
		Map<String, String> response = new HashMap<String, String>();

		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:../database/data", "sa", "");
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select dsc from test");
		while (rset.next()) {
			String name = rset.getString(1);
			response.put("dsc", name);
		}

		conn.close();

		return response;
	}
}
