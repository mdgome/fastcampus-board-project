CREATE DATABASE IF NOT EXISTS boasrd;

CREATE USER `mdgome`@`%` IDENTIFIED BY 'mdgomeBoard@#02';
SELECT user FROM mysql.user;

GRANT ALL ON `board`.* TO `mdgome`@`%` WITH GRANT OPTION;
SHOW GRANTS FOR `mdgome`@`%`;

FLUSH PRIVILEGES;