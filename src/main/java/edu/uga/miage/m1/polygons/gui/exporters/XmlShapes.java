package edu.uga.miage.m1.polygons.gui.exporters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "root")
public class XmlShapes {

    @JacksonXmlElementWrapper(localName = "shapes")
    @JacksonXmlProperty(localName = "shape")
    private ArrayList<JsonNode> shapeList;

    public XmlShapes() {
        this.shapeList = new ArrayList<>();
    }
    
    public List<JsonNode> getShapeList() {
        return shapeList;
    }

    public void pushToShapeList(String shape) {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode shapeNode;
        try {
            shapeNode = xmlMapper.readTree(shape);
            shapeList.add(shapeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
