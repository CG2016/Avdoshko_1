package gui;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Evgeny on 24.04.2016.
 */
public class StubActionListener implements ActionListener {
    JDialog dialog;

    public StubActionListener(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
