CREATE DATABASE  IF NOT EXISTS `pokemondb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pokemondb`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: pokemondb
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pokemon`
--

DROP TABLE IF EXISTS `pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon` (
  `idpokemon` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `levelToFoundOrEvol` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpokemon`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
INSERT INTO `pokemon` VALUES (1,'steelix',5),(2,'Onix',40),(3,'aron',2),(4,'Lairon',10),(5,'Aggron',20),(6,'Digglet',0),(7,'Sandslash',15),(8,'Sandshrew',2),(9,'Gastly',2),(10,'Haunter',10),(11,'Gengar',22),(12,'Charmander',2),(13,'Charmaleon',10),(14,'Charizard',20),(15,'Moltres',21),(16,'squirtle',1),(17,'Wartortle',15),(18,'Blastoise',30),(19,'steelixIn',5),(20,'GengarIto',10),(21,'aronSillo',4),(22,'DiggletSito',1),(23,'AggronSillo',18),(24,'HaunterRigillo',10),(25,'BlastoiseTron',18);
/*!40000 ALTER TABLE `pokemon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemonevolution`
--

DROP TABLE IF EXISTS `pokemonevolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemonevolution` (
  `pokemon_idpokemon` int(11) NOT NULL,
  `idpokemonEvolution` int(11) DEFAULT NULL,
  PRIMARY KEY (`pokemon_idpokemon`),
  KEY `fk_pokemonEvolution_pokemonEvolution1_idx` (`idpokemonEvolution`),
  CONSTRAINT `fk_pokemonEvolution_pokemon1` FOREIGN KEY (`pokemon_idpokemon`) REFERENCES `pokemon` (`idpokemon`),
  CONSTRAINT `fk_pokemonEvolution_pokemonEvolution1` FOREIGN KEY (`idpokemonEvolution`) REFERENCES `pokemonevolution` (`pokemon_idpokemon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemonevolution`
--

LOCK TABLES `pokemonevolution` WRITE;
/*!40000 ALTER TABLE `pokemonevolution` DISABLE KEYS */;
INSERT INTO `pokemonevolution` VALUES (1,NULL),(3,NULL),(6,NULL),(7,NULL),(9,NULL),(11,NULL),(12,NULL),(15,NULL),(16,NULL),(2,1),(4,3),(5,4),(8,7),(10,9),(13,12),(14,13),(17,16),(18,17);
/*!40000 ALTER TABLE `pokemonevolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemonevolution_has_type`
--

DROP TABLE IF EXISTS `pokemonevolution_has_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemonevolution_has_type` (
  `pokemonEvolution_pokemon_idpokemon` int(11) NOT NULL,
  `type_idtype` int(11) NOT NULL,
  PRIMARY KEY (`pokemonEvolution_pokemon_idpokemon`,`type_idtype`),
  KEY `fk_pokemonEvolution_has_type_type1_idx` (`type_idtype`),
  KEY `fk_pokemonEvolution_has_type_pokemonEvolution1_idx` (`pokemonEvolution_pokemon_idpokemon`),
  CONSTRAINT `fk_pokemonEvolution_has_type_pokemonEvolution1` FOREIGN KEY (`pokemonEvolution_pokemon_idpokemon`) REFERENCES `pokemonevolution` (`pokemon_idpokemon`),
  CONSTRAINT `fk_pokemonEvolution_has_type_type1` FOREIGN KEY (`type_idtype`) REFERENCES `type` (`idtype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemonevolution_has_type`
--

LOCK TABLES `pokemonevolution_has_type` WRITE;
/*!40000 ALTER TABLE `pokemonevolution_has_type` DISABLE KEYS */;
INSERT INTO `pokemonevolution_has_type` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,2),(7,2),(8,2),(9,3),(10,3),(11,3),(12,4),(13,4),(14,4),(15,4),(16,5),(17,5),(18,5);
/*!40000 ALTER TABLE `pokemonevolution_has_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemonpet`
--

DROP TABLE IF EXISTS `pokemonpet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemonpet` (
  `pokemon_idpokemon` int(11) NOT NULL,
  `idpokemonEvolution` int(11) NOT NULL,
  `habilities` varchar(350) NOT NULL,
  PRIMARY KEY (`pokemon_idpokemon`,`idpokemonEvolution`),
  KEY `fk_pokemonPet_pokemon1_idx` (`pokemon_idpokemon`),
  KEY `fk_pokemonPet_pokemonEvolution1_idx` (`idpokemonEvolution`),
  CONSTRAINT `fk_pokemonPet_pokemon1` FOREIGN KEY (`pokemon_idpokemon`) REFERENCES `pokemon` (`idpokemon`),
  CONSTRAINT `fk_pokemonPet_pokemonEvolution1` FOREIGN KEY (`idpokemonEvolution`) REFERENCES `pokemonevolution` (`pokemon_idpokemon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemonpet`
--

LOCK TABLES `pokemonpet` WRITE;
/*!40000 ALTER TABLE `pokemonpet` DISABLE KEYS */;
INSERT INTO `pokemonpet` VALUES (19,1,'Se pone todo duro cuando lo molestan'),(20,11,'es un espectro que se puede hacer verde'),(21,3,'Enano de hierro busca pleito'),(22,6,'se sabe esconder bajo la tierra'),(23,5,'Evolucionó rápido y conseguirá experiencia más rápido aún'),(24,10,'Completamente intangible a menos que el sea quien ataque'),(25,18,'A diferencia de las demas de su especie esta tiene el metabolismo más acelerado y come menos');
/*!40000 ALTER TABLE `pokemonpet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `idtype` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(350) NOT NULL,
  PRIMARY KEY (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Acero','Fuerte contra Roca, Hielo, Hada Débil contra Acero, Fuego, Agua, Eléctrico'),(2,'Tierra','Fuerte contra Veneno, Roca, Acero, Fuego, Eléctrico y débil contra Volador, Bicho, Planta vulnerable a Agua, Planta, Hielo'),(3,'Fantasma','Fuerte contra Fantasma, Psíquico y débil contra Normal, Siniestro'),(4,'Fuego','Fuerte contra Bicho, Acero, Planta, Hielo	Débil contra Roca, Fuego, Agua, Dragón'),(5,'Agua','Fuerte contra Tierra, Roca, Fuego	Débil contra Agua, Planta, Dragón');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pokemondb'
--

--
-- Dumping routines for database 'pokemondb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-23 11:43:34
