package info.sjd.exceptions;

public class ExceptionSJD extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public ExceptionSJD(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return getClass().getName() + " 'cause " + message;
	}

}
