package es.ucm.tp1.Exceptions.highlevelexceptions;

//High level Exception
@SuppressWarnings("serial")
public class CommandParseException extends GameException {
	public CommandParseException() {
	}

	public CommandParseException(String message) {
		super(message);
	}

	public CommandParseException(Throwable cause) {
		super(cause);
	}

	public CommandParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}