package fr.smardine.podcaster.helper;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Cette classe gere la mise en cache d'un Bitmap afin d'eviter les ralentissements lors du scroll d'une view
 * @author s.mardine
 */
public class BitmapCache extends LruCache<String, Bitmap> {

	public BitmapCache(int maxSize) {
		super(maxSize);
	}

	@Override
	protected int sizeOf(String key, Bitmap bitmap) {
		// The cache size will be measured in bytes rather than number of items.
		return bitmap.getByteCount();
	}

	/**
	 * Ajouter un bitmap au cache, identifié par sa clé On utilisera ici le chemin du fichier comme clé Si le bitmap n'est pas déjà en
	 * cache, il sera ajouté.
	 * @param key
	 * @param bitmap
	 */
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			this.put(key, bitmap);
		}
	}

	/**
	 * Obtenir le bitmap correspondant a la clé (le chemin du fichier sert de clé)
	 * @param key
	 * @return Le bitmap issu du cache
	 */
	public Bitmap getBitmapFromMemCache(String key) {
		return this.get(key);
	}

}
