package mysql.hikari.lombok.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Gender of an Employee.
 * 
 * {@link #FEMALE} {@link #MALE}
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Getter
@AllArgsConstructor
public enum GenderType {

	MALE("M"), FEMALE("F");

	private String value;

	public static String getEnumName(String value) {

		for (GenderType genderType : GenderType.values()) {
			if (genderType.value.equals(value)) {
				return genderType.name().toString();
			}
		}
		return null;

	}

}
