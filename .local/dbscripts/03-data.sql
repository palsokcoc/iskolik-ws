insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(1, 'Ankara', 'Akyurt', 'Uzunlar', 'Atatürk', 52, 4);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(2, 'Antalya', 'Manavgat', 'Barbaros', 'Cumhuriyet', 104, 13);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(3, 'Ankara', 'Altındağ', 'Çağlayan', 'Fatih', 74, 1);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(4, 'Balıkesir', 'Dursunbey', 'Hasanlar', 'Gül', 8, 26);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(5, 'Diyarbakır', 'Bismil', 'Yasince', 'Okul', 85, 14);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(6, 'Erzurum', 'Aziziye', 'Eğerti', 'Karanfil', 47, 10);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(7, 'İzmir', 'Kemalpaşa', 'Dereköy', 'Lale', 52, 3);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(8, 'Kırşehir', 'Mucur', 'Hamidiye', 'Menekşe', 21, 25);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(9, 'Manisa', 'Soma', 'Hürriyet', 'İnönü', 14, 27);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(10, 'Ankara', 'Çankaya', 'Oğuzlar', 'Anafartalar', 3, 30);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(11, 'Ankara', 'Keçiören', 'Çiçekli', 'İstiklal', 44, 33);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(12, 'İstanbul', 'Bağcılar', 'Demirkapı', 'Sokullu', 79, 5);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(13, 'İstanbul', 'Çatalca', 'Başak', 'Bahariye', 96, 2);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(14, 'Malatya', 'Battalgazi', 'Pelitli', 'Girne', 124, 11);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(15, 'Sakarya', 'Pamukova', 'Çakmaklı', 'Kıbrıs', 35, 18);
insert into iskolik.adres (adres_id,sehir,ilce,mahalle,cadde,apartman_no,daire_no) values(16, 'Samsun', 'İlkadım', 'Kılıçdede', 'Bahçeli', 68, 23);
select setval('iskolik.adres_adres_id_seq', 17, true);

insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (1,1,'5057668945','3122985347');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (2,2,'5326284740','2422413352');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (3,3,'5062579992','3124663822');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (4,4,'5355431543','2664028886');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (5,5,'5053207213','4128036500  ');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (6,6,'5325583416','4425297697');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (7,7,'5366515899','2324676127');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (8,8,'5322407615','3865224912');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (9,9,'5062402359','2362018691');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (10,10,'5052260088','3122717762');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (11,11,'5332906612','3124058305');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (12,12,'5324330114','2125391364');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (13,13,'5055996234','2168888269');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (14,14,'5064913581','4226273449');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (15,15,'5332251619','2653580150');
insert into iskolik.iletisim (iletisim_id,adres_id,cep_telefonu,sabit_telefon ) values (16,16,'5355953655','3622889295');
select setval('iskolik.iletisim_iletisim_id_seq', 17, true);

insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (1,'Bireysel','badi_ekrem','Badi','badi.ekrem@gmail.com','Aktif','2020-06-08','6d57e2bd7a1d87f753229a4b70e242058a31a568',1);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (2,'Bireysel','ethem_gocen','Ethem','ethem.gocen@yahoo.com','Aktif','2019-05-11','6d57e2bd7a1d87f753229a4b70e242058a31a568',2);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (3,'Bireysel','seda_konar','Seda','seda.konar@proton.com','Aktif','2018-12-14','6d57e2bd7a1d87f753229a4b70e242058a31a568',3);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (4,'Bireysel','saliha_akarsu','Saliha','saliha_akarsu@gmail.com','Aktif','2021-08-23','6d57e2bd7a1d87f753229a4b70e242058a31a568',4);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (5,'Bireysel','rustu_gumusbas','Rüştü','rustu.gumusbas@yahoo.com','Aktif','2010-01-16','6d57e2bd7a1d87f753229a4b70e242058a31a568',5);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (6,'Bireysel','levent_cicek','Levent','levent.cicek@hotmail.com','Aktif','2009-06-25','6d57e2bd7a1d87f753229a4b70e242058a31a568',6);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (7,'Bireysel','sevgi_saracli','Sevgi','sevgi.saracli@proton.com','Aktif','2005-07-19','6d57e2bd7a1d87f753229a4b70e242058a31a568',7);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (8,'Bireysel','ayse_koacayurek','Ayşe','ayse.kocayurek@gmail.com','Aktif','2016-10-30','6d57e2bd7a1d87f753229a4b70e242058a31a568',8);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (9,'Bireysel','orkun_tugrul','Orkun','orkun.tugrul@yahoo.com','Aktif','2018-03-24','6d57e2bd7a1d87f753229a4b70e242058a31a568',9);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (10,'Bireysel','yasin_cukurcu','Yasin','yasin.cukurcu@hotmail.com','Aktif','2014-02-28','6d57e2bd7a1d87f753229a4b70e242058a31a568',10);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (11,'Kurumsal','aytek_nakliyat','Aytek Nakliyat','aytek.nakliyat@gmail.com','Aktif','2006-01-31','6d57e2bd7a1d87f753229a4b70e242058a31a568',11);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (12,'Kurumsal','aselsan','Aselsan','aselsan@aselsan.gov.tr','Aktif','2009-12-06','6d57e2bd7a1d87f753229a4b70e242058a31a568',12);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (13,'Kurumsal','tai','Tai','tai@tai.gov.tr','Aktif','2000-01-01','6d57e2bd7a1d87f753229a4b70e242058a31a568',13);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (14,'Kurumsal','google','Google','google@gmail.com','Aktif','2005-09-01','6d57e2bd7a1d87f753229a4b70e242058a31a568',14);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (15,'Kurumsal','microsoft','Microsoft','microsoft@bing.com.tr','Aktif','2003-05-21','6d57e2bd7a1d87f753229a4b70e242058a31a568',15);
insert into iskolik.kullanici (kullanici_id,tip,kullanici_adi,ad,email,durum,kayit_tarihi,sifre_hash,iletisim_id) values (16,'Kurumsal','gokmen_saglik_hiz','Gökmen Sağlık Hizmetleri','gokmen.saglik@gmail.com','Aktif','2021-06-11','6d57e2bd7a1d87f753229a4b70e242058a31a568',16);
select setval('iskolik.kullanici_kullanici_id_seq', 17, true);

insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(1,'Ekrem','E','1950-01-23');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(2,'Göçen','E','1978-05-12');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(3,'Konar','K','1988-10-30');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(4,'Akarsu','K','2000-03-15');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(5,'Gümüşbaş','E','1995-11-23');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(6,'Çiçek','E','1983-02-02');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(7,'Saraçlı','K','1990-06-09');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(8,'Kocayürek','K','1992-12-11');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(9,'Tuğrul','E','1987-01-26');
insert into iskolik.bireysel_kullanici (kullanici_id,soyad,cinsiyet,dogum_tarihi) values(10,'Çukurcu','E','1985-09-20');

insert into iskolik.kurumsal_kullanici (kullanici_id,calisan_sayisi) values (11,25);
insert into iskolik.kurumsal_kullanici (kullanici_id,calisan_sayisi) values (12,5000);
insert into iskolik.kurumsal_kullanici (kullanici_id,calisan_sayisi) values (13,2000);
insert into iskolik.kurumsal_kullanici (kullanici_id,calisan_sayisi) values (14,1500);
insert into iskolik.kurumsal_kullanici (kullanici_id,calisan_sayisi) values (15,1600);
insert into iskolik.kurumsal_kullanici (kullanici_id,calisan_sayisi) values (16,50);

insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(1,'Bilişim Teknolojileri', 'Bilişim Teknolojileri', null);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(2,'Yazılım Geliştirme', 'Yazılım Geliştirme', 1);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(3,'Programlama Dili', 'Programlama Dili', 2);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(4,'C', 'C', 3);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(5,'C++', 'C++', 3);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(6,'Java', 'Java', 3);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(7,'Spring', 'Spring', 6);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(8,'Hibernate', 'Hibernate', 6);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(9,'JUnit', 'JUnit', 6);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(10,'JavaScript', 'JavaScript', 3);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(11,'Node.js', 'Node.js', 10);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(12,'Vue.js', 'Vue.js', 10);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(13,'React.js', 'React.js', 10);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(14,'Python', 'Python', 3);
insert into iskolik.kategori (kategori_id,ad,aciklama,ata_kategori_id) values(15,'Diğer', 'Diğer', 3);
select setval('iskolik.kategori_kategori_id_seq', 16, true);

insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (1,'Bilgisayar Mühendisi','Bilişim Uzmanı','2000 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (2,'Bilgisayar Mühendisi','Yazılım Geliştirici','2001 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (3,'Bilgisayar Mühendisi','Kıdemli Geliştirici','2002 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (4,'Bilgisayar Mühendisi','Takım Lideri','2003 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (5,'Yazılım Mühendisi','Analist','2004 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (6,'Yazılım Mühendisi','Testçi','2005 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (7,'Yazılım Mühendisi','Yazılım Mimarı','2006 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (8,'Bilgisayar Mühendisi','Siber Güvenlik Uzmanı','2007 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (9,'Elektronik Mühendisi','Yazılım Geliştirici','2008 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');
insert into iskolik.profil (kullanici_id,meslek,unvan,ozgecmis) values (10,'Fizikçi','Programcı','2009 yılında Ahmet Yesevi Üniversitesi Bilgisayar Mühendisliği...');

insert into iskolik.sertifika (sertifika_id,profil_id,sertifika_adi) values (1,1,'Java SE 11 Developer Certification');
insert into iskolik.sertifika (sertifika_id,profil_id,sertifika_adi) values (2,1,'Oracle Certified Professional, Java SE 8');
select setval('iskolik.sertifika_sertifika_id_seq', 3, true);

insert into iskolik.sinav (sinav_id,profil_id,sinav_adi,sinav_sonucu) values (1,1,'YDS','75');
insert into iskolik.sinav (sinav_id,profil_id,sinav_adi,sinav_sonucu) values (2,1,'KPSS','82');
select setval('iskolik.sinav_sinav_id_seq', 3, true);

insert into iskolik.yetenek (yetenek_id,profil_id,kategori_id,aciklama) values (1,1,6,'10 yıl tecrübeli, çok iyi');
insert into iskolik.yetenek (yetenek_id,profil_id,kategori_id,aciklama) values (2,1,10,'5 yıl tecrübeli, iyi');
insert into iskolik.yetenek (yetenek_id,profil_id,kategori_id,aciklama) values (3,1,11,'2 yıl tecrübeli, orta');
select setval('iskolik.yetenek_yetenek_id_seq', 4, true);

insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (1, 12, 'Bilişim Güvemliği Uzmanı', false,'Ankara','2021-09-25','2021-11-15',null,'2021-12-15',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Beklemede');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (2, 11, 'İnsan Kaynakları Uzmanı', false,'İzmir','2021-11-01','2021-11-04',null,'2021-12-04',0,'İzmir yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (3, 13, 'Teknisyen', false,'Ankara','2021-09-09','2021-08-10',null,'2021-09-10',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'İptal');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (4, 16, 'Aşçı', false,'Ankara','2021-09-15','2021-09-21',null,'2021-10-21',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Tamamlandı');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (5, 16, 'Hemşire', false,'Ankara','2021-09-25','2021-10-06',null,'2021-10-06',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'İptal');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (6, 13, 'Güvenlik Görevlisi', false,'Ankara','2021-10-05','2021-10-07',null,'2021-11-07',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (7, 14, 'Yazılım Uzmanı', false,'İstanbul','2021-09-25','2021-10-01',null,'2021-12-30',0,'İstanbul yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (8, 15, 'Grafiker', false,'İstanbul','2021-09-25','2021-10-01',null,'2021-12-30',0,'İstanbul yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (9, 11, 'Muhasebe Uzmanı', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (10, 12, 'Garson', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (11, 12, 'Yazılım Uzmanı', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (12, 13, 'Yazılım Geliştirme Uzmanı', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (13, 13, 'Yazılımcı', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (14, 14, 'C++ Yazılım Uzmanı', false,'İstanbul','2021-09-25','2021-10-01',null,'2021-12-30',0,'İstanbul yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (15, 12, 'Kıdemli Java Yazılımcı', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (16, 15, 'Yazılım Geliştirici', false,'İstanbul','2021-09-25','2021-10-01',null,'2021-12-30',0,'İstanbul yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (17, 14, 'Yazılım Uzmanı', false,'İstanbul','2021-09-25','2021-10-01',null,'2021-12-30',0,'İstanbul yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (18, 15, 'Önyüz Geliştirme Uzmanı', false,'İstanbul','2021-09-25','2021-10-01',null,'2021-12-30',0,'İstanbul yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (19, 12, 'Takım Lideri', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
insert into iskolik.ilan (ilan_id,kullanici_id,unvan,is_part_time,yer,giris_tarihi,yayin_tarihi,iptal_tarihi,son_basvuru_tarihi,basvuru_limiti,aciklama,zorunlu_ozellikler,tercihen_ozellikler,min_maas,max_maas,durum)
  values (20, 12, 'Kıdemli Yazılım Geliştirme Uzmanı', false,'Ankara','2021-09-25','2021-10-01',null,'2021-12-30',0,'Ankara yerleşkemizde çalışmak üzere tam zamanlı çalışma arkadaşları arıyoruz.','İngilizce','Almanca',0,0,'Aktif');
select setval('iskolik.ilan_ilan_id_seq', 21, true);

insert into iskolik.basvuru (basvuru_id,kullanici_id,ilan_id,basvuru_tarihi,iptal_tarihi,durum) values (1,1,11,'2021-11-27',null,'Aktif');
insert into iskolik.basvuru (basvuru_id,kullanici_id,ilan_id,basvuru_tarihi,iptal_tarihi,durum) values (2,1,13,'2021-10-25',null,'Aktif');
insert into iskolik.basvuru (basvuru_id,kullanici_id,ilan_id,basvuru_tarihi,iptal_tarihi,durum) values (3,1,15,'2021-09-21',null,'Tamamlandı');
insert into iskolik.basvuru (basvuru_id,kullanici_id,ilan_id,basvuru_tarihi,iptal_tarihi,durum) values (4,1,16,'2021-08-18',null,'Tamamlandı');
insert into iskolik.basvuru (basvuru_id,kullanici_id,ilan_id,basvuru_tarihi,iptal_tarihi,durum) values (5,1,19,'2021-07-13',null,'İptal');
select setval('iskolik.basvuru_basvuru_id_seq', 6, true);

insert into iskolik.ilan_kategori (ilan_id,kategori_id,aciklama) values (11,6,'En az 5 yıl tecrübe');
insert into iskolik.ilan_kategori (ilan_id,kategori_id,aciklama) values (11,7,'En az 10 yıl tecrübe');
insert into iskolik.ilan_kategori (ilan_id,kategori_id,aciklama) values (11,8,'En az 2 yıl tecrübe');
insert into iskolik.ilan_kategori (ilan_id,kategori_id,aciklama) values (12,4,'En az 5 yıl tecrübe');
insert into iskolik.ilan_kategori (ilan_id,kategori_id,aciklama) values (12,5,'En az 8 yıl tecrübe');
