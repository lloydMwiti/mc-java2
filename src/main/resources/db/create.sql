CREATE DATABASE squad;
\c squad;

CREATE TABLE IF NOT EXISTS tasks (
  id serial PRIMARY KEY ,
  name VARCHAR,
  power VARCHAR,
  age VARCHAR,
  weakness VARCHAR,
  fight VARCHAR,
  completed BOOLEAN
);
create table if not exists squad(
    id serial PRIMARY KEY,
    name varchar,
    cause varchar
)