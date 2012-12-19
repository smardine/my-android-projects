package fr.smardine.podcaster.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Environment;
import android.util.Log;
import fr.smardine.podcaster.mdl.MlFlux;

public class DownloadHelper {

	public static void DownloadFromUrl(String p_url, MlFlux p_flux) {

		try {
			URL url = new URL(p_url);
			String PATH = Environment.getExternalStorageDirectory()
					+ "/Podcaster/Podcast/" + p_flux.getTitre();
			Log.v("log_tag", "PATH: " + PATH);
			File directory = new File(PATH);
			if (directory.mkdirs()) {

			}

			String urlFile = url.getFile();

			new DefaultHttpClient()
					.execute(new HttpGet(p_url))
					.getEntity()
					.writeTo(
							new FileOutputStream(new File(directory, urlFile
									.substring(urlFile.lastIndexOf("/")))));
			p_flux.setVignetteTelechargee(true);
			p_flux.setVignetteTelechargee(new File(directory, urlFile
					.substring(urlFile.lastIndexOf("/"))));

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.d("log_tag", "Error: " + e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.d("log_tag", "Error: " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("log_tag", "Error: " + e);
		}
		// URL url = new URL(p_url);
		// HttpURLConnection c = (HttpURLConnection) url.openConnection();
		// c.setRequestMethod("GET");
		// c.setDoOutput(true);
		// c.connect();
		//
		// String PATH = Environment.getExternalStorageDirectory()
		// + "/Podcaster/Podcast/" + p_flux.getTitre();
		// Log.v("log_tag", "PATH: " + PATH);
		// File file = new File(PATH);
		// if (file.mkdirs()) {
		//
		// }
		// String urlFile = url.getFile();
		// File outputFile = new File(file, urlFile.substring(urlFile
		// .lastIndexOf("/")));
		// FileOutputStream fos = new FileOutputStream(outputFile);
		//
		// InputStream is = c.getInputStream();
		//
		// byte[] buffer = new byte[1024];
		// int len1 = 0;
		// while ((len1 = is.read(buffer)) != -1) {
		// fos.write(buffer, 0, len1);
		// }
		// fos.close();
		// is.close();
		// } catch (IOException e) {
		// Log.d("log_tag", "Error: " + e);
		// }
	}

}
