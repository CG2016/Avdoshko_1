package gui;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Evgeny on 24.04.2016.
 */
public abstract class StubMenuListener implements ActionListener {

    private JFrame parentFrame;

    public JFrame getParentFrame() {
        return parentFrame;
    }

    public StubMenuListener(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
