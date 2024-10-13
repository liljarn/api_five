CREATE TABLE themes
(
    id    BIGSERIAL PRIMARY KEY,
    theme TEXT UNIQUE NOT NULL
);

CREATE TABLE quotes
(
    id        BIGSERIAL PRIMARY KEY,
    quote_key TEXT UNIQUE NOT NULL,
    quote     TEXT        NOT NULL,
    theme_id BIGINT      NOT NULL REFERENCES themes (id)
);

INSERT INTO themes (theme) VALUES ('Юмор'),('Философия');

INSERT INTO quotes (quote_key, quote, theme_id)
VALUES
    ('Бананы', '— Шеф, ну что за бред? Нафига вообще отклеивать эти этикетки от бананов? — Для баланса Вселенной. Ведь где-то там, в Африке, такой же огузок, как ты, приклеивает эти этикетки.', 1),
    ('Ошибки', 'Мы не можем не совершать ошибки. Они — часть нашей жизни. А самое главное — они помогают нам найти правильный путь.', 2),
    ('Борьба', 'Никогда не сдавайся. Пока ты продолжаешь бороться, судьба всегда может наградить тебя.', 2),
    ('Решения', 'Порой нам нужно принять решение, от которого будет зависеть наша судьба... И не только наша. Но иногда мы понимаем: чтобы сделать человека счастливым, правильнее будет его отпустить, как бы тяжело это ни было... Да, так будет лучше... Наверное', 2),
    ('Одиночество', 'Солнце светит всем одинаково: будь то нерадивый отец или взбалмошная дочь... Оно равно греет как влюбленным парам, так и одинокому парню, который уже забыл, что такое солнце', 2);