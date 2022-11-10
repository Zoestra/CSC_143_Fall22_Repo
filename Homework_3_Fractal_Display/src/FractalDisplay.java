import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Construct a window to display the fractal shapes. This class is almost
 * complete. Add your own code only in the places marked with the comment TODO
 *
 */

public class FractalDisplay extends JPanel implements MouseListener, ActionListener, ChangeListener {

    // Width of the inner panel
    public static final int WIDTH = 800;

    // Height of the inner panel
    public static final int HEIGHT = 800;

    // Possible shapes
    private int which;
    public static final int SIERPINSKI_TRIANGLE = 0;
    public static final int H_SHAPE = 1;
    public static final int MY_SHAPE = 2;

    private JFrame frame;

    // Other elements in the window
    // Radio buttons (with their titles)
    private String[] titles;
    private JRadioButton[] radioButtons;

    // The button to add/remove levels
    private JButton addLevel, removeLevel;

    // Slider to change the display (extra-credit feature)
    private JSlider slider;

    // Current shape
    private Shape shape;

    // Use a popup menu to display information
    // the total number of shapes in the current shape
    // -> triggered by a right click of the mouse.
    private JPopupMenu popup;
    private JLabel popupLabel;

    /**
     * Constructs a FractalDisplay to display fractal shapes
     */
    public FractalDisplay() {
        // Use a windows look and feel (if available)
        try {
            UIManager.LookAndFeelInfo[] lfinfo = UIManager.getInstalledLookAndFeels();
            UIManager.setLookAndFeel(lfinfo[4].getClassName());
        } catch (Exception e) {/* ignore any problem */
        }

        // Radio buttons
        titles = new String[] { "Sierpinski Triangle", "HShape", "Golden Dragon" };
        radioButtons = new JRadioButton[titles.length];
        // Only one radio button can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new JRadioButton(titles[i]);
            buttonGroup.add(radioButtons[i]);
            radioButtons[i].addActionListener(this);
        }

        // Button to add or remove levels to the shape
        addLevel = new JButton("Add level");
        addLevel.addActionListener(this);
        removeLevel = new JButton("Remove level");
        removeLevel.addActionListener(this);

        // Slider to change the shape
        slider = new JSlider(0, 100, 50);
        slider.addChangeListener(this);

        // Place the components in the frame
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        // at the bottom (SOUTH)
        JPanel southPanel = new JPanel(new GridLayout(3, 1));
        JPanel southPanelFirstRow = new JPanel();
        for (int i = 0; i < radioButtons.length; i++)
            southPanelFirstRow.add(radioButtons[i]);
        southPanel.add(southPanelFirstRow);
        JPanel southPanelSecondRow = new JPanel();
        southPanelSecondRow.add(addLevel);
        southPanelSecondRow.add(removeLevel);
        southPanel.add(southPanelSecondRow);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        JPanel southPanelThirdRow = new JPanel();
        southPanelThirdRow.add(slider);
        southPanel.add(southPanelThirdRow);

        setBackground(Color.WHITE);
        contentPane.add(this, BorderLayout.CENTER);

        // Get ready to listen to mouse clicks
        addMouseListener(this);

        // Put everything in a frame
        frame = new JFrame("Recursive graphics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        // Show it
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        // Resize it with the actual size
        Insets insets = frame.getInsets();
        int width = WIDTH + insets.left + insets.right;
        int height = HEIGHT + insets.top + insets.bottom + (int) (southPanel.getPreferredSize().getHeight());
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);

        // popup menu
        popup = new JPopupMenu();
        popupLabel = new JLabel("", SwingConstants.CENTER);
        popupLabel.setFont(new Font("Courier", Font.BOLD, 32));
        popup.add(popupLabel);
    }

    /**
     * Handles the button clicks
     *
     * @param e the ActionEvent generated by the click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == JRadioButton.class) {
            for (int i = 0; i < radioButtons.length; i++) {
                if (e.getSource() == radioButtons[i]) {
                    which = i;
                    // reset the slider to its default value
                    slider.setValue(50);
                    break;
                }
            }
            switch (which) {
                case SIERPINSKI_TRIANGLE:
                    // TODO
                    // Call your SierpinskiTriangle constructor here
                    // replace shape = null with shape = new SierpinskiTriangle(...)
                    shape = new SierpinskiTriangle(new Point(0,0), new Point(800,800));
                    break;
                case H_SHAPE:
                    // TODO
                    // Call your HShape constructor here
                    // Replace shape = null with shape = new HShape(...))
                    shape = null;
                    break;
                case MY_SHAPE:
                    // TODO
                    // Call your MyShape constructor here
                    // Replace shape = null with shape = new MyShape(...))
                    shape = null;
                    break;
            }
        } else if (e.getSource() == addLevel) {
            // Don't do anything if there is no display
            if (shape != null) {
                boolean success = shape.addLevel();
                if (!success) {
                    JOptionPane.showMessageDialog(this, "Can't add another level", "Message",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (e.getSource() == removeLevel) {
            // Don't do anything if there is no display
            if (shape != null) {
                boolean success = shape.removeLevel();
                if (!success) {
                    JOptionPane.showMessageDialog(this, "Can't remove another level", "Message",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            // unknown source
            return;
        }

        // display the new drawing
        repaint();
    }

    /**
     * Paints the shape
     */
    public void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);
        // If there is nothing to display, stop here
        if (shape != null) {
            // Use some graphics2D features (smooth edges)
            Graphics2D g = (Graphics2D) gfx;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            shape.draw(g);
        }
    }

    /**
     * Different platforms might have different ways to trigger a popup menu: check
     * all possibilities
     */
    public void mousePressed(MouseEvent e) {
        checkPopup(e);
    }

    public void mouseClicked(MouseEvent e) {
        checkPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        checkPopup(e);
    }

    /**
     * Displays a message box giving the total number shapes in the current shape
     */
    private void checkPopup(MouseEvent e) {
        // Do it only if we have a request for a pop up menu
        if (!e.isPopupTrigger()) {
            return;
        }

        // Display the information about the shape currently painted
        if (shape != null) {
            int count = shape.countShapes();
            popupLabel.setText("total number of shapes = " + count);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (shape != null) {
            shape.update(slider.getValue());
            repaint();
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Starts the application
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FractalDisplay());

    }
}