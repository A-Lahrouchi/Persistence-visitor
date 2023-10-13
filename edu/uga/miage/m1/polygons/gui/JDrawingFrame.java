package edu.uga.miage.m1.polygons.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame implements MouseListener, MouseMotionListener {

    private enum ButtonTypes {

        SQUARE, TRIANGLE, CIRCLE, XML, JSON
    }

    private static final long serialVersionUID = 1L;

    private JToolBar m_toolbar;

    private ButtonTypes m_selected;

    private JPanel m_panel;

    private JLabel m_label;

    private ActionListener m_reusableActionListener = new ShapeActionListener();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<ButtonTypes, JButton> m_buttons = new HashMap<>();

    private ArrayList<SimpleShape> shapes=new ArrayList<SimpleShape>();
       

    /**
     * Default constructor that populates the main window.
     * @param frameName
     */
    public JDrawingFrame(String frameName) {
        super(frameName);
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
        addShape(ButtonTypes.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addShape(ButtonTypes.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addShape(ButtonTypes.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));
        addShape(ButtonTypes.XML, "XML");
        addShape(ButtonTypes.JSON, "JSON");
        setPreferredSize(new Dimension(400, 400));
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShape(ButtonTypes shape, ImageIcon icon) {
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

    private void addShape(ButtonTypes buttonType, String exportType) {
        JButton button = new JButton(exportType);
        button.setBorderPainted(false);
        m_buttons.put(buttonType, button);
        button.setActionCommand(buttonType.toString());
        button.addActionListener(m_reusableActionListener);
        if (m_selected == null) {
            button.doClick();
        }
        m_toolbar.add(button);
        m_toolbar.validate();
        repaint();
    }


    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {
        if (m_panel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) m_panel.getGraphics();
            switch(m_selected) {
                case CIRCLE:
                    Circle circleToDraw=new Circle(evt.getX(), evt.getY());
                    circleToDraw.draw(g2);
                    shapes.add(circleToDraw);
                    break;
                case TRIANGLE:
                    Triangle triangleToDraw=new Triangle(evt.getX(), evt.getY());
                    triangleToDraw.draw(g2);
                    shapes.add(triangleToDraw);
                    break;
                case SQUARE:
                    Square squareToDraw=new Square(evt.getX(), evt.getY());
                    squareToDraw.draw(g2);
                    shapes.add(squareToDraw);
                    break;
                case XML:
                    System.out.println("export XML");
                    break;
                case JSON:
                    System.out.println("export JSON");
                    break;
                default:
                    System.out.println("No shape named " + m_selected);
            }
        }
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseEntered(MouseEvent evt) {
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        m_label.setText(" ");
        m_label.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mousePressed(MouseEvent evt) {
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
     */
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
     */
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            // Itère sur tous les boutons
            Iterator<ButtonTypes> keys = m_buttons.keySet().iterator();
            while (keys.hasNext()) {
                ButtonTypes shape = keys.next();
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
