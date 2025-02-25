# Projet Spring Boot - Gestion d'une médiathèque numérique

## Présentation

Ce projet est une application web développée en **Java Spring Boot**, permettant la gestion d'une médiathéque numérique'. Il repose sur une base de données MySQL et expose un CRUD sur les différentes entités.

---

## Technologies Utilisées
- **Java 17**
- **Spring Boot** (Spring MVC, Spring Data JPA)
- **Hibernate** (ORM)
- **MySQL**
- **Maven**

---

## Installation

### 1. Cloner le projet
```bash
git clone https://github.com/votre-repo/spring-boot-gestion.git
cd spring-boot-gestion
```

### 2. Configurer la base de données
Le projet utilise une base de données **Chinook_AutoIncrement**. Un script d'initialisation est disponible à la racine du projet.


 - Importer le script SQL situé à la racine du projet : `Chinook_MySql_AutoIncrementPKs.sql` dans MySQL.
 - Modifier `src/main/resources/application.properties` pour correspondre à votre configuration MySQL.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/chinook
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

  

### 3. **Construire et exécuter l'application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```


L'Application sera disponible sur : `http://localhost:8080`

---

## Fonctionnalités
- CRUD sur les employés (`/employees`)
- CRUD sur les clients (`/customers`)
- Requêtes personnalisées avec Spring Data JPA
- Gestion des erreurs et validation

---

## Historique des commits
| ID Commit  | Description |
|------------|------------|
| `a venir`  | Initialisation du projet avec Spring Boot - Gestion entités Employee et Customer |
| `...`  | ... |
| `...`  | ... |
| `...`  | ... |
| `...`  | ... |

---

## Fonctionnalités
- Gestion des employés : création, modification, suppression et affichage des employés.
- Gestion des clients : ajout, modification et suppression des clients.
- Interface utilisateur basée sur JSP.

---

## Contributeurs
- **[Delacroix Guillaume]** - Développeur principal

## Licence
Ce projet est sous licence AFPA.

