CREATE TABLE `flight` (
  `id_flight` int(11) NOT NULL AUTO_INCREMENT,
  `id_airplane` int(11) NOT NULL,
  `from` varchar(45) NOT NULL,
  `to` varchar(45) NOT NULL,
  `date` datetime(6) NOT NULL,
  PRIMARY KEY (`id_flight`),
  KEY `id_airplane_idx` (`id_airplane`),
  CONSTRAINT `id_airplane` FOREIGN KEY (`id_airplane`) REFERENCES `airplane` (`id_airplane`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci