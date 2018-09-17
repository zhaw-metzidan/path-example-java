package ch.zhaw.iwi.pathexamplejava.service.businesscase;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCase;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCaseTypeEnum;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCaseType_;
import ch.zhaw.iwi.pathexamplejava.model.businesscase.BusinessCase_;
import ch.zhaw.iwi.pathexamplejava.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.pathexamplejava.service.PathListEntry;

public class BusinessCaseDatabaseService extends AbstractCrudDatabaseService<BusinessCase, Long> {

	@Override
	public Class<BusinessCase> getEntityClass() {
		return BusinessCase.class;
	}

	@Override
	public void createPathListEntry(BusinessCase entity, PathListEntry<Long> entry) {
		entry.setKey(entity.getKey(), getKeyName());
		entry.setName(entity.getName());
		entry.setTooltip(entity.getName() + ", " + entity.getTitle() + ", Ziel: " + entity.getTarget());
		entry.getDetails().add(entity.getTitle());
	}

	@Override
	protected void orderBy(Root<BusinessCase> root, List<Order> orderList) {
		orderList.add(getCriteriaBuilder().asc(root.get(BusinessCase_.name)));
	}

	public class BusinessCaseObjectTypeFilter extends AbstractCrudDatabaseService<BusinessCase, Long>.AbstractListFilter {
		private BusinessCaseTypeEnum type;

		public BusinessCaseObjectTypeFilter(BusinessCaseTypeEnum type) {
			super();
			this.type = type;
		}

		@Override
		public void appendFilter(CriteriaQuery<BusinessCase> criteriaQuery, Root<BusinessCase> root) {
			CriteriaBuilder cb = getCriteriaBuilder();
			addPredicate(criteriaQuery, cb.equal(root.get(BusinessCase_.businessCaseObjectType).get(BusinessCaseType_.key), type));
		}
	}
	

}