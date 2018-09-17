package ch.zhaw.iwi.pathexamplejava;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

import ch.zhaw.iwi.pathexamplejava.server.CorsHeaders;
import ch.zhaw.iwi.pathexamplejava.server.Database;
import ch.zhaw.iwi.pathexamplejava.service.businesscase.BusinessCaseTypeRestService;
import ch.zhaw.iwi.pathexamplejava.service.businesscase.BusinessCaseRestService;
import ch.zhaw.iwi.pathexamplejava.service.exception.ExceptionRestService;
import ch.zhaw.iwi.pathexamplejava.service.user.LanguageUtility;
import ch.zhaw.iwi.pathexamplejava.service.user.UserRestService;
import ch.zhaw.iwi.pathexamplejava.service.user.permission.PermissionFunctionRestService;
import ch.zhaw.iwi.pathexamplejava.service.user.permission.PermissionRoleRestService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class Main {
	private static final String HEROKU_PORT = "PORT";
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static final JpaPersistModule persistModule = new JpaPersistModule("AssessmentTool");
	private static Injector injector;

	public static void main(String[] args) {
		initFrontend();
		initServer();
		initDatabase();

		// Cache
		before((request, response) -> {
			response.header("cache-control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
			response.header("pragma", "no-cache"); // HTTP 1.0
			response.header("expires", "0"); // HTTP 1.0 proxies
		});

		// Language
		before("services/*", (request, response) -> {
			String languageCode = request.queryParams("languageCode");
			if (languageCode != null) {
				LanguageUtility.setUserLanguage(languageCode);
			}
		});

		// Wake Up Ping (Heroku)
		get("services/ping", (req, res) -> {
			Jws<Claims> jwt = JwtUtility.getJsonWebToken(req);
			String userId = null;
			if (jwt != null) {
				userId = jwt.getBody().getSubject();
			}
			String defaultUser = "";
			if (req.url().contains("localhost")) {
				defaultUser = "admin";
				String jwtString = JwtUtility.createJsonWebToken(defaultUser, "de");
				res.header("Authorization", jwtString);
			}
			String languageCode = "de";
			return "{ \"status\": \"ok\", \"userId\": \"" + (userId == null ? defaultUser : userId) + "\", \"languageCode\": \"" + languageCode + "\",\"version\": \"" + new Version().getVersion() + "\"}";
		});
		
		// Model Services
		injector.getInstance(BusinessCaseRestService.class).init();
		injector.getInstance(BusinessCaseTypeRestService.class).init();
		injector.getInstance(UserRestService.class).init();
		injector.getInstance(PermissionRoleRestService.class).init();
		injector.getInstance(PermissionFunctionRestService.class).init();
		
		// Exception Handler
		injector.getInstance(ExceptionRestService.class).init();

		logger.info("Path-Example Java Backend running on localhost:" + getHerokuAssignedPort());
	}

	private static void initFrontend() {
		try {
			File frontendDirectory = new File("../frontend/dist");
			if (!frontendDirectory.isDirectory()) {
				frontendDirectory = new File("frontend/dist");
			}
			staticFiles.externalLocation(frontendDirectory.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not resolve frontend files");
			System.exit(1);
		}
	}
	
	private static void initDatabase() {
		Properties properties = new Properties();
		ProcessBuilder processBuilder = new ProcessBuilder();
		/*if (processBuilder.environment().get(HEROKU_PORT) != null) {
			properties.put("javax.persistence.jdbc.driver", Driver.class.getName());
			properties.put("javax.persistence.jdbc.url", processBuilder.environment().get("JDBC_DATABASE_URL"));
			properties.put("javax.persistence.jdbc.user", "");
			properties.put("javax.persistence.jdbc.password", "");
			properties.put("hibernate.dialect", PostgreSQL95Dialect.class.getName());
		}*/
		persistModule.properties(properties);
		injector = Guice.createInjector(persistModule);
		
		injector.getInstance(Database.class).init("9998", "9999", true);
	}

	public static void initServer() {
		port(getHerokuAssignedPort());
		CorsHeaders.init();
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get(HEROKU_PORT) != null) {
			return Integer.parseInt(processBuilder.environment().get(HEROKU_PORT));
		}
		return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
	}

	@SuppressWarnings("unused")
	private static class TranslatedMessage {
		private String messageKey;
		private List<String> parameters;
	}

}
