import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.commands.CommandManager;
import edu.uga.miage.m1.polygons.gui.commands.ExportToJsonCommand;
import edu.uga.miage.m1.polygons.gui.commands.ExportToXmlCommand;
import edu.uga.miage.m1.polygons.gui.io.Exporter;
import edu.uga.miage.m1.polygons.gui.io.Importer;
import edu.uga.miage.m1.polygons.gui.io.JsonExporter;
import edu.uga.miage.m1.polygons.gui.io.JsonImporter;
import edu.uga.miage.m1.polygons.gui.io.XmlExporter;
import edu.uga.miage.m1.polygons.gui.io.XmlImporter;
import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;
import edu.uga.miage.m1.polygons.gui.listofshapes.XmlShapes;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExportCommandTest {

    @Test
    void testExportEmptyJsonShapes() {
        CommandManager commandManager = CommandManager.getInstance();
        Importer importer = JsonImporter.getInstance();
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes;
        String jsonShapesAsString;

        commandManager.executeCommand(new ExportToJsonCommand(new JsonShapes()));
        jsonShapes = (JsonShapes) importer.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    void testExportEmptyXmlShapes() {
        CommandManager commandManager = CommandManager.getInstance();
        Importer importer = XmlImporter.getInstance();
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes;
        String xmlShapesAsString;

        commandManager.executeCommand(new ExportToXmlCommand(new XmlShapes()));
        xmlShapes = (XmlShapes) importer.readShapes();
        xmlShapesAsString = exporter.getShapesAsString(xmlShapes);

        assertEquals("<?xml version='1.0' encoding='UTF-8'?><root><shapes/></root>", xmlShapesAsString);
    }

    @Test
    void testExportJsonShapes() {
        CommandManager commandManager = CommandManager.getInstance();
        Importer importer = JsonImporter.getInstance();
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();
        JsonShapes jsonShapesResult;
        String jsonShapesAsString;

        jsonShapes.pushToShapeList(new Circle(25, 25).accept(JSonVisitor.getInstance()));

        commandManager.executeCommand(new ExportToJsonCommand(jsonShapes));
        jsonShapesResult = (JsonShapes) importer.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapesResult);

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    void testExportXmlShapes() {
        CommandManager commandManager = CommandManager.getInstance();
        Importer importer = XmlImporter.getInstance();
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();
        XmlShapes xmlShapesResult;
        String xmlShapesAsString;

        xmlShapes.pushToShapeList(new Circle(25, 25).accept(XMLVisitor.getInstance()));

        commandManager.executeCommand(new ExportToXmlCommand(xmlShapes));
        xmlShapesResult = (XmlShapes) importer.readShapes();
        xmlShapesAsString = exporter.getShapesAsString(xmlShapesResult);

        assertEquals(
                "<?xml version='1.0' encoding='UTF-8'?><root><shapes><shape><type>circle</type><x>0</x><y>0</y></shape></shapes></root>",
                xmlShapesAsString);
    }
}
