package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片灰度化处理
 * @author hecj
 */
public class ImageGrayscale {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String dir = "/Users/hecj/Desktop/test6";
		File dirFile = new File(dir);
		File[] files = dirFile.listFiles();
		for(File file : files) {
			BufferedImage bufferedImage = ImageIO.read(new FileInputStream(file));
			grayscale(bufferedImage,file.getAbsolutePath());
		}
	}
	
	public static void grayscale(BufferedImage img,String outPath) {
        int sWight = img.getWidth();
        int sHight = img.getHeight();
        BufferedImage newImage = new BufferedImage(sWight, sHight,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < sWight; x++) {
            for (int y = 0; y < sHight; y++) {
                int rgb= img.getRGB(x, y);
                newImage.setRGB(x, y, rgb);
            }
        }
         try {
        	int position = outPath.lastIndexOf(".");
        	String ext = outPath.substring(position+1, outPath.length());
            ImageIO.write(newImage, ext, new File(outPath));
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }

}
