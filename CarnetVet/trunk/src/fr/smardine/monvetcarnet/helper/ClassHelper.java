package fr.smardine.monvetcarnet.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.helper.log.LogCatBuilder;

/**
 * Classe utilitaire
 * @author sims
 */
public final class ClassHelper {

	private static String Tag = ClassHelper.class.getName();

	/**
	 * Obtenir un objet typé a partir du nom d'une classe
	 * @param p_className
	 * @return
	 */
	public static Object createClassFromName(String p_className, Context p_context) {
		Class<?> clazz = null;
		Object object = null;
		try {
			clazz = Class.forName(p_className);
			Constructor<?> ctor = clazz.getConstructor();
			object = ctor.newInstance();
		} catch (NoSuchMethodException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.methode_inconnue, e);
		} catch (IllegalArgumentException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.argument_incorrect, e);
		} catch (InstantiationException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.instanciation_echouee, e);
		} catch (IllegalAccessException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.acces_illegal, e);
		} catch (InvocationTargetException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.cible_incorrecte, e);
		} catch (ClassNotFoundException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.class_inconnue, e);
		}

		return object;
	}

	/**
	 * Obtenir un objet typé a partir de son .class Attention, pour que cela fonctionne, la classe en question doit avoir un constructeur
	 * public sans parametre
	 * @param p_class
	 * @return
	 */
	public static Object createInstanceClassFromClass(Class<?> p_class, Context p_context) {

		Object object = null;
		try {
			Constructor<?> ctor = p_class.getConstructor();
			object = ctor.newInstance();
		} catch (NoSuchMethodException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.methode_inconnue, e);
		} catch (IllegalArgumentException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.argument_incorrect, e);
		} catch (InstantiationException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.instanciation_echouee, e);
		} catch (IllegalAccessException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.acces_illegal, e);
		} catch (InvocationTargetException e) {
			LogCatBuilder.WriteErrorToLog(p_context, Tag, R.string.cible_incorrecte, e);
		}

		return object;
	}
}
