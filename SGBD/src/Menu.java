import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Menu {

	//Un hashset c'est chiant pour acc�der aux �l�ments du coup j'ai mis une list normal pour l'instant. 
	private static ArrayList<Activite> activiteList = new ArrayList<Activite>();
	private static ArrayList<Stagiaire> stagiaireList = new ArrayList<Stagiaire>();
	private static ArrayList<Seance> seanceList = new ArrayList<Seance>();
	private static ArrayList<Badge> badgeList = new ArrayList<Badge>();
	private static ArrayList<Centre> centreList = new ArrayList<Centre>();
	private static ArrayList<Materiel> materielList = new ArrayList<Materiel>();
	private static ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
	private static ArrayList<String> moniteurList = new ArrayList<String>();
	private static ArrayList<Adresse> adresseList = new ArrayList<Adresse>();
	
	private static JDBC jdbc;
	
	private static int uidCentre;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		try{
			jdbc = JDBC.getInstance();
		}
		catch(Exception e){
			System.out.println("Connexion � la BDD �chou�e");			
		}
	
		while(true){
			
			System.out.println("Choisissez un centre");
			getCentres();
			afficheCentres();
			
			int centre = sc.nextInt();
			try{
				uidCentre = centreList.get(centre-1).getUid();
			}
			catch (Exception e){
				
			}
			
			
			System.out.println("Qui etes vous?");
			System.out.println("[1] Un stagiaire");
			System.out.println("[2] Un responsable");
			System.out.println("[3] Un moniteur");
			System.out.println("[4] Quitter");
			
			int role = sc.nextInt();
			int reponse;
			
			if(role==4) break; //Cas Quitter
			
			while(role!=0 ){
				System.out.println("Que voulez vous faire?");
				switch(role){
				case 1 : //Cas Stagiaire
					System.out.println("[1] S'inscrire");
					System.out.println("[2] Se d�sinscrire");	
					System.out.println("[3] Consulter la liste des activit�s");
					System.out.println("[4] Visualiser les s�ances planifi�es");
					System.out.println("[0] Changer de role");
					reponse = sc.nextInt();
					if(reponse==0){
						role=0;
						break;
					}
					reponseS(reponse);
					break;
					
				case 2 : //Cas Responsable
					System.out.println("De quel centre �tes vous responsable?");
					getCentres();
					afficheCentres();
					uidCentre = sc.nextInt();
					System.out.println("[1] Ajouter une activit�");
					System.out.println("[2] Supprimer une activit�");
					System.out.println("[3] Consulter la liste des activit�s");
					System.out.println("[4] Cr�er un groupe");
					System.out.println("[5] Supprimer un groupe");
					System.out.println("[6] Planifier une s�ance pour un groupe");
					System.out.println("[7] G�rer le mat�riel");
					System.out.println("[8] Ajouter un moniteur");
					System.out.println("[9] Supprimer un moniteur");
					System.out.println("[20] Ajouter un stagiaire à un groupe");
					System.out.println("[21] Supprimer un stagiaire d'un groupe");
				case 3: //Cas Moniteur
					System.out.println("[10] Visualiser les s�ances planifi�es");
					System.out.println("[11] Consulter la liste des stagiaires");
					System.out.println("[12] Classer les centres par nombre d'inscrits dans l'ann�e");
					System.out.println("[13] Classer les villes par nombre de stagiaires");
					System.out.println("[0] Changer de role");
					reponse = sc.nextInt();
					if(reponse == 0){
						role = 0;
						break;
					}
					if((reponse>0 && reponse<=9) || reponse == 20 || reponse == 21){
						reponseR(reponse);
					} 
					if(reponse>=10 && reponse<=13){
						reponseM(reponse);
					}
					break;
				}	
			}
		}
	}
	
	//Traitement de la demande du stagiaire
	public static void reponseS(int reponse){
		Scanner sc = new Scanner(System.in);
		switch(reponse){
		case 1: //S'inscrire
			HashSet<Integer> activiteList = new HashSet<Integer>();
			HashSet<Integer> niveauList = new HashSet<Integer>();
			System.out.println("Nom :");
			String nom = sc.next();
			System.out.println("Prenom :");
			String prenom = sc.next();
			System.out.println("Mail :");
			String mail = sc.next();
			System.out.println("Telephone :");
			String tel = sc.next();
			System.out.println("Date de naissance (jj/mm/aaaa):");
			String naissance = sc.next();
			Date date = new Date(naissance);
			System.out.println("Num�ro de rue :");
			int numRue = sc.nextInt();
			System.out.println("Rue :");
			String rue = sc.next();
			System.out.println("Code postal : ");
			String CP = sc.next();
			System.out.println("Ville :");
			String ville = sc.next();
			Adresse adresse = new Adresse(numRue, rue, CP, ville);
			System.out.println("Date de d�but du stage (jj/mm/aaaa)");
			String dateDebut = sc.next();
			System.out.println("Date de fin du stage (jj/mm/aaaa)");
			String dateFin = sc.next();
			Date debut = new Date(dateDebut);
			Date fin = new Date(dateFin);
			System.out.println("Activit� choisie:");
			getActivites();
			afficheActivites();
			int numActivite = sc.nextInt();
			activiteList.add(numActivite);
			int choix;
			do{
				System.out.println("[1] Choisir une activit� suppl�mentaire");
				System.out.println("[2] Terminer l'inscription");
				choix = sc.nextInt();
				if(choix==1){
					System.out.println("Activit� choisie:");
					numActivite = sc.nextInt();
					activiteList.add(numActivite);
					System.out.println("Votre niveau dans cette activit�\n[1]D�butant\n[2]Confirm�\n[3]Expert");
					int niveau = sc.nextInt();
					niveauList.add(niveau);
				}
				
			}while(choix==1);
			getAdresses();
			int uid = 1;
			for(Adresse tempadd : adresseList)
				uid++;
			adresse.uidAdresse = uid;
			Stagiaire stagiaire = new Stagiaire(nom, prenom, mail, tel, date, adresse, activiteList, niveauList);
			ajouteAdresseList(adresseList, adresse);
			
			stagiaire.setDebut(debut);
			stagiaire.setFin(fin);
			inscrireStagiaire(stagiaire);
			
			break;
		case 2: //Se d�sinscrire
			System.out.println("Adresse mail :");
			String mailDel = sc.next();
			desinscrireStagiaire(mailDel);
			break;
		case 3: //Consulter liste activit�s
			getActivites();
			afficheActivites();
			break;
		case 4: //Visualiser s�ances planifi�es
			getSeances();
			afficheSeances();
			break;
		}
	}
	
	//Traitement de la demande du moniteur/responsable
	public static void reponseM(int reponse){
		Scanner sc = new Scanner(System.in);
		switch(reponse){
		case 10: //Visualiser s�ances planifi�es
			getSeances();
			afficheSeances();
			break;
		case 11: //Consulter liste stagiaires
			getStagiaires();
			afficheStagiaires();
			break;
		case 12: //Classer centres par nombre d'inscrits dans l'ann�e
			classeCentre();
			break;
		case 13: //Classer villes par nombre de stagiaires
			classeVille();
			break;
		}
	}
	
	//Traitement de la demande du Responsable
	public static void reponseR(int reponse){
		Scanner sc = new Scanner(System.in);
		switch(reponse){
		case 1: //Ajouter une activit�
			System.out.println("Nom de l'activit� : ");
			String nom = sc.next();
			System.out.println("Cat�gorie de l'activit�\n[1]Montagne\n[2]Nautique\n[3]Air");
			int cat = sc.nextInt();
			System.out.println("Description de l'activit� : ");
			String des = sc.next();
			System.out.println("Nombre minimum de stagiaires pour l'activit� : ");
			int nbmin = sc.nextInt();
			System.out.println("Nombre max de stagiaires par moniteur : ");
			int nbmax = sc.nextInt();
			Activite activite = new Activite(nom, cat, des, nbmin, nbmax);
			creerActivite(activite);
			break;
		case 2: //Supprimer une activit�
			System.out.println("Indiquez l'activit� � supprimer");
			getActivites();
			afficheActivites();
			int acDel = sc.nextInt();
			System.out.println("ACTIVITE SUPPRIMEE : " + activiteList.get(acDel));
			supprimerActivite(acDel);
			break;
		case 3: //Consulter liste activit�s
			getActivites();
			afficheActivites();
			break;
		case 4: //Cr�er un groupes
			System.out.println("Indiquez le niveau du groupe\n[1]D�butant\n[2]Confirm�\n[3]Expert");
			int niveauGroupe = sc.nextInt();
			creerGroupe(niveauGroupe);
			break;
		case 5: //Supprimer un groupe
			System.out.println("Voici la liste des groupes");
			afficherListeGroupe();
			System.out.println("Indiquer le groupe que vous souhaitez supprimer");
			int num = sc.nextInt();
			supprimerGroupe(num);
			break;
		case 6: //Planifier une s�ance pour un groupe
			System.out.println("Indiquez la date et l'heure de d�but de la s�ance (jj/mm/aaaa/h/min)");
			String naissance = sc.next();
			Date date = new Date(naissance);			
			System.out.println("Indiquez le groupe concern�");
			afficherListeGroupe();
			int numGroupe = sc.nextInt();
			System.out.println("Indiquez l'activit� concern�e");
			getActivites();
			afficheActivites();
			int numActivite = sc.nextInt();
			System.out.println("Indiquez la dur�e de la s�ance (en minutes)");
			int duree = sc.nextInt();
			int plusMatos = 0;
			
			Seance seance = new Seance(numGroupe, numActivite, duree, date, seanceList.size()+1);
			int plusMoniteur = 2;
			getMoniteur(numActivite);
			do{
				System.out.println("Ajoutez un moniteur pour cette s�ance (identifi� par son adresse mail)");
				afficheMoniteur();
				int numMoniteur = sc.nextInt();
				String mail = moniteurList.get(numMoniteur);
				seance.addMoniteur(mail);
				System.out.println("Ajouter un moniteur suppl�mentaire?\n[1]Oui\n[2]Non");
				plusMoniteur = sc.nextInt();
			}while(plusMoniteur == 1);
			planifierSeance(seance);
			break;
		case 7: //G�rer le mat�riel
			System.out.println("Quel action souhaitez-vous faire?");
			System.out.println("[1] Faire l'inventaire");
			System.out.println("[2] Ajouter du mat�riel");
			System.out.println("[3] Supprimer du mat�riel");
			System.out.println("[4] R�server du mat�riel");
			int action = sc.nextInt();
			switch(action){
				case 1: //Faire l'inventaire (afficher la liste)
					getMateriel();
					inventaire();
					break;
				case 2: //Ajouter du materiel
					int encore = 2;
					do{
						System.out.println("Ajouter du stock � un mat�riel d�ja existant?\n[1]Oui\n[2]Non");
						int dejaLa = sc.nextInt();
						if(dejaLa==1){
							getMateriel();
							inventaire();
							System.out.println("Indiquez le mat�riel concern�");
							int materiel = sc.nextInt();
							System.out.println("Indiquez la quantit� � ajouter");
							int qte = sc.nextInt();
							ajoutMateriel(materiel, qte);
						}
						System.out.println("Indiquez le type de materiel");
						String type = sc.next();
						System.out.println("Indiquez la marque");
						String marque = sc.next();
						System.out.println("Indiquez le mod�le");
						String modele = sc.next();
						System.out.println("Indiquez le niveau\n[1]D�butant\n[2]Confirm�\n[3]Expert");
						int niveau = sc.nextInt();
						System.out.println("Indiquez la quantite de materiel � ajouter");
						int qte = sc.nextInt();
						Materiel matos = new Materiel(type, marque, modele, niveau, qte);
						ajoutMateriel(matos);
						
						System.out.println("Ajouter encore du mat�riel?\n[1]Oui\n[2]Non");
						encore = sc.nextInt();
					}while(encore == 1);
					
					break;
				case 3: //Supprimer du materiel
					System.out.println("Indiquez le mat�riel � supprimer");
					getMateriel();
					inventaire();
					int idMatos = sc.nextInt();
					System.out.println("Indiquez la quantite de ce mat�riel � supprimer");
					int qte = sc.nextInt();
					suppressionMateriel(idMatos, qte);
					break;
				case 4: //R�server du mat�riel
					int encore2 = 2;
					do{
						System.out.println("Indiquez le mat�riel � r�server");
						getMateriel();
						inventaire();
						int idMatos2 = sc.nextInt();
						System.out.println("Indiquez la quantite de ce mat�riel � r�server");
						int qte2 = sc.nextInt();
						System.out.println("Indiquez la seance pour laquelle le materiel doit etre reserve");
						int uidSeance = sc.nextInt();
						if(materielList.get(idMatos2).getStock()>=qte2){
							reservMateriel(idMatos2, qte2, uidSeance);							
						}
						else{
							System.out.println("Pas assez de stock disponible");
						}
						System.out.println("R�server encore du mat�riel?\n[1]Oui\n[2]Non");
						encore2 = sc.nextInt();
					}while(encore2 == 1);
					
					break;				
			}
			break;
		case 8: //Ajouter un moniteur
			System.out.println("Nom du moniteur");
			String nom3 = sc.next();
			System.out.println("Pr�nom du moniteur");
			String prenom3 = sc.next();
			System.out.println("Adresse mail");
			String mail3 = sc.next();
			System.out.println("T�l�phone");
			String tel = sc.next();
			System.out.println("Date de naissance (jj/mm/aaaa)");
			String naissance2 = sc.next();
			Date date2 = new Date(naissance2);
			System.out.println("Num�ro de rue :");
			int numRue = sc.nextInt();
			System.out.println("Rue :");
			String rue = sc.next();
			System.out.println("Code postal : ");
			String CP = sc.next();
			System.out.println("Ville :");
			String ville = sc.next();
			Adresse adresse = new Adresse(numRue, rue, CP, ville);
			
			getAdresses();
			int uid = 1;
			for(Adresse tempadd : adresseList)
				uid++;
			adresse.uidAdresse = uid;
			
			ajouteAdresseList(adresseList, adresse);
			
			Moniteur moniteur = new Moniteur(nom3, prenom3, mail3, tel, date2, adresse);
			int encore = 2;
			do{
				System.out.println("Indiquez l'activit� que peut encadrer le moniteur");
				getActivites();
				afficheActivites();
				int numAc2 = sc.nextInt();
				moniteur.addActivite(numAc2);
				System.out.println("Ajouter une activit� suppl�mentaire pour ce moniteur?\n[1]Oui\n[2]Non");
				encore = sc.nextInt();
			}while(encore == 1);
			System.out.println("Attribuez un num�ro de badge � ce moniteur");
			int idBadge = sc.nextInt();
			System.out.println("Badge valable jusqu'au (jj/mm/aaaa)");
			String validite = sc.next();
			Date dateBadge = new Date(validite);
			Badge badge = new Badge(idBadge, dateBadge);
			moniteur.setBadge(badge);
			ajoutMoniteur(moniteur);
			break;
		case 9: //Supprimer un moniteur
			System.out.println("Adresse mail du moniteur � supprimer");
			String mailDel = sc.next();
			suppressionMoniteur(mailDel);
			break;
		case 20: // Ajouter un stagiaire à un groupe
			System.out.println("Choisissez un groupe");
			getGroupe();
			afficherListeGroupe();
			int grp = sc.nextInt();
			int encore2 = 2;
			do{
				System.out.println("Choisissez un stagiaire à insérer dans ce groupe");
				getStagiaires();
				afficheStagiaires();
				String mail = sc.next();
				//requête magique
				PreparedStatement pS;
				try{
					pS = jdbc.getConnection().prepareStatement("INSERT INTO Appartient_au_groupe (uidGroupe,mail) VALUES (?,?)");
					pS.setString(1, Integer.toString(grp));
					pS.setString(2, mail);
					pS.executeQuery();
				}catch(Exception e){
					
				}
				System.out.println("Voulez vous ajouter un stagiaire de plus?\n[1]Oui\n[2]Non");
				encore2 = sc.nextInt();
			}while(encore2 == 1);
			break;
		case 21: // Supprimer un stagiaire d'un groupe
			System.out.println("Choisissez un groupe");
			getGroupe();
			afficherListeGroupe();
			int grp2 = sc.nextInt();
			int encore3 = 2;
			do{
				System.out.println("Choisissez un stagiaire à supprimer de ce groupe");
				getStagiaires();
				afficheStagiaires();
				String mail = sc.next();
				//requête magique
				PreparedStatement pS;
				try{
					pS = jdbc.getConnection().prepareStatement("DELETE FROM Appartient_au_groupe WHERE uidGroupe = ? AND mail = ?");
					pS.setString(1, Integer.toString(grp2));
					pS.setString(2, mail);
					pS.executeQuery();
					pS = jdbc.getConnection().prepareStatement("SELECT mail FROM Appartient_au_groupe WHERE uidGroupe = ?");
					pS.setString(1, Integer.toString(grp2));
					ResultSet rS = pS.executeQuery();
					int nbStagiaire = 0;
					while(rS.next()){
						nbStagiaire++;
					}
					if(nbStagiaire==0){
						/*Delete chaque Encadre qui contient un uidSeance lié à l'uidGroupe 
						pS = jdbc.getConnection().prepareStatement("DELETE FROM Encadre NATURAL JOIN Seance WHERE uidGroupe = ?");
						pS.setString(1, Integer.toString(grp2));
						pS.executeQuery();*/
						pS = jdbc.getConnection().prepareStatement("DELETE FROM Seance WHERE uidGroupe = ?");
						pS.setString(1, Integer.toString(grp2));
						pS.executeQuery();
						pS = jdbc.getConnection().prepareStatement("DELETE FROM Groupe WHERE uidGroupe = ?");
						pS.setString(1, Integer.toString(grp2));
						pS.executeQuery();
					}
				}catch(Exception e){
					
				}
				System.out.println("Voulez vous supprimer un stagiaire de plus?\n[1]Oui\n[2]Non");
				encore3 = sc.nextInt();
			}while(encore3 == 1);
		}
	}
	
	public static void getAdresses() {
		PreparedStatement pS;
		try {
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Adresse");
			ResultSet rS = pS.executeQuery();
			adresseList.clear();
			while(rS.next()) {
				Adresse adr = new Adresse(Integer.parseInt(rS.getString("numero")), rS.getString("rue"), rS.getString("codePostal"), rS.getString("ville"));
				adr.setUid(Integer.parseInt(rS.getString("uidAdresse")));
				
				ajouteAdresseList(adresseList, adr);
			}
			
		}
		catch(Exception e) {
			
		}
	}
	
	//OK
	public static void inscrireStagiaire(Stagiaire stagiaire){
		PreparedStatement pS;
		try{
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES (?,?,?,?,?)");
			pS.setString(1, String.format("%010d", stagiaire.getAdresse().getUid()));
			pS.setString(2, Integer.toString(stagiaire.getAdresse().getNum()));
			pS.setString(3, stagiaire.getAdresse().getRue());
			pS.setString(4, stagiaire.getAdresse().getCodePostal());
			pS.setString(5, stagiaire.getAdresse().getVille());
			pS.executeQuery();
			
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES (?,?,?,?,?,?)");
			pS.setString(1, stagiaire.getMail());
			pS.setString(2, String.format("%010d", stagiaire.getAdresse().getUid()));
			pS.setString(3, stagiaire.getNom());
			pS.setString(4, stagiaire.getPrenom());
			java.sql.Date naiss = new java.sql.Date(stagiaire.getDate().getAnnee(), stagiaire.getDate().getMois(), stagiaire.getDate().getJour());
			pS.setDate(5, naiss);
			pS.setString(6, stagiaire.getTel());
			pS.executeQuery();
			
			
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Stagiaire (mail) VALUES (?)");
			pS.setString(1, stagiaire.getMail());
			ResultSet rS = pS.executeQuery();
			
			for(int i :stagiaire.getActivites()){
				java.sql.Date debut = new java.sql.Date(stagiaire.getDebut().getAnnee(), stagiaire.getDebut().getMois(), stagiaire.getDebut().getJour()) ;
				java.sql.Date fin = new java.sql.Date(stagiaire.getFin().getAnnee(), stagiaire.getFin().getMois(),stagiaire.getFin().getJour() ) ;
				
				PreparedStatement pS2 = jdbc.getConnection().prepareStatement("INSERT INTO Inscription (uidActivite,uidCentre,mail,dateDebut,dateFin) VALUES (?,?,?,?,?)");
				pS2.setString(1,String.format("%010d", activiteList.get(i-1).getUid()));
				pS2.setString(2,String.format("%010d", activiteList.get(i-1).getCentre()));
				System.out.println(String.format("%010d", activiteList.get(i-1).getCentre())+String.format("%010d", activiteList.get(i-1).getUid()));
				pS2.setString(3,stagiaire.getMail());
				pS2.setDate(4, debut);
				pS2.setDate(5, fin);
				ResultSet rS2 = pS2.executeQuery();
			}
			System.out.println("\n[OK] Stagiaire inscrit\n");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//OK
	public static void desinscrireStagiaire(String mail){
		//faire les requetes magiques
		PreparedStatement pS;
		try{
			//On r�cup�re tous les groupes auxquels appartient le stagiaire
			ArrayList<String> groupList = new ArrayList<String>();
			pS = jdbc.getConnection().prepareStatement("SELECT uidGroupe FROM Appartient_au_groupe WHERE mail=?");
			pS.setString(1, mail);
			ResultSet rS = pS.executeQuery();
			while(rS.next()){
				groupList.add(rS.getString("uidGroupe"));
			}
			
			//Pour chacun de ces groupes
			for(String groupe:groupList){
				//On r�cup�re le nombre de stagiaire dans le groupe
				int nbDansGroupe = 0;
				PreparedStatement pS4 = jdbc.getConnection().prepareStatement("SELECT mail FROM Appartient_au_groupe WHERE uidGroupe=?");
				pS4.setString(1, groupe);
				ResultSet rS4 = pS4.executeQuery();
				while(rS4.next()){
					nbDansGroupe++;
				}
				nbDansGroupe--;
				
				//On r�cup�re toutes les s�ances auxquelles devaient assister ce groupe
				PreparedStatement pS2 = jdbc.getConnection().prepareStatement("SELECT * FROM Seance WHERE uidGroupe=?");
				pS2.setString(1, groupe);
				ResultSet rS2 = pS2.executeQuery();
				while(rS2.next()){
					int nbMin = 0;
					
					//On r�cup�re le nombre minimum de stagiaire n�cessaire pour l'activit� de la s�ance
					PreparedStatement pS3 = jdbc.getConnection().prepareStatement("SELECT nbMinStagiaireGroupe FROM Activite WHERE uidActivite=?");
					pS3.setString(1, rS2.getString("uidActivite"));
					ResultSet rS3 = pS3.executeQuery();
					nbMin = rS3.getInt("nbMinStagiaireGroupe");
					
					//Si il n'y a plus assez de stagiaire dans le groupe pour l'activit�, on supprime la s�ance
					if(nbDansGroupe<nbMin){
						PreparedStatement pS5 = jdbc.getConnection().prepareStatement("DELETE FROM Seance WHERE uidSeance=?");
						rS2.getString("uidSeance");
						ResultSet rS5 = pS5.executeQuery();
					}
					//Si il n'y a plus de stagiaire dans le groupe, on supprime le groupe
					if(nbDansGroupe==0){
						supprimerGroupe(groupList.indexOf(groupe));
						
					}
				}
				
			}		
			
			//On supprime le stagiaire de la base
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Stagiaire WHERE mail=?" );
			pS.setString(1, mail);
			rS = pS.executeQuery();
			
			
			System.out.println("\n[OK] Stagiaire d�sinscrit\n");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	
	//OK
	public static void creerActivite(Activite ac){
		//faire la requete magique	
		getActivites();
		PreparedStatement pS;
		try{
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES (?,?,?,?,?,?,?)");
			pS.setString(1, (Integer.toString(activiteList.size()+1)));
			pS.setString(2, Integer.toString(0));
			pS.setString(3, ac.getCategorie());
			pS.setString(4, ac.getNom());
			pS.setString(5, ac.getDescription());
			pS.setInt(6, ac.getNbMin());
			pS.setInt(7, ac.nbMax);
			ResultSet rS = pS.executeQuery();
			System.out.println("\n[OK] Activit� ajout�e\n");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//OK
	//OK
	public static void supprimerActivite(int num){
		//faire la requete magique
		getActivites();
		PreparedStatement pS;
		try{
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Activite WHERE uidActivite=?");
			pS.setString(1, String.format("%010d",activiteList.get(num-1).getUid()));
			ResultSet rS = pS.executeQuery();
			System.out.println("\n[OK] Activit� supprim�e\n");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//OK
	
	public static void getMoniteur(int activite){
		PreparedStatement pS;
		try{
			pS = jdbc.getConnection().prepareStatement("SELECT mail FROM Moniteur NATURAL JOIN Habilite_a_encadrer WHERE uidCentre=? AND uidActivite=?");
			pS.setString(1, String.format("%010d", uidCentre));
			pS.setString(2, String.format("%010d",activiteList.get(activite).getUid()));
			ResultSet rS = pS.executeQuery();
			while(rS.next()){				
				ajouteMoniteurList(moniteurList, rS.getString("mail"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void afficheMoniteur(){
		int j = 1;
		for(String i:moniteurList){
			System.out.println("["+j+"]"+i);
			j++;
		}
	}
	
	//OK
	public static void getActivites(){
		PreparedStatement pS;
		try{
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Activite WHERE uidCentre=?");
			pS.setString(1, String.format("%010d", uidCentre));
			ResultSet rS = pS.executeQuery();
			
			while(rS.next()){
				String cat = rS.getString(3);
				int categorie =0;
				if(cat.equals("Montagne")) categorie= 1;
				if(cat.equals("Nautique")) categorie = 2;
				if(cat.equals("Air")) categorie =3;
				Activite ac = new Activite(rS.getString("nom"), categorie, rS.getString("description"), rS.getInt("nbMinStagiaireGroupe"), rS.getInt("nbMaxParMoniteur"));
				ac.setUid(Integer.parseInt(rS.getString("uidActivite")));
				ajouteActiviteList(activiteList, ac);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//OK
	
	//OK
	public static void getStagiaires(){
		PreparedStatement pS;
		try{
			//On r�cup�re la liste des mails des stagiaires
			pS = jdbc.getConnection().prepareStatement("SELECT mail FROM Stagiaire");
			ResultSet rS = pS.executeQuery();
			while(rS.next()){
				//A partir des mails on r�cup�re les stagiaires
				PreparedStatement pS2 = jdbc.getConnection().prepareStatement("SELECT * FROM Personne WHERE mail = ?");
				pS2.setString(1, rS.getString(1));
				ResultSet rS2 = pS2.executeQuery();
				
				//A partir de l'uidAdresse des stagiaire on r�cup�re l'adresse du stagiaire
				PreparedStatement pS3 = jdbc.getConnection().prepareStatement("SELECT * FROM Adresse WHERE uidAdresse=?");
				pS3.setString(1, rS2.getString(2));
				ResultSet rS3 = pS3.executeQuery();
				Adresse adr = new Adresse(rS3.getInt(2),rS3.getString(3), rS3.getString(4), rS3.getString(5));
				
				getAdresses();
				int uid = 1;
				for(Adresse tempadd : adresseList)
					uid++;
				adr.uidAdresse = uid;
				
				Date d = new Date(rS2.getDate(5).getDay(), rS2.getDate(5).getMonth(), rS2.getDate(5).getYear());
				Stagiaire stagiaire = new Stagiaire(rS2.getString(3), rS2.getString(4), rS2.getString(1), rS2.getString(6), d, adr);
				ajouteStagiaireList(stagiaireList, stagiaire);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//OK
	
	//OK
	public static void getSeances(){
		PreparedStatement pS;
		String activite;
		
		try{
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Seance WHERE uidCentre=?");
			pS.setString(1, String.format("%010d", uidCentre));
			ResultSet rS = pS.executeQuery();
			while(rS.next()){
				PreparedStatement pS2 = jdbc.getConnection().prepareStatement("SELECT nom FROM Activite WHERE uidActivite=?");
				pS2.setString(1, rS.getString("uidActivite"));
				ResultSet rS2 = pS2.executeQuery();
				activite = rS2.getString("nom");
				Date d = new Date(rS.getDate("dateSeance").getDay(), rS.getDate("dateSeance").getMonth(), rS.getDate("dateSeance").getYear());
				Seance seance = new Seance(rS.getInt("uidGroupe"), rS.getInt("uidActivite"), rS.getInt("duree"), d, rS.getInt(1));
				ajouteSeanceList(seanceList, seance);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//OK
		
	//OK
	public static void getCentres(){
		PreparedStatement pS;
		try{
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Centre");
			ResultSet rS = pS.executeQuery();
			while(rS.next()){
				PreparedStatement pS2 = jdbc.getConnection().prepareStatement("SELECT * FROM Adresse WHERE uidAdresse=?");
				pS2.setString(1, rS.getString("uidAdresse"));
				ResultSet rS2 = pS2.executeQuery();
				while(rS2.next()) {
					Adresse adr;
					try {
						rS2.getString("numero");
					}
					catch(NullPointerException e){
						adr = new Adresse(0,rS2.getString("rue"), rS2.getString("codePostal"), rS2.getString("ville"));
					}
					finally {
						adr = new Adresse(Integer.parseInt(rS2.getString("numero")),rS2.getString("rue"), rS2.getString("codePostal"), rS2.getString("ville"));
					}

					adr.setUid(Integer.parseInt(rS2.getString("uidAdresse")));
					Centre centre = new Centre(Integer.parseInt(rS.getString("uidCentre")), adr, rS.getString("nom"));
					ajouteCentreList(centreList, centre);
				}
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//OK
	
	//OK
	public static void getMateriel(){
		PreparedStatement pS;
		String activite;
		
		try{
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Materiel WHERE uidCentre=?");
			pS.setString(1, String.format("%010d", uidCentre));
			ResultSet rS = pS.executeQuery();
			while(rS.next()){
				String niveau = rS.getString("niveau");
				int niv = 0;
				if(niveau.equals("Debutant")) niv = 1;
				if(niveau.equals("Confirme")) niv = 2;
				if(niveau.equals("Expert")) niv = 3;
				
				Materiel materiel = new Materiel(rS.getString("type"), rS.getString("marque"), rS.getString("modele"), niv, rS.getInt("stock"));
				materiel.setUid(Integer.parseInt(rS.getString("uidMateriel")));
				ajouteMaterielList(materielList, materiel);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//OK
	
	public static void getGroupe(){
		PreparedStatement pS;
		try{
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Groupe");
			ResultSet rS = pS.executeQuery();
			while(rS.next()){
				Groupe gr = new Groupe(Integer.parseInt(rS.getString("uidGroupe")), rS.getString("niveau"));
				ajouteGroupeList(groupeList, gr);
			}
		}
		catch(Exception e){
			
		}
	}
	
	//OK
	//OK
	public static void afficheActivites(){
		int i = 1;
		for(Activite st : activiteList){
			System.out.println("[" + i + "]" + st.toString());
			i++;
			
		}
	}
	
	//OK
	
	//OK
	public static void afficheStagiaires(){
		int i=0;
		for(Stagiaire st:stagiaireList){
			System.out.println(st.toString());
		}		
	}
	
	//OK
	
	//OK
	public static void afficheSeances(){
		for(Seance se:seanceList){
			System.out.println(se.toString());
		}
	}
	
	//OK
	
	//OK
	public static void afficheCentres(){
		int i=1;
		for(Centre ce:centreList){
			System.out.println("[" + i + "]" + ce.toString());
			i++;
		}
	}
	
	
	
	public static void classeCentre(){
		//faire la requete magique
	}
	
	public static void classeVille(){
		//faire la requete magique
	}	

	public static void creerGroupe(int niveau){
		PreparedStatement pS;
		String activite;
		
		try{
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Groupe (uidGroupe,niveau) VALUES (?,?)");
			getGroupe();
			pS.setString(1, String.format("%010d", groupeList.size()+1));
			if(niveau == 1){
				pS.setString(2, "Debutant");
			}
			if(niveau == 2){
				pS.setString(2, "Confirme");
			}
			if(niveau == 3){
				pS.setString(2, "Expert");
			}
						
			ResultSet rS = pS.executeQuery();
			System.out.println("\n[OK] Groupe cr��\n");
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	//OK
	public static void supprimerGroupe(int numero){
		//faire la requete magique
		//supprimer lles s�ances associ�s � ce groupe + les stagiaires li�s uniquement � ce groupe
		System.out.println("\n[OK] Groupe supprim�\n");
		
		PreparedStatement pS;
		ResultSet rS;
		try{
			//On r�cup�re tous les stagiaires qui appartiennent au groupe
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Appartient_au_groupe WHERE uidGroupe=?");
			pS.setString(1, String.format("%010d",groupeList.get(numero).getUid()));
			rS = pS.executeQuery();
			//Pour chacun de ces stagiaires
			while(rS.next()){
				String mail = rS.getString("mail");
				//On r�cup�re tous les groupes auxquels ils appartiennent
				PreparedStatement pS2 = jdbc.getConnection().prepareStatement("SELECT * FROM Appartient_au_groupe WHERE mail=?");
				pS2.setString(1, mail);
				ResultSet rS2 = pS2.executeQuery();
				int nbGroupe = -1;
				//On compte le nombre de groupes
				while(rS2.next()){
					nbGroupe++;
				}
				//Si le groupe que l'on supprime �tait le seul groupe du stagiaire, on le d�sinscrit
				if(nbGroupe<=0){
					desinscrireStagiaire(mail);
				}
				
			}
			
			pS = jdbc.getConnection().prepareStatement("SELECT uidSeance FROM Seance WHERE uidGroupe=?");
			pS.setString(1,String.format("%010d",groupeList.get(numero).getUid()));
			rS = pS.executeQuery();
			
			//On supprime toutes les occurences des s�ances concern�es par ce groupe dans la table Encadre
			while(rS.next()){
				PreparedStatement pS3 = jdbc.getConnection().prepareStatement("DELETE FROM Encadre WHERE uidSeance=?");
				pS3.setString(1, rS.getString("uidSeance"));
				ResultSet rS3 = pS3.executeQuery();
			}
			
			//On supprime les s�ances auxquelles le groupe participait
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Seance WHERE uidGroupe=?");
			pS.setString(1, String.format("%010d",groupeList.get(numero).getUid()));
			rS = pS.executeQuery();

			//On supprime le groupe
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Groupe WHERE uidGroupe=?");
			pS.setString(1, String.format("%010d",groupeList.get(numero).getUid()));
			rS = pS.executeQuery();
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Appartient_au_groupe WHERE uidGroupe=?");
			pS.setString(1, String.format("%010d",groupeList.get(numero).getUid()));
			rS = pS.executeQuery();
		}
		catch(Exception e){
			
		}
	}
	
		
	public static void planifierSeance(Seance seance){
		
		//verif qu'il n'y a pas de conflit au niveau des dates
		System.out.println("\n[OK] S�ance planifi�e\n");
	}
	
	//OK
	public static void afficherListeGroupe(){
		int i = 1;
		for(Groupe g:groupeList){
			System.out.println("["+i+"]"+g.toString());
		}
	}
	
	//OK
	
	//OK
	public static void inventaire(){
		//faire la requete magique
		int i=1;
		for(Materiel m:materielList){
			System.out.println("["+i+"]" + m.toString());
			i++;
		}
	}
	
	//OK
	
	public static void ajoutMateriel(Materiel materiel ){
		//faire la requete magique
		PreparedStatement pS;
		ResultSet rS;
		try{
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Materiel (uidMateriel,uidCentre,type,marque,modele,niveau,stock) VALUES (?,?,?,?,?,?,?)");
			pS.setString(1, Integer.toString(materielList.size()+1));
			pS.setString(2, String.format("%010d", uidCentre));
			pS.setString(3, materiel.type);
			pS.setString(4, materiel.marque);
			pS.setString(5, materiel.modele);
			pS.setString(6, materiel.getNiveau());
			pS.setInt(7, materiel.stock);
			rS = pS.executeQuery();
			System.out.println("\n[OK] Mat�riel ajout�\n");
		}
		catch(Exception e){
			
		}
	}
	
	//OK
	public static void ajoutMateriel(int num, int qte){
		getMateriel();
		PreparedStatement pS;
		ResultSet rS;
		try{
			pS = jdbc.getConnection().prepareStatement("SELECT stock FROM Materiel WHERE uidMateriel=?");
			pS.setString(1, String.format("%010d",materielList.get(num).getUid()));
			rS = pS.executeQuery();
			int stock = rS.getInt("stock");
			stock+=qte;
			pS = jdbc.getConnection().prepareStatement("UPDATE Materiel SET stock=? WHERE uidMateriel=?");
			pS.setInt(1, stock);
			pS.setString(2, Integer.toString(materielList.get(num).getUid()));
			rS = pS.executeQuery();
			System.out.println("\n[OK] Mat�riel ajout�\n");
		}
		catch(Exception e){
			
		}
	}
	
	//OK
	public static void suppressionMateriel(int idMatos, int quantite){
		//faire la requete magique
		PreparedStatement pS;
		ResultSet rS;
		try{
			pS=jdbc.getConnection().prepareStatement("SELECT stock FROM Materiel WHERE uidMateriel=?");
			pS.setString(1, String.format("%010d",materielList.get(idMatos).getUid()));
			rS = pS.executeQuery();
			int nouveauStock = rS.getInt("stock")-quantite;
			if( nouveauStock <= 0){
				pS = jdbc.getConnection().prepareStatement("DELETE FROM Materiel WHERE uidMateriel=?");
				pS.setString(1, String.format("%010d",materielList.get(idMatos).getUid()));
				rS = pS.executeQuery();
			}
			else{
				pS = jdbc.getConnection().prepareStatement("UPDATE Materiel SET stock=? WHERE uidMateriel=?");
				pS.setInt(1, nouveauStock);
				pS.setString(2, String.format("%010d",materielList.get(idMatos).getUid()));
				rS = pS.executeQuery();
			}
			System.out.println("\n[OK] Mat�riel supprim�\n");
		}
		catch(Exception e){
			
		}
	}
	
	

	public static void reservMateriel(int idMatos, int quantite, int seance){
		PreparedStatement pS;
		ResultSet rS;
		try{
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Emprunt_materiel (uidMateriel,uidCentre,uidSeance,uidActivite,quantite) VALUES (?,?,?,?)");
			pS.setString(1, String.format("%010d",(materielList.get(idMatos).getUid())));
			pS.setString(2, String.format("%010d", uidCentre));
			pS.setString(3, Integer.toString(seanceList.get(seance).getNumSeance()));
			pS.setString(4, Integer.toString(seanceList.get(seance).getActivite()));
			pS.setInt(5, quantite);
			rS = pS.executeQuery();
			System.out.println("\n[OK] Mat�riel r�serv�\n");
		}
		catch(Exception e){
			
		}
	}
	
	
	//OK
	public static void ajoutMoniteur(Moniteur moniteur){
		PreparedStatement pS;
		ResultSet rS;
		try{
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Moniteur (mail,uidBadge) VALUES (?,?)");
			pS.setString(1, moniteur.getMail());
			pS.setString(2, String.format("%010d",moniteur.getBadge().getUid()));
			rS = pS.executeQuery();
			pS = jdbc.getConnection().prepareStatement("INSERT INTO Badge (uidBadge,validite) VALUES (?,?)");
			pS.setString(1, String.format("%010d",moniteur.getBadge().getUid()));
			java.sql.Date date = new java.sql.Date(moniteur.getBadge().getValidite().getAnnee(), moniteur.getBadge().getValidite().getMois(), moniteur.getBadge().getValidite().getJour());
			pS.setDate(2, date);
			rS = pS.executeQuery();
			for(int i:moniteur.getHabilite()){
				String uidAc = String.format("%010d",activiteList.get(i).getUid());
				pS = jdbc.getConnection().prepareStatement("INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES (?,?,?)");
				pS.setString(1, uidAc);
				pS.setString(2, String.format("%010d", uidCentre));
				pS.setString(3, moniteur.getMail());
				rS = pS.executeQuery();
			}
			System.out.println("\n[OK] Moniteur ajout�\n");
		}
		catch(Exception e){
			
		}
	}
	
	//OK
	
	public static void suppressionMoniteur(String mail){
		//faire la requete magique
		PreparedStatement pS;
		ResultSet rS;
		try{			
			
			pS = jdbc.getConnection().prepareStatement("SELECT * FROM Encadre WHERE mail=?");
			pS.setString(1, mail);
			rS = pS.executeQuery();
			while(rS.next()){
				String uidGroupe;	
				String uidSeance;
				PreparedStatement pS2 = jdbc.getConnection().prepareStatement("SELECT * FROM Seance WHERE uidSeance=?");
				pS2.setString(1, rS.getString("uidSeance"));
				ResultSet rS2 = pS2.executeQuery();
				uidGroupe = rS2.getString("uidGroupe");
				uidSeance = rS2.getString("uidSeance");
				while(rS2.next()){
					int nbStagiaire = 0;
					PreparedStatement pS3 = jdbc.getConnection().prepareStatement("SELECT * FROM Appartient_au_groupe WHERE uidGroupe=?");
					pS3.setString(1, uidGroupe);
					ResultSet rS3 = pS3.executeQuery();
					while(rS3.next()){
						nbStagiaire++;
					}
					pS3 = jdbc.getConnection().prepareStatement("SELECT * FROM Activite WHERE uidActivite=?");
					pS3.setString(1, rS2.getString("uidActivite"));
					rS3 = pS3.executeQuery();
					int nbMaxParMoniteur = rS3.getInt("nbMaxParMoniteur");
					
					int nbMoniteurs = 0;
					pS3 = jdbc.getConnection().prepareStatement("SELECT * FROM Encadre WHERE uidSeance=?");
					pS3.setString(1, uidSeance);
					rS3 = pS3.executeQuery();
					while(rS3.next()){
						nbMoniteurs++;
					}
					
					//Si il n'y a plus assez de moniteur pour encadrer la s�ance, on la supprime
					if(nbStagiaire/nbMoniteurs > nbMaxParMoniteur){
						pS3 = jdbc.getConnection().prepareStatement("DELETE FROM Encadre WHERE uidSeance=?");
						pS3.setString(1, uidSeance);
						rS3 = pS3.executeQuery();
						pS3 = jdbc.getConnection().prepareStatement("DELETE FROM Seance WHERE uidSeance=?");
						pS3.setString(1, uidSeance);
						rS3 = pS3.executeQuery();
					}
				}
			}
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Habilite_a_encadrer WHERE mail=?");
			pS.setString(1, mail);
			rS = pS.executeQuery();
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Encadre WHERE mail=?");
			pS.setString(1, mail);
			rS = pS.executeQuery();
			pS = jdbc.getConnection().prepareStatement("DELETE FROM Moniteur WHERE mail=?");
			pS.setString(1, mail);
			rS = pS.executeQuery();			
			
			System.out.println("\n[OK] Moniteur supprim�\n");
		}
		catch(Exception e){
			
		}
	}

	
	private static void ajouteActiviteList(ArrayList<Activite> list, Activite ac){
		boolean exists = false;
		for(Activite a:list){
			if(a.getNom() == ac.getNom()){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(ac);
		}
	}
	
	private static void ajouteStagiaireList(ArrayList<Stagiaire> list, Stagiaire sta){
		boolean exists = false;
		for(Stagiaire a:list){
			if(a.getNom() == sta.getNom()){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(sta);
		}
	}
	
	private static void ajouteMoniteurList(ArrayList<String> list, String sta){
		boolean exists = false;
		for(String a:list){
			if(a.equals(sta)){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(sta);
		}
	}
	
	private static void ajouteSeanceList(ArrayList<Seance> list, Seance sta){
		boolean exists = false;
		for(Seance a:list){
			if(a.getNumSeance() == sta.getNumSeance()){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(sta);
		}
	}

	private static void ajouteCentreList(ArrayList<Centre> list, Centre ce){
		boolean exists = false;
		for(Centre a:list){
			if(a.getUid() == ce.getUid()){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(ce);
		}
	}
	
	private static void ajouteMaterielList(ArrayList<Materiel> list, Materiel sta){
		boolean exists = false;
		for(Materiel a:list){
			if(a.getUid() == sta.getUid()){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(sta);
		}
	}
	
	private static void ajouteGroupeList(ArrayList<Groupe> list, Groupe gr){
		boolean exists = false;
		for(Groupe a:list){
			if(a.getUid() == gr.getUid()){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(gr);
		}
	}
	
	private static void ajouteAdresseList(ArrayList<Adresse> list, Adresse gr){
		boolean exists = false;
		for(Adresse a:list){
			if(a.getUid() == gr.getUid()){
				exists = true;
				break;
			}
		}
		if(!exists){
			list.add(gr);
		}
	}
	public static String getActiviteNameByNumber(int i){
		return activiteList.get(i).getNom();
	}
	
}