-- 這個檔案放置創建資料表的sql

CREATE TABLE IF NOT EXISTS student (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(30),
                         score DOUBLE,
                         graduate BOOLEAN,
                         create_date TIMESTAMP
);