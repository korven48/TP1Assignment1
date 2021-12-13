package es.ucm.tp1.Exceptions.highlevelexceptions;

//High level Exception
@SuppressWarnings("serial")
public class CommandExecuteException extends GameException {

	public CommandExecuteException() {
	}

	public CommandExecuteException(String message) {
		super(message);
	}

	public CommandExecuteException(Throwable cause) {
		super(cause);
	}

	public CommandExecuteException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
