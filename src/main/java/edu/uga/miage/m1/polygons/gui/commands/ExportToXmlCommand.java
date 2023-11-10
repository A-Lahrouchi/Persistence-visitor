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

    public void execute(){
        exporter.writeShapes(xmlShapes);
    }

}
