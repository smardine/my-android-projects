package fr.smardine.podcaster.helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.EnStatutTelechargement;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.thread.EnThreadExecResult;
import fr.smardine.podcaster.thread.hanlder.HandlerDownloadEpisodeProgressDialog;

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

	public static boolean DownloadImageEpisodeFromUrl(Context p_context, String p_url, MlFlux p_flux, MlEpisode p_episode) {
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
				p_episode.setVignetteTelechargee(fichierLocal);
				return true;
			} else {
				p_episode.setVignetteTelechargee(null);
				return false;
			}
		} catch (MalformedURLException e) {
			LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.mauvais_format_url, e);
			p_episode.setVignetteTelechargee(null);
			return false;
		}
	}

	public static boolean DownloadEpisodeFromUrl(Context p_context, HandlerDownloadEpisodeProgressDialog progressDialogHandler,
			String p_url, MlEpisode p_episode) {
		String TAG = "DownloadEpisodeFromUrl";
		String PATH = Environment.getExternalStorageDirectory() + "/Podcaster/Podcast/" + p_episode.getFluxParent().getTitre();
		Log.v("log_tag", "PATH: " + PATH);
		File directory = new File(PATH);
		directory.mkdirs();// creation de l'arborescence de repertoire.
		URL url;
		try {
			url = new URL(p_url);

			String urlFile = url.getFile();
			String extension = urlFile.substring(urlFile.lastIndexOf('.'));
			String nomDuFichierLocal = p_episode.getFluxParent().getTitre() + " "
					+ p_episode.getGuid().substring(p_episode.getGuid().lastIndexOf("/") + 1, p_episode.getGuid().lastIndexOf("."))
					+ extension;// on rajout le +1 pour ne pas
			// prendre le
			// dernier
			// "/"
			File fichierLocal = new File(directory, nomDuFichierLocal);

			if (DownloadFileWithProgress(progressDialogHandler, p_context, p_url, fichierLocal, false)) {
				p_episode.setStatutTelechargement(EnStatutTelechargement.Telecharge);
				p_episode.setMediaTelecharge(fichierLocal);
				return true;
			} else {
				p_episode.setStatutTelechargement(EnStatutTelechargement.Streaming);
				p_episode.setMediaTelecharge(null);
				return false;
			}
		} catch (MalformedURLException e) {
			LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.app_errGrave, e);
		}
		return false;
	}

	private static boolean DownloadFileWithProgress(HandlerDownloadEpisodeProgressDialog progressDialogHandler, Context p_context,
			String p_url, File p_localFile, boolean p_overrideIfExist) {
		String TAG = "DownloadHelper.DownloadFileWithProgress";
		int count;

		URL url;
		try {
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.INIT_DOWNLOAD.getCode(),
					"Connexion en cours"));
			url = new URL(p_url);
			URLConnection conection = url.openConnection();
			conection.connect();
			// getting file length
			int lenghtOfFile = conection.getContentLength();
			// input stream to read file - with 8k buffer
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
			// Output stream to write file
			OutputStream output = new FileOutputStream(p_localFile);
			byte data[] = new byte[1024];
			long total = 0;

			while ((count = input.read(data)) != -1) {
				total += count;

				progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.CHANGE_PROGRESSION.getCode(),
						(int) ((total * 100) / lenghtOfFile)));

				// writing data to file
				output.write(data, 0, count);
			}

			// flushing output
			output.flush();

			// closing streams
			output.close();
			input.close();
			return true;
		} catch (MalformedURLException e) {
			LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.erreur_de_protocole_au_telechargement_d_un_fichier, e);
			return false;
		} catch (IOException e) {
			LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.erreur_de_protocole_au_telechargement_d_un_fichier, e);
			return false;
		}
	}

	private static boolean DownloadFile(Context p_context, String p_url, File p_localFile, boolean p_overrideIfExist) {
		String TAG = "DowloadHelper.DowloadFile";
		// si le fichier existe mais que l'on demande a l'overrider
		// ou bien si le fichier n'existe pas.
		if ((p_overrideIfExist && p_localFile.exists()) || !p_localFile.exists()) {
			try {
				FileOutputStream fs = new FileOutputStream(p_localFile);
				DefaultHttpClient clientHttp = new DefaultHttpClient();
				HttpGet getter = new HttpGet(p_url);
				HttpResponse reponse = clientHttp.execute(getter);
				HttpEntity entity = reponse.getEntity();
				entity.writeTo(fs);

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
