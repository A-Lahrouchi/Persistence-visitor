package edu.uga.miage.m1.polygons.gui.exporters;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class Exporter {

    private static Exporter singletonExporter;

    private ObjectMapper objectMapper;
    private XmlMapper xmlMapper;

    private Exporter() {
        this.objectMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }

    public static Exporter getInstance() {
        if (singletonExporter == null) {
            singletonExporter = new Exporter();
        }
        return singletonExporter;
    }

    public String getJsonShapesAsString(JsonShapes jsonShapes) {
        String jsonShapesAString = null;
        try {
            jsonShapesAString = objectMapper.writeValueAsString(jsonShapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonShapesAString;
    }

    public String writeJsonShapes(JsonShapes jsonShapes) {
        String jsonShapesAString = null;
        JsonNode jsonShapesNode;
        try {
            objectMapper.writeValue(new File("./exports/export.json"), jsonShapes);
            jsonShapesNode = objectMapper.readTree(new File("./exports/export.json"));
            jsonShapesAString = objectMapper.writeValueAsString(jsonShapesNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonShapesAString;
    }

    public String getXmlShapesAsString(XmlShapes xmlShapes) {
        String xmlShapesAString = null;
        try {
            xmlShapesAString = xmlMapper.writeValueAsString(xmlShapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlShapesAString;
    }

    public String writeXmlShapes(XmlShapes xmlShapes) {
        String xmlShapesAString = null;
        XmlShapes xmlShapesResult;
        try {
            xmlMapper.writeValue(new File("./exports/export.xml"), xmlShapes);
            xmlShapesResult = xmlMapper.readValue(new File("./exports/export.xml"), XmlShapes.class);
            xmlShapesAString = xmlMapper.writeValueAsString(xmlShapesResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlShapesAString;
    }
}
