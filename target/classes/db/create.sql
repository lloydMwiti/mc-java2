SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tasks (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  power VARCHAR,
  age VARCHAR,
  weakness VARCHAR,
  fight VARCHAR,
  completed BOOLEAN
);