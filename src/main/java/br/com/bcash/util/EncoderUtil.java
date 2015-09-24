package br.com.bcash.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;

import br.com.bcash.config.Configuration;

public class EncoderUtil {

	private static final String ENCODE = Configuration.getEncode().displayName();

	public static String urlEncode(String data) throws EncoderException {
		try {
			return new URLCodec().encode(data, ENCODE);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("O charset utilizado não está disponível.", e);
		}
	}

	public static String urlDecode(String data) throws DecoderException {
		try {
			return new URLCodec().decode(data, ENCODE);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("O charset utilizado não está disponível.", e);
		}
	}

	public static String base64Encode(String data) {
		try {
			return Base64.encodeBase64String(data.getBytes(ENCODE));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("O charset utilizado não está disponível.", e);
		}
	}

	public static String base64Decode(String data) {
		try {
			return new String(Base64.decodeBase64(data), ENCODE);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("O charset utilizado não está disponível.", e);
		}
	}
}
