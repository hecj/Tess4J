package test.image;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * tesseract for java， ocr（Optical Character Recognition，光学字符识别）
 * 工具类
 * @author wind
 */
public class Tess4jUtils {
    /**
     * 从图片中提取文字,默认设置英文字库,使用classpath目录下的训练库
     * @param path
     * @return
     */
    public static String readChar(String path){
        // JNA Interface Mapping
        ITesseract instance = new Tesseract();
        // JNA Direct Mapping
        // ITesseract instance = new Tesseract1();
        File imageFile = new File(path);
        //In case you don't have your own tessdata, let it also be extracted for you
        //这样就能使用classpath目录下的训练库了
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        //Set the tessdata path
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        //英文库识别数字比较准确
       // instance.setLanguage(Const.ENG);
        return getOCRText(instance, imageFile);
    }

    /**
     * 从图片中提取文字
     * @param path 图片路径
     * @param dataPath 训练库路径
     * @param language 语言字库
     * @return
     */
    public static String readChar(String path, String dataPath, String language){
        File imageFile = new File(path);
        ITesseract instance = new Tesseract();
        instance.setDatapath(dataPath);
        //英文库识别数字比较准确
        instance.setLanguage(language);
        return getOCRText(instance, imageFile);
    }

    /**
     * 识别图片文件中的文字
     * @param instance
     * @param imageFile
     * @return
     */
    private static String getOCRText(ITesseract instance, File imageFile){
        String result = null;
        try {
            result = instance.doOCR(imageFile);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
    	long time = System.currentTimeMillis();
//        String ch = "/Users/hecj/Desktop/car/quzao_1.jpeg";
        String ch = "/Users/hecj/Desktop/n7.png";
//        String ch = "/Users/hecj/Desktop/car/quzao_2.png";
//        String ch = "/Users/hecj/Desktop/car/quzao_3.jpeg";
//        String ch = "/Users/hecj/Desktop/car/quzao_4.jpeg";
//        String language = "DejaVuSansMonoBold";
        String language = "eng";
//        String language = "xylinkfont";
        String tessdata = "/Users/hecj/eclipse-workspace/Tess4J/tessdata";
        String res = readChar(ch, tessdata, language);
        System.out.println("解析结果:"+res);
        res = readChar(ch, tessdata, language);
        System.out.println("解析结果:"+res);
        res = readChar(ch, tessdata, language);
        System.out.println("解析结果:"+res);
        System.out.println("耗时:"+(System.currentTimeMillis()-time));
    }

}