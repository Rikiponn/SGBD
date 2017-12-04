DROP TABLE Appartient_au_groupe;
DROP TABLE Encadre;
DROP TABLE Habilite_a_encadrer;
DROP TABLE Emprunt_materiel;
DROP TABLE Inscription;
DROP TABLE Moniteur;
DROP TABLE Responsable;
DROP TABLE Badge;
DROP TABLE Stagiaire;
DROP TABLE Personne;
DROP TABLE Materiel;
DROP TABLE Seance;
DROP TABLE Groupe;
DROP TABLE Activite;
DROP TABLE Centre;
DROP TABLE Adresse;

CREATE TABLE Adresse(
    uidAdresse CHAR(10),
    numero VARCHAR2(10),
    rue VARCHAR2(100) NOT NULL,
    codePostal VARCHAR2(6) NOT NULL,
    ville VARCHAR2(50) NOT NULL,
    CONSTRAINT pk_Adresse PRIMARY KEY(uidAdresse)
);
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
('0000000001', 'La déchetterie', '15100', 'Sochaux');
INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES
( '0000000002','8', 'Champs de Mars', '14000', 'Versdun');
INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES
( '0000000003','3945', 'Rue du maréchal Pétain', '55550', 'Vichy');
INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES
( '0000000004','851', 'Rue pavée d''andouilles', '71460', 'Saint-Gengoux-le-National');
INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES
( '0000000005','666', 'Rue satanique', '66600', 'l''enfer');
INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES
( '0000000006','851', 'Rue de la truie qui file', '72100', 'Le Mans');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000007', 'Rue des boulets', '26000', 'Valence');
INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES
( '0000000008','50', 'Rue Barthélémy de Laffemas', '26000', 'Valence');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000009', 'Empty String', '00000', 'NULL');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000010', 'Une rue quelconque', '1 code', 'Une ville quelconque');
INSERT INTO Adresse (uidAdresse,numero,rue,codePostal,ville) VALUES
( '0000000011','2', 'Rue minet ici', '26000', 'Valence');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000012', 'Chemin les Perers', '26300', 'Chatuzange le galubet');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000013', 'Place au beurre', '29232', 'Quimper');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000014', 'Un clavier azerty en vaut deux', '85201', 'azerty');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000015', 'Place de la bulle', '84000', 'Avignon');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000016', 'Rue de la vieille', '34070', 'Montpellier');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000017', 'Rue de la pie qui boit', '35400', 'Saint-Malo');
INSERT INTO Adresse (uidAdresse,rue,codePostal,ville) VALUES
( '0000000018', 'Rue du vide', '08080', 'Trou noir');

CREATE TABLE Centre(
    uidCentre CHAR(10),
    nom VARCHAR2(50) NOT NULL,
    uidAdresse CHAR(10) NOT NULL,
    CONSTRAINT pk_Centre PRIMARY KEY(uidCentre),
    CONSTRAINT fk_Centre_uidAdresse FOREIGN KEY (uidAdresse) REFERENCES Adresse(uidAdresse)
);
INSERT INTO Centre (uidCentre,nom,uidAdresse) VALUES
('0000000001', 'Le Camp des Suisses','0000000002');
INSERT INTO Centre (uidCentre,nom,uidAdresse) VALUES
('0000000002', 'Le Camp des Belges','0000000002');
INSERT INTO Centre (uidCentre,nom,uidAdresse) VALUES
('0000000003', 'Sports divers','0000000001');

CREATE TABLE Activite(
    uidActivite CHAR(10),
    uidCentre CHAR(10),
    categorie VARCHAR2(10) NOT NULL,
    nom VARCHAR2(50) NOT NULL,
    description VARCHAR2(200) NOT NULL,
    nbMinStagiaireGroupe number(*,0) NOT NULL,
    nbMaxParMoniteur number(*,0) NOT NULL,
    CONSTRAINT pk_Activite PRIMARY KEY(uidActivite, uidCentre),
    CONSTRAINT fk_Activite_uidCentre FOREIGN KEY (uidCentre) REFERENCES Centre(uidCentre),
    CONSTRAINT nbMinPositif CHECK(nbMinStagiaireGroupe > 0),
    CONSTRAINT nbMaxPositif CHECK(nbMaxParMoniteur > 0),
    CONSTRAINT categorieExiste CHECK( categorie in ('Nautique', 'Montagne', 'Air'))
);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000001','0000000001','Nautique','Plongée','Il s''agit pour les stagiaires d''immerger de façon totale leur corps dans un milieu liquide composé principalement d''eau',1,10);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000002','0000000001','Nautique','Noyade','Votre but est simple : Survivre.',2,10);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000003','0000000003','Montagne','Luge','Le fun en Luge',3,30);

INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000004','0000000003','Montagne','Ski','Le fun en Ski',3,30);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000005','0000000003','Montagne','Snowboard','Le fun en Snowboard',3,30);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000006','0000000003','Montagne','Fesses','Le fun sur l''arrière-train',3,30);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000007','0000000003','Air','Dé à coudre','Les bonnes références',1,5);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000008','0000000003','Air','Parachute','La version nulle de la chute',1,3);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000009','0000000003','Air','Parapente','Un parachute qui monte ? Fou',1,3);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000010','0000000003','Air','Wingsuit','Un parachute qui tue ? Encore plus fou',1,1);

INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000011','0000000001','Air','Le Truc qui vole derrière un Bateau','Apparemment c''est drôle',5,5);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000012','0000000001','Nautique','Brasse','La nage niveau 1',1,14);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000013','0000000001','Nautique','Nage indienne','La nage que personne n''effectue, même pas nos Moniteurs',1,14);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000014','0000000001','Nautique','Crawl','La nage des jambons',1,14);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000015','0000000001','Montagne','Marche à pieds','On paye un moniteur pour ça',1,200);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000016','0000000001','Montagne','Via ferrata','Oui',1,4);

INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000017','0000000002','Nautique','Brasse','La nage niveau 1',1,14);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000018','0000000002','Nautique','Nage indienne','La nage que personne n''effectue, même pas nos Moniteurs',1,14);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000019','0000000002','Nautique','Crawl','La nage des jambons',1,14);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000020','0000000002','Air','Planeurs','seulement 23% de morts par an',1,1);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000021','0000000002','Air','Avion de tourisme','Viendez on est bien',1,1);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000022','0000000002','Montagne','Accro-branche','Est-ce une marque déposée ?',1,6);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000023','0000000002','Nautique','Aqua-Gym','de 7 à 777 ans',6,22);
INSERT INTO Activite (uidActivite,uidCentre,categorie,nom,description,nbMinStagiaireGroupe,nbMaxParMoniteur) VALUES
('0000000024','0000000002','Nautique','Aqua-sieste','Prenez votre Matelas gonflable',6,22);

CREATE TABLE Groupe(
    uidGroupe CHAR(10),
    niveau VARCHAR2(10) NOT NULL,
    CONSTRAINT pk_Groupe PRIMARY KEY(uidGroupe),
    CONSTRAINT groupe_niveauExiste CHECK(niveau in ('Debutant', 'Confirme', 'Expert'))
);

CREATE TABLE Seance(
    uidSeance CHAR(10),
    uidActivite CHAR(10),
    uidCentre CHAR(10),
    uidGroupe CHAR(10),
    dateSeance date NOT NULL,
    heureDebut number(*,0) NOT NULL,
    duree number(*,2) NOT NULL,
    CONSTRAINT pk_Seance PRIMARY KEY(uidSeance, uidActivite, uidCentre),
    CONSTRAINT fk_Seance_uidAct_Centr FOREIGN KEY (uidActivite,uidCentre) REFERENCES Activite(uidActivite,uidCentre),
    CONSTRAINT fk_Seance_uidGroupe FOREIGN KEY (uidGroupe) REFERENCES Groupe(uidGroupe),
    CONSTRAINT dureePositif CHECK(duree > 0)
);

CREATE TABLE Materiel(
    uidMateriel CHAR(10),
    uidCentre CHAR(10),
    type VARCHAR2(20) NOT NULL,
    marque VARCHAR2(20) NOT NULL,
    modele VARCHAR2(20) NOT NULL,
    niveau VARCHAr2(10) NOT NULL,
    stock number(*,0) NOT NULL,
    CONSTRAINT pk_Materiel PRIMARY KEY(uidMateriel, uidCentre),
    CONSTRAINT fk_Materiel_uidCentre FOREIGN KEY (uidCentre) REFERENCES Centre(uidCentre),
    CONSTRAINT stockPositif CHECK(stock>0),
    CONSTRAINT niveauExiste CHECK( niveau in ('Debutant', 'Confirme', 'Expert'))
);

