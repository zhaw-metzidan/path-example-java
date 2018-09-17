package ch.zhaw.iwi.orderbutler.service.user;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import ch.zhaw.iwi.pathexamplejava.server.json.ExcludeFromJson;

public class LanguageMock {

	@SerializedName("key")
	private String code;
	
	@ExcludeFromJson
	private Map<LanguageMock, String> name = new HashMap<>();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Map<LanguageMock, String> getName() {
		return name;
	}
	
	public void setName(Map<LanguageMock, String> name) {
		this.name = name;
	}
		
	/*
	 * required for json serialization
	 */
	@Override
	public String toString() {
		return code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		LanguageMock other = (LanguageMock) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
