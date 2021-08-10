package mysql.hikari.lombok.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class represents available departments in current organization.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Departments {

	private String departmentId;
	private String departmentName;

}
