import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Evgeny on 01.05.2016.
 */
public class MainForm extends JFrame {

    ImagePanel panelImageBefore = new ImagePanel();
//    ImagePanel panelImageAfter = new ImagePanel();

    LinearImageConverter linearImageConverter = new LinearImageConverter();

    private JMenuBar initFileMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(0, 3));
        JButton menuOpenFile = new JButton("Open File");
        JButton menuAddConstant = new JButton("Add Constant");
        JButton menuMultiple = new JButton("Multiple by Constant");
        JButton menuDegree = new JButton("Degree");
        JButton menuLogarithm = new JButton("Logarithm");
        JButton menuNegative = new JButton("Negative");
        JButton menuContrast = new JButton("Linear Contrast");
        JButton menuImageChart = new JButton("Image Chart");
        menuBar.add(menuOpenFile);
        menuBar.add(menuAddConstant);
        menuBar.add(menuMultiple);
        menuBar.add(menuDegree);
        menuBar.add(menuLogarithm);
        menuBar.add(menuNegative);
        menuBar.add(menuContrast);
        menuBar.add(menuImageChart);

        menuOpenFile.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(panelImageBefore);
                File chosenFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(chosenFile);
                    panelImageBefore.setSize(image.getWidth() + 40, image.getHeight() + 70);
                    panelImageBefore.setImage(image);
                    linearImageConverter.setImage(image);
                    setSize(image.getWidth() + 40, image.getHeight() + 130);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        menuAddConstant.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValueDialog valueDialog = new ValueDialog(getParentFrame(), true);
                valueDialog.setSize(150, 150);
                valueDialog.setVisible(true);
                int constant = (int) valueDialog.getValue();
                BufferedImage updatedImage = linearImageConverter.add(constant);
                ImageDialog dialog = new ImageDialog(getParentFrame(), false, updatedImage);
                dialog.setSize(updatedImage.getWidth() + 40, updatedImage.getHeight() + 70);
                dialog.setVisible(true);
            }
        });

        menuMultiple.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValueDialog valueDialog = new ValueDialog(getParentFrame(), true);
                valueDialog.setSize(150, 150);
                valueDialog.setVisible(true);
                double constant = valueDialog.getValue();
                BufferedImage updatedImage = linearImageConverter.multiple(constant);
                ImageDialog dialog = new ImageDialog(getParentFrame(), false, updatedImage);
                dialog.setSize(updatedImage.getWidth() + 40, updatedImage.getHeight() + 70);
                dialog.setVisible(true);
            }
        });

        menuLogarithm.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage updatedImage = linearImageConverter.logarithm();
                ImageDialog dialog = new ImageDialog(getParentFrame(), false, updatedImage);
                dialog.setSize(updatedImage.getWidth() + 40, updatedImage.getHeight() + 70);
                dialog.setVisible(true);
            }
        });

        menuDegree.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValueDialog valueDialog = new ValueDialog(getParentFrame(), true);
                valueDialog.setSize(150, 150);
                valueDialog.setVisible(true);
                double constant = valueDialog.getValue();
                BufferedImage updatedImage = linearImageConverter.degree(constant);
                ImageDialog dialog = new ImageDialog(getParentFrame(), false, updatedImage);
                dialog.setSize(updatedImage.getWidth() + 40, updatedImage.getHeight() + 70);
                dialog.setVisible(true);
            }
        });

        menuNegative.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage updatedImage = linearImageConverter.makeNegative();
                ImageDialog dialog = new ImageDialog(getParentFrame(), false, updatedImage);
                dialog.setSize(updatedImage.getWidth() + 40, updatedImage.getHeight() + 70);
                dialog.setVisible(true);
            }
        });

        menuContrast.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage updatedImage = linearImageConverter.contrast();
                ImageDialog dialog = new ImageDialog(getParentFrame(), false, updatedImage);
                dialog.setSize(updatedImage.getWidth() + 40, updatedImage.getHeight() + 70);
                dialog.setVisible(true);
            }
        });

        menuImageChart.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartDialog dialog = new ChartDialog(getParentFrame(), false, linearImageConverter.getImage());
                dialog.setSize(500, 500);
                dialog.setVisible(true);
            }
        });

        return menuBar;
    }

    public MainForm() throws IOException {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panelImageBefore.setSize(this.getWidth(), 0);
        panelImageBefore.setLayout(new GridLayout(0, 1));

        this.setLayout(new GridLayout(1, 1));
        this.add(panelImageBefore);

        setSize(500, 500);

        this.setJMenuBar(initFileMenu());
    }

    public static void main(String[] args) {
        MainForm app = null;
        try {
            app = new MainForm();
        } catch (IOException e) {
            e.printStackTrace();
        }
        app.setVisible(true);
    }

}
