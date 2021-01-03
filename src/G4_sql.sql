CREATE TABLE plaka(
    p_id integer PRIMARY KEY,
    plakano character(20)
);

CREATE TABLE kisiler(
    tckn bigint PRIMARY KEY,
    isim character varying(20) NOT NULL,
    soyisim character varying(20) NOT NULL,
    sicilno character varying(6),
    adres character varying(255) NOT NULL
);

ALTER TABLE kisiler ADD CONSTRAINT min_tckn_check CHECK (tckn > 10000000000);
ALTER TABLE kisiler ADD CONSTRAINT max_tckn_check CHECK (tckn < 99999999999);

CREATE TABLE araclar(
    plaka integer NOT NULL,
    aractürü varchar(20),
    ruhsatsahibitc bigint NOT NULL,
    CONSTRAINT fk_plaka FOREIGN KEY (plaka) REFERENCES plaka (p_id),
    CONSTRAINT fk_ruhsat FOREIGN KEY (ruhsatsahibitc) REFERENCES kisiler (tckn)
);

CREATE TABLE cezalar(
    cezano integer PRIMARY KEY,
    odemesuresi character varying(3),
    tabanucret integer,
    cezaismi character varying(40)
);

CREATE TABLE polisler(
    tckn bigint PRIMARY KEY,
    isim character varying(20) NOT NULL,
    soyisim character varying(20) NOT NULL,
    sube character varying(40) NOT NULL
);

CREATE TABLE borclar(
    toplamborc integer,
    tckn bigint,
    CONSTRAINT fk_tckn FOREIGN KEY (tckn) REFERENCES kisiler(tckn)
);

CREATE TABLE yenilencezalar(
    surucutckn bigint NOT NULL,
    cezano integer PRIMARY KEY,
    polistckn bigint NOT NULL,
    plaka integer NOT NULL,
    cezatipi integer,
    cezaucreti integer,
    kesilmetarihi date,
	sonodemetarihi date,
    CONSTRAINT fk_plaka FOREIGN KEY (plaka) REFERENCES plaka (p_id),
    CONSTRAINT fk_ruhsatno FOREIGN KEY (surucutckn) REFERENCES kisiler (tckn),
    CONSTRAINT yenilencezalar_cezatipi_fkey FOREIGN KEY (cezatipi) REFERENCES cezalar (cezano)
);

CREATE SEQUENCE ceza_id
	INCREMENT 1
	START 0
	MINVALUE 0;

CREATE SEQUENCE plaka_id
	INCREMENT 1
	START 0
	MINVALUE 0;
	
CREATE SEQUENCE arac_id
	INCREMENT 1
	START 0
	MINVALUE 0;

CREATE VIEW ceza AS
	SELECT cezano, cezaismi, tabanucret
	FROM cezalar
	ORDER BY cezano;

CREATE OR REPLACE FUNCTION kotu_polis()
RETURNS TABLE(isim varchar, soyisim varchar) AS $$
BEGIN
        		RETURN QUERY
        		SELECT p.isim, p.soyisim
        		FROM polisler p
        		WHERE p.tckn=(SELECT p.tckn
        				FROM polisler p
        				INTERSECT
        				SELECT yc.surucutckn 
        				FROM yenilencezalar yc);
    	END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION login(log_tckn bigint)
    RETURNS numeric AS $$
	BEGIN
		IF ( SELECT COUNT(1) FROM polisler p WHERE p.tckn = log_tckn ) THEN
			RETURN 1;
		ELSIF ( SELECT COUNT(1) FROM kisiler k WHERE k.tckn = log_tckn ) THEN
			RETURN 2;
		ELSE
			RETURN 0;
		END IF;
	END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION ceza_sor(ceza_tckn bigint)
	RETURNS TABLE(ceza_id integer,"Ýsim" character varying, soyisim character varying, ceza_tipi character varying, ceza_ucreti integer, kesilmetarihi date, son_odeme_tarihi date, plaka character) AS $$
	BEGIN
		RETURN QUERY
			SELECT yc.cezano, k.isim, k.soyisim, c.cezaismi, yc.cezaucreti, yc.kesilmetarihi, yc.sonodemetarihi,p.plakano
			FROM yenilencezalar yc, kisiler k, cezalar c, plaka p
			WHERE ceza_tckn = yc.surucutckn
			AND k.tckn = yc.surucutckn
			AND c.cezano = yc.cezatipi
			AND p.p_id = yc.plaka
			ORDER BY yc.cezano;
	END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION borc_sor(borc_tckn bigint)
    RETURNS bigint AS $$
	BEGIN
        RETURN (SELECT toplamborc FROM borclar b WHERE borc_tckn = b.tckn);
    END;
