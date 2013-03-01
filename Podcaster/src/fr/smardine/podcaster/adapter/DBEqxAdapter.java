package fr.smardine.podcaster.adapter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.log.EnNiveauLog;
import fr.smardine.podcaster.helper.log.LogCatBuilder;

/*
 * OBJET METIER d'accès aux données de la base SQLite - instancier le moteur SQLite - ouvrir la base de données - fermer les ressources -
 * enregistrer et lire les données
 */
public class DBEqxAdapter {
	private final String TAG = this.getClass().getSimpleName();

	public static final String T_PLANTRA = "plantra";
	public static final String T_PRATICIEN = "praticien";
	public static final String T_TOURNEE = "tournee";
	public static final String T_VISITE = "visite";
	public static final String T_ORDO = "ordo";
	public static final String T_MEDECIN = "medecin";
	public static final String T_BENEF = "benef";
	public static final String T_BNFCAISSE = "bnfcaisse"; // extension de la
															// table benef :
															// assuré et caisse
	public static final String T_BNFMUTUELLE = "bnfmutuelle"; // extension de la
																// table benef :
																// complémentaire
	public static final String T_BNFLASTVISITE = "bnflastvisite";
	public static final String T_VSTALERTE = "vstalerte"; // alerte associée à
															// une visite
	public static final String T_FACTURE = "facture"; // factures PDT chargées
														// dans TLA destinées à
														// un bénéficaire donné

	public static final String C_AS_PLT_ID = "plt_id";
	public static final String C_PLT_ID = "_id";
	public static final String C_PLT_VERSION = "plt_version";
	public static final String C_PLT_LICENCE = "plt_licence";
	public static final String C_PLT_VEREQX = "plt_vereqx";
	public static final String C_PLT_DATEEQX = "plt_dateeqx";
	public static final String C_PLT_DU = "plt_du";
	public static final String C_PLT_AU = "plt_au";

	public static final String C_AS_PRT_ID = "prt_id";
	public static final String C_PRT_ID = "_id";
	public static final String C_PRT_CLEVENUS = "prt_clevenus";
	public static final String C_PRT_FINESS = "prt_finess";
	public static final String C_PRT_COLOR = "prt_color";
	public static final String C_PRT_LIBELLE = "prt_libelle";
	public static final String C_PRT_PRIORITY = "prt_priority";
	public static final int C_PRT_PRIORITY_MAX = 99; // lorsque ce praticien est
														// le propiétaire du
														// mobile
	public static final int C_PRT_NON_ATTRIB = Integer.MAX_VALUE;
	public static final int C_PRT_TOUS = Integer.MAX_VALUE - 1;

	public static final String C_AS_TRN_ID = "trn_id";
	public static final String C_TRN_ID = "_id";
	public static final String C_TRN_ID_PLT_ID = "trn_id_plt_id";
	public static final String C_TRN_ID_PRT_ID = "trn_id_prt_id";
	public static final String C_TRN_SHORTDATE = "trn_shortdate";
	public static final String C_TRN_LONGDATE = "trn_longdate";
	public static final String C_TRN_DATE_ISO8601 = "trn_dateiso8601";
	public static final String C_TRN_IS_TERMINE = "trn_istermine";
	public static final String C_TRN_NOTE = "trn_note";
	// public static final int C_TRN_AFAIRE = 0;
	// public static final int C_TRN_FINIE = 1;
	// public static final int C_TRN_AFAIRE_FINIE = Integer.MAX_VALUE;
	// private static final String C_TRN_UPD_TOURNEE_ISTERMINE =
	// "upd_tournee_istermine";

	public static final String C_AS_VST_ID = "vst_id";
	public static final String C_VST_ID = "_id";
	public static final String C_VST_ID_TRN_ID = "vst_id_trn_id";
	public static final String C_VST_ID_PRT_ID = "vst_id_prt_id";
	public static final String C_VST_ID_BNF_ID = "vst_id_bnf_id";
	public static final String C_VST_CLEBENEF = "vst_clebenef";
	public static final String C_VST_LIEU = "vst_lieu";
	public static final String C_VST_HORAIRE = "vst_horaire";
	public static final String C_VST_HORAIRE_ISO8601 = "vst_horaireiso8601";
	public static final String C_VST_SECTEUR = "vst_secteur";
	public static final String C_VST_GARDE = "vst_garde";
	public static final String C_VST_IS_TERMINE = "vst_istermine";
	public static final int C_VST_AFAIRE = 0;
	public static final int C_VST_FINIE = 1;
	public static final String C_VST_INDIC_GARDE = "G";
	public static final String C_VST_INDIC_CAB = "CAB";
	public static final String C_VST_INDIC_DOM = "DOM";

	public static final String C_AS_VSTALT_ID = "vstalt_id";
	public static final String C_VSTALT_ID = "_id"; // sert aussi d'identifiant
													// de notification
	public static final String C_VSTALT_ID_VST_ID = "vstalt_id_vst_id";
	public static final String C_VSTALT_DATETIME_ISO8601 = "vstalt_datetime_iso8601";
	public static final String C_VSTALT_LIBELLE = "vstalt_libelle";

	public static final String C_AS_ORD_ID = "ord_id";
	public static final String C_ORD_ID = "_id";
	public static final String C_ORD_ID_VST_ID = "ord_id_vst_id";
	public static final String C_ORD_ID_TRN_ID = "ord_id_trn_id";
	public static final String C_ORD_ID_PRT_ID = "ord_id_prt_id";
	public static final String C_ORD_ID_BNF_ID = "ord_id_bnf_id";
	public static final String C_ORD_ID_MED_ID = "ord_id_med_id";
	public static final String C_ORD_NFG = "ord_nfg";
	public static final String C_ORD_NUM = "ord_num";
	public static final String C_ORD_DATE = "ord_date";
	public static final String C_ORD_DATE_ISO8601 = "ord_dateiso8601";
	public static final String C_ORD_FINTRT = "ord_fintrt";
	public static final String C_ORD_NBRE_ACTES = "ord_nbreactes";
	public static final String C_ORD_TYPE_SOIN = "ord_typesoin";
	public static final String C_ORD_DATE_SUIVANTE = "ord_datesvte";
	public static final String C_ORD_COMMENT_TRT = "ord_commentrt";
	public static final String C_ORD_COMMENT_VISITE = "ord_commentVst";
	public static final String C_ORD_INDIC_FINTRT = "//";

	public static final String C_AS_BNF_ID = "bnf_id";
	public static final String C_BNF_ID = "_id";
	public static final String C_BNF_SECTEUR = "bnf_secteur";
	public static final String C_BNF_CLEBENEF = "bnf_clebenef";
	public static final String C_BNF_LIBELLE = "bnf_libelle";
	public static final String C_BNF_NOM = "bnf_nom";
	public static final String C_BNF_PRENOM = "bnf_prenom";
	public static final String C_BNF_CIVILITE = "bnf_civilite";
	public static final String C_BNF_TEL1 = "bnf_tel1";
	public static final String C_BNF_TEL2 = "bnf_tel2";
	public static final String C_BNF_TEL3 = "bnf_tel3";
	public static final String C_BNF_ADRESSE = "bnf_adresse";
	public static final String C_BNF_CPOSTAL = "bnf_cpostal";
	public static final String C_BNF_COMMUNE = "bnf_commune";
	public static final String C_BNF_MAP = "bnf_map";
	public static final String C_BNF_ADRESSE2 = "bnf_adresse2";
	public static final String C_BNF_CPOSTAL2 = "bnf_cpostal2";
	public static final String C_BNF_COMMUNE2 = "bnf_commune2";
	public static final String C_BNF_DATENAIS = "bnf_datenais";
	public static final String C_BNF_AGE = "bnf_age";
	public static final String C_BNF_QUALITE = "bnf_qualite";
	public static final String C_BNF_DATE_LECT_CV = "bnf_datelectcv";
	public static final String C_BNF_DATE_LECT_CV_ISO8601 = "bnf_datelectcviso8601";
	public static final String C_BNF_COMMENT = "bnf_comment";
	public static final String C_BNF_NOTE = "bnf_note";

	public static final String C_AS_BNFCAI_ID = "bnfcai_id";
	public static final String C_BNFCAI_ID = "_id";
	public static final String C_BNFCAI_ID_BNF_ID = "bnfcai_id_bnf_id";
	public static final String C_BNFCAI_ASS_NOM = "bnfcai_assnom";
	public static final String C_BNFCAI_ASS_PNOM = "bnfcai_asspnom";
	public static final String C_BNFCAI_ASS_INSEE = "bnfcai_assinsee";
	public static final String C_BNFCAI_GRDRG = "bnfcai_grdrg";
	public static final String C_BNFCAI_CAIGEST = "bnfcai_caigest";
	public static final String C_BNFCAI_CENGEST = "bnfcai_cengest";
	public static final String C_BNFCAI_LIBELLE = "bnfcai_libelle";
	public static final String C_BNFCAI_TEL = "bnfcai_tel";
	public static final String C_BNFCAI_ADR1 = "bnfcai_adr1";
	public static final String C_BNFCAI_ADR2 = "bnfcai_adr2";
	public static final String C_BNFCAI_CPOSTAL = "bnfcai_cpostal";
	public static final String C_BNFCAI_COMMUNE = "bnfcai_commune";
	public static final String C_BNFCAI_EMAIL = "bnfcai_email";
	public static final String C_BNFCAI_REF_GIE = "bnfcai_refgie";
	public static final String C_BNFCAI_JUSTIF_AMO = "bnfcai_justifamo";
	public static final String C_BNFCAI_JUSTIF_AMO_VITAL = "4";
	private static final String C_DLT_BNFCAISSE_BNF_ID = "dlt_bnfcaisse_bnf_id";

	public static final String C_AS_BNFMUT_ID = "bnfmut_id";
	public static final String C_BNFMUT_ID = "_id";
	public static final String C_BNFMUT_ID_BNF_ID = "bnfmut_id_bnf_id";
	public static final String C_BNFMUT_IDENT = "bnfmut_ident";
	public static final String C_BNFMUT_LIBELLE = "bnfmut_libelle";
	public static final String C_BNFMUT_CATEGORIE = "bnfmut_categorie";
	public static final String C_BNFMUT_GEST_UNIQUE = "bnfmut_gestunique";
	public static final String C_BNFMUT_JUSTIF_AMC = "bnfmut_justifamc";
	public static final String C_BNFMUT_DATE_DROITS_DEB = "bnfmut_datedroitsdeb";
	public static final String C_BNFMUT_DATE_DROITS_FIN = "bnfmut_datedroitsfin";
	public static final String C_BNFMUT_TEL = "bnfmut_tel";
	public static final String C_BNFMUT_ADR = "bnfmut_adr";
	public static final String C_BNFMUT_CPOSTAL = "bnfmut_cpostal";
	public static final String C_BNFMUT_COMMUNE = "bnfmut_commune";
	public static final String C_BNFMUT_JUSTIF_AMC_VITAL = "4";
	private static final String C_DLT_BNFMUTUELLE_BNF_ID = "dlt_bnfmutuelle_bnf_id";

	public static final String C_AS_BNFVST_ID = "bnfvst_id";
	public static final String C_BNFVST_ID = "_id";
	public static final String C_BNFVST_ID_BNF_ID = "bnfvst_id_bnf_id";
	public static final String C_BNFVST_ID_VST_ID = "bnfvst_id_vst_id";
	public static final String C_BNFVST_ID_PRT_ID = "bnfvst_id_prt_id";
	public static final String C_BNFVST_DERVSTDATE = "bnfvst_dervstdate";
	public static final String C_BNFVST_DERVSTPRAT = "bnfvst_dervstprat";
	private static final String C_DLT_BNFLASTVISITE_BNF_ID = "dlt_bnflastvisite_bnf_id";

	public static final String C_AS_FAC_ID = "fac_id";
	public static final String C_FAC_ID = "_id";
	public static final String C_FAC_ID_BNF_ID = "fac_id_bnf_id";
	public static final String C_FAC_TLA = "fac_tla";
	public static final String C_FAC_MNT_FACTURE = "fac_mntfacture";
	public static final String C_FAC_MNT_ASSURE = "fac_mntassure";
	public static final String C_FAC_NO_FACTURE = "fac_nofacture";
	public static final String C_FAC_ABRV_FACTURANT = "fac_abrvfacturant";
	public static final String C_FAC_MNT_FACTURE_I = "fac_mntfacture_i";
	public static final String C_FAC_MNT_ASSURE_I = "fac_mntassure_i";

	public static final String C_AS_MED_ID = "med_id";
	public static final String C_MED_ID = "_id";
	public static final String C_MED_NOM = "med_nom";
	public static final String C_MED_FINESS = "med_finess";
	public static final String C_MED_TEL1 = "med_tel1";
	public static final String C_MED_TEL2 = "med_tel2";
	public static final String C_MED_EMAIL = "med_email";
	public static final String C_MED_ADR = "med_adr";
	public static final String C_MED_CODEP = "med_codep";
	public static final String C_MED_COMMUNE = "med_commune";

	private static final Pattern EQX_DATE_PATTERN = Pattern.compile("(\\d\\d?)/(\\d\\d?)/(\\d\\d\\d\\d)");
	private static final Pattern EQX_HEURE_PATTERN = Pattern.compile("(\\d\\d?)h(\\d\\d?)");
	private static final Pattern EQX_MONTANTFAC_PATTERN = Pattern.compile("Total : (\\d\\d?\\d?\\d?),(\\d\\d?)");
	private static final Pattern EQX_MONTANTASS_PATTERN = Pattern.compile("Part Assuré : (\\d\\d?\\d?\\d?),(\\d\\d?)");

	// private static final String UPDATE_VSTID_TRNID = null;
	private final DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	private final Context context;
	private final String BASE_EQUINOX;

	public DBEqxAdapter(Context p_context) {
		// instancier la base de données sur un Helper qui s'occupe de créer et
		// mettre à jour la base de données
		context = p_context;
		BASE_EQUINOX = getDatabaseName(context);
		DBHelper = new DatabaseHelper(p_context);

	}

	private String getDatabaseName(Context p_context) {
		return p_context.getString(R.string.app_name);

	}

	public String getPath() {
		return db.getPath();
	}

