package wargame.exceptions;

public class GoneTooFarException extends RuntimeException {
	public GoneTooFarException() {
		super();
	}
	public GoneTooFarException(String s) {
		super(s);
	}
}
