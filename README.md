# Base de donnée
![Schéma BDD](/schema.png "Schéma BDD")
- [ ] Validation du schéma
- [ ] Création de la base de donnée
- [ ] Création du premier utilisateur admin

# Fenêtre d'authentification 
Page avec un formulaire de connexion et un formulaire d'inscription.
- [x] La page d'authentification doit s'ouvrir au lancement de l'app
- [x] Formulaire de connexion (email, mot de passe)
- [x] Formulaire d'inscription (pseudo, email, mot de passe)
- [ ] Pour créer un compte avec succès, l'e-mail doit être "sur liste blanche"
- [ ] Le mot de passe doit être haché
- [ ] Afficher erreurs de connexion ou d'inscription

# Fenêtre de l'application

## Onglet qui liste les articles
Page qui liste tous les articles.
- [ ] CRUD article

## Onglet qui liste les rôles
Page qui liste tous les rôles.
- [ ] CRUD rôle

## Onglet qui liste les magasins
Page qui liste tous les magasins existants.
- [ ] CRUD magasin

## Onglet qui liste les utilisateurs
Page qui liste tous les utilisateurs existants.
- [ ] CRUD utilisateur

## Onglet qui liste les emails sur liste blanche
Page qui liste tous les emails sur liste blanche.
- [ ] CRUD liste blanche

## Onglet qui liste les employés (du magasin courant)
Page qui liste tous les employés du magasin
- [ ] CRUD employés (Employé : ne peut se modifier/supprimer que soi-même)

## Onglet qui liste l'inventaire (du magasin courant)
Page qui liste tous les articles dans l'inventaire du magasin.
- [ ] CRUD inventaire (Employé : peut juste augmenter/diminuer le nombre d'articles)