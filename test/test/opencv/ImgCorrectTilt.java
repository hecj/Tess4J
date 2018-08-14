package test.opencv;

import java.io.File;
import java.io.IOException;
/**
 * 图片倾斜矫正
 * @author MuZB
 *
 */
public abstract class ImgCorrectTilt {
    static {
        File directory = new File("");//参数为空
        String courseFile;
        try {
            courseFile = directory.getCanonicalPath();
            System.load(courseFile + File.separator + "AI.dll");
        } catch (IOException e) {
            e.printStackTrace();
        }
//      System.load("D:" + File.separator + "AI.dll");
    }
    public native static int correct(String inFile,String outFile);
    public static void main(String[] args) {
        int i = correct("E:/2.jpg","E:/haha.jpg");
        System.out.println(i);
    }

}
