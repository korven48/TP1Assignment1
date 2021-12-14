package es.ucm.tp1.Exceptions.lowlevelexceptions;

@SuppressWarnings("serial")
public class RecordsException extends Exception {

	public RecordsException() {
	}

	public RecordsException(String message) {
		super(message);
	}

	public RecordsException(Throwable cause) {
		super(cause);
	}

	public RecordsException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
