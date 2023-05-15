package ruds.desafio.alterdata.servicos.hotdog.infrastructure.service.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SpecificationRestrictions {
	
	public static String likeAnyWhere(String value) {
		return "%" + value.toLowerCase() + "%";
	}

}
