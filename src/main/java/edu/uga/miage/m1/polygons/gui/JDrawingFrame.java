package edu.uga.miage.m1.polygons.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import edu.uga.miage.m1.polygons.gui.exporters.Exporter;
import edu.uga.miage.m1.polygons.gui.exporters.JsonShapes;
import edu.uga.miage.m1.polygons.gui.exporters.XmlShapes;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {

    private enum Shapes {
        SQUARE, TRIANGLE, CIRCLE
    }

    private enum Exports {
        JSON, XML
    }

    private static final long serialVersionUID = 1L;

    private JToolBar toolbar;

    private Shapes selectedShape;

    private Exports selectedExport;

    private JPanel panel;

    private JLabel label;

    private ActionListener reusableActionListener = new ShapeActionListener();

    private ActionListener exportActionListener = new ExportActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<Shapes, JButton> shapeButtons = new HashMap<>();

    /**
     * Tracks buttons to manage the exports.
     */
    private Map<Exports, JButton> exportButtons = new HashMap<>();

    private Exporter exporter;
    private JSonVisitor jsonVisitor;
    private XMLVisitor xmlVisitor;
    private JsonShapes jsonShapes;
    private XmlShapes xmlShapes;

    /**
     * Default constructor that populates the main window.
     * 
     * @param frameName
     */
    public JDrawingFrame(String frameName) {
        super(frameName);

        exporter = Exporter.getInstance();
        jsonVisitor = JSonVisitor.getInstance();
        xmlVisitor = XMLVisitor.getInstance();
        jsonShapes = new JsonShapes();
        xmlShapes = new XmlShapes();

        // Instantiates components
        toolbar = new JToolBar("Toolbar");
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setMinimumSize(new Dimension(400, 400));
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        label = new JLabel(" ", JLabel.LEFT);

        // Fills the panel
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);

        // Add shapes on the left side of the menu
        addShape(Shapes.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addShape(Shapes.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addShape(Shapes.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));

        // Add export buttons on the right side of the menu
        toolbar.add(Box.createHorizontalGlue());
        addExport(Exports.JSON, "JSON");
        addExport(Exports.XML, "XML");

        setPreferredSize(new Dimension(400, 400));
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * 
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        shapeButtons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(reusableActionListener);
        if (selectedShape == null) {
            button.doClick();
        }
        toolbar.add(button);
        toolbar.validate();
        repaint();
    }

    /**
     * Add export buttons to the toolbar
     * 
     * @param export      The type of export (JSON ou XML)
     * @param buttonLabel The label associated to the button
     */
    private void addExport(Exports export, String buttonLabel) {
        JButton button = new JButton(buttonLabel);
        button.setBorderPainted(false);
        exportButtons.put(export, button);
        button.setActionCommand(export.toString());
        button.addActionListener(exportActionListener);

        if (selectedExport == null) {
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
        if (panel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            switch (selectedShape) {
                case CIRCLE:
                    Circle c = new Circle(evt.getX(), evt.getY());
                    c.draw(g2);
                    jsonShapes.pushToShapeList(c.accept(jsonVisitor));
                    xmlShapes.pushToShapeList(c.accept(xmlVisitor));
                    break;
                case TRIANGLE:
                    new Triangle(evt.getX(), evt.getY()).draw(g2);
                    Triangle t = new Triangle(evt.getX(), evt.getY());
                    t.draw(g2);
                    jsonShapes.pushToShapeList(t.accept(jsonVisitor));
                    xmlShapes.pushToShapeList(t.accept(xmlVisitor));
                    break;
                case SQUARE:
                    Square s = new Square(evt.getX(), evt.getY());
                    s.draw(g2);
                    jsonShapes.pushToShapeList(s.accept(jsonVisitor));
                    xmlShapes.pushToShapeList(s.accept(xmlVisitor));
                    break;
                default:
                    System.out.println("No shape named " + selectedShape);
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
        label.setText(" ");
        label.repaint();
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
        label.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     */
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            // Itere sur tous les boutons
            Iterator<Shapes> keys = shapeButtons.keySet().iterator();
            while (keys.hasNext()) {
                Shapes shape = keys.next();
                JButton btn = shapeButtons.get(shape);
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    selectedShape = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }

    /**
     * Simple action listener for export tool bar buttons that export
     * the shape list to JSON and XML file when receiving an action event.
     */
    private class ExportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Itere sur tous les boutons
            Iterator<Exports> keys = exportButtons.keySet().iterator();
            while (keys.hasNext()) {
                Exports exportType = keys.next();
                JButton btn = exportButtons.get(exportType);
                if (evt.getActionCommand().equals(exportType.toString())){
                    selectedExport = exportType;
                    switch (selectedExport) {
                        case JSON:
                            exporter.writeJsonShapes(jsonShapes);
                            System.out.println("Exported to: " + selectedExport.toString());
                            break;

                        case XML:
                            exporter.writeXmlShapes(xmlShapes);
                            System.out.println("Exported to: " + selectedExport.toString());
                            break;

                        default:
                            System.out.println("No Export type selected" + selectedExport);
                    }
                }
                btn.repaint();
            }
        }
    }
}
