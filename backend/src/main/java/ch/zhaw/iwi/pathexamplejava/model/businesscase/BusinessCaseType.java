package ch.zhaw.iwi.pathexamplejava.model.businesscase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessCaseType implements Comparable<BusinessCaseType> {

	@Id
	private BusinessCaseTypeEnum key;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BusinessCaseTypeEnum getKey() {
		return key;
	}
	
	public void setKey(BusinessCaseTypeEnum key) {
		this.key = key;
	}

	@Override
	public int compareTo(BusinessCaseType o) {
		int nameComparison = this.getName().compareTo(o.getName());
		if (nameComparison != 0) {
			return nameComparison;
		}
		return this.getKey().compareTo(o.getKey());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessCaseType other = (BusinessCaseType) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
