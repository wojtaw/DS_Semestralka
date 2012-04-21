
/* Inicializace - Momentálně nepotřebujeme */
/* 
DROP DATABASE metatv;
CREATE DATABASE metatv;
\c metatv;
*/

/*
příkaz na připojení:
psql -h krizik.felk.cvut.cz -p 5434 -d student_db12_10 -U student_db12_10
*/

CREATE TABLE channels (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description_cs TEXT NOT NULL DEFAULT 'Bez popisu',
	description_en TEXT NOT NULL DEFAULT 'Untitled',
	rating INTEGER NOT NULL,
	private_code VARCHAR(255) NOT NULL,
	private BOOLEAN NOT NULL,
	canonical_name VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(32) NOT NULL,
	role VARCHAR(255) NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	email VARCHAR(255) NOT NULL UNIQUE,
	phone VARCHAR(20),
	purpose TEXT NOT NULL);

CREATE TABLE folders (
	id SERIAL PRIMARY KEY,
	name VARCHAR(45) NOT NULL DEFAULT 'Untitled folder',
	channel_id INTEGER NOT NULL REFERENCES channels (id)
		ON DELETE CASCADE ON UPDATE CASCADE);
	
CREATE TABLE presentations (
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL DEFAULT 'Untitled',
	description VARCHAR(255) NOT NULL DEFAULT 'Description missing',
	lang VARCHAR(255) NOT NULL,
	date_recorded TIMESTAMP NOT NULL,
	service VARCHAR(255) NOT NULL,
	service_id VARCHAR(255),
	length SMALLINT NOT NULL DEFAULT 0,
	slides BOOLEAN NOT NULL,
	video BOOLEAN NOT NULL,
	folder_id INTEGER NOT NULL REFERENCES folders (id)
		ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE speakers (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	user_id INTEGER REFERENCES channels (id)
		ON DELETE NO ACTION ON UPDATE CASCADE);

CREATE TABLE subscriptions (
	id SERIAL PRIMARY KEY,
	channel_id INTEGER NOT NULL REFERENCES channels (id)
		ON DELETE CASCADE ON UPDATE CASCADE,
	email VARCHAR(255) NOT NULL);

CREATE TABLE statistics (
	presentation_id INTEGER NOT NULL REFERENCES presentations (id)
		ON DELETE CASCADE ON UPDATE CASCADE,
	user_id INTEGER NOT NULL REFERENCES channels (id)
		ON DELETE CASCADE ON UPDATE CASCADE,
	loads_count INTEGER NOT NULL DEFAULT 0,
	finishes_count INTEGER NOT NULL DEFAULT 0,
	plays_count INTEGER NOT NULL DEFAULT 0);

CREATE TABLE tags (
	id SERIAL PRIMARY KEY,
	name VARCHAR(45));

CREATE TABLE tags_presentations (
	presentation_id INTEGER REFERENCES presentations (id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	tag_id INTEGER REFERENCES presentations (id)
		ON UPDATE CASCADE ON DELETE CASCADE);
