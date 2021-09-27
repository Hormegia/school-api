INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 1, 'Transición') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Transición'
    ) LIMIT 1;

INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 2, 'Preescolar') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Preescolar'
    ) LIMIT 1;

INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 3, 'Primero') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Primero'
    ) LIMIT 1;

INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 4, 'Segundo') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Segundo'
    ) LIMIT 1;



INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 5, 'Tercero') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Tercero'
    ) LIMIT 1;


INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 6, 'Cuarto') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Cuarto'
    ) LIMIT 1;


INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 7, 'Quinto') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Quinto'
    ) LIMIT 1;


INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 8, 'Sexto') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Sexto'
    ) LIMIT 1;


INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 9, 'Septimo') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Septimo'
    ) LIMIT 1;


INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 10, 'Octavo') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Octavo'
    ) LIMIT 1;


INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 11, 'Noveno') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Noveno'
    ) LIMIT 1;

INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 12, 'Decimo') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Decimo'
    ) LIMIT 1;

INSERT INTO grado (id, nombre)
SELECT * FROM ( SELECT 13, 'Once') AS tmp
WHERE NOT EXISTS(
        SELECT id FROM grado WHERE nombre = 'Once'
    ) LIMIT 1;

