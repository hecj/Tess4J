package test.opencv;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
 /**
  * 图像反色
  * @author hecj
  */
public class InvertColor {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			Mat src=Imgcodecs.imread("/Users/hecj/Desktop/image/WechatIMG135.jpeg");
			Mat dst = src.clone();
			//复制矩阵进入dst
			Core.bitwise_not(src,dst);
			//使用的颜色反转
			Imgcodecs.imwrite("/Users/hecj/Desktop/image/WechatIMG1351.jpeg", dst);
		}catch(Exception e){
		}
	}
 
}