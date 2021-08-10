package mysql.hikari.lombok.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JobTitle {

	private int employeeId;
	private String jobTitle;

	private String jobTitleFromDate;
	private String jobTitleToDate;

}
