package ch.zhaw.iwi.orderbutler.service.businesscase;

import ch.zhaw.iwi.orderbutler.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCase;
import ch.zhaw.iwi.pathexamplejava.service.businesscase.BusinessCaseRestService;

public class BusinessCaseRestServiceTest extends AbstractCrudRestServiceTest<BusinessCase, Long> {

	@Override
	protected Class<?> getService() {
		return BusinessCaseRestService.class;
	}

	@Override
	protected Class<BusinessCase> getEntityClass() {
		return BusinessCase.class;
	}

}
