# twitch-stream-finder

This Repo contains two programs. 

First is a Server that fetches data from [Twitch.tv](https://twitch.tv) website using their rest api. Then the server inturn exposes another rest api which lets you do more fine grain searches based on a multitude of filters.

The second is a Simple android app that uses the server rest api. for an example on how to use the rest server.

## Setup

### Requiremnts

This program uses two IDEs Intellij and Android Studio
Also MYSQL database with imported table called twitch.

### Steps

1. Mysql Import Schema
    
  Go to sql folder and import db.sql file to a schema named twitch.
  [Folder](https://github.com/afTrolle/twitch-stream-finder/tree/panic-push/sql)
  
2. Config rest api
  
  Go to resources folder in the server directory. 
  [Folder](https://github.com/afTrolle/twitch-stream-finder/tree/panic-push/server/src/main/resources)
  There is a file called "twitchConfig.template.properties" make a copy of it named "twitchConfig.properties"
  Then fill in the empty fields in that file.

3. Open Server with Intellij and generate static classes

Open build.gradle update in the lower part the mysql login and user name. then run assemble to generate the missing classes.

4. Start Server

  Configuratio is done of server run Main.

## SQL Database ERD

![GitHub Logo](/sql/sql_erd.png)
