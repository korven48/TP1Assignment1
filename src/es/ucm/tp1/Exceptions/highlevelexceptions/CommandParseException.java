package es.ucm.tp1.Exceptions.highlevelexceptions;

//High level Exception
@SuppressWarnings("serial")
public class CommandParseException extends GameException {
	public CommandParseException() {
		// TODO Auto-generated constructor stub
	}

	public CommandParseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CommandParseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CommandParseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CommandParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
