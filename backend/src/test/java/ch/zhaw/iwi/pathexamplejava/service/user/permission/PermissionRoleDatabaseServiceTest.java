package ch.zhaw.iwi.pathexamplejava.service.user.permission;

import ch.zhaw.iwi.pathexamplejava.model.user.permission.PermissionRole;
import ch.zhaw.iwi.pathexamplejava.service.AbstractCrudDatabaseServiceTest;
import ch.zhaw.iwi.pathexamplejava.service.user.permission.PermissionRoleDatabaseService;

public class PermissionRoleDatabaseServiceTest extends AbstractCrudDatabaseServiceTest<PermissionRole, Long> {
	
	@Override
	protected Class<?> getService() {
		return PermissionRoleDatabaseService.class;
	}

	@Override
	protected Class<PermissionRole> getEntity() {
		return PermissionRole.class;
	}	

}