import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Evgeny on 01.03.2016.
 */
public class Image {
    private String imageExtension;
    private File imageFile;
    private BufferedImage bufferedImage;

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
        imageExtension = FilenameUtils.getExtension(imageFile.getAbsolutePath());
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public Image() {
    }

    public Image(String imageExtension, File imageFile, BufferedImage bufferedImage) {
        this.imageExtension = imageExtension;
        this.imageFile = imageFile;
        this.bufferedImage = bufferedImage;
    }
}
