package es.ucm.tp1.model.CusExceptions;

import es.ucm.tp1.control.commands.CusComExceptions.CommandExecuteException;

public class InvalidPositionException extends CommandExecuteException {

	public InvalidPositionException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidPositionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidPositionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPositionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