	/*
	 * cette classe crée la base de données + mise à jour des données {@link http://www.sqlite.org/foreignkeys.html}
	 */
	public class DatabaseHelper extends SQLiteOpenHelper {
		private static final String CREA_T_PLANTRA = "CREATE TABLE " + T_PLANTRA + " (" + C_PLT_ID + " integer primary key autoincrement, "
				+ C_PLT_VERSION + " texte non null, " // "version"
				+ C_PLT_LICENCE + " texte non null, " // "NumLicence"
				+ C_PLT_VEREQX + " texte non null, " // "NumVersionLogiciel"
				+ C_PLT_DATEEQX + " texte non null, " // "DateRefPrint"
				+ C_PLT_DU + " texte non null, " // "FirstDatePrint"
				+ C_PLT_AU + " texte non null " // "LastDatePrint"
				+ ");";
		private static final String CREA_T_PRATICIEN = "CREATE TABLE " + T_PRATICIEN + " (" + C_PRT_ID
				+ " integer primary key autoincrement, " + C_PRT_CLEVENUS + " texte non null, " // "clevenus"
				+ C_PRT_FINESS + " texte non null, " // "finess"
				+ C_PRT_COLOR + " texte non null, " // "color"
				+ C_PRT_LIBELLE + " texte non null, " // "nomprat" nom prénom
				+ C_PRT_PRIORITY + " integer  " // 0 non définie 99:prioritaire
				+ ");";
		private static final String CREA_T_TOURNEE = "CREATE TABLE " + T_TOURNEE + " (" + C_TRN_ID + " integer primary key autoincrement, "
				+ C_TRN_ID_PLT_ID
				+ " integer non null, " // foreign key sur plantra
				+ C_TRN_ID_PRT_ID
				+ " integer, " // foreign key sur praticien. null=la tournée
								// concerne des visites non attribuées
				+ C_TRN_SHORTDATE
				+ " texte non null, " // "ShortDatePrint" JJ/MM/AAAA
				+ C_TRN_LONGDATE
				+ " texte non null, " // "DatePrint"
				+ C_TRN_DATE_ISO8601
				+ " texte non null, "// "ShortDatePrint" YYYY-MM-DD
				+ C_TRN_IS_TERMINE
				+ " integer, " // en sqlite boolean n'existe pas
				+ C_TRN_NOTE + " texte, " + "FOREIGN KEY(" + C_TRN_ID_PLT_ID + ") REFERENCES " + T_PLANTRA + "(" + C_PLT_ID + "), "
				+ "FOREIGN KEY(" + C_TRN_ID_PRT_ID + ") REFERENCES " + T_PRATICIEN + "(" + C_PRT_ID + ")  " + ");";
		private static final String CREA_T_VISITE = "CREATE TABLE " + T_VISITE
				+ " ("
				+ C_VST_ID
				+ " integer primary key autoincrement, "
				+ C_VST_ID_TRN_ID
				+ " integer non null, "// foreign key sur tournee.
				+ C_VST_ID_PRT_ID
				+ " integer, " // foreign key sur praticien. null=la visite
								// n'est pas attribuée à un prat
				+ C_VST_ID_BNF_ID
				+ " integer non null, " // foreign key sur benef.
				+ C_VST_CLEBENEF
				+ " texte non null, " // "CleBenefPrint"
				+ C_VST_LIEU
				+ " texte non null, " // "lieu"
				+ C_VST_HORAIRE
				+ " texte, " // "HorairePrint"
				+ C_VST_HORAIRE_ISO8601
				+ " texte, " // "HorairePrint" 'YYYY-MM-DD HH:MM:SS:SSS'
				+ C_VST_SECTEUR
				+ " texte, " // "LblSecteurPrint"
				+ C_VST_GARDE
				+ " texte, " // "NFGPrint" uniquement si contient 'G'
				+ C_VST_IS_TERMINE
				+ " integer, " // en sqlite boolean n'existe pas
				+ "FOREIGN KEY(" + C_VST_ID_TRN_ID + ") REFERENCES " + T_TOURNEE + "(" + C_TRN_ID + "), " + "FOREIGN KEY("
				+ C_VST_ID_PRT_ID + ") REFERENCES " + T_PRATICIEN + "(" + C_PRT_ID + "), " + "FOREIGN KEY(" + C_VST_ID_BNF_ID
				+ ") REFERENCES " + T_BENEF + "(" + C_BNF_ID + ")  " + ");";
		private static final String CREA_T_VSTALERTE = "CREATE TABLE " + T_VSTALERTE + " (" + C_VSTALT_ID
				+ " integer primary key autoincrement, " + C_VSTALT_ID_VST_ID
				+ " integer non null, " // foreign key sur visite.
				+ C_VSTALT_DATETIME_ISO8601 + " texte, " + C_VSTALT_LIBELLE + " texte, " + "FOREIGN KEY(" + C_VSTALT_ID_VST_ID
				+ ") REFERENCES " + T_VISITE + "(" + C_VST_ID + ") " + ");";

		private static final String CREA_T_ORDO = "CREATE TABLE "
				+ T_ORDO
				+ " ("
				+ C_ORD_ID
				+ " integer primary key autoincrement, "
				+ C_ORD_ID_VST_ID
				+ " integer non null, " // foreign key sur visite.
				+ C_ORD_ID_TRN_ID
				+ " integer non null, " // foreign key sur tournee.
				+ C_ORD_ID_PRT_ID
				+ " integer, " // foreign key sur praticien. null=le soin n'est
								// pas attribué à un prat
				+ C_ORD_ID_BNF_ID
				+ " integer non null, " // foreign key sur benef.
				+ C_ORD_ID_MED_ID
				+ " integer,  " // foreign key sur éventuel medecin prescripteur
				+ C_ORD_NFG
				+ " texte, " // "NFGPrint"
				+ C_ORD_NUM
				+ " texte, " // "NumOrdoPrint"
				+ C_ORD_DATE
				+ " texte, " // "DateOrdoPrint"
				+ C_ORD_DATE_ISO8601
				+ " texte, " // "DateOrdoPrint" YYYY-MM-DD
				+ C_ORD_FINTRT
				+ " texte, " // "FinTrtPrint"
				+ C_ORD_NBRE_ACTES
				+ " texte, " // "NbreActesPrint"
				+ C_ORD_TYPE_SOIN
				+ " texte, " // "TypeSoinPrint"
				+ C_ORD_DATE_SUIVANTE
				+ " texte, " // "VisiteSvtePrint"
				+ C_ORD_COMMENT_TRT
				+ " texte, " // "CommentsTrtPrint"
				+ C_ORD_COMMENT_VISITE
				+ " texte, " // "CommentsVisitePrint"
				+ "FOREIGN KEY(" + C_ORD_ID_VST_ID + ") REFERENCES " + T_VISITE + "(" + C_VST_ID + "), " + "FOREIGN KEY(" + C_ORD_ID_TRN_ID
				+ ") REFERENCES " + T_TOURNEE + "(" + C_TRN_ID + "), " + "FOREIGN KEY(" + C_ORD_ID_PRT_ID + ") REFERENCES " + T_PRATICIEN
				+ "(" + C_PRT_ID + "), " + "FOREIGN KEY(" + C_ORD_ID_BNF_ID + ") REFERENCES " + T_BENEF + "(" + C_BNF_ID + "), "
				+ "FOREIGN KEY(" + C_ORD_ID_MED_ID + ") REFERENCES " + T_MEDECIN + "(" + C_MED_ID + ")  " + ");";
		private static final String CREA_T_BENEF = "CREATE TABLE " + T_BENEF + " (" + C_BNF_ID + " integer primary key autoincrement, "
				+ C_BNF_CLEBENEF + " texte non null, " // "CleBenefPrint"
				+ C_BNF_NOM + " texte, " // "BeneficiaireNomPrint"
				+ C_BNF_PRENOM + " texte, " // "BeneficiairePNomPrint"
				+ C_BNF_TEL1 + " texte, " // "Telephone1Print"
				+ C_BNF_TEL2 + " texte, " // "Telephone2Print"
				+ C_BNF_ADRESSE + " texte, " // "AdressePrint"
				+ C_BNF_CPOSTAL + " texte, " // "CodePostalPrint"
				+ C_BNF_COMMUNE + " texte, " // "CommunePrint"
				+ C_BNF_DATENAIS + " texte, " // "BenefDateNaisPrint"
				+ C_BNF_AGE + " texte, " // "BenefAgePrint"
				+ C_BNF_LIBELLE + " texte, " // "BeneficiairePrint"
				+ C_BNF_SECTEUR + " texte, " // "LblSecteurPrint"
				+ C_BNF_CIVILITE + " texte, " // "Benef_Civilite"
				+ C_BNF_TEL3 + " texte, " // "Telephone3Print"
				+ C_BNF_ADRESSE2 + " texte, " // "BenefAdresse2Print"
				+ C_BNF_CPOSTAL2 + " texte, " // "BenefCodePostal2Print"
				+ C_BNF_COMMUNE2 + " texte, " // "BenefCommune2Print"
				+ C_BNF_QUALITE + " texte, " // "BenefQualitePrint"
				+ C_BNF_DATE_LECT_CV + " texte, " // "Benef_DateLectureCVPrint"
				+ C_BNF_DATE_LECT_CV_ISO8601 + " texte, " + C_BNF_MAP + " texte, " // "QueryMap"
				+ C_BNF_COMMENT + " texte, " // "CommentsBenefPrint"
				+ C_BNF_NOTE + " texte  " + ");";
		private static final String CREA_T_BNFCAISSE = "CREATE TABLE " + T_BNFCAISSE + " (" + C_BNFCAI_ID
				+ " integer primary key autoincrement, " + C_BNFCAI_ID_BNF_ID + " integer non null, " // foreign key sur benef.
				+ C_BNFCAI_ASS_NOM + " texte, " // "AssureNomPrint"
				+ C_BNFCAI_ASS_PNOM + " texte, " // "AssurePNomPrint"
				+ C_BNFCAI_ASS_INSEE + " texte, " // "NumInseePrint"
				+ C_BNFCAI_GRDRG + " texte, " // "GrdRegimePrint"
				+ C_BNFCAI_CAIGEST + " texte, " // "CaisGestPrint"
				+ C_BNFCAI_CENGEST + " texte, " // "CenGestPrint"
				+ C_BNFCAI_LIBELLE + " texte, " // "Cais_LibellePrint"
				+ C_BNFCAI_REF_GIE + " texte, " // "Cais_RefGIEPrint"
				+ C_BNFCAI_JUSTIF_AMO + " texte, " // "PieceJustifAMOPrint"
				+ C_BNFCAI_TEL + " texte, " // "Cais_TelephonePrint"
				+ C_BNFCAI_ADR1 + " texte, " // "Cais_AdressePrint"
				+ C_BNFCAI_ADR2 + " texte, " // "Cais_Adresse2Print"
				+ C_BNFCAI_CPOSTAL + " texte, " // "Cais_CodePostalPrint"
				+ C_BNFCAI_COMMUNE + " texte, " // "Cais_CommunePrint"
				+ C_BNFCAI_EMAIL + " texte, " // "Cais_EmailPrint"
				+ "FOREIGN KEY(" + C_BNFCAI_ID_BNF_ID + ") REFERENCES " + T_BENEF + "(" + C_BNF_ID + ")  " + ");";
		private static final String CREA_T_BNFMUTUELLE = "CREATE TABLE " + T_BNFMUTUELLE + " (" + C_BNFMUT_ID
				+ " integer primary key autoincrement, " + C_BNFMUT_ID_BNF_ID + " integer non null, " // foreign key sur benef.
				+ C_BNFMUT_IDENT + " texte, " // "Mut_IdentPrint"
				+ C_BNFMUT_LIBELLE + " texte, " // "Mut_NomPrint"
				+ C_BNFMUT_CATEGORIE + " texte, " // "Mut_CategoriePrint"
				+ C_BNFMUT_GEST_UNIQUE + " texte, " // "Mut_GestionUniquePrint"
				+ C_BNFMUT_JUSTIF_AMC + " texte, " // "Benef_JustifDroitsAMCPrint"
				+ C_BNFMUT_DATE_DROITS_DEB + " texte, " // "Mut_DateDebutDroitsPrint"
				+ C_BNFMUT_DATE_DROITS_FIN + " texte, " // "Mut_DateFinDroitsPrint"
				+ C_BNFMUT_TEL + " texte, " // "Mut_TelephonePrint"
				+ C_BNFMUT_ADR + " texte, " // "Mut_AdresPrint"
				+ C_BNFMUT_CPOSTAL + " texte, " // "Mut_CodePPrint"
				+ C_BNFMUT_COMMUNE + " texte, " // "Mut_CommunePrint"
				+ "FOREIGN KEY(" + C_BNFMUT_ID_BNF_ID + ") REFERENCES " + T_BENEF + "(" + C_BNF_ID + ")  " + ");";

		private static final String CREA_T_BNFLASTVISITE = "CREATE TABLE " + T_BNFLASTVISITE + " (" + C_BNFVST_ID
				+ " integer primary key autoincrement, "
				+ C_BNFVST_ID_BNF_ID
				+ " integer non null, " // foreign key sur benef.
				+ C_BNFVST_ID_VST_ID
				+ " integer, " // foreign key sur visite.
				+ C_BNFVST_ID_PRT_ID
				+ " integer, " // foreign key sur praticien.
				+ C_BNFVST_DERVSTDATE
				+ " texte, " // "AAAA-MM-JJ HH:MM"
				+ C_BNFVST_DERVSTPRAT + " texte, " + "FOREIGN KEY(" + C_BNFVST_ID_BNF_ID + ") REFERENCES " + T_BENEF + "(" + C_BNF_ID
				+ "), " + "FOREIGN KEY(" + C_BNFVST_ID_VST_ID + ") REFERENCES " + T_VISITE + "(" + C_VST_ID + "), " + "FOREIGN KEY("
				+ C_BNFVST_ID_PRT_ID + ") REFERENCES " + T_PRATICIEN + "(" + C_PRT_ID + ")  " + ");";

		private static final String CREA_T_MEDECIN = "CREATE TABLE " + T_MEDECIN + " (" + C_MED_ID + " integer primary key autoincrement, "
				+ C_MED_NOM + " texte, " // "Med_NomPrint"
				+ C_MED_FINESS + " texte, " // "Med_FinessPrint"
				+ C_MED_TEL1 + " texte, " // "Med_Tel1Print"
				+ C_MED_TEL2 + " texte, " // "Med_Tel2Print"
				+ C_MED_EMAIL + " texte, " // "Med_EMail"
				+ C_MED_ADR + " texte, " // "Med_Adr"
				+ C_MED_CODEP + " texte, " // "Med_CodeP"
				+ C_MED_COMMUNE + " texte " // "Med_Commune"
				+ ");";

		private static final String CREA_T_FACTURE = "CREATE TABLE " + T_FACTURE + " (" + C_FAC_ID + " integer primary key autoincrement, "
				+ C_FAC_ID_BNF_ID
				+ " integer non null, " // foreign key sur benef.
				+ C_FAC_TLA
				+ " texte, " // "AppellationPrint"
				+ C_FAC_MNT_FACTURE
				+ " texte, " // "MontantFactPrint"
				+ C_FAC_MNT_ASSURE
				+ " texte, " // "MontantAssurePrint"
				+ C_FAC_NO_FACTURE
				+ " texte, " // "CleFacturePrint"
				+ C_FAC_ABRV_FACTURANT
				+ " texte, " // "AbrevFacturantPrint"
				+ C_FAC_MNT_FACTURE_I + " integer, " + C_FAC_MNT_ASSURE_I + " integer, " + "FOREIGN KEY(" + C_FAC_ID_BNF_ID
				+ ") REFERENCES " + T_BENEF + "(" + C_BNF_ID + ")  " + ");";

