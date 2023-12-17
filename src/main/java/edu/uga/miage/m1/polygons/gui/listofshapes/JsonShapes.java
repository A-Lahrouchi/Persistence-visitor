package edu.uga.miage.m1.polygons.gui.listofshapes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedList;

public class JsonShapes implements ListOfShapes {

    @JsonProperty("shapes")
    private LinkedList<JsonNode> shapeList;

    public JsonShapes() {
        this.shapeList = new LinkedList<>();
    }

    public List<JsonNode> getShapeList() {
        return shapeList;
    }

    public void pushToShapeList(String shape) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode shapeNode;
        try {
            shapeNode = objectMapper.readTree(shape);
            shapeList.push(shapeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String popFromShapeList(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode shapeNode;
        String shapeAsString = null;
        try {
            shapeNode = shapeList.pop();
            shapeAsString = objectMapper.writeValueAsString(shapeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shapeAsString;
    }

}