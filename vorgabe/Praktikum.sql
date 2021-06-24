DROP TABLE zutat CASCADE CONSTRAINTS;
DROP TABLE cocktail CASCADE CONSTRAINTS;
DROP TABLE glas CASCADE CONSTRAINTS ;
DROP TABLE lokal CASCADE CONSTRAINTS;
DROP TABLE zutat_cocktail CASCADE CONSTRAINTS;
DROP TABLE cocktail_lokal CASCADE CONSTRAINTS;

CREATE TABLE zutat (
 zid number PRIMARY KEY,
 zname varchar2(20) ,
 alkoholgehalt number );

CREATE TABLE lokal (
 lid number PRIMARY KEY,
 lname varchar2(30) ,
 plz number(6,0),
 stadt varchar2(20));

CREATE TABLE glas (
 gid number PRIMARY KEY,
 gname varchar2(15) );

CREATE TABLE cocktail (
 cid number PRIMARY KEY,
 cname varchar2(20),
 alkoholisch char(1),
 gid number,
 FOREIGN KEY (gid) REFERENCES glas (gid));

CREATE TABLE zutat_cocktail (
 zid number,
 cid number,
 menge number,
 PRIMARY KEY (zid, cid),
 FOREIGN KEY (zid) REFERENCES zutat (zid),
 FOREIGN KEY (cid) REFERENCES cocktail (cid));

CREATE TABLE cocktail_lokal (
 cid number,
 lid number,
 PRIMARY KEY (lid, cid),
 FOREIGN KEY (lid) REFERENCES lokal (lid),
 FOREIGN KEY (cid) REFERENCES cocktail (cid));

INSERT INTO zutat VALUES (1,'Tequila',34);
INSERT INTO zutat VALUES (2,'Curacao Triple Sec',36);
INSERT INTO zutat VALUES (3,'Limettensaft',0);
INSERT INTO zutat VALUES (4,'weisser Rum',52);
INSERT INTO zutat VALUES (5,'brauner Rum',67);
INSERT INTO zutat VALUES (6,'Apricot Brandy',45);
INSERT INTO zutat VALUES (7,'Ananassaft',0);
INSERT INTO zutat VALUES (8,'Zitronensaft',0);
INSERT INTO zutat VALUES (9,'Weinbrand',45);
INSERT INTO zutat VALUES (10,'Creme de Menthe',22);
INSERT INTO zutat VALUES (11,'Cointreau',12);
INSERT INTO zutat VALUES (12,'Canadian Whisky',45);
INSERT INTO zutat VALUES (13,'Vermouth rosso',23);
INSERT INTO zutat VALUES (14,'Campari',28);

INSERT INTO glas VALUES (1,'Floete');
INSERT INTO glas VALUES (2,'Schwenker');
INSERT INTO glas VALUES (3,'Kelch');
INSERT INTO glas VALUES (4,'Schale');
INSERT INTO glas VALUES (5,'Stiefel');
INSERT INTO glas VALUES (6,'Weinglas');
INSERT INTO glas VALUES (7,'Sektglas');
INSERT INTO glas VALUES (8,'Bierglas');
INSERT INTO glas VALUES (9,'Bierkrug');
INSERT INTO glas VALUES (10,'Flasche');
INSERT INTO glas VALUES (11,'Cocktailglas');
INSERT INTO glas VALUES (12,'Tumbler');
INSERT INTO glas VALUES (13,'Longdrinkglas');

INSERT INTO cocktail VALUES (1,'BlauerBaum','y',1);
INSERT INTO cocktail VALUES (2,'Russentod','y',5);
INSERT INTO cocktail VALUES (3,'Bond007','y',4);
INSERT INTO cocktail VALUES (4,'Donnergurgler','y',5);
INSERT INTO cocktail VALUES (5,'Knieweich','y',3);
INSERT INTO cocktail VALUES (6,'Cola_gruen','n',1);
INSERT INTO cocktail VALUES (7,'BlaueOma','y',4);
INSERT INTO cocktail VALUES (8,'GelbeHose','y',8);
INSERT INTO cocktail VALUES (9,'Pot','y',5);
INSERT INTO cocktail VALUES (10,'Down','y',2);
INSERT INTO cocktail VALUES (11,'Alexander','y',11);
INSERT INTO cocktail VALUES (12,'Americano','y',11);
INSERT INTO cocktail VALUES (13,'Bronx','y',11);
INSERT INTO cocktail VALUES (14,'Daiquiri','y',12);
INSERT INTO cocktail VALUES (15,'Manhattan','y',11);
INSERT INTO cocktail VALUES (16,'Stinger','y',11);
INSERT INTO cocktail VALUES (17,'Zombie','y',13);

