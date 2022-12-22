# Socket_Projet


voici les requêtes possibles 


--Pour Creation/Utilisation des Databases
    dataCreate databaseName
    use databaseName

*le préfixe "table" à été ajouté pour se distinguer des autres requêtes*
--Pour Création d'une table et insertion des données
    tableCreate NomTable:col1,col2,...
    tableInsert into NomTable:data1,data2,...
    tableDelete NomTable  (delete the data of the table)

--Pour les operations entre une ou plusieurs tables

    select * from table
    select * from table where col1=val and ...
    select col1,col2... from table
    select col1,col2... from table where col1=val and ...
    
    join table1 with table2 on colonne=value
    intersect tab1 with tab2
    substract table1 with table2
    divide tab1 with table2
    Unify table1 with table2

NB: 
Il faut d'abord choisir une database existante avant toute chose
