package com.example.xmltojson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.xml.sax.ContentHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class XmlToJsonApplication {

	public static void main(String[] args) throws IOException {

//		File xmlFile = new File(String.valueOf("src/main/resources/pacs008.xml"));
//		File jsonFile = new File(String.valueOf("src/main/resources/output.json"));
//
//		XmlMapper xmlMapper = new XmlMapper();
//
//		ObjectMapper jsonMapper = new ObjectMapper();
//		jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		Object data = xmlMapper.readValue(xmlFile,Object.class);
//		jsonMapper.writeValue(jsonFile, data);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		JsonNode jsonData = jsonMapper.readTree(jsonFile);
//
//		System.out.println(jsonData);
//
//		// Extract the required nested objects
//		JsonNode fitoFICstmrCdtTrf = jsonData.get("FIToFICstmrCdtTrf");
//		JsonNode grpHdr = fitoFICstmrCdtTrf.get("GrpHdr");
//
//		// Extract values and store them in variables
//		String msgId = grpHdr.get("MsgId").asText();
//		String creDtTm = grpHdr.get("CreDtTm").asText();
//		String nbOfTxs = grpHdr.get("NbOfTxs").asText();
//		String ccy = grpHdr.at("/TtlIntrBkSttlmAmt/Ccy").asText();
//		String amount = grpHdr.at("/TtlIntrBkSttlmAmt/").asText();
//		String intrBkSttlmDt = grpHdr.get("IntrBkSttlmDt").asText();
//		String sttlmMtd = grpHdr.at("/SttlmInf/SttlmMtd").asText();
//		String clrSysPrtry = grpHdr.at("/SttlmInf/ClrSys/Prtry").asText();
//		String instgAgtBICFI = grpHdr.at("/InstgAgt/FinInstnId/BICFI").asText();
//
//		JsonNode cdtTrfTxInf = jsonData.at("/FIToFICstmrCdtTrf/CdtTrfTxInf");
//
//		// Extract values and store them in variables
//		String instrId = cdtTrfTxInf.at("/PmtId/InstrId").asText();
//		String endToEndId = cdtTrfTxInf.at("/PmtId/EndToEndId").asText();
//		String txId = cdtTrfTxInf.at("/PmtId/TxId").asText();
//		String svcLvlCd = cdtTrfTxInf.at("/PmtTpInf/SvcLvl/Cd").asText();
//		String lclInstrmCd = cdtTrfTxInf.at("/PmtTpInf/LclInstrm/Cd").asText();
//		String ctgyPurpCd = cdtTrfTxInf.at("/PmtTpInf/CtgyPurp/Cd").asText();
//		String intrBkSttlmCcy = cdtTrfTxInf.at("/IntrBkSttlmAmt/Ccy").asText();
//		String intrBkSttlmValue = cdtTrfTxInf.at("/IntrBkSttlmAmt/").asText();
//		String chrgBr = cdtTrfTxInf.at("/ChrgBr").asText();
//		String dbtrNm = cdtTrfTxInf.at("/Dbtr/Nm").asText();
//		String dbtrAcctId = cdtTrfTxInf.at("/DbtrAcct/Id/Othr/Id").asText();
//		String dbtrAgtBICFI = cdtTrfTxInf.at("/DbtrAgt/FinInstnId/BICFI").asText();
//		String cdtrAgtBICFI = cdtTrfTxInf.at("/CdtrAgt/FinInstnId/BICFI").asText();
//		String cdtrNm = cdtTrfTxInf.at("/Cdtr/Nm").asText();
//		String cdtrAcctId = cdtTrfTxInf.at("/CdtrAcct/Id/Othr/Id").asText();
//		String purpCd = cdtTrfTxInf.at("/Purp/Cd").asText();
//
//		// Print the extracted values
//		System.out.println("MsgId: " + msgId);
//		System.out.println("CreDtTm: " + creDtTm);
//		System.out.println("NbOfTxs: " + nbOfTxs);
//		System.out.println("Ccy: " + ccy);
//		System.out.println("Amount: " + amount);
//		System.out.println("IntrBkSttlmDt: " + intrBkSttlmDt);
//		System.out.println("SttlmMtd: " + sttlmMtd);
//		System.out.println("ClrSysPrtry: " + clrSysPrtry);
//		System.out.println("InstgAgtBICFI: " + instgAgtBICFI);
//
//		// Print the extracted values
//		System.out.println("InstrId: " + instrId);
//		System.out.println("EndToEndId: " + endToEndId);
//		System.out.println("TxId: " + txId);
//		System.out.println("SvcLvl Cd: " + svcLvlCd);
//		System.out.println("LclInstrm Cd: " + lclInstrmCd);
//		System.out.println("CtgyPurp Cd: " + ctgyPurpCd);
//		System.out.println("IntrBkSttlm Ccy: " + intrBkSttlmCcy);
//		System.out.println("IntrBkSttlm Value: " + intrBkSttlmValue);
//		System.out.println("ChrgBr: " + chrgBr);
//		System.out.println("Dbtr Nm: " + dbtrNm);
//		System.out.println("DbtrAcct Id: " + dbtrAcctId);
//		System.out.println("DbtrAgt BICFI: " + dbtrAgtBICFI);
//		System.out.println("CdtrAgt BICFI: " + cdtrAgtBICFI);
//		System.out.println("Cdtr Nm: " + cdtrNm);
//		System.out.println("CdtrAcct Id: " + cdtrAcctId);
//		System.out.println("Purp Cd: " + purpCd);
//



//		try {
//			ObjectMapper objectMapper1 = new ObjectMapper();
//			String jsonFilePath = "src/main/resources/output.json";
//			String xmlFilePath = "src/main/resources/output.xml";
//
//			// Read JSON data from file
//			JsonNode jsonData1 = objectMapper1.readTree(new File(jsonFilePath));
//
//			// Convert JSON to XML using Jackson's XmlMapper
//			XmlMapper xmlMapper1 = new XmlMapper();
//			xmlMapper1.setDefaultUseWrapper(false); // Disable wrapper elements
//			String xmlString = xmlMapper1.writeValueAsString(jsonData1);
//
//			// Modify XML string to add root and namespaces
//			String formattedXmlString = addRootAndNamespaces(xmlString);
//
//			// Remove specific tags manually
//			formattedXmlString = removeTag(formattedXmlString, "<ObjectNode>");
//			formattedXmlString = removeTag(formattedXmlString, "</ObjectNode>");
//
//			// Format the XML string
//			String formattedXml = formatXml(formattedXmlString);
//
//			// Write formatted XML to file
//			try (FileOutputStream fos = new FileOutputStream(xmlFilePath)) {
//				fos.write(formattedXml.getBytes());
//			}
//
//			System.out.println("XML conversion completed and written to output.xml");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


	}

//
//
//	// Your existing addRootAndNamespaces method
//	private static String addRootAndNamespaces(String xml) {
//		// Add Document root element and namespaces
//		return "<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
//				xml +
//				"\n</Document>";
//	}
//
//	// Method to remove specific tag from XML string
//	private static String removeTag(String xml, String tag) {
//		return xml.replace(tag, "");
//	}
//
//	// Method to format XML string
//	private static String formatXml(String xml) throws IOException {
//		try {
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document document = builder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xml)));
//
//			OutputFormat format = new OutputFormat(document);
//			format.setIndenting(true);
//			format.setIndent(4);
//			format.setLineWidth(0); // No line width limit
//			format.setPreserveEmptyAttributes(true);
//
//			// Remove the XML declaration from the output
//			format.setOmitXMLDeclaration(true);
//
//			StringWriter writer = new StringWriter();
//			XMLSerializer serializer = new XMLSerializer(writer, format);
//			serializer.serialize(document);
//
//			return writer.toString();
//		} catch (Exception e) {
//			throw new IOException("Failed to format XML: " + e.getMessage());
//		}
//	}

}
