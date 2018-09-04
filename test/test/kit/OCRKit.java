package test.kit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import test.image.Tess4jUtils;

/**
 * 图像识别
 * 
 * @author hecj
 *
 */
public class OCRKit {
	static ImagePrepare imagePrepare = new ImagePrepare();

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread("/Users/hecj/Desktop/n4.jpeg");
		Mat formatMat = imagePrepare.formatMatSize(src);
		src.release();
		// 轮廓
		Mat outlineMat = ImagePrepare.imageOutline(formatMat);
		List<MatOfPoint> rectangleList = imagePrepare.findRegion(outlineMat);
		String text = ocrText(formatMat, rectangleList);
		System.out.println(text);

//		String text = ocr(formatMat);
//		System.out.println(text);
	}

	public static String ocrText(Mat formatMat, List<MatOfPoint> rectangleList) {
		Imgproc.drawContours(formatMat, rectangleList, -1, new Scalar(0, 0, 255));
		List<Mat> regionMatList = new ArrayList<Mat>();
		// 截取特征目标
		for (int i = 0; i < rectangleList.size(); i++) {
			MatOfPoint matOfPoint = rectangleList.get(i);
			List<Point> points = matOfPoint.toList();
			int x = (int) points.get(0).x;
			int y = (int) points.get(0).y - 5;
			int width = (int) (points.get(1).x - points.get(0).x) + 5;
			int height = (int) (points.get(2).y - points.get(0).y) + 10;
			Mat regionMat = new Mat(formatMat, new Rect(x, y, width, height));
			regionMatList.add(regionMat);
		}

		// 识别
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < regionMatList.size(); i++) {
			Mat mat = regionMatList.get(i);
			String text = ocr(mat);
			resultList.add(text);
		}
		String resultText = bestText(resultList.toArray(new String[] {}));
		return resultText;
	}

	public static String ocr(Mat src) {
		Mat gray = imagePrepare.gray(src);
		Mat threshold = imagePrepare.threshold(gray);
		// 中值腐蚀
//		Mat medianBlur = new Mat();
//	    Imgproc.medianBlur(threshold, medianBlur, 5);

		Tess4jUtils tess4jUtils = new Tess4jUtils();
		String fileName = "/Users/hecj/Desktop/1.jpeg";
		Imgcodecs.imwrite(fileName, threshold);
		String language = "eng";
//		String language = "xylinkfont";
		String tessdata = "/Users/hecj/eclipse-workspace/Tess4J/tessdata";
		String text = tess4jUtils.readChar(fileName, tessdata, language);
		String[] textList = text.split("[\n]");
		String resultText = bestText(textList);
		return resultText;
	}
	
	/**
	 * 最优号码
	 * @param textList
	 * @return
	 */
	public static String bestText(String[] textList) {
		int maxLen = 0;
		String resultText = "";
		for (String str : textList) {
			str = str.trim();
			if(isChars(str)) {
				if(str.length()>maxLen) {
					maxLen = str.length();
					resultText = str;
				}
			}
		}
		return resultText;
	}
	

	public static boolean isChars(String content) {
		String pattern = "[a-zA-Z]{0,2}[0-9]+";
		return Pattern.matches(pattern, content);
	}
	
}
