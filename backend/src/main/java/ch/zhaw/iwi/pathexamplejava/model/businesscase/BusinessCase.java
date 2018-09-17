package ch.zhaw.iwi.pathexamplejava.model.businesscase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.zhaw.iwi.pathexamplejava.model.AbstractEntity;
import ch.zhaw.iwi.pathexamplejava.model.user.User;

@Entity
public class BusinessCase extends AbstractEntity implements Comparable<BusinessCase> {

	private String name;
	private String title;
	
	@Column(length = COMMENT_FIELD_LENGTH)
	private String comments;
	
	@ManyToOne
    private BusinessCaseType businessCaseType;

	@ManyToOne
    private User manager;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
		
	public BusinessCaseType getBusinessCaseType() {
		return businessCaseType;
	}

	public void setBusinessCaseType(BusinessCaseType businessCaseType) {
		this.businessCaseType = businessCaseType;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int compareTo(BusinessCase o) {
		int nameComparison = this.getName().compareTo(o.getName());
		if (nameComparison != 0) {
			return nameComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

}
