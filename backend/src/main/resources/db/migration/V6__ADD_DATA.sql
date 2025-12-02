INSERT INTO category (name)
VALUES
    ('koty'),
    ('gry komputerowe'),
    ('muzyka'),
    ('sport'),
    ('zakola');

INSERT INTO post (username, title, content, category_id, created_at, updated_at)
VALUES
    ('Franek',
     'Kocham koty',
     'Jezu jak ja kocham koty',
     (SELECT id FROM category WHERE name = 'koty'),
     TIMESTAMP 'now',
     TIMESTAMP 'now'
    ),
    ('Kamil',
     'Lana del rey',
     'Boże jak ja kocham Lane del rey (185cm, kocham feministyczną literature)',
     (SELECT id FROM category WHERE name = 'muzyka'),
     TIMESTAMP 'now',
     TIMESTAMP 'now'
    ),
    ('Marcin',
     'Kocham podnosić ciężkie rzeczy',
     'Matko bosko jak ja kocham podnosić ciężkie rzeczy 10 razy po 3 razy',
     (SELECT id FROM category WHERE name = 'sport'),
     TIMESTAMP 'now',
     TIMESTAMP 'now'
    ),
    ('Sławek',
     'Odpalajcie Rusta',
     'Odpalajcie, baze napadli, szybko szybko',
     (SELECT id FROM category WHERE name = 'gry komputerowe'),
     TIMESTAMP 'now',
     TIMESTAMP 'now'
    ),
    ('Marcel',
     'It''s over',
     'Chłopaki robimy zrzute na operacje w Turcji',
     (SELECT id FROM category WHERE name = 'koty'),
     TIMESTAMP 'now',
     TIMESTAMP 'now'
    );

INSERT INTO comment (username, content, post_id, created_at)
VALUES
    ('Ania',
     'Ja mam super kotki w piwnicy, chcesz zobaczyć?',
     (SELECT id FROM post WHERE username = 'Franek'),
     TIMESTAMP 'now'
    ),
    ('Marcel',
     'Rel, kocham koty',
     (SELECT id FROM post WHERE username = 'Franek'),
     TIMESTAMP 'now'
    ),
    ('Marcin',
     'Uważaj na Anię, to 45-letni chłop o imieniu Krzysztof',
     (SELECT id FROM post WHERE username = 'Franek'),
     TIMESTAMP 'now'
    ),
    ('SeksiKicia69',
     'Zobacz moje super fotki na dysk.google.com/seksiKicia69',
     (SELECT id FROM post WHERE username = 'Kamil'),
     TIMESTAMP 'now'
    ),
    ('Kamilek',
     'Wyciskaj to żelastwo dla mnie siłaczu, spoć sie dla mnie',
     (SELECT id FROM post WHERE username = 'Marcin'),
     TIMESTAMP 'now'
    ),
    ('Mróz',
     'Nie',
     (SELECT id FROM post WHERE username = 'Sławek'),
     TIMESTAMP 'now'
    ),
    ('Kamil',
     'Poka',
     (SELECT id FROM post WHERE username = 'Marcel'),
     TIMESTAMP 'now'
    ),
    ('Kamilek',
     'Poka',
     (SELECT id FROM post WHERE username = 'Marcel'),
     TIMESTAMP 'now'
    ),
    ('Marcin',
     'Poka',
     (SELECT id FROM post WHERE username = 'Marcel'),
     TIMESTAMP 'now'
    ),
    ('Mróz',
     'Poka',
     (SELECT id FROM post WHERE username = 'Marcel'),
     TIMESTAMP 'now'
    ),
    ('Sławek',
     'Poka',
     (SELECT id FROM post WHERE username = 'Marcel'),
     TIMESTAMP 'now'
    );