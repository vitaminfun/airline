CREATE TABLE `airplane` (
                            `id_airplane` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) NOT NULL,
                            `numbersOfSeats` varchar(45) NOT NULL,
                            PRIMARY KEY (`id_airplane`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `flight` (
                          `id_flight` int(11) NOT NULL AUTO_INCREMENT,
                          `id_airplane` int(11) NOT NULL,
                          `from` varchar(45) NOT NULL,
                          `to` varchar(45) NOT NULL,
                          `date` datetime(6) NOT NULL,
                          PRIMARY KEY (`id_flight`),
                          KEY `id_airplane_idx` (`id_airplane`),
                          CONSTRAINT `id_airplane` FOREIGN KEY (`id_airplane`) REFERENCES `airplane` (`id_airplane`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ticket` (
                          `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
                          `id_flight` int(11) NOT NULL,
                          `seat` int(11) NOT NULL,
                          `cost` int(11) NOT NULL,
                          PRIMARY KEY (`id_ticket`),
                          KEY `id_flight_idx` (`id_flight`),
                          CONSTRAINT `id_flight` FOREIGN KEY (`id_flight`) REFERENCES `flight` (`id_flight`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `client` (
                          `id_client` int(11) NOT NULL AUTO_INCREMENT,
                          `first_name` varchar(45) NOT NULL,
                          `last_name` varchar(45) NOT NULL,
                          `id_ticket` int(11) NOT NULL,
                          `vip` tinyint(4) NOT NULL,
                          `luggage` tinyint(4) NOT NULL,
                          PRIMARY KEY (`id_client`),
                          KEY `id_ticket_idx` (`id_ticket`),
                          CONSTRAINT `id_ticket` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id_ticket`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
                        `id_user` int(11) NOT NULL AUTO_INCREMENT,
                        `login` varchar(45) NOT NULL,
                        `first_name` varchar(45) NOT NULL,
                        `last_name` varchar(45) NOT NULL,
                        `password` varchar(45) NOT NULL,
                        `isAdmin` tinyint(4) NOT NULL,
                        PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;