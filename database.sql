-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `aprovacao_votacao`
--

DROP TABLE IF EXISTS `aprovacao_votacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aprovacao_votacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_votacao` int NOT NULL,
  `id_admin` int NOT NULL,
  `status` enum('PENDENTE','APROVADA','REPROVADA') NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID_UNIQUE` (`id`),
  KEY `ID_Admin_idx` (`id_admin`),
  CONSTRAINT `FK_idAdmin` FOREIGN KEY (`id_admin`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aprovacao_votacao`
--

LOCK TABLES `aprovacao_votacao` WRITE;
/*!40000 ALTER TABLE `aprovacao_votacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `aprovacao_votacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `id_Grupos` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(225) NOT NULL,
  `tipo_grupo` varchar(20) NOT NULL DEFAULT 'CURSO',
  PRIMARY KEY (`id_Grupos`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (5,'ANÁLISE E DESENVOLVIMENTO DE SISTEMAS','CURSO'),(6,'ENGENHARIA CIVIL','CURSO'),(7,'ENGENHARIA DE CONTROLE E AUTOMAÇÃO','CURSO'),(8,'ENGENHARIA QUÍMICA','CURSO'),(9,'FÍSICA','CURSO'),(10,'PROFESSOR','CURSO'),(11,'SERVIDOR','CURSO'),(12,'TODOS OS ALUNOS','VIRTUAL'),(13,'TODOS OS SERVIDORES','VIRTUAL'),(14,'SERVIDORES E ALUNOS','VIRTUAL'),(15,'ADMINISTRADORES','PERMISSAO');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcao_voto`
--

DROP TABLE IF EXISTS `opcao_voto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcao_voto` (
  `id_opcao` int NOT NULL AUTO_INCREMENT,
  `id_votacao` int NOT NULL,
  `descricao` varchar(225) NOT NULL,
  PRIMARY KEY (`id_opcao`),
  UNIQUE KEY `id_opcao_UNIQUE` (`id_opcao`),
  KEY `id_votacao_idx` (`id_votacao`),
  CONSTRAINT `FKid_votacao` FOREIGN KEY (`id_votacao`) REFERENCES `votacao` (`id_Votacao`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcao_voto`
--

LOCK TABLES `opcao_voto` WRITE;
/*!40000 ALTER TABLE `opcao_voto` DISABLE KEYS */;
INSERT INTO `opcao_voto` VALUES (1,1,'eu'),(2,1,'sim'),(3,2,'dada'),(4,2,'da');
/*!40000 ALTER TABLE `opcao_voto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resultado`
--

DROP TABLE IF EXISTS `resultado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resultado` (
  `id_Resultado` int NOT NULL,
  `id_Votacao` int NOT NULL,
  `id_Voto` int NOT NULL,
  `id_Opcao` int NOT NULL,
  `total_Votos` int NOT NULL,
  `data_Registro` date NOT NULL,
  PRIMARY KEY (`id_Resultado`),
  UNIQUE KEY `id_Resultado_UNIQUE` (`id_Resultado`),
  KEY `id_votacao_idx` (`id_Votacao`),
  KEY `id_voto_idx` (`id_Voto`),
  KEY `id_opcao_idx` (`id_Opcao`),
  CONSTRAINT `FK_id_Votacao` FOREIGN KEY (`id_Votacao`) REFERENCES `votacao` (`id_Votacao`),
  CONSTRAINT `FKid_opcao` FOREIGN KEY (`id_Opcao`) REFERENCES `opcao_voto` (`id_opcao`),
  CONSTRAINT `FKid_voto` FOREIGN KEY (`id_Voto`) REFERENCES `voto` (`id_Voto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resultado`
--

LOCK TABLES `resultado` WRITE;
/*!40000 ALTER TABLE `resultado` DISABLE KEYS */;
/*!40000 ALTER TABLE `resultado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupos`
--

DROP TABLE IF EXISTS `usuario_grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_grupos` (
  `id_usuario_grupo` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_grupo` int NOT NULL,
  PRIMARY KEY (`id_usuario_grupo`),
  KEY `fk_usuariogrupo_usuario_idx` (`id_usuario`),
  KEY `fk_usuariogrupo_grupo_idx` (`id_grupo`),
  CONSTRAINT `fk_usuariogrupo_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupos` (`id_Grupos`),
  CONSTRAINT `fk_usuariogrupo_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupos`
--

LOCK TABLES `usuario_grupos` WRITE;
/*!40000 ALTER TABLE `usuario_grupos` DISABLE KEYS */;
INSERT INTO `usuario_grupos` VALUES (1,41,5),(2,42,6),(3,1,15);
/*!40000 ALTER TABLE `usuario_grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `CPF` varchar(45) NOT NULL,
  `matricula` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` char(64) NOT NULL,
  `Tipo_usuario` varchar(45) NOT NULL,
  `token` varchar(10) DEFAULT NULL,
  `token_expiracao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `ID_usuario_UNIQUE` (`id_usuario`),
  UNIQUE KEY `Nome_UNIQUE` (`nome`),
  UNIQUE KEY `CPF_UNIQUE` (`CPF`),
  UNIQUE KEY `Matricula_UNIQUE` (`matricula`),
  UNIQUE KEY `UN_email_usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Administrador','000.000.000-00','0000000000000','admin@ifro.edu.br','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','ADMIN','826159','2025-07-12 12:09:40'),(41,'Athos Ribeiro','092.189.074-91','2024106090016','athos.r@estudante.ifro.edu.br','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','DISCENTE',NULL,NULL),(42,'Floriano Araujo','089.452.175-93','2024106090025','f.araujo@estudante.ifro.edu.br','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','DISCENTE','832770','2025-07-12 22:53:09');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votacao`
--

DROP TABLE IF EXISTS `votacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `votacao` (
  `id_Votacao` int NOT NULL AUTO_INCREMENT,
  `id_Criador` int NOT NULL,
  `id_grupo_destino` int NOT NULL,
  `data_Resultado` date NOT NULL,
  `titulo` varchar(225) NOT NULL,
  `descricao` text,
  `data_inicio` date NOT NULL,
  `data_fim` date NOT NULL,
  `status` enum('PENDENTE','APROVADA','REPROVADA','CONCLUIDA') NOT NULL,
  `pergunta` varchar(100) NOT NULL,
  PRIMARY KEY (`id_Votacao`),
  UNIQUE KEY `id_Votacao_UNIQUE` (`id_Votacao`),
  KEY `id_criador_idx` (`id_Criador`),
  KEY `id_grupo_destino_idx` (`id_grupo_destino`),
  CONSTRAINT `FK_idCriador` FOREIGN KEY (`id_Criador`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `FK_idGrupoDestino` FOREIGN KEY (`id_grupo_destino`) REFERENCES `grupos` (`id_Grupos`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votacao`
--

LOCK TABLES `votacao` WRITE;
/*!40000 ALTER TABLE `votacao` DISABLE KEYS */;
INSERT INTO `votacao` VALUES (1,1,5,'2025-07-14','Titulo','Descreve','2025-07-14','2025-07-14','APROVADA','Eu mesmo?'),(2,1,5,'2025-07-14','dada','','2025-07-14','2025-07-14','APROVADA','dada');
/*!40000 ALTER TABLE `votacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voto`
--

DROP TABLE IF EXISTS `voto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voto` (
  `id_Voto` int NOT NULL AUTO_INCREMENT,
  `id_Votacao` int NOT NULL,
  `id_Usuario` int NOT NULL,
  `id_Opcao` int NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id_Voto`),
  UNIQUE KEY `id_Voto_UNIQUE` (`id_Voto`),
  KEY `id_votacao_idx` (`id_Votacao`),
  KEY `id_usuario_idx` (`id_Usuario`),
  KEY `id_opcao_idx` (`id_Opcao`),
  CONSTRAINT `FK_id_Usuario` FOREIGN KEY (`id_Usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `FK_idOpcao` FOREIGN KEY (`id_Opcao`) REFERENCES `opcao_voto` (`id_opcao`),
  CONSTRAINT `FK_idVotacao` FOREIGN KEY (`id_Votacao`) REFERENCES `votacao` (`id_Votacao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voto`
--

LOCK TABLES `voto` WRITE;
/*!40000 ALTER TABLE `voto` DISABLE KEYS */;
INSERT INTO `voto` VALUES (1,2,1,3,'2025-07-14');
/*!40000 ALTER TABLE `voto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-14 19:46:39
