package es.ucm.tp1.Exceptions.lowlevelexceptions;

@SuppressWarnings("serial")
public class InputOutputRecordException extends Exception {

	public InputOutputRecordException() {
	}

	public InputOutputRecordException(String message) {
		super(message);
	}

	public InputOutputRecordException(Throwable cause) {
		super(cause);
	}

	public InputOutputRecordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputOutputRecordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
