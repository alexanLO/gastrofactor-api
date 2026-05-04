CREATE TABLE food_yield (
    id BIGSERIAL PRIMARY KEY,
    food_name VARCHAR(100) NOT NULL,
    correction_factor NUMERIC(8,4) NOT NULL,
    coccion_factor NUMERIC(8,4) NOT NULL
);


INSERT INTO food_yield (food_name, correction_factor, coccion_factor)
VALUES
  ('Filé Mignon', 1.18, 1.25),
  ('Peito de Frango', 1.05, 1.20),
  ('Batata Inglesa', 1.10, 1.30),
  ('Cenoura', 1.08, 1.15),
  ('Arroz Cru', 1.00, 2.50);
