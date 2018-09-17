package ch.zhaw.iwi.pathexamplejava.service;

import com.google.inject.Inject;

import ch.zhaw.iwi.pathexamplejava.server.json.JsonHelper;
import ch.zhaw.iwi.pathexamplejava.server.json.JsonTransformer;

public abstract class AbstractRestService {

	@Inject
	JsonHelper jsonHelper;

	public abstract void init();
	
	protected <C> C fromJson(String json, Class<C> classOfC) {
		return jsonHelper.fromJson(json, classOfC);
	}

	protected JsonTransformer getJsonTransformer() {
		return jsonHelper.getJsonTransformer();
	}
	
}
