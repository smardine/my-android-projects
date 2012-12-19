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
			String nomDuFichierLocal = urlFile.substring(urlFile
					.lastIndexOf("/"));
			File fichierLocal = new File(directory, nomDuFichierLocal);
			if (!fichierLocal.exists()) {
				new DefaultHttpClient().execute(new HttpGet(p_url)).getEntity()
						.writeTo(new FileOutputStream(fichierLocal));
			}
			p_flux.setVignetteTelechargee(true);
			p_flux.setVignetteTelechargee(fichierLocal);

		} catch (ClientProtocolException e) {
			p_flux.setVignetteTelechargee(false);
			p_flux.setVignetteTelechargee(null);
			Log.d("log_tag", "Error: " + e);
		} catch (FileNotFoundException e) {
			p_flux.setVignetteTelechargee(false);
			p_flux.setVignetteTelechargee(null);
			Log.d("log_tag", "Error: " + e);
		} catch (IOException e) {
			p_flux.setVignetteTelechargee(false);
			p_flux.setVignetteTelechargee(null);
			Log.d("log_tag", "Error: " + e);
		}
	}
}
