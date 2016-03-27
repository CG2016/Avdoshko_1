import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Evgeny on 27.03.2016.
 */
public class MetadataRetriever {
    String imageName;
    ImageInfo imageInfo;

    public MetadataRetriever(File file) {
        imageName = file.getName();
        try {
            imageInfo = Sanselan.getImageInfo(file);
        } catch (Exception e) {
            // ignore
        }
    }

    public String retrieveMetadata() {
        if (imageInfo == null)
            throw new IllegalStateException("Image must be set before retrieving metadata");
        return String.format("Image name: %s\nInfo: %s", imageName, imageInfo.toString());
    }

}