		private static final String CREA_TRIGGER_DLT_BNFCAISSE_BNF_ID = "CREATE TRIGGER " + C_DLT_BNFCAISSE_BNF_ID + " BEFORE DELETE ON "
				+ T_BENEF + " FOR EACH ROW BEGIN " + " DELETE from " + T_BNFCAISSE + " WHERE " + C_BNFCAI_ID_BNF_ID + " = OLD." + C_BNF_ID
				+ "; END";
		private static final String CREA_TRIGGER_DLT_BNFMUTUELLE_BNF_ID = "CREATE TRIGGER " + C_DLT_BNFMUTUELLE_BNF_ID
				+ " BEFORE DELETE ON " + T_BENEF + " FOR EACH ROW BEGIN " + " DELETE from " + T_BNFMUTUELLE + " WHERE "
				+ C_BNFMUT_ID_BNF_ID + " = OLD." + C_BNF_ID + "; END";
		private static final String CREA_TRIGGER_DLT_BNFLASTVISITE_BNF_ID = "CREATE TRIGGER " + C_DLT_BNFLASTVISITE_BNF_ID
				+ " BEFORE DELETE ON " + T_BENEF + " FOR EACH ROW BEGIN " + " DELETE from " + T_BNFLASTVISITE + " WHERE "
				+ C_BNFVST_ID_BNF_ID + " = OLD." + C_BNF_ID + "; END";

		private static final String CREA_IDX_TRN_ISTERMINE = "CREATE  INDEX IDX_TRN_ISTERMINE ON " + T_TOURNEE + " (" + C_TRN_IS_TERMINE
				+ ")";
		private static final String CREA_IDX_TRN_ID_PRT_ID = "CREATE INDEX IDX_TRN_ID_PRT_ID ON " + T_TOURNEE + " (" + C_TRN_ID_PRT_ID
				+ ")";
		private static final String CREA_IDX_TRN_SHORTDATE = "CREATE INDEX IDX_TRN_SHORTDATE ON " + T_TOURNEE + " (" + C_TRN_SHORTDATE
				+ ")";

		private static final String CREA_IDX_VST_ID_TRN_ID = "CREATE INDEX IDX_VST_ID_TRN_ID ON " + T_VISITE + " (" + C_VST_ID_TRN_ID + ")";
		private static final String CREA_IDX_ORDO_ID_VST_ID = "CREATE INDEX IDX_ORD_ID_VST_ID ON " + T_ORDO + " (" + C_ORD_ID_VST_ID + ")";

		private static final int DATABASE_VERSION_1 = 1; // version courante
		private static final int DATABASE_VERSION_2 = 2; // ...la prochaine
		private static final int DATABASE_VERSION_3 = 3; // ...la prochaine
		private static final int DATABASE_VERSION_COURANTE = DATABASE_VERSION_1;

		private final Context context;

		public DatabaseHelper(Context context) {
			super(context, BASE_EQUINOX, null, DATABASE_VERSION_COURANTE); // provoque
																			// la
																			// création
																			// si
																			// la
																			// base
																			// n'existe
																			// pas
																			// dans
																			// l'application
			this.context = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				LogCatBuilder.WriteInfoToLog(context, EnNiveauLog.INFO, TAG, R.string.info_databasecreate, BASE_EQUINOX);
				db.execSQL(CREA_T_PLANTRA);
				db.execSQL(CREA_T_PRATICIEN);
				db.execSQL(CREA_T_TOURNEE);
				db.execSQL(CREA_T_VISITE);
				db.execSQL(CREA_T_VSTALERTE);
				db.execSQL(CREA_T_ORDO);
				db.execSQL(CREA_T_MEDECIN);
				db.execSQL(CREA_T_BENEF);
				db.execSQL(CREA_T_BNFCAISSE);
				db.execSQL(CREA_T_BNFMUTUELLE);
				db.execSQL(CREA_T_BNFLASTVISITE);
				db.execSQL(CREA_T_FACTURE);

				db.execSQL(CREA_TRIGGER_DLT_BNFCAISSE_BNF_ID);
				db.execSQL(CREA_TRIGGER_DLT_BNFMUTUELLE_BNF_ID);
				db.execSQL(CREA_TRIGGER_DLT_BNFLASTVISITE_BNF_ID);

				db.execSQL(CREA_IDX_TRN_ISTERMINE);
				db.execSQL(CREA_IDX_TRN_ID_PRT_ID);
				db.execSQL(CREA_IDX_TRN_SHORTDATE);
				db.execSQL(CREA_IDX_VST_ID_TRN_ID);
				db.execSQL(CREA_IDX_ORDO_ID_VST_ID);
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			}
		}

		// Dans le cas où il faudrait complètement reconstruire la base
		public void drop_to_re_create(SQLiteDatabase db) {
			try {
				db.execSQL("DROP TABLE if exists " + T_PLANTRA);
				db.execSQL("DROP TABLE if exists " + T_PRATICIEN);
				db.execSQL("DROP TABLE if exists " + T_TOURNEE);
				db.execSQL("DROP TABLE if exists " + T_VISITE);
				db.execSQL("DROP TABLE if exists " + T_VSTALERTE);
				db.execSQL("DROP TABLE if exists " + T_ORDO);
				db.execSQL("DROP TABLE if exists " + T_MEDECIN);
				db.execSQL("DROP TABLE if exists " + T_BENEF);
				db.execSQL("DROP TABLE if exists " + T_BNFCAISSE);
				db.execSQL("DROP TABLE if exists " + T_BNFMUTUELLE);
				db.execSQL("DROP TABLE if exists " + T_BNFLASTVISITE);
				db.execSQL("DROP TABLE if exists " + T_FACTURE);
				//
				onCreate(db);
				//
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int p_oldVersion, int p_newVersion) {
			int oldVersion = p_oldVersion;

			int newv = 0;
			try {
				if (p_oldVersion != p_newVersion) {
					// mise à jour de la base de données en fournissant la
					// nouvelle version de la base
					String message = context.getString(R.string.info_databaseupdate, BASE_EQUINOX, p_oldVersion, p_newVersion);
					LogCatBuilder.WriteInfoToLog(context, EnNiveauLog.INFO, TAG, R.string.info_databaseupgrade, message);
					Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
					if (p_oldVersion == DATABASE_VERSION_1) {
						// ce qu'il faut pour passer de la 1 à la 2
						/*
						 * db.execSQL("ALTER TABLE "+T_BENEF+" ADD "+C_BNF_SECTEUR +" texte ");
						 */
						newv = DATABASE_VERSION_2;
					}
					if (oldVersion == DATABASE_VERSION_2) {
						// ... etc
						// pour la prochaine version
						newv = DATABASE_VERSION_3;
					}
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.error_databaseupgrade, e, newv);
				throw e;
			}
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// ouvrie la base de données
	public DBEqxAdapter open() {
		try {
			// ouvre la base de données pour y insérer des données
			db = DBHelper.getWritableDatabase();
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.error_databaseopen, e);
			throw e;
		}
		return this;
	}

	// fermer la base de données
	public void close() {
		try {
			db.close();
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.error_databaseclose, e);
		}
	}

