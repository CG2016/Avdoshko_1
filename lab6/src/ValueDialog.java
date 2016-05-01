import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Created by Evgeny on 01.05.2016.
 */
public class ValueDialog extends JDialog {

    private double value;

    public double getValue() {
        return value;
    }

    public ValueDialog(Frame owner, boolean modal) {
        super(owner, modal);
        JPanel panel = new JPanel();
        JTextField valueTextField = new JTextField();
        JButton submit = new JButton("Submit");

        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLayout(new GridLayout(0, 1));

        submit.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    value = Double.parseDouble(valueTextField.getText());
                    dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
                } catch(Exception ex) {

                }
            }
        });
        panel.add(valueTextField);
        panel.add(submit);
        add(panel);
    }
}
