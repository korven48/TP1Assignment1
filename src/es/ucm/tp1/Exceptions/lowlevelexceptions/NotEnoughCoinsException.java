package es.ucm.tp1.Exceptions.lowlevelexceptions;

@SuppressWarnings("serial")
public class NotEnoughCoinsException extends Exception {

	public NotEnoughCoinsException() {
	}

	public NotEnoughCoinsException(String message) {
		super(message);
	}

	public NotEnoughCoinsException(Throwable cause) {
		super(cause);
	}

	public NotEnoughCoinsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughCoinsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
