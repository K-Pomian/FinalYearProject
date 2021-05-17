package finalyearproject.exceptions;

public class SizeMismatchException extends RuntimeException {
	private static final long serialVersionUID = -7800473848064654784L;
	
	public SizeMismatchException(String message) {
		super(message);
	}
}