$$ LANGUAGE 'plpgsql';

CREATE FUNCTION ceza_add()
    RETURNS TRIGGER AS $$
BEGIN
    IF ((SELECT COUNT(1) FROM borclar b WHERE b.tckn = NEW.surucutckn) = 0) THEN
        INSERT INTO borclar VALUES(NEW.cezaucreti, NEW.surucutckn);
        RETURN NEW;
    ELSE
        UPDATE borclar b SET toplamborc = toplamborc + NEW.cezaucreti WHERE NEW.surucutckn = b.tckn;
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER ceza_ekle
AFTER INSERT ON yenilencezalar
FOR EACH ROW EXECUTE PROCEDURE ceza_add();

CREATE FUNCTION ceza_delete()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE borclar SET toplamborc = toplamborc - OLD.cezaucreti WHERE tckn = OLD.surucutckn;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER ceza_sil
AFTER DELETE ON yenilencezalar
FOR EACH ROW EXECUTE PROCEDURE ceza_delete();

CREATE OR REPLACE FUNCTION ceza_zammi(ceza numeric, yeniucret numeric)
   RETURNS VOID AS $$
	BEGIN
    	UPDATE cezalar c SET tabanucret = yeniucret WHERE ceza = c.cezano;
	END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION min_ceza_sorgu()
    RETURNS TABLE(isim varchar, soyisim varchar, cezasayisi bigint) AS $$
	BEGIN
        RETURN QUERY
        	SELECT k.isim, k.soyisim, COUNT(yc.surucutckn)
        	FROM kisiler k, yenilencezalar yc
        	WHERE yc.surucutckn = k.tckn
        	GROUP BY k.isim, k.soyisim
        	HAVING COUNT(*)>3;
	END;
$$ LANGUAGE 'plpgsql';

INSERT INTO polisler VALUES(85940275837,'Fatih','Avni','Fatih');
INSERT INTO polisler VALUES(49949451965,'Selim Ozan','Tufan','Zeytinburnu');
INSERT INTO polisler VALUES(87653603706,'Doðuhan','Köker','Zeytinburnu');
INSERT INTO polisler VALUES(78107914098,'Burhan','Karakoyunlu','Fatih');
INSERT INTO polisler VALUES(22146498892,'Baþar','Ayar','Kartal');
INSERT INTO polisler VALUES(15652800255,'Ali Hasan','Kansu','Maltepe');
INSERT INTO polisler VALUES(51407048637,'Serkan','Yýldýrým','Pendik');
INSERT INTO polisler VALUES(80998066043,'Halil Uður','Metin','Arnavutköy');
INSERT INTO polisler VALUES(79994313335,'Snitch','Akýn','Beylerbeyi');
INSERT INTO polisler VALUES(72673069725,'Selimcan','Kanat','Fatih');

