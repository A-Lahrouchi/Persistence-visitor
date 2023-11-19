package edu.uga.miage.m1.polygons.gui.commands;

import edu.uga.miage.m1.polygons.gui.exporters.Exporter;
import edu.uga.miage.m1.polygons.gui.exporters.XmlExporter;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.XmlShapes;

public class ExportToXmlCommand implements Command {

    private Exporter exporter;
    private XmlShapes xmlShapes;

    public ExportToXmlCommand(XmlShapes xmlShapes){
        this.exporter = XmlExporter.getInstance();
        this.xmlShapes = xmlShapes;
    }

    @Override
    public void execute(){
        exporter.writeShapes(xmlShapes);
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
