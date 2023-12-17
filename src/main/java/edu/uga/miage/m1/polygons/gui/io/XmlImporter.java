package edu.uga.miage.m1.polygons.gui.io;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import edu.uga.miage.m1.polygons.gui.listofshapes.ListOfShapes;
import edu.uga.miage.m1.polygons.gui.listofshapes.XmlShapes;

public class XmlImporter implements Importer {

    private static XmlImporter singletonXmlImporter;

    private String fileName;
    private XmlMapper xmlMapper;

    private XmlImporter() {
        this.xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }

    public static XmlImporter getInstance() {
        if (singletonXmlImporter == null) {
            singletonXmlImporter = new XmlImporter();
        }
        return singletonXmlImporter;
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
}
