package es.ucm.tp1.Exceptions.lowlevelexceptions;

@SuppressWarnings("serial")
public class GenerateNewGameElementException extends Exception {

	public GenerateNewGameElementException() {
	}

	public GenerateNewGameElementException(String message) {
		super(message);
	}

	public GenerateNewGameElementException(Throwable cause) {
		super(cause);
	}

	public GenerateNewGameElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenerateNewGameElementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
