package edu.uga.miage.m1.polygons.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.uga.miage.m1.polygons.gui.commands.DrawShapesCommand;
import edu.uga.miage.m1.polygons.gui.commands.CommandManager;
import edu.uga.miage.m1.polygons.gui.commands.ExportToJsonCommand;
import edu.uga.miage.m1.polygons.gui.commands.ExportToXmlCommand;
import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;
import edu.uga.miage.m1.polygons.gui.listofshapes.XmlShapes;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {

    private enum Tools {
        SQUARE, TRIANGLE, CIRCLE
    }

    private static final long serialVersionUID = 1L;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu importMenu;
    private JMenu exportMenu;
    private JMenuItem importJsonItem;
    private JMenuItem importXmlItem;
    private JMenuItem exportJsonItem;
    private JMenuItem exportXmlItem;
    private JMenuItem undoItem;
    private JMenuItem redoItem;
    private ActionListener menuActionListener = new MenuActionListener();

    private JToolBar toolbar;
    private Tools selectedTool;
    private Map<Tools, JButton> toolButtons = new HashMap<>();
    private ActionListener toolActionListener = new ToolActionListener();

    private JPanel drawingPanel;

    private JLabel coordinatesLabel;

    private JsonShapes jsonShapes;
    private XmlShapes xmlShapes;
    private CommandManager commandManager;

    /**
     * Default constructor that populates the main window.
     * 
     * @param frameName
     */
    public MainFrame(String frameName) {
        super(frameName);

        jsonShapes = new JsonShapes();
        xmlShapes = new XmlShapes();
        commandManager = CommandManager.getInstance();

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        importMenu = new JMenu("Import");
        exportMenu = new JMenu("Export");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        importJsonItem = new JMenuItem("Json");
        importXmlItem = new JMenuItem("Xml");
        exportJsonItem = new JMenuItem("Json");
        exportXmlItem = new JMenuItem("Xml");
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");

        importMenu.add(importJsonItem);
        importMenu.add(importXmlItem);
        exportMenu.add(exportJsonItem);
        exportMenu.add(exportXmlItem);

        fileMenu.add(importMenu);
        fileMenu.add(exportMenu);
        editMenu.add(undoItem);
        editMenu.add(redoItem);

        importJsonItem.addActionListener(menuActionListener);
        importXmlItem.addActionListener(menuActionListener);
        exportJsonItem.addActionListener(menuActionListener);
        exportXmlItem.addActionListener(menuActionListener);
        undoItem.addActionListener(menuActionListener);
        redoItem.addActionListener(menuActionListener);

        toolbar = new JToolBar("Toolbar");
        toolbar.setLayout(new FlowLayout());
        toolbar.setBackground(Color.lightGray);
        toolbar.setPreferredSize(new Dimension(100, toolbar.getHeight()));

        drawingPanel = new JPanel();
        drawingPanel.setBackground(Color.WHITE);
        drawingPanel.setLayout(null);
        drawingPanel.setMinimumSize(new Dimension(800, 400));
        drawingPanel.addMouseListener(this);
        drawingPanel.addMouseMotionListener(this);

        coordinatesLabel = new JLabel("--", JLabel.RIGHT);

        this.setLayout(new BorderLayout());
        this.add(toolbar, BorderLayout.WEST);
        this.add(drawingPanel, BorderLayout.CENTER);
        this.add(coordinatesLabel, BorderLayout.SOUTH);

        addTool(Tools.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addTool(Tools.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addTool(Tools.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));

        this.setJMenuBar(menuBar);
        this.setPreferredSize(new Dimension(800, 400));
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * 
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addTool(Tools tool, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        toolButtons.put(tool, button);
        button.setActionCommand(tool.toString());
        button.addActionListener(toolActionListener);
        if (selectedTool == null) {
            button.doClick();
        }
        toolbar.add(button);
        toolbar.validate();
        repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {
        if (drawingPanel.contains(evt.getX(), evt.getY())) {
            switch (selectedTool) {
                case CIRCLE:
                    commandManager.executeCommand(new DrawShapesCommand(
                            drawingPanel,
                            "circle",
                            evt.getX(),
                            evt.getY(),
                            jsonShapes,
                            xmlShapes));
                    break;
                case SQUARE:
                    commandManager.executeCommand(new DrawShapesCommand(
                            drawingPanel,
                            "square",
                            evt.getX(),
                            evt.getY(),
                            jsonShapes,
                            xmlShapes));
                    break;
                case TRIANGLE:
                    commandManager.executeCommand(new DrawShapesCommand(
                            drawingPanel,
                            "triangle",
                            evt.getX(),
                            evt.getY(),
                            jsonShapes,
                            xmlShapes));
                    break;
                default:
                    System.out.println("No shape named " + selectedTool);
            }
        }
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseEntered(MouseEvent evt) {
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        coordinatesLabel.setText("--");
        coordinatesLabel.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * 
     * @param evt The associated mouse event.
     */
    public void mousePressed(MouseEvent evt) {
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * 
     * @param evt The associated mouse event.
     */
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        coordinatesLabel.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     */
    private class ToolActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            // Itere sur tous les boutons
            Iterator<Tools> keys = toolButtons.keySet().iterator();
            while (keys.hasNext()) {
                Tools tool = keys.next();
                JButton btn = toolButtons.get(tool);
                if (evt.getActionCommand().equals(tool.toString())) {
                    btn.setBorderPainted(true);
                    selectedTool = tool;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }

    private class MenuActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == exportJsonItem) {
                commandManager.executeCommand(new ExportToJsonCommand(jsonShapes));
            }

            if (evt.getSource() == exportXmlItem) {
                commandManager.executeCommand(new ExportToXmlCommand(xmlShapes));
            }

            if (evt.getSource() == undoItem) {
                commandManager.undoCommand();
            }

            if (evt.getSource() == redoItem) {
                commandManager.redoCommand();
            }
        }
    }
}
