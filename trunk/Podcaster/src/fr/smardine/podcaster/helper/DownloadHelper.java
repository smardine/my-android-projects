package fr.smardine.podcaster.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlFlux;

public class DownloadHelper {

	public static boolean DownloadImageFluxFromUrl(Context p_context, String p_url, MlFlux p_flux) {
		String TAG = "DownloadHelper.DownloadImageFluxFromUrl";
		URL url;
		try {
			url = new URL(p_url);

			String PATH = Environment.getExternalStorageDirectory() + "/Podcaster/Podcast/" + p_flux.getTitre();
			Log.v("log_tag", "PATH: " + PATH);
			File directory = new File(PATH);
			if (directory.mkdirs()) {

			}

			String urlFile = url.getFile();
			String nomDuFichierLocal = urlFile.substring(urlFile.lastIndexOf("/"));
			File fichierLocal = new File(directory, nomDuFichierLocal);

			if (DownloadFile(p_context, p_url, fichierLocal, false)) {
				p_flux.setVignetteTelechargee(fichierLocal);
				return true;
			} else {
				p_flux.setVignetteTelechargee(null);
				return false;
			}
		} catch (MalformedURLException e) {
			LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.mauvais_format_url, e);
			p_flux.setVignetteTelechargee(null);
			return false;
		}
	}

	private static boolean DownloadFile(Context p_context, String p_url, File p_localFile, boolean p_overrideIfExist) {
		String TAG = "DowloadHelper.DowloadFile";
		// si le fichier existe mais que l'on demande a l'overrider
		// ou bien si le fichier n'existe pas.
		if ((p_overrideIfExist && p_localFile.exists()) || !p_localFile.exists()) {
			try {
				new DefaultHttpClient().execute(new HttpGet(p_url)).getEntity().writeTo(new FileOutputStream(p_localFile));
			} catch (ClientProtocolException e) {
				LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.erreur_de_protocole_au_telechargement_d_un_fichier, e);
				return false;
			} catch (FileNotFoundException e) {
				LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.fichier_non_trouve, e);

				return false;

			} catch (IOException e) {
				LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.IO_exception, e);
				return false;

			}
		}
		return true;
	}
}
