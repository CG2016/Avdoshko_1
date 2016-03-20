import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Evgeny on 01.03.2016.
 */
public class Form {
    private static JFrame frame;
    private JSlider sliderDistance;
    private JPanel mainPanel;
    private JPanel imageBeforePanel;
    private JPanel imageAfterPanel;

    private JFileChooser fileChooser;

    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuItemOpenImage;
    private JMenuItem menuItemSaveImage;
    private JMenu menuColor;
    private JMenuItem menuItemColorBefore;
    private JMenuItem menuItemColorAfter;
    private JMenu menuConverter;
    private JMenuItem convertMenuItem;

    JLabel leftImage = new JLabel();
    JLabel rightImage = new JLabel();

    private Image imageBefore = new Image();;
    private Image imageAfter = new Image();;

    private ColorFormat colorBefore = ColorFormat.colorRgb(255, 255, 255);
    private ColorFormat colorAfter = ColorFormat.colorRgb(255, 255, 255);

    public Form() {
        initFileChooser();
        initMenu();
    }

    private void initFileChooser() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png");
        fileChooser.setFileFilter(filter);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        initFileMenu();
        initColorMenu();
        initConvertMenu();
    }

    private void initConvertMenu() {
        menuConverter = new JMenu("Converter");
        convertMenuItem = new JMenuItem("Convert!");
        menuConverter.add(convertMenuItem);
        menuBar.add(menuConverter);
        convertMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageAfter.setBufferedImage(Converter.convert(
                        imageBefore.getBufferedImage(), colorBefore, colorAfter, sliderDistance.getValue()*0.52)
                );
                rightImage.setIcon(new ImageIcon(imageAfter.getBufferedImage().getScaledInstance(300, 300, java.awt.Image.SCALE_AREA_AVERAGING)));
            }
        });
    }

    private void initFileMenu() {
        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuItemOpenImage = new JMenuItem("Open image");
        menuItemSaveImage = new JMenuItem("Save image");
        menuFile.add(menuItemOpenImage);
        menuFile.add(menuItemSaveImage);
        imageBeforePanel.setLayout(new BorderLayout());
        imageAfterPanel.setLayout(new BorderLayout());
        imageBeforePanel.add(leftImage, BorderLayout.CENTER);
        imageAfterPanel.add(rightImage, BorderLayout.CENTER);
        menuItemOpenImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(mainPanel);
                File choosenFile = fileChooser.getSelectedFile();
                imageBefore.setImageFile(choosenFile);
                try {
                    imageBefore.setBufferedImage(ImageIO.read(choosenFile));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                leftImage.setIcon(new ImageIcon(imageBefore.getBufferedImage().getScaledInstance(300, 300, java.awt.Image.SCALE_AREA_AVERAGING)));
            }
        });
        menuItemSaveImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showSaveDialog(mainPanel);
                File updatedFile = fileChooser.getSelectedFile();
                imageAfter.setImageFile(updatedFile);
                try {
                    ImageIO.write(imageAfter.getBufferedImage(), imageAfter.getImageExtension(), imageAfter.getImageFile());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void initColorMenu() {
        menuColor = new JMenu("Color");
        menuBar.add(menuColor);
        menuItemColorBefore = new JMenuItem("Before");
        menuItemColorAfter = new JMenuItem("After");
        menuColor.add(menuItemColorBefore);
        menuColor.add(menuItemColorAfter);
        menuItemColorBefore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(frame, "Choose a background",
                        new Color(colorBefore.getRed(), colorBefore.getGreen(), colorBefore.getBlue()));
                colorBefore = ColorFormat.colorRgb(color.getRed(), color.getGreen(), color.getBlue());
            }
        });
        menuItemColorAfter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(frame, "Choose a background",
                        new Color(colorAfter.getRed(), colorAfter.getGreen(), colorAfter.getBlue()));
                colorAfter = ColorFormat.colorRgb(color.getRed(), color.getGreen(), color.getBlue());
            }
        });
    }

    public static void main(String[] args) {
        Form form = new Form();
        frame = new JFrame("Form");
        frame.setContentPane(form.mainPanel);
        frame.setJMenuBar(form.menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 450);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
