CREATE TABLE authors (
  id         INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL
);

CREATE TABLE books (
  id        INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title     VARCHAR(200) NOT NULL,
  author_id INT,
  FOREIGN KEY (author_id) REFERENCES authors (id)
);

INSERT INTO authors (id, first_name, last_name)
VALUES
  (1, 'Leo', 'Tolstoy'),
  (2, 'Vassily', 'Grossman'),
  (3, 'Alexandre', 'Dumas'),
  (4, 'Fyodor', 'Dostoevsky'),
  (5, 'Khalid', 'Hosseini'),
  (6, 'Candice', 'Millard'),
  (7, 'Erich', 'Remarque'),
  (8, 'Anthony', 'Doerr');

INSERT INTO books (id, `title`, author_id)
VALUES
  (1, 'War and Peace', 1),
  (2, 'Life and Fate', 2),
  (3, 'Count of Monte Cristo', 3),
  (4, 'Anna Karenina', 4),
  (5, 'Crime and Punishment', 4),
  (6, 'A Thousand Splendid Suns', 5),
  (7, 'The Kite Runner', 5),
  (8, 'All The Light We Cannot See', 8),
  (9, 'All Quiet On The Western Front', 7),
  (10, 'Hero of the Empire', 6);

