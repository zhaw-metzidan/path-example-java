package ch.zhaw.iwi.pathexamplejava.service.user.permission;

import ch.zhaw.iwi.pathexamplejava.model.user.permission.PermissionFunction;
import ch.zhaw.iwi.pathexamplejava.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.PathListEntry;

public class PermissionFunctionDatabaseService extends AbstractCrudDatabaseService<PermissionFunction, String> {

	@Override
	public Class<PermissionFunction> getEntityClass() {
		return PermissionFunction.class;
	}

	@Override
	public void createPathListEntry(PermissionFunction entity, PathListEntry<String> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
	}

}