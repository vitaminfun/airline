CREATE TABLE `ticket` (
  `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
  `id_flight` int(11) NOT NULL,
  `seat` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `id_flight_idx` (`id_flight`),
  CONSTRAINT `id_flight` FOREIGN KEY (`id_flight`) REFERENCES `flight` (`id_flight`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci