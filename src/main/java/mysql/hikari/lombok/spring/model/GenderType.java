package mysql.hikari.lombok.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public enum GenderType {

	MALE("M"), FEMALE("F");

	private String value;

	private GenderType(String value) {
		this.value = value;
	}

	public static String getEnumName(String value) {

		for (GenderType genderType : GenderType.values()) {
			if (genderType.value.equals(value)) {
				return genderType.name().toString();
			}
		}
		return null;

	}

}
