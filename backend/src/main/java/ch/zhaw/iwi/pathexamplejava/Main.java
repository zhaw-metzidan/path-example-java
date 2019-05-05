package ch.zhaw.iwi.pathexamplejava;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	private static String selectedDatabase;
	private static final String defaultDatabase = "h2"; //default database
	private static final String propertiesFilePath = "src/main/resources/database.properties";

	public static void main(String[] args) {
		initFrontend();
		initServer();
		initDatabase(getSelectedDatabase());

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
	
//----
	
	/**
	 * initialize the database
	 * @param database the name of the databse, e.g. h2 or postgresql
	 * @author Daniel Metzinger
	 */
	private static void initDatabase(String database) {
		Properties properties = new Properties();
		
		// get values from propertis File
		ArrayList<String> propertieList = getProperties(new ArrayList<>(Arrays.asList(
				database+".jdbc.driver",
				database+".jdbc.url",
				database+".jdbc.user",
				database+".jdbc.password",
				database+".hibernate.dialect")));
			
		// set values from propertiesfile place them in persistance.xml file
		properties.put("javax.persistence.jdbc.driver", propertieList.get(0));
		properties.put("javax.persistence.jdbc.url", propertieList.get(1));
		properties.put("javax.persistence.jdbc.user", propertieList.get(2));
		properties.put("javax.persistence.jdbc.password", propertieList.get(3));
		properties.put("hibernate.dialect", propertieList.get(4));
			
		persistModule.properties(properties);
		injector = Guice.createInjector(persistModule);
		
		injector.getInstance(Database.class).init("9998", "9999", true);
	}
	
	/**
	 * set selected database to value defiend in propertiesfile
	 * @author Daniel Metzinger
	 */
	public static void setSelectedDatabaseFromProperties() {
		
		// call readFromPropertiesfile method to get values from properties
		selectedDatabase = getProperties(new ArrayList<>(Arrays.asList("db"))).get(0);
		
		// if nothing is declared in propertiesfile, set database value to default
		if (selectedDatabase == null || selectedDatabase == "")
			setSelectedDatabase("");
	}
	
	/**
	 * alternative set variable value of selectedDatabase with attribute value and not from propertiesfile
	 * @param database name
	 * @author Daniel Metzinger
	 */
	public static void setSelectedDatabase(String database) {
		//if nothing specified set value as default database
		if (database == null || database == "")
			selectedDatabase = defaultDatabase;
		else
			selectedDatabase = database;
	}
	
	/**
	 * get String variable value of selectedDatabase
	 * @return String name of selected database
	 * @author Daniel Metzinger
	 */
	public static String getSelectedDatabase() {
		setSelectedDatabaseFromProperties();
		return selectedDatabase;
	}
	
	/**
	 * Get values from properties file and return them as ArrayList
	 * @param properties
	 * @return ArrayList<String> with all property values
	 * @author Daniel Metzinger
	 */
	public static ArrayList<String> getProperties(ArrayList<String> properties) {
		ArrayList<String> returnValue = new ArrayList<>();
		Properties propFile = new Properties();
		try {
			// set location of database properties file
			InputStream input = new FileInputStream(propertiesFilePath);
			// load from properties file
			propFile.load(input);
			
			//for each property read value from properties File and save it in ArrayList
			properties.forEach(prop -> returnValue.add(propFile.getProperty(prop)));
			
		} catch (FileNotFoundException e) {
			System.out.println("Propertiesfile not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Propertiesfile could not be loaded!");
			e.printStackTrace();
		}
		return returnValue;
	}

//----
	
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
