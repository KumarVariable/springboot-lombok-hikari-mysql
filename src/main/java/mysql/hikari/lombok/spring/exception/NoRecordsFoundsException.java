package mysql.hikari.lombok.spring.exception;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class NoRecordsFoundsException extends RuntimeException {

	/**
	 * Custom Exception for No Records Found in Database.
	 * 
	 * @author metanoia
	 * @since 1.0s
	 */
	private static final long serialVersionUID = -6631635776589534870L;

	private String exceptionMessage;

	public NoRecordsFoundsException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}

}