INSERT INTO kisiler VALUES(79994313335, 'Snitch', 'Akýn', '159753', 'Paþa Mah. Kahramanbey Sok. No:34/7');
INSERT INTO kisiler VALUES(57796733282, 'Berkay', 'Koç', '548794', 'Kocamustafapaþa Mah. Merhaba Cad. Çamçak Sok. No:3/8');
INSERT INTO kisiler VALUES(51407048637, 'Serkan', 'Yýldýrým', '687315', 'Halkalý Merkez Mah. Avrupa Konutlarý No:11/24');
INSERT INTO kisiler VALUES(77479161769, 'Hakan', 'Ayyýlmaz', '736894', 'Yýlmazbey Mah. Þemsi Sok. No:22/5');
INSERT INTO kisiler VALUES(85605898200, 'Güler', 'Yýlmaz', '753951', 'Kemancý Mah. Eminbey Konutlarý Alibeyköy 3 No:12/4');
INSERT INTO kisiler VALUES(24219831813, 'Ergün', 'Þanlý', '561684', 'Yýldýz Mah. Serencebey Yokuþu No:1/6');
INSERT INTO kisiler VALUES(15878792279, 'Kerem', 'Ortaç', '542162', 'Büyükdere Mah. Canfes Sok. No:27/2');
INSERT INTO kisiler VALUES(17630807965, 'Feyyaz', 'Yiðit', '821672', 'Bahçelievler Mah. Naci Kasým Cad. Erde Sok. No:4/3');
INSERT INTO kisiler VALUES(30879763130, 'Þamil', 'Güvener', '159753', 'Kule Mah. Yaþar Sok. No:11/8');
INSERT INTO kisiler VALUES(80998066043, 'Halil Uður' ,'Metin', '456497', 'Fatih Mah. Emin Cad. Gülsor Sitesi A1 Blok No:24/4');
INSERT INTO kisiler VALUES(22146498892, 'Baþar' ,'Ayar', '297654', 'Yukari Atalar Mah., Doðu Cd. No:19, 34862 Kartal/Ýstanbul');
INSERT INTO kisiler VALUES(78107914098, 'Burhan' ,'Karakoyunlu', '549821', 'Merkez Mah.Eski Edirne Cad.No:1384 / A, 34275 Arnavutköy/Ýstanbul');
INSERT INTO kisiler VALUES(15652800255, 'Ali Hasan' ,'Kansu', '235613', 'Baðlarbaþý, Ýnönü Cd. No:121, 34844 Maltepe/Ýstanbul');
INSERT INTO kisiler VALUES(49949451965, 'Selim Ozan' ,'Tufan', '271313', 'Yenidoðan Mh, Þht. Komiser Günaydýn Cd. 48. Sk D:114, 34020 Zeytinburnu/Ýstanbul');
INSERT INTO kisiler VALUES(72673069725, 'Selimcan', 'Kanat', '785193', 'Merkez Mah.Eski Edirne Cad.No:1384 / A, 34275 Arnavutköy/Ýstanbul');
INSERT INTO kisiler VALUES(87653603706, 'Doðuhan', 'Köker', '537571', 'Telsiz, 85. Sk. No:48, 34020 Zeytinburnu/Ýstanbul');
INSERT INTO kisiler VALUES(85940275837, 'Fatih', 'Avni', '158493', 'Zeyrek, 33, Yesarizade Cd., 34083 Fatih/Ýstanbul');

INSERT INTO cezalar VALUES('1001', '50', '288', 'Kýrmýzý Iþýkta Geçme Cezasý');
INSERT INTO cezalar VALUES('1021', '40', '680', 'Dikkatsiz Sürüþ Cezasý');
INSERT INTO cezalar VALUES('1031', '25', '1002', 'Makas Atma Cezasý');
INSERT INTO cezalar VALUES('1401', '15', '1228', 'Emniyet Þeridi Ýhlali Cezasý');
INSERT INTO cezalar VALUES('1550', '15', '288', 'Hýz Sýnýrý Ýhlali Cezasý');
INSERT INTO cezalar VALUES('1666', '7', '2473', 'Ehliyetsiz Araç Kullanma Cezasý');
INSERT INTO cezalar VALUES('1751', '15', '288', 'Muayenesiz Araç Kullanma Cezasý');
INSERT INTO cezalar VALUES('1397', '15', '132', 'Sigortasýz Araç Kullanma Cezasý');
INSERT INTO cezalar VALUES('1286', '15', '288', 'Araç Ýçinde Telefonla Konuþma Cezasý');
INSERT INTO cezalar VALUES('1506', '15', '6141', 'Drift Atma Cezasý');
INSERT INTO cezalar VALUES('1552', '15', '132', 'Takip Mesafesi Cezasý');
INSERT INTO cezalar VALUES('1242', '21', '1228', 'Alkollü Araç Kullanma Cezasý');

