package ch.zhaw.iwi.pathexamplejava.service.businesscase;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.pathexamplejava.service.AbstractCrudRestService;

public class BusinessCaseTypeRestService extends AbstractCrudRestService<BusinessCaseTypeDatabaseService, Long, BusinessCaseDatabaseService> {

	@Inject
	public BusinessCaseTypeRestService(Injector injector) {
		super(injector, BusinessCaseTypeDatabaseService.class);
	}
	
}
