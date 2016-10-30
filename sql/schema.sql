CREATE TABLE student (
    `id` bigint PRIMARY KEY not null,
    `name` varchar(64) not null,
    `email` varchar(64) not null,
    `age` int not null,
    `gender` varchar(8) not null,
    `group` varchar(16) not null,
    `year` int not null
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;