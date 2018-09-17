package ch.zhaw.iwi.pathexamplejava.service.businesscase;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCaseType;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCaseTypeEnum;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCaseType_;
import ch.zhaw.iwi.pathexamplejava.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.PathListEntry;

public class BusinessCaseTypeDatabaseService extends AbstractCrudDatabaseService<BusinessCaseType, BusinessCaseTypeEnum> {

	@Override
	public Class<BusinessCaseType> getEntityClass() {
		return BusinessCaseType.class;
	}

	@Override
	public void createPathListEntry(BusinessCaseType entity, PathListEntry<BusinessCaseTypeEnum> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
	}

	@Override
	protected void orderBy(Root<BusinessCaseType> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(BusinessCaseType_.name)));
	}

}