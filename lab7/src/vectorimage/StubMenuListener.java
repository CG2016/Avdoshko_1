package vectorimage;

import javax.swing.*;
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
