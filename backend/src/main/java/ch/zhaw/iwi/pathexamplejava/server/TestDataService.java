package ch.zhaw.iwi.pathexamplejava.server;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCase;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCaseType;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCaseTypeEnum;
import ch.zhaw.iwi.pathexamplejava.model.user.User;
import ch.zhaw.iwi.pathexamplejava.model.user.permission.PermissionFunction;
import ch.zhaw.iwi.pathexamplejava.model.user.permission.PermissionFunctionEnum;
import ch.zhaw.iwi.pathexamplejava.model.user.permission.PermissionRole;
import ch.zhaw.iwi.pathexamplejava.service.AbstractDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.DateUtility;
import ch.zhaw.iwi.pathexamplejava.service.businesscase.BusinessCaseDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.businesscase.BusinessCaseTypeDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.user.UserDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.user.permission.PermissionFunctionDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.user.permission.PermissionRoleDatabaseService;

public class TestDataService extends AbstractDatabaseService {

	private static final Logger logger = LoggerFactory.getLogger(TestDataService.class);

	@Inject
	BusinessCaseDatabaseService businessCaseDatabaseService;

	@Inject
	BusinessCaseTypeDatabaseService businessCaseObjectTypeDatabaseService;
	
	@Inject
	UserDatabaseService userDatabaseService;

	@Inject
	private PermissionRoleDatabaseService permissionRoleDatabaseService;

	@Inject
	private PermissionFunctionDatabaseService permissionFunctionDatabaseService;


	@Transactional
	public void create() {
		logger.info("Test Data");
		
		// User Permissions
		PermissionFunction administration = createDemoPermissionFunction(PermissionFunctionEnum.readAdministration, "Administration");
		PermissionRole administratorRole = createDemoPermissionRole("Administrator", administration);
		
		// Generic
		User user1 = createTestUser("user", "Test", "User");
		User user2 = createTestUser("admin", "Admin", "User", administratorRole);

		// Business Case Types
		BusinessCaseType businessCaseType1 = createTestBusinessCaseType(BusinessCaseTypeEnum.contract, "Vertrag");
		BusinessCaseType businessCaseType2 = createTestBusinessCaseType(BusinessCaseTypeEnum.offer, "Offerte");
		BusinessCaseType businessCaseType3 = createTestBusinessCaseType(BusinessCaseTypeEnum.project, "Projekt");
		BusinessCaseType businessCaseType4 = createTestBusinessCaseType(BusinessCaseTypeEnum.showcase, "Showcase");

		// Business Cases
		createTestBusinessCase("Vertrag ZHAW", "Vertrag-Beschreibung", businessCaseType1, user1);
		createTestBusinessCase("Offerte ETH", "Offerte-Beschreibung", businessCaseType2, user1);
		createTestBusinessCase("Projekt Uni Zürich", "Projekt-Beschreibung", businessCaseType3, user1);
		createTestBusinessCase("Showcase EPFL", "Showcase-Beschreibung", businessCaseType4, user2);
	}

	private BusinessCaseType createTestBusinessCaseType(BusinessCaseTypeEnum type, String name) {
		BusinessCaseType businessCaseObjectType = new BusinessCaseType();
		businessCaseObjectType.setKey(type);
		businessCaseObjectType.setName(name);
		businessCaseObjectTypeDatabaseService.create(businessCaseObjectType);
		return businessCaseObjectType;
	}

	private BusinessCase createTestBusinessCase(String name, String title, BusinessCaseType businessCaseType, User manager) {
		BusinessCase businessCase = new BusinessCase();
		businessCase.setName(name);
		businessCase.setTitle(title);
		businessCase.setBusinessCaseType(businessCaseType);
		businessCase.setManager(manager);
		businessCaseDatabaseService.create(businessCase);
		return businessCase;
	}

	private User createTestUser(String email, String firstName, String lastName, PermissionRole... permissionRoles) {
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(".");
		user.setRepeatPassword(".");
		user.setEvtCreationDate(DateUtility.truncateToDay(new Date()));
		user.getPermissionRoles().addAll(Arrays.asList(permissionRoles));
		userDatabaseService.create(user);
		return user;
	}
	
	private PermissionFunction createDemoPermissionFunction(PermissionFunctionEnum key, String name) {
		PermissionFunction function = new PermissionFunction();
		function.setKey(key.name());
		function.setName(name);
		permissionFunctionDatabaseService.create(function);
		return function;
	}

	private PermissionRole createDemoPermissionRole(String name, PermissionFunction... functions) {
		PermissionRole role = new PermissionRole();
		role.setName(name);
		role.getPermissionFunctions().addAll(Arrays.asList(functions));
		permissionRoleDatabaseService.create(role);
		return role;
	}


}