CREATE TABLE Personne(
    mail VARCHAR2(100),
    uidAdresse CHAR(10),
    nom VARCHAR2(50) NOT NULL,
    prenom VARCHAR2(50) NOT NULL,
    dateNaissance date NOT NULL,
    tel CHAR(10) NOT NULL,
    CONSTRAINT pk_Personne PRIMARY KEY(mail),
    CONSTRAINT fk_Personne_uidAdr FOREIGN KEY(uidAdresse) REFERENCES Adresse(uidAdresse)
);
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('patouargent45@fric.com','0000000004','Argent','Patrick',to_date('13-09-1943','DD-MM-YYYY'),'0145886575');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('isargent45@fric.com','0000000004','Argent','Isabelle',to_date('27-04-1946','DD-MM-YYYY'),'0145886575');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('rhum60@vin.de','0000000005','Kollyck','Al',to_date('10-04-1960','DD-MM-YYYY'),'0123456789');

INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('lidye@hotmail.fr','0000000006','Oduvillaj','Lidye',to_date('01-01-1999','DD-MM-YYYY'),'9876543210');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('kelly@hotmail.fr','0000000006','Diote','Kelly',to_date('28-10-1996','DD-MM-YYYY'),'0423416592');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('timilexot-6793@yopmail.com','0000000007','Lexot','Timi',to_date('10-10-1910','DD-MM-YYYY'),'0865321763');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('yboddilyd-0713@yopmail.com','0000000008','Aire','Axel',to_date('11-10-1910','DD-MM-YYYY'),'0496613387');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('typpuwyxef-9525@yopmail.com','0000000009','Tim','Vic',to_date('12-10-1910','DD-MM-YYYY'),'1441844560');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('adennarriss-2526@yopmail.com','0000000010','Strueux','Simon',to_date('13-10-1910','DD-MM-YYYY'),'0998580088');

INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('merridduwa-0932@yopmail.com','0000000011','Aimar','Jean',to_date('14-10-1910','DD-MM-YYYY'),'3799782685');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('elijiffall-0341@yopmail.com','0000000012','Proviste','Alain',to_date('15-10-1910','DD-MM-YYYY'),'8385039342');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('qewytiddeb-7671@yopmail.com','0000000013','Aroide','Paul',to_date('16-10-1910','DD-MM-YYYY'),'7004996446');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('middepputabu-5625@yopmail.com','0000000014','Pelle','Sarah',to_date('17-10-1910','DD-MM-YYYY'),'8265651820');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('wabavyko-3343@yopmail.com','0000000015','Croche','Sarah',to_date('18-10-1910','DD-MM-YYYY'),'4102273523');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('ojigihot-6691@yopmail.com','0000000016','Courci','Sarah',to_date('19-10-1910','DD-MM-YYYY'),'0791280009');

INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('awallaffaffo-3862@yopmail.com','0000000017','Cover','Harry',to_date('20-10-1910','DD-MM-YYYY'),'6882601550');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('tummoppesar-5348@yopmail.com','0000000018','Peuplus','Jean',to_date('30-10-1910','DD-MM-YYYY'),'6081066229');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('annavizyk-6570@yopmail.com','0000000018','Melmou','Cara',to_date('21-10-1910','DD-MM-YYYY'),'3948427069');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('esisar@etu.grenoble-inp.fr','0000000017','Renault','Mégane',to_date('22-10-1910','DD-MM-YYYY'),'3584404600');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('eddubaffyl-3375@yopmail.com','0000000016','Pludbiere','Roger',to_date('10-11-1910','DD-MM-YYYY'),'7062727856');
INSERT INTO Personne (mail,uidAdresse,nom,prenom,dateNaissance,tel) VALUES
('ikuttuppa-4958@yopmail.com','0000000015','Némard','Jean',to_date('10-12-1910','DD-MM-YYYY'),'4681895616');



CREATE TABLE Stagiaire(
    mail VARCHAR2(100),
    CONSTRAINT pk_Stagiaire PRIMARY KEY(mail),
    CONSTRAINT fk_Stagiaire_mail FOREIGN KEY (mail) REFERENCES Personne(mail)
);

CREATE TABLE Badge(
    uidBadge CHAR(10),
    validite date NOT NULL,
    CONSTRAINT pk_Badge PRIMARY KEY(uidBadge)
);

