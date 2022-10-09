# About #

## About the Toolbox ##

The Toolbox Science is an open source framework of services based on the fedora-commons repository software. 
The frameworks aim is to provide APIs to variant third party systems while beeing open to all kind of materials 
and internal structrures of materials. The Toolbox Science is feasable for monographs, series, journals, research 
data, open educational resources and website archives. 
Therefore the Toolbox is thought to be the swiss knife in the Open Access Repository world. 

To fulfill these expections the Toolbox Science on the one hand makes heavy use of the fedora-commons flexibe 
storage system and on the other hand provides a framework of RESTful API, which is used in a self consuming manner.    

## About to.science.core ##

The core components of the Toolbox will be restructured in an independent module with much less dependencies to other application frameworks like the play framework or a running application container (tomcat e.g.). Aim is to provide a core system representing the enhanceable data model of to.science and an low level API to connect metadata mappers with this model. 

The to.science data model is thought to be <em>open</em> for enhancements according the future needs meanwhile it aims to be <em>closed</em> to modifications of the already existing data model.  

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2edbb771373c4c308e3affb75968071c)](https://www.codacy.com/gh/hbz/to.science.core/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hbz/to.science.core&amp;utm_campaign=Badge_Grade)

## Prerequisites ##

-  OpenJDK 1.8 or above
-  Maven 3.x or so for integration and deployment

## Installation ##

No installation required. Can be used as library within other Software Modules. 

## Usage ##

Integrate to.science.core as dependency in your framework via Maven Central. 
Use with Maven via pom.xml:

```xml
<dependency>
    <groupId>io.github.hbz</groupId>
    <artifactId>to.science.core</artifactId>
    <version>1.1.0</version>
</dependency>
```
Use with SBT via build.sbt:

```
libraryDependencies += "io.github.hbz" % "to.science.core" % "1.1.0"
```

Get further details for integration at  https://central.sonatype.dev/artifact/io.github.hbz/to.science.core/1.1.0/overview

For running to.science.core standalone you can use the latest release to.science.core-jar-with-dependencies.jar file
