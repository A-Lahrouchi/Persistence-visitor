package edu.uga.miage.m1.polygons.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class GUIHelper {

    public static void showOnFrame(String frameName) {
        try {
            FileWriter jsonFileWriter = new FileWriter("exportedShapes.json", false);
            FileWriter xmlFileWriter = new FileWriter("exportedShapes.xml", false);
            JFrame frame = new JDrawingFrame(frameName, jsonFileWriter, xmlFileWriter);
            WindowAdapter wa = new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        jsonFileWriter.close();
                        xmlFileWriter.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }
            };
            frame.addWindowListener(wa);
            frame.pack();
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}