INSERT INTO plaka VALUES(nextval('plaka_id'),'34-AZ-252');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-HUM-6253');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-CIH-914');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-ADC-246');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-GR-0443');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-ER-2376');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-BL-9750');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-YKZ-333');
INSERT INTO plaka VALUES(nextval('plaka_id'),'61-OF-61');
INSERT INTO plaka VALUES(nextval('plaka_id'),'34-TC-4444');
INSERT INTO plaka VALUES(nextval('plaka_id'),'22-HIT-123');
INSERT INTO plaka VALUES(nextval('plaka_id'),'11-FY-362');
INSERT INTO plaka VALUES(nextval('plaka_id'),'54-ID-987');
INSERT INTO plaka VALUES(nextval('plaka_id'),'35-ZCT-671');
INSERT INTO plaka VALUES(nextval('plaka_id'),'16-FÇ-362');
INSERT INTO plaka VALUES(nextval('plaka_id'),'05-MFA-1162');

INSERT INTO araclar VALUES(nextval('arac_id'),'Traktör',80998066043);
INSERT INTO araclar VALUES(nextval('arac_id'),'Motorsiklet',79994313335);
INSERT INTO araclar VALUES(nextval('arac_id'),'Otomobil',51407048637);
INSERT INTO araclar VALUES(nextval('arac_id'),'Týr',51407048637);
INSERT INTO araclar VALUES(nextval('arac_id'),'Ýþ Makinesi',57796733282);
INSERT INTO araclar VALUES(nextval('arac_id'),'Ticari',17630807965);
INSERT INTO araclar VALUES(nextval('arac_id'),'Otomobil',24219831813);
INSERT INTO araclar VALUES(nextval('arac_id'),'Otomobil',30879763130);
INSERT INTO araclar VALUES(nextval('arac_id'),'Otomobil',85605898200);
INSERT INTO araclar VALUES(nextval('arac_id'),'Otomobil',77479161769);
INSERT INTO araclar VALUES(nextval('arac_id'),'Týr',15878792279);

INSERT INTO yenilencezalar VALUES(15878792279, nextval('ceza_id'), 51407048637, 11, 1031, 1002, '2021-01-03', '2021-01-28');
INSERT INTO yenilencezalar VALUES(17630807965, nextval('ceza_id'), 79994313335, 6, 1242, 1228, '2021-01-03', '2021-01-24');
INSERT INTO yenilencezalar VALUES(85605898200, nextval('ceza_id'), 80998066043, 9, 1021, 680, '2021-01-03', '2021-02-12');
INSERT INTO yenilencezalar VALUES(24219831813, nextval('ceza_id'), 87653603706, 7, 1552, 132, '2021-01-03', '2021-01-18');
INSERT INTO yenilencezalar VALUES(79994313335, nextval('ceza_id'), 22146498892, 2, 1751, 288, '2021-01-03', '2021-01-18');
INSERT INTO yenilencezalar VALUES(57796733282, nextval('ceza_id'), 85940275837, 5, 1666, 2473, '2021-01-03', '2021-01-10');
INSERT INTO yenilencezalar VALUES(30879763130, nextval('ceza_id'), 51407048637, 8, 1242, 1228, '2021-01-03', '2021-01-24');
INSERT INTO yenilencezalar VALUES(80998066043, nextval('ceza_id'), 78107914098, 1, 1751, 288, '2021-01-03', '2021-01-18');
INSERT INTO yenilencezalar VALUES(51407048637, nextval('ceza_id'), 80998066043, 3, 1401, 1228, '2021-01-03', '2021-01-18');
INSERT INTO yenilencezalar VALUES(85605898200, nextval('ceza_id'), 51407048637, 9, 1666, 2473, '2021-01-03', '2021-01-10');