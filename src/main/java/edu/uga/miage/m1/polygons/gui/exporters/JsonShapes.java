package edu.uga.miage.m1.polygons.gui.exporters;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class JsonShapes {

    @JsonProperty("shapes")
    private ArrayList<JsonNode> shapeList;

    public JsonShapes() {
        this.shapeList = new ArrayList<>();
    }

    public List<JsonNode> getShapeList() {
        return shapeList;
    }

    public void pushToShapeList(String shape) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode shapeNode;
        try {
            shapeNode = objectMapper.readTree(shape);
            shapeList.add(shapeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}