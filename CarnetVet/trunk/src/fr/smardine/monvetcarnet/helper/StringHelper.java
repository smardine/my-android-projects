/**
 * 
 */
package fr.smardine.monvetcarnet.helper;

/**
 * @author sims
 */
public final class StringHelper {

	/**
	 * Determine si une chaine est null ou egal à ""
	 * @param p_string
	 * @return
	 */
	public static boolean IsNullOrEmpty(String p_string) {
		return p_string == null || p_string.length() == 0;
	}

}
