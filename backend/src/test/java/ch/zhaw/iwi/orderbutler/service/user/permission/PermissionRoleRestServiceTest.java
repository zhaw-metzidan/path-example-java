package ch.zhaw.iwi.orderbutler.service.user.permission;

import ch.zhaw.iwi.orderbutler.service.AbstractCrudRestServiceTest;
import ch.zhaw.iwi.pathexamplejava.model.user.permission.PermissionRole;
import ch.zhaw.iwi.pathexamplejava.service.user.permission.PermissionRoleRestService;

public class PermissionRoleRestServiceTest extends AbstractCrudRestServiceTest<PermissionRole, Long> {

	@Override
	protected Class<?> getService() {
		return PermissionRoleRestService.class;
	}

	@Override
	protected Class<PermissionRole> getEntityClass() {
		return PermissionRole.class;
	}

}
