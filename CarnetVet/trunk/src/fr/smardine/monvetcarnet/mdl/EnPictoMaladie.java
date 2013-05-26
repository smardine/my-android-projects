/**
 * 
 */
package fr.smardine.monvetcarnet.mdl;

import fr.smardine.monvetcarnet.R;

/**
 * @author sims
 */
public enum EnPictoMaladie {

	Oreille(R.drawable.ic_vaccins_48px, 0), //
	Yeux(R.drawable.ic_vermifuge_48px, 1), //
	Nez(R.drawable.ic_launcher, 2), //
	Patte(R.drawable.ic_photo_animal, 3);

	private final int ressourcePicto;
	private final int position;

	EnPictoMaladie(int ressourcePicto, int position) {
		this.ressourcePicto = ressourcePicto;
		this.position = position;
	}

	public static EnPictoMaladie pictoMaladieParNom(String nom) {
		for (EnPictoMaladie unPicto : EnPictoMaladie.values()) {
			if (unPicto.name() == nom) {
				return unPicto;
			}
		}
		return null;
	}

	public static EnPictoMaladie pictoMaladieParRessource(int ressource) {
		for (EnPictoMaladie unPicto : EnPictoMaladie.values()) {
			if (unPicto.ressourcePicto == ressource) {
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

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

}
