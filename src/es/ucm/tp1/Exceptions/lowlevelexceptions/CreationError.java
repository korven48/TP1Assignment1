package es.ucm.tp1.Exceptions.lowlevelexceptions;

@SuppressWarnings("serial")
public class CreationError extends Exception {
		
	public CreationError() {
		super();
	}
	
	public CreationError(String message) {
		super(message);
	}

	public CreationError(Throwable cause) {
		super(cause);
	}

	public CreationError(String message, Throwable cause) {
		super(message, cause);
	}

	public CreationError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