INSERT INTO Badge (uidBadge,validite) VALUES
('4242424242',to_date('31-12-2018','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('0865321763',to_date('15-06-2020','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('5831529712',to_date('05-10-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('1750084164',to_date('07-11-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('0326198378',to_date('28-08-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('0604032035',to_date('10-08-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('0968738048',to_date('11-07-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('2674388382',to_date('13-06-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('5102427674',to_date('30-05-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('3830935807',to_date('24-04-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('2396820462',to_date('14-03-2020','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('0495474462',to_date('01-02-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('7809789723',to_date('02-01-2020','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('8436888304',to_date('03-10-2019','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('5343490158',to_date('04-12-2020','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('4141077758',to_date('23-11-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('4497101081',to_date('10-08-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('7807651560',to_date('23-09-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('9020475944',to_date('23-09-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('2754580255',to_date('23-09-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('3654780200',to_date('23-09-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('7026939006',to_date('23-09-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('7582879150',to_date('23-09-2119','DD-MM-YYYY'));
INSERT INTO Badge (uidBadge,validite) VALUES
('4647436932',to_date('23-09-2119','DD-MM-YYYY'));


CREATE TABLE Responsable(
    mail VARCHAR2(100),
    uidCentre CHAR(10) NOT NULL,
    uidBadge CHAR(10) NOT NULL,
    CONSTRAINT pk_Responsable PRIMARY KEY(mail),
    CONSTRAINT fk_Responsable_mail FOREIGN KEY (mail) REFERENCES Personne(mail),
    CONSTRAINT fk_Responsable_uidBadge FOREIGN KEY (uidBadge) REFERENCES Badge(uidBadge),
    CONSTRAINT fk_Responsable_uidCentre FOREIGN KEY (uidCentre) REFERENCES Centre(uidCentre)
);

INSERT INTO Responsable (mail,uidCentre,uidBadge) VALUES
('patouargent45@fric.com','0000000001','4242424242');
INSERT INTO Responsable (mail,uidCentre,uidBadge) VALUES
('isargent45@fric.com','0000000002','0865321763');
INSERT INTO Responsable (mail,uidCentre,uidBadge) VALUES
('rhum60@vin.de','0000000003','5831529712');

CREATE TABLE Moniteur(
    mail VARCHAR2(100),
    uidBadge CHAR(10) NOT NULL,
    CONSTRAINT pk_Moniteur PRIMARY KEY(mail),
    CONSTRAINT fk_Moniteur_mail FOREIGN KEY (mail) REFERENCES Personne(mail),
    CONSTRAINT fk_Moniteur_uidBadge FOREIGN KEY (uidBadge) REFERENCES Badge(uidBadge)
);

INSERT INTO Moniteur (mail,uidBadge) VALUES
('lidye@hotmail.fr','1750084164');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('kelly@hotmail.fr','0326198378');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('timilexot-6793@yopmail.com','0604032035');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('yboddilyd-0713@yopmail.com','3830935807');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('typpuwyxef-9525@yopmail.com','2396820462');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('adennarriss-2526@yopmail.com','0495474462');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('merridduwa-0932@yopmail.com','7809789723');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('elijiffall-0341@yopmail.com','8436888304');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('qewytiddeb-7671@yopmail.com','5343490158');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('middepputabu-5625@yopmail.com','4141077758');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('wabavyko-3343@yopmail.com','4497101081');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('ojigihot-6691@yopmail.com','7807651560');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('awallaffaffo-3862@yopmail.com','9020475944');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('tummoppesar-5348@yopmail.com','2754580255');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('annavizyk-6570@yopmail.com','3654780200');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('esisar@etu.grenoble-inp.fr','7026939006');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('eddubaffyl-3375@yopmail.com','7582879150');
INSERT INTO Moniteur (mail,uidBadge) VALUES
('ikuttuppa-4958@yopmail.com','4647436932');

CREATE TABLE Inscription(
    uidActivite CHAR(10),
    uidCentre CHAR(10),
    mail VARCHAR2(100),
    dateDebut date NOT NULL,
    dateFin date NOT NULL,
    CONSTRAINT pk_Inscription PRIMARY KEY(uidActivite, uidCentre, mail),
    CONSTRAINT fk_Inscription_ActCentr FOREIGN KEY (uidActivite, uidCentre) REFERENCES Activite(uidActivite, uidCentre),
    CONSTRAINT fk_Inscription_mail FOREIGN KEY (mail) REFERENCES Stagiaire(mail),
    CONSTRAINT coherenceDate CHECK(dateDebut < dateFin)
);

CREATE TABLE Emprunt_materiel(
    uidMateriel CHAR(10),
    uidCentre CHAR(10),
    uidSeance CHAR(10),
    uidActivite CHAR(10),
    quantite number(*,0) NOT NULL,
    CONSTRAINT pk_Emprunt_materiel PRIMARY KEY(uidMateriel, uidCentre, uidSeance, uidActivite),
    CONSTRAINT fk_Emprunt_mat_uidMatCentr FOREIGN KEY (uidMateriel, uidCentre) REFERENCES Materiel(uidMateriel, uidCentre),
    CONSTRAINT fk_Emprunt_mat_uidSean FOREIGN KEY (uidSeance, uidActivite, uidCentre) REFERENCES Seance(uidSeance, uidActivite, uidCentre)
);

CREATE TABLE Habilite_a_encadrer(
    uidActivite CHAR(10),
    uidCentre CHAR(10),
    mail VARCHAR2(100),
    CONSTRAINT pk_hab_a_enc PRIMARY KEY(uidActivite, uidCentre, mail),
    CONSTRAINT fk_hab_a_enc_ActCentr FOREIGN KEY (uidActivite, uidCentre) REFERENCES Activite(uidActivite, uidCentre),
    CONSTRAINT fk_hab_a_enc_mail FOREIGN KEY (mail) REFERENCES Moniteur(mail)
);

INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000001','0000000001','lidye@hotmail.fr');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000002','0000000001','kelly@hotmail.fr');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000001','0000000001','timilexot-6793@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000002','0000000001','timilexot-6793@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000011','0000000001','timilexot-6793@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000012','0000000001','timilexot-6793@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000013','0000000001','timilexot-6793@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000003','0000000003','yboddilyd-0713@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000014','0000000001','yboddilyd-0713@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000002','0000000001','yboddilyd-0713@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000016','0000000001','typpuwyxef-9525@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000015','0000000001','adennarriss-2526@yopmail.com');

INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000024','0000000002','merridduwa-0932@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000023','0000000002','merridduwa-0932@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000022','0000000002','merridduwa-0932@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000021','0000000002','merridduwa-0932@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000020','0000000002','merridduwa-0932@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000024','0000000002','elijiffall-0341@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000002','0000000001','elijiffall-0341@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000001','0000000001','elijiffall-0341@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000023','0000000002','elijiffall-0341@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000017','0000000002','qewytiddeb-7671@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000018','0000000002','qewytiddeb-7671@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000019','0000000002','qewytiddeb-7671@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000020','0000000002','qewytiddeb-7671@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000024','0000000002','middepputabu-5625@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000023','0000000002','middepputabu-5625@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000023','0000000002','wabavyko-3343@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000022','0000000002','wabavyko-3343@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000021','0000000002','wabavyko-3343@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000020','0000000002','wabavyko-3343@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000019','0000000002','wabavyko-3343@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000018','0000000002','wabavyko-3343@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000017','0000000002','wabavyko-3343@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000024','0000000002','ojigihot-6691@yopmail.com');

INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000003','0000000003','awallaffaffo-3862@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000004','0000000003','awallaffaffo-3862@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000005','0000000003','awallaffaffo-3862@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000006','0000000003','awallaffaffo-3862@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000007','0000000003','awallaffaffo-3862@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000007','0000000003','tummoppesar-5348@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000008','0000000003','tummoppesar-5348@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000009','0000000003','tummoppesar-5348@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000010','0000000003','tummoppesar-5348@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000010','0000000003','annavizyk-6570@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000001','0000000001','annavizyk-6570@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000020','0000000002','annavizyk-6570@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000009','0000000003','annavizyk-6570@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000011','0000000001','annavizyk-6570@yopmail.com');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000002','0000000001','esisar@etu.grenoble-inp.fr');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000010','0000000003','esisar@etu.grenoble-inp.fr');
INSERT INTO Habilite_a_encadrer (uidActivite,uidCentre,mail) VALUES
('0000000010','0000000003','ikuttuppa-4958@yopmail.com');

CREATE TABLE Encadre(
    uidSeance CHAR(10),
    uidActivite CHAR(10),
    uidCentre CHAR(10),
    mail VARCHAR2(100),
    CONSTRAINT pk_encadre PRIMARY KEY(uidSeance, uidActivite, uidCentre, mail),
    CONSTRAINT fk_encadre_seance FOREIGN KEY (uidSeance, uidActivite, uidCentre) REFERENCES Seance(uidSeance, uidActivite, uidCentre),
    CONSTRAINT fk_encadre_mail FOREIGN KEY (mail) REFERENCES Moniteur(mail)
);

CREATE TABLE Appartient_au_groupe(
    uidGroupe CHAR(20),
    mail VARCHAR2(100),
    CONSTRAINT pk_appart_au_grp PRIMARY KEY(uidGroupe, mail),
    CONSTRAINT fk_appart_au_grp_uidGroupe FOREIGN KEY (uidGroupe) REFERENCES Groupe(uidGroupe),
    CONSTRAINT fk_appart_au_grp_mail FOREIGN KEY (mail) REFERENCES Stagiaire(mail)
);
​
