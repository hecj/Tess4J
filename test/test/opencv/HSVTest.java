package test.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class HSVTest {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img = Imgcodecs.imread("/Users/hecj/Desktop/image/car.jpeg");
		Mat imgHSV = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
//		Mat img2 = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
		// 转成HSV空间
		Imgproc.cvtColor(img, imgHSV, Imgproc.COLOR_BGR2HSV);
		// 转回BGR空间
//		Imgproc.cvtColor(imgHSV, img2, Imgproc.COLOR_HSV2BGR);
		
		Imgcodecs.imwrite("/Users/hecj/Desktop/image/car2.jpeg", imgHSV);
	}
}
