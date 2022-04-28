# Projet SGBD

This project is a part of the [Ensimag Working Group](http://datascience.codeforsanfrancisco.org). You can find the subject in its entirety at the following address [main chamilo](https://github.com/sfbrigade/data-science-wg).

#### -- Project Status: [Completed]

## Project Intro/Objective
The purpose of this project is to manage car pulling via an app. 


### Technologies
* Java
* jdbc
* sql2 
* Maven
* Netbeans
* Swings
  

## Getting Started

1. The project is divided into 3 folders :
    * dbCreator --> contains the sql scripts to create the database (deployement phase)
    * voiture --> contains the source code of the app (development phase)
    * Complete java doc of the project
    
2. To launch the app, you need to have the database created.
    * To create the database, you can use the script createDB.sh in the dbCreator folder.
    * Run with Netbeans
    * If you have divers issue you might try: 
  
```mvn install:install-file -Dfile=lib/ojdbc6/ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar ```





## Contributing DSWG Members

| Name                                                     | Slack Handle |
| -------------------------------------------------------- | ------------ |
| [Haroun Al mounayar](https://github.com/almounah)        | @almounah    |
| [Alexandre Jullien ](https://gitlab.ensimag.fr/jullieal) | @jullieal    |
| [Najda Mohamamad](https://gitlab.ensimag.fr/najdam)      | @Najda       |
| [Romane Bos](https://gitlab.ensimag.fr/bosr)             | @bosr        |


