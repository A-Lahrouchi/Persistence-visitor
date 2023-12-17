package edu.uga.miage.m1.polygons.gui.commands;

import edu.uga.miage.m1.polygons.gui.io.Exporter;
import edu.uga.miage.m1.polygons.gui.io.JsonExporter;
import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;

public class ExportToJsonCommand implements Command{

    private Exporter exporter;
    private JsonShapes jsonShapes;

    public ExportToJsonCommand(JsonShapes jsonShapes){
        this.exporter = JsonExporter.getInstance();
        this.jsonShapes = jsonShapes;
    }

    @Override
    public void execute(){
        exporter.writeShapes(jsonShapes);
    }

    @Override
    public void undo() {
        // Undoing an export is not applicable in this example
    }

    @Override
    public boolean isUndoable() {
        return false; // Export is not undoable
    }
}