	// effacer toutes les données de la base
	public void truncate() {
		try {
			db.execSQL("DELETE FROM " + T_PLANTRA);
			db.execSQL("DELETE FROM " + T_PRATICIEN);
			db.execSQL("DELETE FROM " + T_TOURNEE);
			db.execSQL("DELETE FROM " + T_VSTALERTE); // référence vst_id
			db.execSQL("DELETE FROM " + T_VISITE);
			db.execSQL("DELETE FROM " + T_ORDO);
			db.execSQL("DELETE FROM " + T_MEDECIN);
			db.execSQL("DELETE FROM " + T_FACTURE); // référence bnf_id
			db.execSQL("DELETE FROM " + T_BNFCAISSE);
			db.execSQL("DELETE FROM " + T_BNFMUTUELLE);
			db.execSQL("DELETE FROM " + T_BNFLASTVISITE);
			db.execSQL("DELETE FROM " + T_BENEF);
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.error_databasetruncate, e);
		}
	}

	// effacer les tournees de la base
	public void effacerLesTournees() {
		try {
			db.execSQL("DELETE FROM " + T_PLANTRA);
			db.execSQL("DELETE FROM " + T_PRATICIEN);
			db.execSQL("DELETE FROM " + T_TOURNEE);
			db.execSQL("DELETE FROM " + T_VSTALERTE);
			effacerReferenceVisitePraticienDebnflastvisite();
			db.execSQL("DELETE FROM " + T_VISITE);
			db.execSQL("DELETE FROM " + T_ORDO);
			// db.execSQL("DELETE FROM "+T_MEDECIN); à garder
			// db.execSQL("DELETE FROM "+T_BENEF); à garder
			// db.execSQL("DELETE FROM "+T_BNFCAISSE); à garder
			// db.execSQL("DELETE FROM "+T_BNFMUTUELLE); à garder
			db.execSQL("DELETE FROM " + T_FACTURE);
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.error_databasetourneesclear, e);
		}
	}

	/**
	 * Effacer les références visite et praticien dans la table bnflastvisite. Ne laisser que les libellés. L'indication sera lisible dans
	 * le détail du bénéficiaire mais l'accès au détail de la visite ne sera pas possible. Clear bnfvst_id_vst_id et bnfvst_id_prt_id
	 */
	private void effacerReferenceVisitePraticienDebnflastvisite() {
		try {
			ContentValues values = new ContentValues();
			values.put(C_BNFVST_ID_VST_ID, (String) null);
			values.put(C_BNFVST_ID_PRT_ID, (String) null);
			db.update(T_BNFLASTVISITE, values, null, null);
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// PLANTRA
	// public long insererUnPlantra(xml_Plantra p_xplantra) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_PLT_VERSION, p_xplantra.getplantraVersion());
	// values.put(C_PLT_LICENCE, p_xplantra.getplantraLicence());
	// values.put(C_PLT_VEREQX, p_xplantra.getplantraVersionEqx());
	// values.put(C_PLT_DATEEQX, p_xplantra.getplantraDateEqx());
	// values.put(C_PLT_DU, p_xplantra.getplantraFirstDate());
	// values.put(C_PLT_AU, p_xplantra.getplantraLastDate());
	// return db.insert(T_PLANTRA, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	public Cursor recupererLaListeDesPlantra() {
		try {
			return db.query(T_PLANTRA, new String[] { C_PLT_ID, C_PLT_VERSION, C_PLT_LICENCE, C_PLT_VEREQX, C_PLT_DATEEQX, C_PLT_DU,
					C_PLT_AU }, // tous
								// les
								// champs
					null, // selection,
					null, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// PRATICIEN
	// public long insererUnPraticien(xml_Praticien p_xprat) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_PRT_CLEVENUS, p_xprat.getpratCleVenus());
	// values.put(C_PRT_FINESS, p_xprat.getpratFiness());
	// values.put(C_PRT_COLOR, p_xprat.getpratColor());
	// values.put(C_PRT_LIBELLE, p_xprat.getpratLibelle());
	// // C_PRT_PRIORITY non connue donc non valorisée
	// return db.insert(T_PRATICIEN, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// private boolean supprimerUnPraticien(long p_id) {
	// // renvoie une valeur supérieure à 0 si l'enreg a été supprimé
	// return db.delete(T_PRATICIEN, C_PRT_ID + "=" + p_id, null) > 0;
	// }

	public Cursor recupererLaListeDesPraticiens() {
		try {
			return db.query(T_PRATICIEN,
					new String[] { C_PRT_ID, C_PRT_CLEVENUS, C_PRT_FINESS, C_PRT_COLOR, C_PRT_LIBELLE, C_PRT_PRIORITY }, // tous
																															// les
																															// champs
					null, // selection,
					null, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// public void mettreajourUnPraticien(long p_prtid, xml_Praticien p_xprat) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_PRT_CLEVENUS, p_xprat.getpratCleVenus());
	// values.put(C_PRT_FINESS, p_xprat.getpratFiness());
	// values.put(C_PRT_COLOR, p_xprat.getpratColor());
	// values.put(C_PRT_LIBELLE, p_xprat.getpratLibelle());
	// // C_PRT_PRIORITY pas de mise à jour à partir de plantra.xml
	// db.update(T_PRATICIEN, values, C_PRT_ID + "=?", new String[] { "" + p_prtid });
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	/*
	 * renvoie _id du praticien d'identiifant p_clevenus sachant qu'il existe deux valeurs "génériques" représentative du choix
	 * "tous les praticiens" et "non attribues" dans ces cais l' _id retourné est une valeur précise permettant de filtrer les tournées
	 * souhaitées. Lorsque un idenfifiant n'est plus reconnu alors _id retourné est celui correspondant à "tous"
	 */
	public long find_prt_idByCleVenus(String p_clevenus) {
		long prt_id;
		String tous = "" + C_PRT_TOUS;
		String nonattribue = "" + C_PRT_NON_ATTRIB;
		if (tous.equals(p_clevenus)) {
			prt_id = 0;
		} else {
			if (nonattribue.equals(p_clevenus)) {
				prt_id = C_PRT_NON_ATTRIB;
			} else {
				Cursor c = db.query(T_PRATICIEN, new String[] { C_PRT_ID, C_PRT_CLEVENUS }, C_PRT_CLEVENUS + "=?",
						new String[] { p_clevenus }, null, null, null);
				try {
					if (c.moveToNext()) {
						prt_id = c.getLong(0);
					} else {
						prt_id = 0; // tous si le praticien n'est plus reconnu
									// du logiciel
					}
				} catch (SQLException e) {
					LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
					throw e;
				} finally {
					c.close();
				}
			}
		}
		return prt_id;
	}

	/**
	 * Returns tous les praticiens connus
	 * @param isneedtous et isneednonattrib indicateurs pour commencer le tableau avec les libellés tous et non attribués
	 * @return List de tous les praticiens
	 */
	// public ArrayList<Prt_Item> recupererLaListeDesPraticiens(boolean isneedtous, boolean isneednonattrib) {
	// Cursor cur = null;
	// ArrayList<Prt_Item> praticiens = new ArrayList<Prt_Item>();
	// try {
	// if (isneedtous) {
	// final Prt_Item prat = new Prt_Item();
	// prat.setPrt_libelle((String) context.getResources().getText(R.string.s_trn_spinner_tous));
	// prat.setPrt_clevenus("" + C_PRT_TOUS);
	// praticiens.add(prat);
	// }
	// if (isneednonattrib) {
	// final Prt_Item prat = new Prt_Item();
	// prat.setPrt_id(C_PRT_NON_ATTRIB);
	// prat.setPrt_libelle((String) context.getResources().getText(R.string.s_trn_spinner_noattrib));
	// prat.setPrt_clevenus("" + C_PRT_NON_ATTRIB);
	// praticiens.add(prat);
	// }
	// cur = db.query(T_PRATICIEN,
	// new String[] { C_PRT_ID, C_PRT_CLEVENUS, C_PRT_FINESS, C_PRT_COLOR, C_PRT_LIBELLE, C_PRT_PRIORITY }, // tous
	// // les
	// // champs
	// null, // selection,
	// null, // selectionArgs,
	// null, // groupBy,
	// null, // having,
	// C_PRT_PRIORITY + " ASC"); // orderBy)
	// if (cur.moveToFirst()) {
	// do {
	// final Prt_Item prat = new Prt_Item();
	// prat.setPrt_id(cur.getLong(cur.getColumnIndex(C_PRT_ID)));
	// prat.setPrt_clevenus(cur.getString(cur.getColumnIndex(C_PRT_CLEVENUS)));
	// prat.setPrt_finess(cur.getString(cur.getColumnIndex(C_PRT_FINESS)));
	// prat.setPrt_color(cur.getString(cur.getColumnIndex(C_PRT_COLOR)));
	// prat.setPrt_libelle(cur.getString(cur.getColumnIndex(C_PRT_LIBELLE)));
	// prat.setPrt_priority(cur.getInt(cur.getColumnIndex(C_PRT_PRIORITY)));
	// //
	// praticiens.add(prat);
	// } while (cur.moveToNext());
	// }
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// } finally {
	// cur.close();
	// }
	// return praticiens;
	// }

	// --------------------------------------------------------------------------------------------------------------------------
	// TOURNEE
	// public long insererUneTournee(xml_Tournee p_xtournee, long p_pltid, long p_prtid) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_TRN_ID_PLT_ID, p_pltid);
	// values.put(C_TRN_ID_PRT_ID, p_prtid);
	// values.put(C_TRN_SHORTDATE, p_xtournee.gettournee_ShortDate());
	// values.put(C_TRN_LONGDATE, p_xtournee.gettournee_Date());
	// values.put(C_TRN_DATE_ISO8601, this.JJMMAAAAToIso8601(p_xtournee.gettournee_ShortDate()));
	// // rien pour C_TRN_IS_TERMINE
	// // rien pour C_TRN_NOTE
	// return db.insert(T_TOURNEE, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// private boolean supprimerUneTournee(long p_id) {
	// // renvoie une valeur supérieure à 0 si l'enreg a été supprimé
	// return db.delete(T_TOURNEE, C_TRN_ID + "=" + p_id, null) > 0;
	// }

	public Cursor recupererLaListeDesTournees() {
		try {
			return db.query(T_TOURNEE, new String[] { C_TRN_ID, C_TRN_ID_PLT_ID, C_TRN_ID_PRT_ID, C_TRN_SHORTDATE, C_TRN_LONGDATE,
					C_TRN_IS_TERMINE }, // tous les champs
										// SAUF
										// LA NOTE qui
										// risque
										// d'être
										// volumineuse
					null, // selection,
					null, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// mise à jour de l'indicateur de fin de tournée
	// public void updateTourneeIsTermine(long p_trnid, Boolean p_isTermine) {
	// boolean isopen = db.isOpen();
	// try {
	// if (!isopen) {
	// this.open();
	// }
	// int termine = EnTourneeState.TRN_AFAIRE.getCode();
	// if (p_isTermine) {
	// termine = EnTourneeState.TRN_FINIE.getCode();
	// }
	// ContentValues values = new ContentValues();
	// values.put(C_TRN_IS_TERMINE, "" + termine);
	// db.update(T_TOURNEE, values, C_TRN_ID + "=?", new String[] { "" + p_trnid });
	// //
	// values.clear();
	// values.put(C_VST_IS_TERMINE, "" + termine);
	// db.update(T_VISITE, values, C_VST_ID_TRN_ID + "=?", new String[] { "" + p_trnid });
	// //
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// } finally {
	// this.close();
	// if (isopen) {
	// this.open();
	// }
	//
	// }
	// }

	// mise à jour de la note de la tournée
	public void updateTourneeNote(long p_trnid, String p_note) {
		try {
			ContentValues values = new ContentValues();
			values.put(C_TRN_NOTE, p_note);
			db.update(T_TOURNEE, values, C_TRN_ID + "=?", new String[] { "" + p_trnid });
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// renvoyer un cursor contenant TOUS les champs de la ligne d'id unique
	// p_trn_id
	public Cursor recupereTourneebyID(long p_trnId) {
		try {
			return db.query(T_TOURNEE, new String[] { C_TRN_ID, C_TRN_ID_PLT_ID, C_TRN_ID_PRT_ID, C_TRN_SHORTDATE, C_TRN_LONGDATE,
					C_TRN_IS_TERMINE, C_TRN_NOTE }, // tous les
													// champs Y
													// COMPRIS
													// LA NOTE
													// qui peut
													// être
													// volumineuse
					C_TRN_ID + "=?", // selection,
					new String[] { "" + p_trnId }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	public Cursor recupereLaTourneeID(long p_trnId) {
		String clausewhere = "where (" + T_TOURNEE + "." + C_TRN_ID + "=? )  ";
		String requete = "select " + T_TOURNEE + "." + C_TRN_ID + ", " + T_TOURNEE + "." + C_TRN_SHORTDATE + ", " + T_TOURNEE + "."
				+ C_TRN_LONGDATE + ", " + T_TOURNEE + "." + C_TRN_IS_TERMINE + ", " + T_PRATICIEN + "." + C_PRT_LIBELLE + ", "
				+ T_PRATICIEN + "." + C_PRT_COLOR + " " + "from " + T_TOURNEE + " " + "left outer join " + T_PRATICIEN + " on ("
				+ T_TOURNEE + "." + C_TRN_ID_PRT_ID + " = " + T_PRATICIEN + "." + C_PRT_ID + ") " + clausewhere;
		return db.rawQuery(requete, new String[] { "" + p_trnId });
	}

	// select tournee.trn_id_prt_id as _id, tournee._id as trn_id,
	// praticien._id as prt_id, prt_libelle, prt_color
	// from tournee
	// left outer join praticien on (tournee.trn_id_prt_id = praticien._id)
	// where trn_istermine=0
	// group by praticien._id, praticien.prt_libelle,praticien.prt_color
	/*
	 * cette requête est exploitée dans PrtTrnAdapter qui descend de SimpleCursorAdapter, ce composant métier impose que le premier champ
	 * soit la clé primaire _id de la table qui fait l'objet du select en l'occurance ici tournee alors que les infos utiles concernent
	 * uniquement praticien (ayant des tournees) l'astuce est de prendre trn_id_prt_id comme _id pour récupérer la clé primaire de praticien
	 * en allant la chercher dans tournee! Ensuite pour éviter ERROR/CursorWindow(544): Bad request for field slot 0,-1. laisser les champs
	 * prt_libelle et prt_color sans les précéder de praticien.
	 */

	/**
	 * Renvoie la clause where construite à partir des filtres p_enTourneeState et p_prtId
	 * @param p_enTourneeState filtre état tournée (faite / pas faite)
	 * @param p_prtId filtre praticien =0:tous les praticien donc pas de filtrage
	 */
	// public String buildWherePourLaListeDesPratTournees(EnTourneeState p_enTourneeState, long p_prtId) {
	// String clausewhere = "";
	// if ((p_prtId > 0) || (p_enTourneeState != EnTourneeState.TRN_AFAIRE_FINIE)) {
	// clausewhere = " where ";
	// }
	// if (p_prtId > 0) {
	// clausewhere = clausewhere + "(" + T_TOURNEE + "." + C_TRN_ID_PRT_ID + "=? )";
	// }
	// if ((p_prtId > 0) && (p_enTourneeState != EnTourneeState.TRN_AFAIRE_FINIE)) {
	// clausewhere = clausewhere + " and ";
	// }
	// if (p_enTourneeState != EnTourneeState.TRN_AFAIRE_FINIE) {
	// if (p_enTourneeState == EnTourneeState.TRN_AFAIRE) {
	// clausewhere = clausewhere + " ((" + T_TOURNEE + "." + C_TRN_IS_TERMINE + " is NULL ) or (" + T_TOURNEE + "."
	// + C_TRN_IS_TERMINE + "=? ))  ";
	// } else {
	// clausewhere = clausewhere + " (" + T_TOURNEE + "." + C_TRN_IS_TERMINE + "=? )  ";
	// }
	// }
	// return clausewhere;
	// }

	/**
	 * Renvoie un curseur d'ensemble résultat composé de la clé primaire, du libellé et du code couleur des praticiens qui ont au moins une
	 * tournée répondant au filtre p_trnIsTermine
	 * @param p_enTourneeState indicateur des tournées que l'utilisateur souhaite voir
	 * @param p_prtId : à 0:pas de filtrage sinon filtrage par praticien
	 */
	// public Cursor recupererLaListeDesPratTournees(EnTourneeState p_enTourneeState, long p_prtId) {
	// Cursor retour;
	// String clausewhere = buildWherePourLaListeDesPratTournees(p_enTourneeState, p_prtId);
	// //
	// String requete = "select " + T_TOURNEE + "." + C_TRN_ID_PRT_ID + " as " + C_PRT_ID + ", " + T_TOURNEE + "." + C_TRN_ID + " as "
	// + C_AS_TRN_ID + ", " + T_PRATICIEN + "." + C_PRT_ID + " as " + C_AS_PRT_ID + ", " + C_PRT_LIBELLE + ", " + T_PRATICIEN
	// + "." + C_PRT_COLOR + " " + "from " + T_TOURNEE + " " + "left outer join " + T_PRATICIEN + " on (" + T_TOURNEE + "."
	// + C_TRN_ID_PRT_ID + " = " + T_PRATICIEN + "." + C_PRT_ID + ") "
	// + clausewhere //
	// + " group by " + T_PRATICIEN + "." + C_PRT_ID + ", " + T_PRATICIEN + "." + C_PRT_LIBELLE + ", " + T_PRATICIEN + "."
	// + C_PRT_COLOR;
	//
	// if (p_prtId == 0) {
	// if (p_enTourneeState == EnTourneeState.TRN_AFAIRE_FINIE) {
	// retour = db.rawQuery(requete, null);
	// } else {
	// retour = db.rawQuery(requete, new String[] { "" + p_enTourneeState.getCode() });
	// }
	// } else {
	// long filtre_prt_id = p_prtId;
	// if (p_prtId == C_PRT_NON_ATTRIB) {
	// filtre_prt_id = 0;
	// }
	// if (p_enTourneeState == EnTourneeState.TRN_AFAIRE_FINIE) {
	// retour = db.rawQuery(requete, new String[] { "" + filtre_prt_id });
	// } else {
	// retour = db.rawQuery(requete, new String[] { "" + filtre_prt_id, "" + p_enTourneeState.getCode() });
	// }
	// }
	// //
	// return retour;
	// }
	//
	// /*
	// * select tournee._id as trn_id, tournee.trn_longdate, tournee.trn_istermine, praticien._id as prt_id, praticien.prt_libelle from
	// * tournee left outer join praticien on (tournee.trn_id_prt_id = praticien._id) order by tournee.trn_dateiso8601
	// */
	// public Cursor recupererLaListeDesTourneesOrdonneeDate(EnTourneeState p_enTourneeState) {
	// Cursor retour;
	// String clausewhere = "";
	// if (p_enTourneeState != EnTourneeState.TRN_AFAIRE_FINIE) {
	// clausewhere = "where (" + T_TOURNEE + "." + C_TRN_IS_TERMINE + "=? )  ";
	// if (p_enTourneeState == EnTourneeState.TRN_AFAIRE) {
	// clausewhere = "where ((" + T_TOURNEE + "." + C_TRN_IS_TERMINE + " is NULL ) or (" + T_TOURNEE + "." + C_TRN_IS_TERMINE
	// + "=? ))  ";
	// }
	// }
	// String requete = "select " + T_TOURNEE + "." + C_TRN_ID + ", " + T_TOURNEE + "." + C_TRN_ID + " as " + C_AS_TRN_ID + ", "
	// + T_TOURNEE + "." + C_TRN_SHORTDATE + ", " + T_TOURNEE + "." + C_TRN_LONGDATE + ", " + T_TOURNEE + "." + C_TRN_IS_TERMINE
	// + ", " + T_PRATICIEN + "." + C_PRT_ID + " as " + C_AS_PRT_ID + ", " + T_PRATICIEN + "." + C_PRT_LIBELLE + ", "
	// + T_PRATICIEN + "." + C_PRT_COLOR + " " + "from " + T_TOURNEE + " " + "left outer join " + T_PRATICIEN + " on ("
	// + T_TOURNEE + "." + C_TRN_ID_PRT_ID + " = " + T_PRATICIEN + "." + C_PRT_ID + ") " + clausewhere + "order by " + T_TOURNEE
	// + "." + C_TRN_DATE_ISO8601;
	// if (p_enTourneeState == EnTourneeState.TRN_AFAIRE_FINIE) {
	// retour = db.rawQuery(requete, null);
	// } else {
	// retour = db.rawQuery(requete, new String[] { "" + p_enTourneeState.getCode() });
	// }
	// return retour;
	// }

	// SQL liste des tournées ordonnées par praticien puis par date
	// select tournee._id as trn_id, tournee.trn_longdate,tournee.trn_istermine,
	// praticien._id as prt_id, praticien.prt_libelle
	// from tournee
	// left outer join praticien on (tournee.trn_id_prt_id = praticien._id)
	// order by praticien._id, tournee.trn_dateiso8601

	// SQL liste des tournées filtrées par praticien + les non attribuées pour
	// ne pas les oublier
	// select tournee._id as trn_id, tournee.trn_longdate,
	// tournee.trn_istermine, praticien._id as prt_id, praticien.prt_libelle
	// from tournee
	// left outer join praticien on (tournee.trn_id_prt_id =praticien._id)
	// where (tournee.trn_id_prt_id=0 ) or (tournee.trn_id_prt_id=? )
	// order by tournee.trn_dateiso8601

	/**
	 * Renvoie toutes les tournée filtrées par praticien p_prtId + les non attribuées pour ne pas les oublier
	 * @param p_prtId clé primaire praticien (pas cle_venus)
	 * @param p_trnIsTermine indicateur de filtrage <faite><pas faite>
	 * @return curseur sur ensemble résultat de la requête
	 */
	// public Cursor recupererLaListeDesTourneesOrdonneeDateDePrat(long p_prtId, EnTourneeState p_enTourneeState) {
	// Cursor retour;
	// long filtre_prt_id = p_prtId;
	// if (p_prtId == C_PRT_NON_ATTRIB) {
	// filtre_prt_id = 0;
	// }
	//
	// // ne plus faire apparaître les tournées non attribuées AVEC les
	// // trournées d'un praticien sélectionné par l'utilisateur
	// // String clausewhere
	// // ="where (("+T_TOURNEE+"."+C_TRN_ID_PRT_ID+"=0 ) or ("+T_TOURNEE+"."+C_TRN_ID_PRT_ID+"=? ))  ";
	// String clausewhere = "where (" + T_TOURNEE + "." + C_TRN_ID_PRT_ID + "=? ) ";
	// if (p_enTourneeState != EnTourneeState.TRN_AFAIRE_FINIE) {
	// if (p_enTourneeState == EnTourneeState.TRN_AFAIRE) {
	// clausewhere = clausewhere + " and ((" + T_TOURNEE + "." + C_TRN_IS_TERMINE + " is NULL ) or (" + T_TOURNEE + "."
	// + C_TRN_IS_TERMINE + "=? ))  ";
	// } else {
	// clausewhere = clausewhere + " and  (" + T_TOURNEE + "." + C_TRN_IS_TERMINE + "=? )  ";
	// }
	// }
	// String requete = "select " + T_TOURNEE + "." + C_TRN_ID + ", " + T_TOURNEE + "." + C_TRN_ID + " as " + C_AS_TRN_ID + ", "
	// + T_TOURNEE + "." + C_TRN_SHORTDATE + ", " + T_TOURNEE + "." + C_TRN_LONGDATE + ", " + T_TOURNEE + "." + C_TRN_IS_TERMINE
	// + ", " + T_PRATICIEN + "." + C_PRT_ID + " as " + C_AS_PRT_ID + ", " + T_PRATICIEN + "." + C_PRT_LIBELLE + ", "
	// + T_PRATICIEN + "." + C_PRT_COLOR + " " + "from " + T_TOURNEE + " " + "left outer join " + T_PRATICIEN + " on ("
	// + T_TOURNEE + "." + C_TRN_ID_PRT_ID + " = " + T_PRATICIEN + "." + C_PRT_ID + ") " + clausewhere + "order by " + T_TOURNEE
	// + "." + C_TRN_DATE_ISO8601;
	// if (p_enTourneeState == EnTourneeState.TRN_AFAIRE_FINIE) {
	// retour = db.rawQuery(requete, new String[] { "" + filtre_prt_id });
	// } else {
	// retour = db.rawQuery(requete, new String[] { "" + filtre_prt_id, "" + p_enTourneeState.getCode() });
	// }
	// return retour;
	// }

	/*
	 * String requete = "select tournee.trn_longdate, praticien.prt_libelle from tournee "
	 * +"left outer join praticien on (tournee.trn_id_prt_id = praticien._id) " +"where tournee.trn_id_prt_id=? " +"order by tournee._id";
	 * Cursor c = db.rawQuery(requete, new String[] {""+prt_id});
	 */
	// --------------------------------------------------------------------------------------------------------------------------
	// VISITE
	// public long insererUneVisite(xml_Visite p_xvisite, long p_trnid, long p_prtid, long p_bnfid, xml_Tournee p_xtournee) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_VST_ID_TRN_ID, p_trnid);
	// values.put(C_VST_ID_PRT_ID, p_prtid);
	// values.put(C_VST_ID_BNF_ID, p_bnfid);
	// values.put(C_VST_CLEBENEF, p_xvisite.getvisiteCleBenef());
	// values.put(C_VST_LIEU, p_xvisite.getvisiteLieu());
	// values.put(C_VST_HORAIRE, p_xvisite.getvisiteHoraire());
	// values.put(C_VST_HORAIRE_ISO8601, this.construireDateHeureVisite(p_xtournee, p_xvisite));
	// values.put(C_VST_SECTEUR, p_xvisite.getvisiteSecteur());
	// values.put(C_VST_GARDE, p_xvisite.getvisiteGarde());
	// // rien pour C_VST_IS_TERMINE
	// return db.insert(T_VISITE, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// private boolean supprimerUneVisite(long p_id) {
	// // renvoie une valeur supérieure à 0 si l'enreg a été supprimé
	// return db.delete(T_VISITE, C_VST_ID + "=" + p_id, null) > 0;
	// }

	// PAS VRAIMENT UTILE en l'état
	public Cursor recupererLaListeDesVisites() {
		try {
			return db.query(T_VISITE, new String[] { C_VST_ID, C_VST_ID_TRN_ID, C_VST_ID_PRT_ID, C_VST_ID_BNF_ID, C_VST_CLEBENEF,
					C_VST_LIEU, C_VST_HORAIRE, C_VST_HORAIRE_ISO8601, C_VST_SECTEUR, C_VST_GARDE, C_VST_IS_TERMINE }, // tous les
																														// champs
					null, // selection,
					null, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	/*
	 * Valoriser l'indicateur de terminaison du rendez-vous de clé primaire p_vstid. Si l'indicateur à positionner est <à faire> alors
	 * valoriser ce même indicateur au niveau de la tournée de clé primaire p_trnid
	 * @param p_vstid long
	 * @param p_trnid long
	 */
	// public void updateVisiteIsTermine(long p_trnid, long p_vstid, Boolean p_isTermine) {
	// boolean isopen = db.isOpen();
	// try {
	// if (!isopen) {
	// this.open();
	// }
	//
	// int termine = C_VST_AFAIRE;
	// if (p_isTermine) {
	// termine = C_VST_FINIE;
	// }
	// ContentValues values = new ContentValues();
	// values.put(C_VST_IS_TERMINE, "" + termine);
	// db.update(T_VISITE, values, C_VST_ID + "=?", new String[] { "" + p_vstid });
	// // si la visite bascule à <à faire> alors s'assurer que la tournée
	// // bascule aussi en <à faire>
	// if (!p_isTermine) {
	// values.clear();
	// values.put(C_TRN_IS_TERMINE, "" + termine);
	// db.update(T_TOURNEE, values, C_TRN_ID + "=?", new String[] { "" + p_trnid });
	// } else {
	// // si la visite bascule en <faite> alors si c'est la dernière la
	// // tournée bascule en <faite> aussi
	// execSQL_update_vst_id_trn_id(p_trnid);
	// }
	//
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// } finally {
	// this.close();
	// if (isopen) {
	// this.open();
	// }
	// }
	// }

	public long get_trnIDfromvisite(long p_vstId) {
		long trn_id = 0;
		if (p_vstId > 0) {
			Cursor c = db.query(T_VISITE, new String[] { C_VST_ID_TRN_ID }, "(" + C_VST_ID + "=?)", new String[] { "" + p_vstId }, null,
					null, null);
			try {
				if (c.moveToNext()) {
					trn_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return trn_id;
	}

	/**
	 * Valoriser tournee.is_termine à C_TRN_FINIE lorsque le serveur DB ne trouve plus aucune visite de cette tournée en C_VST_AFAIRE
	 * @param p_trnId clé primaire de la tournée à vérifier
	 */
	// private void execSQL_update_vst_id_trn_id(long p_trnId) {
	// String sql = "update tournee set " + C_TRN_IS_TERMINE + " = " + EnTourneeState.TRN_FINIE.getCode() //
	// + " where ( ( " //
	// + "  select count(*) from " + T_VISITE //
	// + " where " + T_VISITE + "." + C_VST_ID_TRN_ID + " = " + T_TOURNEE + "." + C_TRN_ID //
	// + "  and " + T_VISITE + "." + C_VST_IS_TERMINE + " = " + C_VST_AFAIRE //
	// + " ) <= 0 ) and ( " + T_TOURNEE + "." + C_TRN_ID + "=" + p_trnId + ")"; //
	// db.execSQL(sql);
	// }

	/*
	 * DETAIL D'UNE TOURNEE = liste de RDV select v.vst_lieu, v.vst_horaire, b.bnf_libelle, v.vst_secteur, v.vst_id_prt_id, v.vst_garde,
	 * v.vst_istermine from visite v left outer join benef b on (v.vst_id_bnf_id = b._id) left outer join tournee t on (v.vst_id_trn_id =
	 * t._id) where (v.vst_id_trn_id=5) or ( (t.trn_shortdate='18/08/2010') and ((t.trn_id_prt_id is null) or (t.trn_id_prt_id=0) )) order
	 * by v.vst_istermine ASC, v.vst_garde ASC, v.vst_id_prt_id DESC, v.vst_horaireiso8601 ASC La liste doit rapporter - les RDV de la
	 * tournée p_trn_id - plus les RDV à la date de p_trn_id non attribués ordonnés comme suit - les RDV 'à faire' d'abord - les RDV sans
	 * horaire en premier - puis ordonnés par horaire croissant - les RDV 'en garde' ou 'non attribué' à la fin
	 */
	public Cursor recupererLaListeDesVisitesOrdonneesDeTrn(long p_trnId, String p_trnShortdate) {
		String clausewhere = "where (" + T_VISITE + "." + C_VST_ID_TRN_ID + "=?) or ( (" + T_TOURNEE + "." + C_TRN_SHORTDATE + "=?) and (("
				+ T_TOURNEE + "." + C_TRN_ID_PRT_ID + " is null) or (" + T_TOURNEE + "." + C_TRN_ID_PRT_ID + "=0) ))";
		String requete = "select " + T_VISITE + "." + C_VST_ID + ", " + T_VISITE + "." + C_VST_LIEU + ", " + T_TOURNEE + "."
				+ C_TRN_SHORTDATE + ", " + T_VISITE + "." + C_VST_HORAIRE + ", " + T_VISITE + "." + C_VST_ID_BNF_ID + ", " + T_BENEF + "."
				+ C_BNF_LIBELLE + ", " + T_BENEF + "." + C_BNF_DATENAIS + ", " + T_VISITE + "." + C_VST_SECTEUR + ", " + T_VISITE + "."
				+ C_VST_ID_PRT_ID + ", " + T_VISITE + "." + C_VST_GARDE + ", " + T_VISITE + "." + C_VST_IS_TERMINE + " " + "from "
				+ T_VISITE + " " + "left outer join " + T_BENEF + " on (" + T_VISITE + "." + C_VST_ID_BNF_ID + " = " + T_BENEF + "."
				+ C_BNF_ID + ") " + "left outer join " + T_TOURNEE + " on (" + T_VISITE + "." + C_VST_ID_TRN_ID + " = " + T_TOURNEE + "."
				+ C_TRN_ID + ") " + clausewhere + "order by "
				// +T_VISITE+"."+C_VST_IS_TERMINE+" ASC, " ne pas placer les
				// 'finis' en fin de liste
				+ T_VISITE + "." + C_VST_GARDE + " ASC, "
				// +T_VISITE+"."+C_VST_ID_PRT_ID+" DESC, " ne pas placer les
				// non-attribués en fin de liste
				+ T_VISITE + "." + C_VST_HORAIRE_ISO8601 + " ASC ";
		return db.rawQuery(requete, new String[] { "" + p_trnId, p_trnShortdate });
	}

	// todo vérifier s'il sera nécessaire de créer des index sur les champs en
	// "order by"

	/*
	 * Retrouver toutes les alertes de rendez-vous ordonnées par date_heure de rendez-vous le curseur fourni les mêmes champs que
	 * recupererLaListeDesVisitesOrdonneesDeTrn afin d'être utilisé par VisteListAdapter
	 */
	public Cursor recupererLaListeDesVisitesOrdonneesAvecAlerte() {
		String requete = "select " + T_VISITE + "." + C_VST_ID + ", " + T_VISITE + "." + C_VST_LIEU + ", " + T_TOURNEE + "."
				+ C_TRN_SHORTDATE + ", " + T_VISITE + "." + C_VST_HORAIRE + ", " + T_VISITE + "." + C_VST_ID_BNF_ID + ", " + T_BENEF + "."
				+ C_BNF_LIBELLE + ", " + T_BENEF + "." + C_BNF_DATENAIS + ", " + T_VISITE + "." + C_VST_SECTEUR + ", " + T_VISITE + "."
				+ C_VST_ID_PRT_ID + ", " + T_VISITE + "." + C_VST_GARDE + ", " + T_VISITE + "." + C_VST_IS_TERMINE + " " + "from "
				+ T_VSTALERTE + " " + "left outer join " + T_VISITE + " on (" + T_VSTALERTE + "." + C_VSTALT_ID_VST_ID + " = " + T_VISITE
				+ "." + C_VST_ID + ") " + "left outer join " + T_BENEF + " on (" + T_VISITE + "." + C_VST_ID_BNF_ID + " = " + T_BENEF + "."
				+ C_BNF_ID + ") " + "left outer join " + T_TOURNEE + " on (" + T_VISITE + "." + C_VST_ID_TRN_ID + " = " + T_TOURNEE + "."
				+ C_TRN_ID + ") " + "order by "
				// +T_VISITE+"."+C_VST_IS_TERMINE+" ASC, " ne pas placer les
				// 'finis' en fin de liste
				+ T_VISITE + "." + C_VST_GARDE + " ASC, "
				// +T_VISITE+"."+C_VST_ID_PRT_ID+" DESC, " ne pas placer les
				// non-attribués en fin de liste
				+ T_VISITE + "." + C_VST_HORAIRE_ISO8601 + " ASC ";
		return db.rawQuery(requete, null);
	}

	// DETAIL D'UN RDV
	public Cursor recupererLeDetailDeLaVisite(long p_vstId) {
		String clausewhere = "where (" + T_VISITE + "." + C_VST_ID + "=?)";
		String requete = "select " + T_VISITE + "." + C_VST_ID + ", " + T_PRATICIEN + "." + C_PRT_LIBELLE + ", " + T_PRATICIEN + "."
				+ C_PRT_COLOR + ", " + T_VISITE + "." + C_VST_LIEU + ", " + T_VISITE + "." + C_VST_HORAIRE + ", " + T_VISITE + "."
				+ C_VST_HORAIRE_ISO8601 + ", " + T_TOURNEE + "." + C_TRN_SHORTDATE + ", " + T_TOURNEE + "." + C_TRN_LONGDATE + ", "
				+ T_VISITE + "." + C_VST_ID_BNF_ID + ", " + T_BENEF + "." + C_BNF_CLEBENEF + ", " + T_BENEF + "." + C_BNF_LIBELLE + ", "
				+ T_BENEF + "." + C_BNF_DATENAIS + ", " + T_BENEF + "." + C_BNF_AGE + ", " + T_BENEF + "." + C_BNF_DATE_LECT_CV + ", "
				+ T_BENEF + "." + C_BNF_NOTE + ", " + T_VISITE + "." + C_VST_GARDE + ", " + T_VISITE + "." + C_VST_IS_TERMINE + " "
				+ "from " + T_VISITE + " " + "left outer join " + T_BENEF + " on (" + T_VISITE + "." + C_VST_ID_BNF_ID + " = " + T_BENEF
				+ "." + C_BNF_ID + ") " + "left outer join " + T_TOURNEE + " on (" + T_VISITE + "." + C_VST_ID_TRN_ID + " = " + T_TOURNEE
				+ "." + C_TRN_ID + ") " + "left outer join " + T_PRATICIEN + " on (" + T_VISITE + "." + C_VST_ID_PRT_ID + " = "
				+ T_PRATICIEN + "." + C_PRT_ID + ") " + clausewhere;
		return db.rawQuery(requete, new String[] { "" + p_vstId });
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// T_VSTALERTE
	// public long insererUneAlerte(String p_libelle, Date p_datealerte, long p_vstid) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_VSTALT_ID_VST_ID, p_vstid);
	// values.put(C_VSTALT_LIBELLE, p_libelle);
	// String when = StringHelper.DateToIso8601(p_datealerte);
	// values.put(C_VSTALT_DATETIME_ISO8601, when);
	// long retour = db.insert(T_VSTALERTE, null, values);
	// return retour;
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	public boolean supprimerLesAlertesDUneVisite(long p_vstid) {
		// renvoie une valeur supérieure à 0 si l'enreg a été supprimé
		return db.delete(T_VSTALERTE, C_VSTALT_ID_VST_ID + "=" + p_vstid, null) > 0;
	}

	// renvoyer un cursor contenant TOUS les champs de toutes les alertes créées
	// pour la visite p_vstid
	public Cursor recupereAlerteDeVisite(long p_vstid) {
		try {
			return db.query(T_VSTALERTE, new String[] { C_VSTALT_ID, C_VSTALT_ID_VST_ID, C_VSTALT_DATETIME_ISO8601, C_VSTALT_LIBELLE },
					C_VSTALT_ID_VST_ID + "=?", // selection,
					new String[] { "" + p_vstid }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	public boolean isAuMoinsUneAlerteDeVisite(long p_vstid) {
		long vstalt_id = 0;
		if (p_vstid > 0) {
			Cursor c = db.query(T_VSTALERTE, new String[] { C_VSTALT_ID }, C_VSTALT_ID_VST_ID + "=?", new String[] { "" + p_vstid }, null,
					null, null);
			try {
				if (c.moveToNext()) {
					vstalt_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return (vstalt_id > 0);
	}

	/*
	 * Renvoie 0 si aucune alerte de rendez-vous sinon renvoie le nombre total quelque soit la tournée et le praticien
	 */
	public int getNombreTotalAlertes() {
		int nombre = 0;
		Cursor c = db.query(T_VSTALERTE, new String[] { C_VSTALT_ID }, null, null, null, null, null);
		try {
			nombre = c.getCount();
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		} finally {
			c.close();
		}
		return nombre;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// ORDO
	// public long insererUneOrdo(xml_Ordo p_xordo, long p_vstid, long p_trnid, long p_prtid, long p_bnfid, long p_medid) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_ORD_ID_VST_ID, p_vstid);
	// values.put(C_ORD_ID_TRN_ID, p_trnid);
	// values.put(C_ORD_ID_PRT_ID, p_prtid);
	// values.put(C_ORD_ID_BNF_ID, p_bnfid);
	// values.put(C_ORD_ID_MED_ID, p_medid);
	// values.put(C_ORD_NFG, p_xordo.getordoNFG());
	// values.put(C_ORD_NUM, p_xordo.getordoNum());
	// values.put(C_ORD_DATE, p_xordo.getordoDate());
	// values.put(C_ORD_DATE_ISO8601, this.JJMMAAAAToIso8601(p_xordo.getordoDate()));
	// values.put(C_ORD_FINTRT, p_xordo.getordoFinTrt());
	// values.put(C_ORD_NBRE_ACTES, p_xordo.getordoNbActes());
	// values.put(C_ORD_TYPE_SOIN, p_xordo.getordoTypeSoin());
	// values.put(C_ORD_DATE_SUIVANTE, p_xordo.getordoDateSuiv());
	// values.put(C_ORD_COMMENT_TRT, p_xordo.getordoCommentTrt());
	// values.put(C_ORD_COMMENT_VISITE, p_xordo.getordoCommentVisite());
	// return db.insert(T_ORDO, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// private boolean supprimerUneOrdo(long p_id) {
	// // renvoie une valeur supérieure à 0 si l'enreg a été supprimé
	// return db.delete(T_ORDO, C_ORD_ID + "=" + p_id, null) > 0;
	// }

	public Cursor recupereOrdobyID(long p_ordId) {
		try {
			return db.query(T_ORDO, new String[] { C_ORD_ID, C_ORD_ID_VST_ID, C_ORD_ID_TRN_ID, C_ORD_ID_PRT_ID, C_ORD_ID_BNF_ID,
					C_ORD_ID_MED_ID, C_ORD_NFG, C_ORD_NUM, C_ORD_DATE, C_ORD_DATE_ISO8601, C_ORD_FINTRT, C_ORD_NBRE_ACTES, C_ORD_TYPE_SOIN,
					C_ORD_DATE_SUIVANTE, C_ORD_COMMENT_TRT, C_ORD_COMMENT_VISITE }, // tous les champs
					C_ORD_ID + "=?", // selection,
					new String[] { "" + p_ordId }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	/*
	 * outre le commentaire posé sur le RDV dans EQUINOX prendre en compte les saisies locales dans bloc-notes public boolean
	 * isAuMoinsUnCommentVisite(long p_vstid){ long ord_id=0; if (p_vstid > 0) { Cursor c = db.query(T_ORDO, new String[]{C_ORD_ID},
	 * "("+C_ORD_ID_VST_ID+"=?) AND (" +C_ORD_COMMENT_VISITE+" IS NOT NULL) AND ("+C_ORD_COMMENT_VISITE+"<>?)", new String[]{""+p_vstid,
	 * ""}, null, null, null); try { if (c.moveToNext()) { ord_id = c.getLong(0); } } catch (SQLException e) { Log.d(TAG,
	 * ">> isAuMoinsUnCommentVisite ERROR: " + e.getLocalizedMessage()); throw e; } finally { c.close(); } } return (ord_id > 0); }* select
	 * ordo._id from ordo left outer join BENEF on (ORDO.ORD_ID_BNF_ID = BENEF._ID) where (ORD_ID_VST_ID=8) AND (((ord_commentVst IS NOT
	 * NULL) AND (ord_commentVst<>"")) OR ((BNF_NOTE IS NOT NULL) AND (BNF_NOTE<>"")) )
	 */
	public boolean isAuMoinsUnCommentVisite(long p_vstid) {
		long ord_id = 0;
		if (p_vstid > 0) {
			String requete = "select " + T_ORDO + "." + C_ORD_ID + " " + "from " + T_ORDO + " " + "left outer join " + T_BENEF + " on ("
					+ T_ORDO + "." + C_ORD_ID_BNF_ID + " = " + T_BENEF + "." + C_BNF_ID + ") " + "where " + "(" + C_ORD_ID_VST_ID
					+ "=?) AND ( " + "((" + C_ORD_COMMENT_VISITE + " IS NOT NULL) AND (" + C_ORD_COMMENT_VISITE + "<>?)) OR " + "(("
					+ C_BNF_NOTE + " IS NOT NULL) AND (" + C_BNF_NOTE + "<>?)) )";

			Cursor c = db.rawQuery(requete, new String[] { "" + p_vstid, "", "" });
			try {
				if (c.moveToNext()) {
					ord_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return (ord_id > 0);
	}

	// le SimpleCursorAdapter nécessite un champ _id, la requête doit comporter
	// une clause GROUP BY pour associer ensemble
	// les traitements d'une même ordo dans une même ligne ordo
	// BIDOUILLE ici pour avoir une colonne _id sur la foreign key ord_id_vst_id
	public Cursor recupererLesOrdosDeLaVisite(long p_vstId) {
		try {
			String requete = "select "
					// +T_ORDO+"."+C_ORD_ID+", "
					+ C_ORD_ID_VST_ID + " as " + C_VST_ID + ", " + C_ORD_ID + " as " + C_AS_ORD_ID + ", " + C_ORD_NUM + ", " + C_ORD_DATE
					+ " " + "from " + T_ORDO + " " + "where " + C_ORD_ID_VST_ID + "=? " + "group by " + C_ORD_NUM + ", " + C_ORD_DATE + " ";
			return db.rawQuery(requete, new String[] { "" + p_vstId });
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	//
	public Cursor recupererLesTrtDeLOrdo(long p_vstId, String p_ordNum) {
		try {
			String requete = "select " + T_ORDO + "." + C_ORD_ID + ", " + T_ORDO + "." + C_ORD_TYPE_SOIN + ", " + T_ORDO + "."
					+ C_ORD_COMMENT_TRT + ", " + T_ORDO + "." + C_ORD_NBRE_ACTES + ", " + T_ORDO + "." + C_ORD_DATE_SUIVANTE + ", "
					+ T_ORDO + "." + C_ORD_FINTRT + ", " + T_ORDO + "." + C_ORD_COMMENT_VISITE + ", " + T_ORDO + "." + C_ORD_NFG + " "
					+ "from " + T_ORDO + " " + "where (" + T_ORDO + "." + C_ORD_ID_VST_ID + "=?) AND (" + T_ORDO + "." + C_ORD_NUM + "=?) ";
			return db.rawQuery(requete, new String[] { "" + p_vstId, p_ordNum });
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// MEDECIN
	// public long insererUnMedecin(xml_Ordo p_xordo) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_MED_NOM, p_xordo.getordoMedNom());
	// values.put(C_MED_FINESS, p_xordo.getordoMedFiness());
	// values.put(C_MED_TEL1, p_xordo.getordoMedTel1());
	// values.put(C_MED_TEL2, p_xordo.getordoMedTel2());
	// values.put(C_MED_EMAIL, p_xordo.getordoMedEmail());
	// values.put(C_MED_ADR, p_xordo.getordoMedAdr());
	// values.put(C_MED_CODEP, p_xordo.getordoMedCodeP());
	// values.put(C_MED_COMMUNE, p_xordo.getordoMedCommune());
	// return db.insert(T_MEDECIN, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// public void mettreajourUnMedecin(long p_medid, xml_Ordo p_xordo) {
	// try {
	// ContentValues values = new ContentValues();
	// values.put(C_MED_NOM, p_xordo.getordoMedNom());
	// values.put(C_MED_FINESS, p_xordo.getordoMedFiness());
	// values.put(C_MED_TEL1, p_xordo.getordoMedTel1());
	// values.put(C_MED_TEL2, p_xordo.getordoMedTel2());
	// values.put(C_MED_EMAIL, p_xordo.getordoMedEmail());
	// values.put(C_MED_ADR, p_xordo.getordoMedAdr());
	// values.put(C_MED_CODEP, p_xordo.getordoMedCodeP());
	// values.put(C_MED_COMMUNE, p_xordo.getordoMedCommune());
	// db.update(T_MEDECIN, values, C_MED_ID + "=?", new String[] { "" + p_medid });
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// private boolean supprimerUnMedecin(long p_id) {
	// // renvoie une valeur supérieure à 0 si l'enreg a été supprimé
	// return db.delete(T_MEDECIN, C_MED_ID + "=" + p_id, null) > 0;
	// }

	public Cursor recupererLaListeDesMedecins() {
		try {
			return db.query(T_MEDECIN, new String[] { C_MED_ID, C_MED_NOM, C_MED_FINESS, C_MED_TEL1, C_MED_TEL2, C_MED_EMAIL, C_MED_ADR,
					C_MED_CODEP, C_MED_COMMUNE }, // tous les champs
					null, // selection,
					null, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	public Cursor recupereMedecinbyID(long p_medId) {
		try {
			return db.query(T_MEDECIN, new String[] { C_MED_ID, C_MED_NOM, C_MED_FINESS, C_MED_TEL1, C_MED_TEL2, C_MED_EMAIL, C_MED_ADR,
					C_MED_CODEP, C_MED_COMMUNE }, // tous les champs
					C_MED_ID + "=?", // selection,
					new String[] { "" + p_medId }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	/*
	 * renvoie med_id du medecin prescripteur de finess p_finess renvoie 0 si non trouvé
	 */
	public long find_med_idByFiness(String p_finess) {
		long med_id = 0;
		if (!("").equals(p_finess.trim())) {
			Cursor c = db.query(T_MEDECIN, new String[] { C_MED_ID }, C_MED_FINESS + "=?", new String[] { p_finess }, null, null, null);
			try {
				if (c.moveToNext()) {
					med_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return med_id;
	}

	/*
	 * renvoie med_id du medecin prescripteur de nom p_nom renvoie 0 si non trouvé
	 */
	public long find_med_idByNom(String p_nom) {
		long med_id = 0;
		if (!("").equals(p_nom.trim())) {
			Cursor c = db.query(T_MEDECIN, new String[] { C_MED_ID }, C_MED_NOM + "=?", new String[] { p_nom }, null, null, null);
			try {
				if (c.moveToNext()) {
					med_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return med_id;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// BENEF
	// méthode commune à l'insertion et à la mise à jour
	// private ContentValues construireValuesBenef(xml_Benef p_xbenef) {
	// ContentValues values = new ContentValues();
	// values.put(C_BNF_CLEBENEF, p_xbenef.getBenefCle());
	// values.put(C_BNF_NOM, p_xbenef.getBenefNom());
	// values.put(C_BNF_PRENOM, p_xbenef.getBenefPrenom());
	// values.put(C_BNF_TEL1, p_xbenef.getBenefTel1());
	// values.put(C_BNF_TEL2, p_xbenef.getBenefTel2());
	// values.put(C_BNF_ADRESSE, p_xbenef.getBenefAdr1());
	// values.put(C_BNF_CPOSTAL, p_xbenef.getBenefCPos1());
	// values.put(C_BNF_COMMUNE, p_xbenef.getBenefCom1());
	// values.put(C_BNF_MAP, p_xbenef.getBenefMap1());
	// values.put(C_BNF_DATENAIS, p_xbenef.getBenefDNais());
	// values.put(C_BNF_AGE, p_xbenef.getBenefAge());
	// values.put(C_BNF_LIBELLE, p_xbenef.getBenefLibelle());
	// values.put(C_BNF_SECTEUR, p_xbenef.getBenefSecteur());
	// values.put(C_BNF_CIVILITE, p_xbenef.getBenefCivilite());
	// values.put(C_BNF_TEL3, p_xbenef.getBenefTel3());
	// values.put(C_BNF_ADRESSE2, p_xbenef.getBenefAdr2());
	// values.put(C_BNF_CPOSTAL2, p_xbenef.getBenefCPos2());
	// values.put(C_BNF_COMMUNE2, p_xbenef.getBenefCom2());
	// values.put(C_BNF_QUALITE, p_xbenef.getBenefQualite());
	// values.put(C_BNF_DATE_LECT_CV, p_xbenef.getBenefDateLectCV());
	// values.put(C_BNF_DATE_LECT_CV_ISO8601, this.JJMMAAAAToIso8601(p_xbenef.getBenefDateLectCV()));
	// values.put(C_BNF_COMMENT, p_xbenef.getBenefComment());
	// return values;
	// }

	// public long insererUnBenef(xml_Benef p_xbenef) {
	// try {
	// ContentValues values = construireValuesBenef(p_xbenef);
	// return db.insert(T_BENEF, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }
	//
	// public void mettreajourUnBenef(long p_bnfid, xml_Benef p_xbenef) {
	// try {
	// ContentValues values = construireValuesBenef(p_xbenef);
	// db.update(T_BENEF, values, C_BNF_ID + "=?", new String[] { "" + p_bnfid });
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// mise à jour de la note du bénéficiaire
	public void updateBenefNote(long p_bnfid, String p_note) {
		try {
			ContentValues values = new ContentValues();
			values.put(C_BNF_NOTE, p_note);
			db.update(T_BENEF, values, C_BNF_ID + "=?", new String[] { "" + p_bnfid });
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	/**
	 * Renvoie TRUE si le bénéficiaire de clé primaire p_bnfId peut être supprimé c'est à dire si aucune visite ne fait référence à ce
	 * bénéficiaire
	 * @param p_bnfId clé primaire benef
	 * @return true si pas de visite pour ce benef
	 */
	private boolean isBenefSupprimable(long p_bnfId) {
		long vst_id = 0;
		if (p_bnfId > 0) {
			Cursor c = db.query(T_VISITE, new String[] { C_VST_ID }, "(" + C_VST_ID_BNF_ID + "=?)", new String[] { "" + p_bnfId }, null,
					null, null);
			try {
				if (c.moveToNext()) {
					vst_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return (vst_id <= 0);
	}

	private boolean supprimerUnBenef(long p_id) {
		// renvoie une valeur supérieure à 0 si l'enreg a été supprimé
		return db.delete(T_BENEF, C_BNF_ID + "=" + p_id, null) > 0;
	}

	/**
	 * Si le bénéficiaire de clé primaire p_bnfId n'est pas référencé dans une visite alors il est supprimé les trigger BEFORE DELETE créés
	 * pour bnfcaisse + bnflastvisite + bnfmutuelle sont exécutés à la suppression de p_bnfId
	 * @param p_bnfId
	 * @return
	 */
	public boolean tenterLaSuppressionUnBenef(long p_bnfId) {
		boolean retour = isBenefSupprimable(p_bnfId);
		if (retour) {
			retour = supprimerUnBenef(p_bnfId);
		}
		return retour;
	}

	public Cursor recupererLaListeDesBenefs() {
		try {
			return db.query(T_BENEF, new String[] { C_BNF_ID, C_BNF_CLEBENEF, C_BNF_NOM, C_BNF_PRENOM, C_BNF_DATENAIS, C_BNF_LIBELLE }, // nbre
																																		// champs
																																		// réduit
					null, // selection,
					null, // selectionArgs,
					null, // groupBy,
					null, // having,
					C_BNF_LIBELLE); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	public long find_bnf_idByCleBenef(String p_clebenef) {
		long bnf_id;
		Cursor c = db.query(T_BENEF, new String[] { C_BNF_ID, C_BNF_CLEBENEF }, C_BNF_CLEBENEF + "=?", new String[] { p_clebenef }, null,
				null, null);
		try {
			if (c.moveToNext()) {
				bnf_id = c.getLong(0);
			} else {
				bnf_id = 0;
			}
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		} finally {
			c.close();
		}
		return bnf_id;
	}

	// renvoyer un cursor contenant tous les champs de la ligne d'id unique
	// p_bnfId
	public Cursor recupereBenefbyID(long p_bnfId) {
		try {
			return db.query(T_BENEF, new String[] { C_BNF_ID, C_BNF_CLEBENEF, C_BNF_NOM, C_BNF_PRENOM, C_BNF_TEL1, C_BNF_TEL2,
					C_BNF_ADRESSE, C_BNF_CPOSTAL, C_BNF_COMMUNE, C_BNF_MAP, C_BNF_DATENAIS, C_BNF_AGE, C_BNF_LIBELLE, C_BNF_SECTEUR,
					C_BNF_CIVILITE, C_BNF_TEL3, C_BNF_ADRESSE2, C_BNF_CPOSTAL2, C_BNF_COMMUNE2, C_BNF_QUALITE, C_BNF_DATE_LECT_CV,
					C_BNF_DATE_LECT_CV_ISO8601, C_BNF_COMMENT, C_BNF_NOTE }, // tous
																				// les
																				// champs

					C_BNF_ID + "=?", // selection,
					new String[] { "" + p_bnfId }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	public Cursor recupererLaListeDesBenefsDe(StringBuilder p_whereClause, String[] p_args, String p_orderBy) {
		try {
			return db.query(T_BENEF, new String[] { C_BNF_ID, C_BNF_CLEBENEF, C_BNF_NOM, C_BNF_PRENOM, C_BNF_DATENAIS, C_BNF_LIBELLE }, // nbre
																																		// champs
																																		// réduit
					p_whereClause == null ? null : p_whereClause.toString(), // selection,
					p_args, // selectionArgs,
					null, // groupBy,
					null, // having,
					p_orderBy); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	public boolean isNoteRenseigneePourBenef(long p_bnfId) {
		boolean retour = false;
		if (p_bnfId > 0) {
			Cursor c = db.query(T_BENEF, new String[] { C_BNF_ID }, "(" + C_BNF_ID + "=?) AND ((" + C_BNF_NOTE + " IS NOT NULL) OR ("
					+ C_BNF_NOTE + " <> ?))", new String[] { "" + p_bnfId, "" }, null, null, null);
			try {
				retour = (c.moveToNext());
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return retour;
	}

	public String getNoteDeBenef(long p_bnfId) {
		String retour = "";
		if (p_bnfId > 0) {
			Cursor c = db.query(T_BENEF, new String[] { C_BNF_NOTE }, "(" + C_BNF_ID + "=?)", new String[] { "" + p_bnfId }, null, null,
					null);
			try {
				if (c.moveToNext()) {
					retour = c.getString(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return retour;
	}

	// ======================================================================================================================

	// --------------------------------------------------------------------------------------------------------------------------
	// T_BNFCAISSE
	// méthode commune à l'insertion et à la mise à jour
	// private ContentValues construireValuesBenefCai(xml_Benef p_xbenef, long p_bnfId) {
	// xml_BenefCai bc = p_xbenef.getbenefcai();
	// ContentValues values = new ContentValues();
	// values.put(C_BNFCAI_ID_BNF_ID, p_bnfId);
	// values.put(C_BNFCAI_ASS_NOM, bc.getbnfcaiAssNom());
	// values.put(C_BNFCAI_ASS_PNOM, bc.getbnfcaiAssPnom());
	// values.put(C_BNFCAI_ASS_INSEE, bc.getbnfcaiAssInsee());
	// values.put(C_BNFCAI_GRDRG, bc.getbnfcaiGrdRg());
	// values.put(C_BNFCAI_CAIGEST, bc.getbnfcaiCaiGest());
	// values.put(C_BNFCAI_CENGEST, bc.getbnfcaiCenGest());
	// values.put(C_BNFCAI_LIBELLE, bc.getbnfcaiLibelle());
	// values.put(C_BNFCAI_TEL, bc.getbnfcaiTel());
	// values.put(C_BNFCAI_ADR1, bc.getbnfcaiAdr1());
	// values.put(C_BNFCAI_ADR2, bc.getbnfcaiAdr2());
	// values.put(C_BNFCAI_CPOSTAL, bc.getbnfcaiCpostal());
	// values.put(C_BNFCAI_COMMUNE, bc.getbnfcaiCommune());
	// values.put(C_BNFCAI_EMAIL, bc.getbnfcaiEmail());
	// values.put(C_BNFCAI_REF_GIE, bc.getbnfcaiRefGie());
	// values.put(C_BNFCAI_JUSTIF_AMO, bc.getbnfcaiJustAmo());
	// return values;
	// }

	// public long insererUnBenefCai(xml_Benef p_xbenef, long p_bnfId) {
	// if (p_xbenef.getbenefcai().bnfcaiIsNull()) {
	// return 0;
	// }
	//
	// try {
	// ContentValues values = construireValuesBenefCai(p_xbenef, p_bnfId);
	// return db.insert(T_BNFCAISSE, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }
	//
	// public void mettreajourUnBenefCai(xml_Benef p_xbenef, long p_bnfId) {
	// try {
	// // on ne se pose pas de question on supprime et on remplace si c'est
	// // utile
	// supprimerUnBenefCai(p_bnfId);
	// insererUnBenefCai(p_xbenef, p_bnfId);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// p_bnfid la foreign key représentant benef
	private boolean supprimerUnBenefCai(long p_bnfid) {
		// renvoie une valeur supérieure à 0 si l'enreg a été supprimé
		return db.delete(T_BNFCAISSE, C_BNFCAI_ID_BNF_ID + "=" + p_bnfid, null) > 0;
	}

	// renvoyer un cursor contenant tous les champs de la ligne d'id unique
	// p_bnfId correspondant
	public Cursor recupereBenefCaibyBnf_Id(long p_bnfId) {
		try {
			return db.query(T_BNFCAISSE, new String[] { C_BNFCAI_ID, C_BNFCAI_ID_BNF_ID, C_BNFCAI_ASS_NOM, C_BNFCAI_ASS_PNOM,
					C_BNFCAI_ASS_INSEE, C_BNFCAI_GRDRG, C_BNFCAI_CAIGEST, C_BNFCAI_CENGEST, C_BNFCAI_LIBELLE, C_BNFCAI_TEL, C_BNFCAI_ADR1,
					C_BNFCAI_ADR2, C_BNFCAI_CPOSTAL, C_BNFCAI_COMMUNE, C_BNFCAI_EMAIL, C_BNFCAI_REF_GIE, C_BNFCAI_JUSTIF_AMO }, // tous les
																																// champs
					C_BNFCAI_ID_BNF_ID + "=?", // selection,
					new String[] { "" + p_bnfId }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// ======================================================================================================================

	// --------------------------------------------------------------------------------------------------------------------------
	// T_BNFMUTUELLE
	// méthode commune à l'insertion et à la mise à jour
	// private ContentValues construireValuesBenefMut(xml_Benef p_xbenef, long p_bnfId) {
	// xml_BenefMut bc = p_xbenef.getbenefmut();
	// ContentValues values = new ContentValues();
	// values.put(C_BNFMUT_ID_BNF_ID, p_bnfId);
	// values.put(C_BNFMUT_IDENT, bc.getbnfmutident());
	// values.put(C_BNFMUT_LIBELLE, bc.getbnfmutlibelle());
	// values.put(C_BNFMUT_CATEGORIE, bc.getbnfmutcategorie());
	// values.put(C_BNFMUT_GEST_UNIQUE, bc.getbnfmutgestunique());
	// values.put(C_BNFMUT_JUSTIF_AMC, bc.getbnfmutjustifamc());
	// values.put(C_BNFMUT_DATE_DROITS_DEB, bc.getbnfmutdatedroitsdeb());
	// values.put(C_BNFMUT_DATE_DROITS_FIN, bc.getbnfmutdatedroitsfin());
	// values.put(C_BNFMUT_TEL, bc.getbnfmuttel());
	// values.put(C_BNFMUT_ADR, bc.getbnfmutadr());
	// values.put(C_BNFMUT_CPOSTAL, bc.getbnfmutcpostal());
	// values.put(C_BNFMUT_COMMUNE, bc.getbnfmutcommune());
	// return values;
	// }
	//
	// public long insererUnBenefMut(xml_Benef p_xbenef, long p_bnfId) {
	// if (p_xbenef.getbenefmut().bnfmutIsNull()) {
	// return 0;
	// }
	//
	// try {
	// ContentValues values = construireValuesBenefMut(p_xbenef, p_bnfId);
	// return db.insert(T_BNFMUTUELLE, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }
	//
	// public void mettreajourUnBenefMut(xml_Benef p_xbenef, long p_bnfId) {
	// try {
	// // on ne se pose pas de question on supprime et on remplace si c'est
	// // utile
	// supprimerUnBenefMut(p_bnfId);
	// insererUnBenefMut(p_xbenef, p_bnfId);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// p_bnfid la foreign key représentant benef
	private boolean supprimerUnBenefMut(long p_bnfid) {
		// renvoie une valeur supérieure à 0 si l'enreg a été supprimé
		return db.delete(T_BNFMUTUELLE, C_BNFMUT_ID_BNF_ID + "=" + p_bnfid, null) > 0;
	}

	// renvoyer un cursor contenant tous les champs de la ligne d'id unique
	// p_bnfId correspondant
	public Cursor recupereBenefMutbyBnf_Id(long p_bnfId) {
		try {
			return db.query(T_BNFMUTUELLE, new String[] { C_BNFMUT_ID, C_BNFMUT_ID_BNF_ID, C_BNFMUT_IDENT, C_BNFMUT_LIBELLE,
					C_BNFMUT_CATEGORIE, C_BNFMUT_GEST_UNIQUE, C_BNFMUT_JUSTIF_AMC, C_BNFMUT_DATE_DROITS_DEB, C_BNFMUT_DATE_DROITS_FIN,
					C_BNFMUT_TEL, C_BNFMUT_ADR, C_BNFMUT_CPOSTAL, C_BNFMUT_COMMUNE }, // tous les champs
					C_BNFMUT_ID_BNF_ID + "=?", // selection,
					new String[] { "" + p_bnfId }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// ======================================================================================================================

	// --------------------------------------------------------------------------------------------------------------------------
	// T_BNFLASTVISITE
	/**
	 * Insérer dans table bnflastvisite une association p_bnfId-p_dervstdate si p_dervstdate est vraiment la date de rendez-vous la plus
	 * grande
	 * @return la clé primaire de l'enregistrement créé
	 */
	public long insererDerniereVisiteSiNecessaire(long p_bnfId, long p_vstId, long p_prtId, String p_dervstprat, String p_dervstdate) {
		long retour = isAuMoinsUneDerniereVisiteSupOuEgale(p_bnfId, p_dervstdate);
		if (retour == 0) {
			// effacer éventuelle visite de date-heure inférieure
			supprimerDerniereVisiteDuBenef(p_bnfId);
			// inscrire le rendez-vous le plus récent
			retour = insererUneDerniereVisite(p_bnfId, p_vstId, p_prtId, p_dervstprat, p_dervstdate);
		}
		return retour;
	}

	/**
	 * Créer un enregistrement dans table bnflastvisite pour associer benef p_bnfId et visite p_vstId/p_dervstdate
	 * @param p_bnfId clé primaire du bénéf
	 * @param p_vstId clé primaire de la visite
	 * @param p_prtId 0 ou clé primaire du praticien à qui la visite est attribuée
	 * @param p_dervstprat '' ou libellé du praticien
	 * @param p_dervstdate date-heure visite au format AAAA-MM-JJ HH:MM
	 * @return clé primaire de l'enreg créé
	 */
	private long insererUneDerniereVisite(long p_bnfId, long p_vstId, long p_prtId, String p_dervstprat, String p_dervstdate) {
		try {
			ContentValues values = new ContentValues();
			values.put(C_BNFVST_ID_BNF_ID, p_bnfId);
			values.put(C_BNFVST_ID_VST_ID, p_vstId);
			values.put(C_BNFVST_ID_PRT_ID, p_prtId);
			values.put(C_BNFVST_DERVSTDATE, p_dervstdate);
			values.put(C_BNFVST_DERVSTPRAT, p_dervstprat);
			long retour = db.insert(T_BNFLASTVISITE, null, values);
			return retour;
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	/**
	 * Supprimer de la table bnflastvisite toute association benef/derniere visite du bénéf de clé p_bnfId
	 * @param p_bnfId clé primaire du bénéficiaire
	 * @return TRUE si suppression faite
	 */
	private boolean supprimerDerniereVisiteDuBenef(long p_bnfId) {
		// renvoie une valeur supérieure à 0 si l'enreg a été supprimé
		return db.delete(T_BNFLASTVISITE, C_BNFVST_ID_BNF_ID + "=" + p_bnfId, null) > 0;
	}

	/**
	 * Renvoie un curseur d'association benef/derniere date visite pour beneficiaire p_bnfId
	 * @param p_bnfId clé primaire du bénéficiaire
	 * @return cursor
	 */
	public Cursor recupereDerniereVisiteDeBenef(long p_bnfId) {
		try {
			return db.query(T_BNFLASTVISITE, new String[] { C_BNFVST_ID, C_BNFVST_ID_BNF_ID, C_BNFVST_ID_VST_ID, C_BNFVST_ID_PRT_ID,
					C_BNFVST_DERVSTDATE, C_BNFVST_DERVSTPRAT }, C_BNFVST_ID_BNF_ID + "=?", // selection,
					new String[] { "" + p_bnfId }, // selectionArgs,
					null, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	/**
	 * Renvoie <> 0 si trouve une visite de date >= à p_dervstdate associée au bénéficiaire p_bnfId
	 * @param p_bnfId clé primaire bénéficiaire
	 * @param p_dervstdate date/heure de la visite dont il faut vérifier l'éventuelle référence au format AAAA-MM-JJ HH:MM
	 * @return valeur de clé si association déjà faite 0 sinon
	 */
	private long isAuMoinsUneDerniereVisiteSupOuEgale(long p_bnfId, String p_dervstdate) {
		long bnfvst_id = 0;
		if (p_bnfId > 0) {
			Cursor c = db.query(T_BNFLASTVISITE, new String[] { C_BNFVST_ID }, "(" + C_BNFVST_ID_BNF_ID + "=?) AND (" + C_BNFVST_DERVSTDATE
					+ ">=?)", new String[] { "" + p_bnfId, p_dervstdate }, null, null, null);
			try {
				if (c.moveToNext()) {
					bnfvst_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return bnfvst_id;
	}

	// ======================================================================================================================

	// --------------------------------------------------------------------------------------------------------------------------
	// T_FACTURE
	// méthode commune à l'insertion et à la mise à jour
	// private ContentValues construireValuesFacture(xml_Facture p_xfacture, long p_bnfId) {
	// ContentValues values = new ContentValues();
	// values.put(C_FAC_ID_BNF_ID, p_bnfId);
	// values.put(C_FAC_TLA, p_xfacture.getFactla());
	// values.put(C_FAC_MNT_FACTURE, p_xfacture.getFacmnt_facture());
	// values.put(C_FAC_MNT_ASSURE, p_xfacture.getFacmnt_assure());
	// values.put(C_FAC_NO_FACTURE, p_xfacture.getFacno_facture());
	// values.put(C_FAC_ABRV_FACTURANT, p_xfacture.getFacabrev_facturant());
	// values.put(C_FAC_MNT_FACTURE_I, "" + MontantFactureToInteger(p_xfacture.getFacmnt_facture()));
	// values.put(C_FAC_MNT_ASSURE_I, "" + MontantAssureToInteger(p_xfacture.getFacmnt_assure()));
	// return values;
	// }

	// public long insererUneFacture(xml_Facture p_xfacture, long p_bnfId) {
	// try {
	// ContentValues values = construireValuesFacture(p_xfacture, p_bnfId);
	// return db.insert(T_FACTURE, null, values);
	// } catch (SQLException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
	// throw e;
	// }
	// }

	// p_bnfid la foreign key représentant benef
	// private boolean supprimerLesFacturesUnBenef(long p_bnfid) {
	// // renvoie une valeur supérieure à 0 si l'enreg a été supprimé
	// return db.delete(T_FACTURE, C_FAC_ID_BNF_ID + "=" + p_bnfid, null) > 0;
	// }

	// renvoyer un cursor sur le(s) libelle(s) du TLA contenant les factures du
	// bénéficiaire p_bnfId correspondant
	public Cursor recupereLesTlaUnBenef(long p_bnfId) {
		try {
			return db.query(T_FACTURE, new String[] { C_FAC_ID, C_FAC_ID_BNF_ID, C_FAC_TLA }, C_FAC_ID_BNF_ID + "=?", // selection,
					new String[] { "" + p_bnfId }, // selectionArgs,
					C_FAC_TLA, // groupBy,
					null, // having,
					C_FAC_TLA + " ASC"); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	// renvoyer un cursor contenant tous les champs des n factures du
	// bénéficiaire p_bnfId correspondant
	// chargées dans le TLA p_tlaLibelle
	public Cursor recupereLesFacturesUnBenefUnTla(long p_bnfId, String p_tlaLibelle) {
		try {
			return db.query(T_FACTURE, new String[] { C_FAC_ID, C_FAC_ID_BNF_ID, C_FAC_TLA, C_FAC_MNT_FACTURE, C_FAC_MNT_ASSURE,
					C_FAC_NO_FACTURE, C_FAC_ABRV_FACTURANT, C_FAC_MNT_FACTURE_I, C_FAC_MNT_ASSURE_I }, // tous les
																										// champs
					"(" + C_FAC_ID_BNF_ID + "=?) and (" + C_FAC_TLA + "=?)", // selection,
					new String[] { "" + p_bnfId, p_tlaLibelle }, // selectionArgs,
					C_FAC_ID_BNF_ID + "," + C_FAC_TLA, // groupBy,
					null, // having,
					null); // orderBy)
		} catch (SQLException e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
			throw e;
		}
	}

	public boolean isAuMoinsUneFactureDeBenef(long p_bnfid) {
		long fac_id = 0;
		if (p_bnfid > 0) {
			Cursor c = db.query(T_FACTURE, new String[] { C_FAC_ID }, C_FAC_ID_BNF_ID + "=?", new String[] { "" + p_bnfid }, null, null,
					null);
			try {
				if (c.moveToNext()) {
					fac_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return (fac_id > 0);
	}

	public boolean isAuMoinsUnMontantAssureNonNulDeBenef(long p_bnfid) {
		long fac_id = 0;
		if (p_bnfid > 0) {
			Cursor c = db.query(T_FACTURE, new String[] { C_FAC_ID }, "(" + C_FAC_ID_BNF_ID + "=?) AND (" + C_FAC_MNT_ASSURE_I + ">?)",
					new String[] { "" + p_bnfid, "" + 0 }, null, null, null);
			try {
				if (c.moveToNext()) {
					fac_id = c.getLong(0);
				}
			} catch (SQLException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
				throw e;
			} finally {
				c.close();
			}
		}
		return (fac_id > 0);
	}

	// ======================================================================================================================

	/**
	 * Convertir une date au format JJ/MM/AAAA au format TEXT ISO8601 accessible aux fonctions date-heure SQLite {@link http
	 * ://www.sqlite.org/lang_datefunc.html}
	 * @param dateString la date au format JJ/MM/AAAA
	 * @return chaine YYYY-MM-DD HH:MM:SS:SSS
	 */
	public String JJMMAAAAToIso8601(String dateString) {

		if ((dateString != null) & (dateString.length() > 0) & (!("").equals(dateString.trim()))) {
			Matcher m = EQX_DATE_PATTERN.matcher(dateString);
			if (!m.matches()) {
				throw new NumberFormatException("DBEqxAdapter: [" + dateString + "]Format de date invalide");
			}
			return String.valueOf(m.group(3)) + "-" + String.valueOf(m.group(2)) + "-" + String.valueOf(m.group(1));
		} else {
			return "";
		}
	}

	/**
	 * Convertir une date au format JJ/MM/AAAA au format <code>java.util.Date</code>
	 * @param dateString la date au format JJ/MM/AAAA
	 * @return l'objet <code>java.util.Date</code> représentant cette date
	 */
	public Date JJMMAAAAToDate(String dateString) {
		Matcher m = EQX_DATE_PATTERN.matcher(dateString);
		Calendar date = new GregorianCalendar();
		if (!m.matches()) {
			throw new NumberFormatException("DBEqxAdapter: [" + dateString + "]Format date (JJ/MM/AAAA) invalide");
		}
		// void set (int year, int month, int day, int hourOfDay, int minute,
		// int second)
		date.set(Integer.valueOf(m.group(3)), Integer.valueOf(m.group(2)) - 1, Integer.valueOf(m.group(1)), 0, 0, 0);
		return date.getTime();

	}

	/**
	 * Convertir une date au format JJ/MM/AAAA en un entier correspondant à la valeur aaaa+mm+jj
	 * @param dateString la date au format JJ/MM/AAAA
	 * @return l'entier représentant cette date
	 */
	public int JJMMAAAAToInteger(String dateString) {
		if (!("").equals(dateString.trim())) {
			Date retour = JJMMAAAAToDate(dateString);
			return retour.getYear() + retour.getMonth() + retour.getDate();
		} else {
			return 0;
		}
	}

	/**
	 * construire une date/heure au format iso8601 YYYY-MM-DD HH:MM:SS:SSS . l'heure peut être absente
	 * @param p_xtournee structure tournee (date)
	 * @param p_xvisite structure visite (heure)
	 * @return
	 */
	// public String construireDateHeureVisite(xml_Tournee p_xtournee, xml_Visite p_xvisite) {
	// String heure_v = "";
	// String h = p_xvisite.getvisiteHoraire().trim();
	// if ((h != null) & (h.length() > 0) & (!("").equals(h.trim()))) {
	// Matcher m = EQX_HEURE_PATTERN.matcher(h);
	// if (!m.matches()) {
	// throw new NumberFormatException("DBEqxAdapter: [" + h + "]Format horaire (HHhMM) invalide");
	// }
	// heure_v = String.valueOf(m.group(1)) + ":" + String.valueOf(m.group(2));
	// }
	// String retour = JJMMAAAAToIso8601(p_xtournee.gettournee_ShortDate()) + " " + heure_v;
	// return retour.trim();
	// }

	// récupérer le montant facturé au format entier
	// private int MontantFactureToInteger(String p_montantFacture) {
	// int retour = 0;
	// if ((p_montantFacture != null) & (p_montantFacture.length() > 0) & (!("").equals(p_montantFacture.trim()))) {
	// Matcher m = EQX_MONTANTFAC_PATTERN.matcher(p_montantFacture);
	// if (!m.matches()) {
	// throw new NumberFormatException("DBEqxAdapter: [" + p_montantFacture + "]Format montant (Total : x,xx) invalide");
	// }
	// String montant = String.valueOf(m.group(1)) + String.valueOf(m.group(2));
	// try {
	// retour = Integer.parseInt(montant);
	// } catch (NumberFormatException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.error_formatmontant, e, p_montantFacture);
	// }
	// }
	// return retour;
	// }

	// récupérer le montant facturé au format chaine simple xxxx.xx
	// public String MontantFactureToString(String p_montantFacture) {
	// String retour = "";
	// if ((p_montantFacture != null) & (p_montantFacture.length() > 0) & (!("").equals(p_montantFacture.trim()))) {
	// Matcher m = EQX_MONTANTFAC_PATTERN.matcher(p_montantFacture);
	// if (!m.matches()) {
	// throw new NumberFormatException("DBEqxAdapter: [" + p_montantFacture + "]Format montant (Total : x,xx) invalide");
	// }
	// retour = String.valueOf(m.group(1)) + "." + String.valueOf(m.group(2));
	// }
	// return retour;
	// }

	// récupérer le montant assuré au format entier
	// private int MontantAssureToInteger(String p_montantAssure) {
	// int retour = 0;
	// if ((p_montantAssure != null) & (p_montantAssure.length() > 0) & (!("").equals(p_montantAssure.trim()))) {
	// Matcher m = EQX_MONTANTASS_PATTERN.matcher(p_montantAssure);
	// if (!m.matches()) {
	// throw new NumberFormatException("DBEqxAdapter: [" + p_montantAssure + "]Format montant (Part Assuré : x,xx) invalide");
	// }
	// String montant = String.valueOf(m.group(1)) + String.valueOf(m.group(2));
	// try {
	// retour = Integer.parseInt(montant);
	// } catch (NumberFormatException e) {
	// LogCatBuilder.WriteErrorToLog(context, TAG, R.string.error_formatmontant, e, p_montantAssure);
	// }
	// }
	// return retour;
	// }

	// récupérer le montant assuré au format chaine xxxx.xx
	public String MontantAssureToString(String p_montantAssure) {
		String retour = "";
		if ((p_montantAssure != null) & (p_montantAssure.length() > 0) & (!("").equals(p_montantAssure.trim()))) {
			Matcher m = EQX_MONTANTASS_PATTERN.matcher(p_montantAssure);
			if (!m.matches()) {
				throw new NumberFormatException("DBEqxAdapter: [" + p_montantAssure + "]Format montant (Part Assuré : x,xx) invalide");
			}
			retour = String.valueOf(m.group(1)) + "." + String.valueOf(m.group(2));
		}
		return retour;
	}

	// ======================================================================================================================

	// ======================================================================================================================

	/*
	 * exemple de mise à jour public void updateFolder(long folderID, String name) { ContentValues values = new ContentValues();
	 * values.put(FolderColumns.NAME, name); SQLiteDatabase db = null; try { db = mOpenHelper.getWritableDatabase();
	 * db.update(FOLDER_TABLE_NAME, values, FolderColumns._ID + "=" + folderID, null); } finally { if (db != null) db.close(); } }
	 */

	/*
	 * emple de recherche private void addPackages(SQLiteDatabase db) { Cursor query = db.query(AppLabelDao.TABLE_NAME, new String[] {
	 * "_id", AppLabelDao.APP_COL_NAME }, AppLabelDao.PACKAGE_NAME_COL_NAME+ " is null", null, null, null, null); try { while
	 * (query.moveToNext()) { Cursor c = db.query(AppCacheDao.TABLE_NAME, new String[] { AppCacheDao.PACKAGE_NAME_COL_NAME },
	 * AppCacheDao.NAME_COL_NAME+ "=?", new String[] { query.getString(1) }, null, null, null); try { if (c.moveToNext()) { ContentValues
	 * contentValues = new ContentValues(); contentValues.put(AppLabelDao.PACKAGE_NAME_COL_NAME, c.getString(0));
	 * db.update(AppLabelDao.TABLE_NAME, contentValues, "_id=?", new String[] { Long.toString(query.getLong(0)) }); } else {
	 * db.delete(AppLabelDao.TABLE_NAME, "_id=?", new String[] { Long.toString(query.getLong(0)) }); } } finally { c.close(); } } } finally
	 * { query.close(); } }
	 */

}
