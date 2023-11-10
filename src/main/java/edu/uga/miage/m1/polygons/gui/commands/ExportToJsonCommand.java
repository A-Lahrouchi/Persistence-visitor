package edu.uga.miage.m1.polygons.gui.commands;

import edu.uga.miage.m1.polygons.gui.exporters.Exporter;
import edu.uga.miage.m1.polygons.gui.exporters.JsonExporter;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.JsonShapes;

public class ExportToJsonCommand implements Command{

    private Exporter exporter;
    private JsonShapes jsonShapes;

    public ExportToJsonCommand(JsonShapes jsonShapes){
        this.exporter = JsonExporter.getInstance();
        this.jsonShapes = jsonShapes;
    }

    public void execute(){
        exporter.writeShapes(jsonShapes);
    }
}
