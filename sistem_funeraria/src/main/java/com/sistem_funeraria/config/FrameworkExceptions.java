package com.sistem_funeraria.config;

public class FrameworkExceptions extends RuntimeException {
	private static final long serialVersionUID = 8528078029067494799L;

	public FrameworkExceptions() {
		super();
	}

	public FrameworkExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FrameworkExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public FrameworkExceptions(String message) {
		super(message);
	}

	public FrameworkExceptions(Throwable cause) {
		super(cause);
	}
}