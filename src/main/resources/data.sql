CREATE SCHEMA IF NOT EXISTS boardgames_db;

INSERT INTO boardgame (name)
VALUES ('Settlers of Catan'),
       ('Ticket to Ride'),
       ('Pandemic'),
       ('7 Wonders'),
       ('Terraforming Mars'),
       ('Small World'),
       ('Small World of Warcraft'),
       ('Red Dragon Inn'),
       ('Puerto Rico');

INSERT INTO player (name)
VALUES ('Emil'),
       ('Funk'),
       ('Jacob'),
       ('Lasse'),
       ('Nick'),
       ('Niklas'),
       ('Nikolaj'),
       ('Rasmus');

INSERT INTO result (result_date, victories, points, player_id, boardgame_id)
VALUES
    ('2024-01-01', 3, 85, 1, 1),
    ('2024-01-02', 1, 88, 2, 2),
    ('2024-01-03', 1, 88, 3, 3),
    ('2024-01-04', 1, 91, 4, 4),
    ('2024-01-05', 1, 78, 5, 5),
    ('2024-01-06', 1, 83, 6, 6),
    ('2024-01-07', 1, 93, 7, 7),
    ('2024-01-08', 1, 90, 8, 8);

