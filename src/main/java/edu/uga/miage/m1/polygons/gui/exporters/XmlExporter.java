package edu.uga.miage.m1.polygons.gui.exporters;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.ListOfShapes;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.XmlShapes;

public class XmlExporter implements Exporter {

    private static XmlExporter singletonXmlExporter;

    private String fileName;
    private XmlMapper xmlMapper;

    private XmlExporter() {
        this.fileName = "./exports/export.xml";
        this.xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }

    public static XmlExporter getInstance() {
        if (singletonXmlExporter == null) {
            singletonXmlExporter = new XmlExporter();
        }
        return singletonXmlExporter;
    }

    public void setFileName(String xmlFileName) {
        this.fileName = xmlFileName;
    }

    public String getShapesAsString(ListOfShapes xmlShapes) {
        String xmlShapesAString = null;
        try {
            xmlShapesAString = xmlMapper.writeValueAsString(xmlShapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlShapesAString;
    }

    public ListOfShapes readShapes() {
        XmlShapes xmlShapes = null;
        try {
            xmlShapes = xmlMapper.readValue(new File(fileName), XmlShapes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlShapes;
    }

    public void writeShapes(ListOfShapes xmlShapes) {
        try {
            xmlMapper.writeValue(new File(fileName), xmlShapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
