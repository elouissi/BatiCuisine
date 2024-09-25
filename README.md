# BatiCuisine

BatiCuisine est une application de gestion de projets pour le secteur de la construction, permettant de créer, gérer et suivre des projets incluant les matériaux, la main-d'œuvre et les devis. Elle permet également de calculer les coûts et la TVA pour les projets.

## Fonctionnalités

- **Gestion des projets** : Créez, mettez à jour et suivez l'état des projets de construction.
- **Gestion des composants** : Suivi des matériaux et de la main-d'œuvre associés à chaque projet.
- **Gestion des devis** : Création de devis avec un suivi des dates d'émission et de validité, gestion de l'acceptation des devis.
- **Calcul des coûts et de la TVA** : Calcul automatique des coûts totaux des projets avec prise en compte de la TVA.
- **État des projets** : Suivi de l'état des projets, avec possibilité de les marquer comme "Terminés" ou "Annulés" en fonction des décisions liées au devis.

## Technologies

- **Langage** : Java
- **Base de données** : PostgreSQL
- **Bibliothèque JDBC** : Pour la gestion des interactions avec la base de données
- **Framework JDBC** : Utilisé pour l'accès et la manipulation des données dans PostgreSQL
- **DateTimeFormatter** : Pour la gestion des formats de date

## Installation

### Prérequis

1. **Java** : Installez Java (version 8 ou supérieure).
2. **PostgreSQL** : Installez PostgreSQL et configurez une base de données pour l'application.
3. **JDBC** : Configurez les drivers JDBC pour la connexion à PostgreSQL.

### Configuration

1. Clonez ce dépôt sur votre machine locale :

    ```bash
    git clone https://github.com/votre-compte/BatiCuisine.git
    ```

2. Configurez la base de données PostgreSQL avec les tables nécessaires :

    ```sql
    CREATE TABLE Composants (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        TypeComposant VARCHAR(100),
        TauxTVA DOUBLE PRECISION
    );

    CREATE TABLE Materiaux (
        coutUnitaire DOUBLE PRECISION,
        quantite DOUBLE PRECISION,
        coutTransport DOUBLE PRECISION
    ) INHERITS (Composants);

    CREATE TABLE Main_oeuvre (
        tauxHoraire DOUBLE PRECISION,
        heuresTravail DOUBLE PRECISION,
        productiviteOuvrier DOUBLE PRECISION,
        typeMain_oeuvre VARCHAR(255)
    ) INHERITS (Composants);

    CREATE TABLE Devis (
        id SERIAL PRIMARY KEY,
        montantEstime DOUBLE PRECISION,
        dateEmission DATE,
        dateValidite DATE,
        accepte BOOLEAN,
        projectId INT REFERENCES Projet(id)
    );

    CREATE TABLE Projet (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100),
        etatProjet VARCHAR(50),
        coutTotal DOUBLE PRECISION,
        margeBeneficiaire DOUBLE PRECISION
    );
    ```

3. Mettez à jour la configuration de la connexion à la base de données dans votre code Java (fichier `db.properties`) :

    ```properties
    db.url=jdbc:postgresql://localhost:5432/baticuisine
    db.username=GreenPulse
    db.password=""
    ```

### Lancement de l'application

1. Compilez et exécutez l'application à partir de votre IDE préféré (comme IntelliJ IDEA ou Eclipse).
2. L'application vous demandera des informations sur les projets, composants et devis, et vous guidera dans leur gestion.

## Utilisation

### Gestion des Projets

- **Créer un projet** : Saisissez les informations relatives au projet (nom, coût estimé, etc.).
- **Mettre à jour un projet** : Modifiez les informations d'un projet existant.
- **Changer l'état d'un projet** : Un projet peut être marqué comme "Annulé" ou "Terminé" en fonction de l'acceptation du devis.

### Gestion des Devis

- **Créer un devis** : Saisissez les dates d'émission et de validité, ainsi que le montant estimé du projet.
- **Accepter ou refuser un devis** : L'utilisateur peut accepter ou refuser un devis, ce qui met à jour l'état du projet associé.

### Gestion des Composants

- **Ajouter des composants** : Matériaux ou main-d'œuvre peuvent être ajoutés à un projet.
- **Calcul de la TVA** : Appliquez automatiquement la TVA aux composants si nécessaire.

## Structure du projet

- **Main.java** : Point d'entrée de l'application.
- **Service/** : Contient la logique métier pour les projets, composants, et devis.
- **Repository/** : Interactions avec la base de données (CRUD).
- **View/** : Interface utilisateur console, pour l'interaction avec les projets et les devis.

## Contribution

Si vous souhaitez contribuer à ce projet, veuillez suivre les étapes suivantes :

1. Forkez le dépôt
2. Créez une branche (`git checkout -b feature/amazing-feature`)
3. Commitez vos changements (`git commit -m 'Add some amazing feature'`)
4. Poussez la branche (`git push origin feature/amazing-feature`)
5. Ouvrez une Pull Request

## Licence

Ce projet est sous licence MIT. Veuillez consulter le fichier `LICENSE` pour plus de détails.
