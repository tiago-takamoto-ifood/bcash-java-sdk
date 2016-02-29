package br.com.bcash.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.error.ResponseError;

public class XmlErrorUtil {

	public static ErrorList fromXml(String xml) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();

			return extractErrors(doc.getElementsByTagName("error"));
		} catch (ParserConfigurationException e) {
			throw new XmlErrorException("Impossível extrair erro de xml [" + xml + "]", e);
		} catch (SAXException e) {
			throw new XmlErrorException("Impossível extrair erro de xml [" + xml + "]", e);
		} catch (IOException e) {
			throw new XmlErrorException("Impossível extrair erro de xml [" + xml + "]", e);
		}
	}

	private static ErrorList extractErrors(NodeList nodeList) {

		List<ResponseError> errors = new ArrayList<ResponseError>();
		for (int i = 0; i < nodeList.getLength(); i++) {

			ResponseError error = extractError(nodeList.item(i));
			if (error != null) {
				errors.add(error);
			}
		}
		ErrorList errorList = new ErrorList();
		errorList.setList(errors);

		return errorList;
	}

	private static ResponseError extractError(Node node) {

		if (node.getNodeType() != Node.ELEMENT_NODE) {
			return null;
		}

		Element element = (Element) node;
		String code = element.getElementsByTagName("code").item(0).getTextContent();
		String description = element.getElementsByTagName("description").item(0).getTextContent();

		ResponseError error = new ResponseError();
		error.setCode(code);
		error.setDescription(description);
		return error;
	}
}
