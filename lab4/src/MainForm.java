import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Evgeny on 27.03.2016.
 */
public class MainForm extends JFrame {

    JPanel panel = new JPanel();
    JTextArea infoTextArea = new JTextArea();

    private JMenuBar initFileMenu(JPanel panel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemOpenImage = new JMenuItem("Open image");
        menuFile.add(menuItemOpenImage);
        menuBar.add(menuFile);

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png", "bmp", "tif");
        fileChooser.setFileFilter(filter);

        menuItemOpenImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(panel);
                File chosenFile = fileChooser.getSelectedFile();
                MetadataRetriever metadataRetriever = new MetadataRetriever(chosenFile);
                infoTextArea.setText(metadataRetriever.retrieveMetadata());
            }
        });
        return menuBar;
    }

    public MainForm() {
        super("Image Info");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        infoTextArea.setFont(new Font("Verdana", Font.PLAIN, 14));
        infoTextArea.setEditable(false);
        panel.setSize(this.getWidth(), 0);
        panel.setLayout(new GridLayout(0, 1));
        panel.setEnabled(false);
        panel.add(infoTextArea);
        JScrollPane scroll = new JScrollPane(infoTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
        this.setLayout(new GridLayout(0, 1));
        this.add(panel);
        this.setJMenuBar(initFileMenu(panel));
    }

    public static void main(String[] args) {
        MainForm app = new MainForm();
        app.setVisible(true);
    }

}
