CREATE TABLE `calendar_entry` (
  `id` bigint(20) NOT NULL,
  `date_completed` datetime DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `habit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_habit_id` (`habit_id`),
  CONSTRAINT `fk_habit_id` FOREIGN KEY (`habit_id`) 
REFERENCES `habit` (`id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
