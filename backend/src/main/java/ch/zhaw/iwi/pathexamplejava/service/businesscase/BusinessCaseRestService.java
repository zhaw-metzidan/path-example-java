package ch.zhaw.iwi.pathexamplejava.service.businesscase;

import static spark.Spark.get;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.pathexamplejava.service.AbstractCrudRestService;

public class BusinessCaseRestService extends AbstractCrudRestService<BusinessCaseDatabaseService, Long, BusinessCaseDatabaseService> {

	@Inject
	public BusinessCaseRestService(Injector injector) {
		super(injector, BusinessCaseDatabaseService.class);
	}

	@Override
	protected void initGet() {
		super.initGet();

		get("services/businessCase/businessObjectType/:businessObjectTypeKey", (req, res) -> {
			return new Object();
		}, getJsonTransformer());
	}

}
