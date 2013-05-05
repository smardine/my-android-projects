/**
 * 
 */
package fr.smardine.monvetcarnet.mdl;

import fr.smardine.monvetcarnet.R;

/**
 * @author sims
 */
public enum EnPictoMaladie {

	Oreille(R.drawable.ic_vaccins_48px), //
	Yeux(R.drawable.ic_vermifuge_48px), //
	Nez(R.drawable.ic_launcher), //
	Patte(R.drawable.ic_photo_animal);

	private final int ressourcePicto;

	EnPictoMaladie(int ressourcePicto) {
		this.ressourcePicto = ressourcePicto;
	}

	public static EnPictoMaladie pictoMaladieParNom(String nom) {
		for (EnPictoMaladie unPicto : EnPictoMaladie.values()) {
			if (unPicto.name() == nom) {
				return unPicto;
			}
		}
		return null;
	}

	/**
	 * @return the ressourcePicto
	 */
	public int getRessourcePicto() {
		return ressourcePicto;
	}

}
