package finalyearproject.exceptions;

public class TestTypeMismatchException extends RuntimeException {
	private static final long serialVersionUID = -2441448989471835559L;
	
	public TestTypeMismatchException(String message) {
		super(message);
	}
}
