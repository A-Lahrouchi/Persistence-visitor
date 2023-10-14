package edu.uga.miage.m1.polygons.gui;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 * 
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class JDrawingFrame extends JFrame
        implements MouseListener, MouseMotionListener {
    private enum Shapes {
        SQUARE, TRIANGLE, CIRCLE,
        JSON, XML
    };

    private static final long serialVersionUID = 1L;
    private JToolBar m_toolbar;
    private Shapes m_selected;
    private JPanel m_panel;
    private JLabel m_label;
    private ActionListener m_reusableActionListener = new ShapeActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<Shapes, JButton> m_buttons = new HashMap<>();

    private FileWriter jsonFileWriter;
    private FileWriter xmlFileWriter;

    /**
     * Default constructor that populates the main window.
     * 
     * @param frameName
     **/
    public JDrawingFrame(String frameName, FileWriter jsonFileWriter, FileWriter xmlFileWriter) {
        super(frameName);

        // FileWriters
        this.jsonFileWriter = jsonFileWriter;
        this.xmlFileWriter = xmlFileWriter;

        // Instantiates components
        m_toolbar = new JToolBar("Toolbar");
        m_panel = new JPanel();
        m_panel.setBackground(Color.WHITE);
        m_panel.setLayout(null);
        m_panel.setMinimumSize(new Dimension(400, 400));
        m_panel.addMouseListener(this);
        m_panel.addMouseMotionListener(this);
        m_label = new JLabel(" ", JLabel.LEFT);

        // Fills the panel
        setLayout(new BorderLayout());
        add(m_toolbar, BorderLayout.NORTH);
        add(m_panel, BorderLayout.CENTER);
        add(m_label, BorderLayout.SOUTH);

        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addShape(Shapes.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addShape(Shapes.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));

        // Add export buttons in menu
        addShape(Shapes.JSON, "JSON");
        addShape(Shapes.XML, "XML");

        setPreferredSize(new Dimension(400, 400));
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * 
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon  The icon associated with the injected <tt>SimpleShape</tt>.
     **/
    private void addShape(Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        m_buttons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(m_reusableActionListener);

        if (m_selected == null) {
            button.doClick();
        }

        m_toolbar.add(button);
        m_toolbar.validate();
        repaint();
    }

    /*
     * Add export buttons
     */
    private void addShape(Shapes shape, String exportType) {
        JButton button = new JButton(exportType);
        button.setBorderPainted(false);
        m_buttons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(m_reusableActionListener);

        if (m_selected == null) {
            button.doClick();
        }

        m_toolbar.add(button);
        m_toolbar.validate();
        repaint();
    }

    /**
     * TODO Use the factory to abstract shape creation
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * 
     * @param evt The associated mouse event.
     **/

    JsonBuilderFactory factory = Json.createBuilderFactory(null);
    JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
    JsonObjectBuilder jsonObjectBuilder = factory.createObjectBuilder();

    StringBuilder xmlStringBuilder = new StringBuilder()
        .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><shapes>");

    public void mouseClicked(MouseEvent evt) {

        JSonVisitor jsonVisitor = new JSonVisitor();
        XMLVisitor xmlVisitor = new XMLVisitor();

        if (m_panel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) m_panel.getGraphics();

            switch (m_selected) {
                case CIRCLE:
                    Circle circle = new Circle(evt.getX(), evt.getY());
                    circle.draw(g2);

                    circle.accept(jsonVisitor);
                    circle.accept(xmlVisitor);

                    jsonArrayBuilder.add(jsonVisitor.getRepresentation());
                    xmlStringBuilder.append(xmlVisitor.getRepresentation());
                    break;

                case TRIANGLE:
                    Triangle triangle = new Triangle(evt.getX(), evt.getY());
                    triangle.draw(g2);

                    triangle.accept(jsonVisitor);
                    triangle.accept(xmlVisitor);

                    jsonArrayBuilder.add(jsonVisitor.getRepresentation());
                    xmlStringBuilder.append(xmlVisitor.getRepresentation());
                    break;

                case SQUARE:
                    Square square = new Square(evt.getX(), evt.getY());
                    square.draw(g2);

                    square.accept(jsonVisitor);
                    square.accept(xmlVisitor);

                    jsonArrayBuilder.add(jsonVisitor.getRepresentation());
                    xmlStringBuilder.append(xmlVisitor.getRepresentation());
                    break;

                case JSON:
                    jsonObjectBuilder.add("shapes", jsonArrayBuilder);
                    try {
                        jsonFileWriter.write(jsonObjectBuilder.build().toString());
                        jsonFileWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case XML:
                    xmlStringBuilder.append("</shapes>");
                    try {
                        xmlFileWriter.write(xmlStringBuilder.toString());
                        xmlFileWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("No shape named " + m_selected);
            }
        }
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * 
     * @param evt The associated mouse event.
     **/
    public void mouseEntered(MouseEvent evt) {

    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * 
     * @param evt The associated mouse event.
     **/
    public void mouseExited(MouseEvent evt) {
        m_label.setText(" ");
        m_label.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * 
     * @param evt The associated mouse event.
     **/
    public void mousePressed(MouseEvent evt) {
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * 
     * @param evt The associated mouse event.
     **/
    public void mouseReleased(MouseEvent evt) {
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * 
     * @param evt The associated mouse event.
     **/
    public void mouseDragged(MouseEvent evt) {
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * 
     * @param evt The associated mouse event.
     **/
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        m_label.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     **/
    private class ShapeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // Itère sur tous les boutons
            Iterator<Shapes> keys = m_buttons.keySet().iterator();
            while (keys.hasNext()) {
                Shapes shape = keys.next();
                JButton btn = m_buttons.get(shape);
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    m_selected = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }
}