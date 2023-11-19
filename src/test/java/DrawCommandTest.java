import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.commands.CommandManager;
import edu.uga.miage.m1.polygons.gui.commands.DrawShapesCommand;
import edu.uga.miage.m1.polygons.gui.commands.ExportToJsonCommand;
import edu.uga.miage.m1.polygons.gui.exporters.Exporter;
import edu.uga.miage.m1.polygons.gui.exporters.JsonExporter;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.JsonShapes;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.XmlShapes;

class DrawCommandTest {
    @Test
    void testDrawCircle() {
        CommandManager commandManager = CommandManager.getInstance();
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
        jsonShapesResult = (JsonShapes) exporter.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapesResult);
        

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    void testUndoDrawing() {
        CommandManager commandManager = CommandManager.getInstance();
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
        jsonShapesResult = (JsonShapes) exporter.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapesResult);
        

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    void testRedoDrawing() {
        CommandManager commandManager = CommandManager.getInstance();
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
        jsonShapesResult = (JsonShapes) exporter.readShapes();
        jsonShapesAsString = exporter.getShapesAsString(jsonShapesResult);
        

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }
}
