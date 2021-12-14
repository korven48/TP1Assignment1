package es.ucm.tp1.Exceptions.lowlevelexceptions;

@SuppressWarnings("serial")
public class StandardInputOutputException extends Exception {

	public StandardInputOutputException() {
	}

	public StandardInputOutputException(String message) {
		super(message);
	}

	public StandardInputOutputException(Throwable cause) {
		super(cause);
	}

	public StandardInputOutputException(String message, Throwable cause) {
		super(message, cause);
	}

	public StandardInputOutputException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
