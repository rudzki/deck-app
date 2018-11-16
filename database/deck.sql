DROP TABLE IF EXISTS cards CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS scores CASCADE;

DROP SEQUENCE IF EXISTS seq_card_id;

CREATE SEQUENCE seq_card_id;

CREATE TABLE categories(
	id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE cards
(
	id INT PRIMARY KEY DEFAULT NEXTVAL('seq_card_id'),
	card_question VARCHAR(500) NOT NULL,
	card_answer TEXT NOT NULL,
	category_id INT REFERENCES categories(id),
	card_date TIMESTAMP DEFAULT NOW()
);

CREATE TABLE scores
(
	id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	card_id INT REFERENCES cards(id),
	score INT
);

INSERT INTO CATEGORIES(name) VALUES ('category1');
INSERT INTO CATEGORIES(name) VALUES ('category2');
INSERT INTO CATEGORIES(name) VALUES ('category3');

INSERT INTO CARDS(card_question, card_answer, category_id) VALUES ('this is a question', 'this is an answer', 1);
INSERT INTO CARDS(card_question, card_answer, category_id) VALUES ('this is another question', 'this is another answer', 1);
INSERT INTO CARDS(card_question, card_answer, category_id) VALUES ('this is yet another question', 'this is yet another answer', 2);
