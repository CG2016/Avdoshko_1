import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Evgeny on 01.05.2016.
 */
public class ImageDialog extends JDialog {

    private BufferedImage image;
    ImagePanel imagePanel = new ImagePanel();

    public BufferedImage getImage() {
        return image;
    }

    private JMenuBar initFileMenu() {
        JMenuBar menuBar = new JMenuBar();
        JButton menuSaveFile = new JButton("Save File");
        menuBar.add(menuSaveFile);

        menuSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(imagePanel);
                File updatedFile = fileChooser.getSelectedFile();
                try {
                    ImageIO.write(image,
                            FilenameUtils.getExtension(updatedFile.getAbsolutePath()),
                            updatedFile
                    );
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        return menuBar;
    }

    public ImageDialog(JFrame owner, boolean modal, BufferedImage image) {
        super(owner, modal);
        this.image = image;
        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setImage(image);
        setJMenuBar(initFileMenu());
        add(imagePanel);
    }
}
