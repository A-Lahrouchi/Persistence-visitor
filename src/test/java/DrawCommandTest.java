import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.commands.CommandManager;
import edu.uga.miage.m1.polygons.gui.commands.DrawShapesCommand;
import edu.uga.miage.m1.polygons.gui.commands.ExportToJsonCommand;
import edu.uga.miage.m1.polygons.gui.io.Exporter;
import edu.uga.miage.m1.polygons.gui.io.Importer;
import edu.uga.miage.m1.polygons.gui.io.JsonExporter;
import edu.uga.miage.m1.polygons.gui.io.JsonImporter;
import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;
import edu.uga.miage.m1.polygons.gui.listofshapes.XmlShapes;

class DrawCommandTest {
    @Test
    void testDrawCircle() {
        CommandManager commandManager = CommandManager.getInstance();
        Importer importer = JsonImporter.getInstance();
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();
        XmlShapes xmlShapes = new XmlShapes();
        JsonShapes jsonShapesResult;
        String jsonShapesAsString = null;

        commandManager.executeCommand(new DrawShapesCommand(
                null,
                "circle",
                25,
                25,
                jsonShapes,
                xmlShapes));
        commandManager.executeCommand(new ExportToJsonCommand(jsonShapes));
        jsonShapesResult = (JsonShapes) importer.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapesResult);
        

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    void testUndoDrawing() {
        CommandManager commandManager = CommandManager.getInstance();
        Importer importer = JsonImporter.getInstance();
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();
        XmlShapes xmlShapes = new XmlShapes();
        JsonShapes jsonShapesResult;
        String jsonShapesAsString = null;

        commandManager.executeCommand(new DrawShapesCommand(
                null,
                "circle",
                25,
                25,
                jsonShapes,
                xmlShapes));
        commandManager.undoCommand();
        commandManager.executeCommand(new ExportToJsonCommand(jsonShapes));
        jsonShapesResult = (JsonShapes) importer.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapesResult);
        

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    void testRedoDrawing() {
        CommandManager commandManager = CommandManager.getInstance();
        Importer importer = JsonImporter.getInstance();
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();
        XmlShapes xmlShapes = new XmlShapes();
        JsonShapes jsonShapesResult;
        String jsonShapesAsString = null;

        commandManager.executeCommand(new DrawShapesCommand(
                null,
                "circle",
                25,
                25,
                jsonShapes,
                xmlShapes));
        commandManager.undoCommand();
        commandManager.redoCommand();
        commandManager.executeCommand(new ExportToJsonCommand(jsonShapes));
        jsonShapesResult = (JsonShapes) importer.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapesResult);
        

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }
}
