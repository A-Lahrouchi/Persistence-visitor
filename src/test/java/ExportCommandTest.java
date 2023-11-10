import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.commands.CommandInvoker;
import edu.uga.miage.m1.polygons.gui.commands.ExportToJsonCommand;
import edu.uga.miage.m1.polygons.gui.commands.ExportToXmlCommand;
import edu.uga.miage.m1.polygons.gui.exporters.Exporter;
import edu.uga.miage.m1.polygons.gui.exporters.JsonExporter;
import edu.uga.miage.m1.polygons.gui.exporters.XmlExporter;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.JsonShapes;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.XmlShapes;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExportCommandTest {

    @Test
    void testExportEmptyJsonShapes() {
        CommandInvoker invoker = CommandInvoker.getInstance();
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes;
        String jsonShapesAsString;

        invoker.setCommand(new ExportToJsonCommand(new JsonShapes()));
        invoker.executeCommand();
        jsonShapes = (JsonShapes) exporter.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    void testExportEmptyXmlShapes() {
        CommandInvoker invoker = CommandInvoker.getInstance();
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes;
        String xmlShapesAsString;

        invoker.setCommand(new ExportToXmlCommand(new XmlShapes()));
        invoker.executeCommand();
        xmlShapes = (XmlShapes) exporter.readShapes();
        xmlShapesAsString = exporter.getShapesAsString(xmlShapes);

        assertEquals("<?xml version='1.0' encoding='UTF-8'?><root><shapes/></root>", xmlShapesAsString);
    }

}
