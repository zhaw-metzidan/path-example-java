package ch.zhaw.iwi.pathexamplejava.service;

import java.util.ArrayList;
import java.util.Collection;

public final class RestUtility {

	private RestUtility() {
	}
		
	@FunctionalInterface
	public interface ButtonCustomizer<ENTITY> {
	  public void apply(ENTITY entity, PathListEntry<String> button, Collection<ENTITY> collection);
	}
	
	public static <ENTITY> Collection<PathListEntry<String>> createButtonList(Collection<ENTITY> collection, ButtonCustomizer<ENTITY> customizer) {
		Collection<PathListEntry<String>> result = new ArrayList<>();
		for (ENTITY entity : collection) {
			PathListEntry<String> entry = new PathListEntry<>();
			customizer.apply(entity, entry, collection);
			result.add(entry);
		}
		return result;
	}
	
}
