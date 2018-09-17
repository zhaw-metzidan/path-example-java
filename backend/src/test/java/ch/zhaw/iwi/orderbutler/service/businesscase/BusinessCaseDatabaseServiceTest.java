package ch.zhaw.iwi.orderbutler.service.businesscase;

import ch.zhaw.iwi.orderbutler.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCase;
import ch.zhaw.iwi.pathexamplejava.service.businesscase.BusinessCaseDatabaseService;

public class BusinessCaseDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<BusinessCase, Long> {

	@Override
	protected Class<?> getService() {
		return BusinessCaseDatabaseService.class;
	}

	@Override
	protected Class<BusinessCase> getEntity() {
		return BusinessCase.class;
	}
	

}