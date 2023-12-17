package edu.uga.miage.m1.polygons.gui.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;
import edu.uga.miage.m1.polygons.gui.listofshapes.ListOfShapes;

public class JsonImporter implements Importer {

    private static JsonImporter singletonJsonImporter;

    private String fileName;
    private ObjectMapper objectMapper;

    private JsonImporter() {
        this.fileName = "./exports/export.json";
        this.objectMapper = new ObjectMapper();
    }

    public static JsonImporter getInstance() {
        if (singletonJsonImporter == null) {
            singletonJsonImporter = new JsonImporter();
        }
        return singletonJsonImporter;
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
}
