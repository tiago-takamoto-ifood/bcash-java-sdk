package br.com.bcash.util;

public class XmlErrorException extends RuntimeException {

	private static final long serialVersionUID = -3682104750214747339L;

	public XmlErrorException(String msg, Exception e) {
		super(msg, e);
	}
}
