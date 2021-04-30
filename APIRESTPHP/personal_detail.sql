CREATE TABLE `personal_detail` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `age` int(11) NOT NULL,
  `mobile` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla para hacer pruebas API REST PHP';

ALTER TABLE `personal_detail`  ADD PRIMARY KEY (`id`);

ALTER TABLE `personal_detail`  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;