INSERT INTO lokal VALUES (1,'Klamauk', 39108, 'Magdeburg');
INSERT INTO lokal VALUES (2,'bagel', 39108, 'Magdeburg');
INSERT INTO lokal VALUES (3,'Alcatraz', 39104, 'Magdeburg');
INSERT INTO lokal VALUES (4,'CyberSpaceCafe Orbit', 39104, 'Magdeburg');
INSERT INTO lokal VALUES (5,'El Greco', 39104, 'Magdeburg');
INSERT INTO lokal VALUES (6,'Exlibris', 39104, 'Magdeburg');
INSERT INTO lokal VALUES (7,'Falstaff', 39104, 'Magdeburg');
INSERT INTO lokal VALUES (8,'Klewitz', 39112, 'Magdeburg');
INSERT INTO lokal VALUES (9,'Le Petit', 39114, 'Magdeburg');
INSERT INTO lokal VALUES (10,'Rubens', 39106, 'Magdeburg');
INSERT INTO lokal VALUES (11,'Durango Saloon', 39128,'Magdeburg');
INSERT INTO lokal VALUES (12,'P 70', 39108, 'Magdeburg');
INSERT INTO lokal VALUES (13,'Zum Alten Dessauer', 39104, 'Magdeburg');
INSERT INTO zutat_cocktail VALUES (9,5,50);
INSERT INTO zutat_cocktail VALUES (14,5,50);
INSERT INTO zutat_cocktail VALUES (7,5,50);
INSERT INTO zutat_cocktail VALUES (7,8,50);
INSERT INTO zutat_cocktail VALUES (9,8,150);
INSERT INTO zutat_cocktail VALUES (9,9,34);
INSERT INTO zutat_cocktail VALUES (9,7,45);
INSERT INTO zutat_cocktail VALUES (1,9,60);
INSERT INTO zutat_cocktail VALUES (11,9,67);
INSERT INTO zutat_cocktail VALUES (8,3,56);
INSERT INTO zutat_cocktail VALUES (14,15,23);
INSERT INTO zutat_cocktail VALUES (9,15,34);
INSERT INTO zutat_cocktail VALUES (11,15,23);
INSERT INTO zutat_cocktail VALUES (12,15,12);
INSERT INTO zutat_cocktail VALUES (6,15,23);

INSERT INTO cocktail_lokal VALUES (17, 1);
INSERT INTO cocktail_lokal VALUES (16, 2);
INSERT INTO cocktail_lokal VALUES (15, 3);
INSERT INTO cocktail_lokal VALUES (4, 4);
INSERT INTO cocktail_lokal VALUES (4, 5);
INSERT INTO cocktail_lokal VALUES (14, 6);
INSERT INTO cocktail_lokal VALUES (8, 7);
INSERT INTO cocktail_lokal VALUES (16, 8);
INSERT INTO cocktail_lokal VALUES (8, 9);
INSERT INTO cocktail_lokal VALUES (8, 10);
INSERT INTO cocktail_lokal VALUES (5, 11);
INSERT INTO cocktail_lokal VALUES (15, 12);
INSERT INTO cocktail_lokal VALUES (15, 13);
INSERT INTO cocktail_lokal VALUES (14, 1);
INSERT INTO cocktail_lokal VALUES (5, 2);
INSERT INTO cocktail_lokal VALUES (4, 3);
INSERT INTO cocktail_lokal VALUES (5, 4);
INSERT INTO cocktail_lokal VALUES (17, 5);
INSERT INTO cocktail_lokal VALUES (5, 6);
INSERT INTO cocktail_lokal VALUES (16, 7);
INSERT INTO cocktail_lokal VALUES (5, 8);
INSERT INTO cocktail_lokal VALUES (5, 9);
INSERT INTO cocktail_lokal VALUES (13, 10);
INSERT INTO cocktail_lokal VALUES (13, 11);
INSERT INTO cocktail_lokal VALUES (17, 12);
INSERT INTO cocktail_lokal VALUES (4, 13);