import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainForm {

    static Projector projectorX = new Projector(Projector.YZ_MODE);
    static Projector projectorY = new Projector(Projector.XZ_MODE);
    static Projector projectorZ = new Projector(Projector.XY_MODE);

    static final GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);

    static final GLCanvas glcanvasX = new GLCanvas(capabilities);
    static final GLCanvas glcanvasY = new GLCanvas(capabilities);
    static final GLCanvas glcanvasZ = new GLCanvas(capabilities);

    static JSlider xSlider = new JSlider(0, 360);
    static JSlider ySlider = new JSlider(0, 360);
    static JSlider zSlider = new JSlider(0, 360);

    public static void main(String[] args) {

        // TODO Auto-generated method stub

        glcanvasX.addGLEventListener(projectorX);
        glcanvasY.addGLEventListener(projectorY);
        glcanvasZ.addGLEventListener(projectorZ);
        glcanvasX.setSize(400, 400);
        glcanvasY.setSize(400, 400);
        glcanvasZ.setSize(400, 400);

        final JFrame frame = new JFrame ("Projections");
        frame.setLayout(new GridLayout(2, 2));

        JPanel rotatePanel = new JPanel();
        rotatePanel.add(xSlider);
        rotatePanel.add(ySlider);
        rotatePanel.add(zSlider);
        frame.add(rotatePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(glcanvasX);
        frame.add(glcanvasY);
        frame.add(glcanvasZ);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

        xSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redrawX();
            }
        });
        ySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redrawY();
            }
        });
        zSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redrawZ();
            }
        });
    }

    private static void redrawX() {
        projectorX.setXrot(xSlider.getValue());
        projectorY.setXrot(xSlider.getValue());
        projectorZ.setXrot(xSlider.getValue());
        glcanvasX.display();
        glcanvasY.display();
        glcanvasZ.display();
    }

    private static void redrawY() {
        projectorX.setYrot(ySlider.getValue());
        projectorY.setYrot(ySlider.getValue());
        projectorZ.setYrot(ySlider.getValue());
        glcanvasX.display();
        glcanvasY.display();
        glcanvasZ.display();
    }

    private static void redrawZ() {
        projectorX.setZrot(zSlider.getValue());
        projectorY.setZrot(zSlider.getValue());
        projectorZ.setZrot(zSlider.getValue());
        glcanvasX.display();
        glcanvasY.display();
        glcanvasZ.display();
    }

}