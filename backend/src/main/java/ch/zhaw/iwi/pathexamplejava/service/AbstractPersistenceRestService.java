package ch.zhaw.iwi.pathexamplejava.service;

import com.google.inject.Injector;

public abstract class AbstractPersistenceRestService<T extends AbstractDatabaseService> extends AbstractRestService {

	
	private T databaseService;

	public AbstractPersistenceRestService(Injector injector, Class<T> databaseServiceClass) {
		super();
		this.databaseService = injector.getInstance(databaseServiceClass);
	}

	protected T getDatabaseService() {
		return databaseService;
	}
	
}
