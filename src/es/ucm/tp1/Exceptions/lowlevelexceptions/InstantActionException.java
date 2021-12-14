package es.ucm.tp1.Exceptions.lowlevelexceptions;

@SuppressWarnings("serial")
public class InstantActionException extends Exception {

	public InstantActionException() {
	}

	public InstantActionException(String message) {
		super(message);
	}

	public InstantActionException(Throwable cause) {
		super(cause);
	}

	public InstantActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InstantActionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
