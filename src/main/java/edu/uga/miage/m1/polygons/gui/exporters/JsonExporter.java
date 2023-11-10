package edu.uga.miage.m1.polygons.gui.exporters;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.JsonShapes;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.ListOfShapes;

public class JsonExporter implements Exporter {

    private static JsonExporter singletonJsonExporter;

    private String fileName;
    private ObjectMapper objectMapper;

    private JsonExporter() {
        this.fileName = "./exports/export.json";
        this.objectMapper = new ObjectMapper();
    }

    public static JsonExporter getInstance() {
        if (singletonJsonExporter == null) {
            singletonJsonExporter = new JsonExporter();
        }
        return singletonJsonExporter;
    }

    public void setFileName(String jsonFileName) {
        this.fileName = jsonFileName;
    }

    public String getShapesAsString(ListOfShapes jsonShapes) {
        String jsonShapesAString = null;
        try {
            jsonShapesAString = objectMapper.writeValueAsString(jsonShapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonShapesAString;
    }

    public JsonShapes readShapes() {
        JsonShapes jsonShapes = null;
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(new File(fileName));
            jsonShapes = objectMapper.treeToValue(jsonNode, JsonShapes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonShapes;
    }

    public void writeShapes(ListOfShapes jsonShapes) {
        try {
            objectMapper.writeValue(new File(fileName), jsonShapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
