import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Evgeny on 01.05.2016.
 */
public class StubActionListener implements ActionListener {
    private JFrame parentFrame;
    public JDialog dialog;

    public JFrame getParentFrame() {
        return parentFrame;
    }

    public StubActionListener(JDialog dialog) {
        this.dialog = dialog;
    }

    public StubActionListener(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
