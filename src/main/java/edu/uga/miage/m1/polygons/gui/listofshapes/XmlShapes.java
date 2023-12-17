package edu.uga.miage.m1.polygons.gui.listofshapes;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "root")
public class XmlShapes implements ListOfShapes {

    @JacksonXmlElementWrapper(localName = "shapes")
    @JacksonXmlProperty(localName = "shape")
    private LinkedList<JsonNode> shapeList;

    public XmlShapes() {
        this.shapeList = new LinkedList<>();
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

    public String popFromShapeList(){
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode shapeNode;
        String shapeAsString = null;
        try {
            shapeNode = shapeList.pop();
            shapeAsString = xmlMapper.writeValueAsString(shapeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shapeAsString;
    }

}
