package fr.smardine.podcaster.helper;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageHelper {

	// public static Bitmap DownloadImage(String URL)
	// {
	// Bitmap bitmap = null;
	// InputStream in = null;
	// try {
	// in = OpenHttpConnection(URL);
	// bitmap = BitmapFactory.decodeStream(in);
	// in.close();
	// } catch (IOException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// return bitmap;
	// }

	public static Bitmap decodeBitmapFromFile(File p_file, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(p_file.getAbsolutePath(), options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(p_file.getAbsolutePath(), options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

}
