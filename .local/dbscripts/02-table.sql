create table iskolik.adres (
    adres_id    serial,
    sehir       text,
    ilce        text,
    mahalle     text,
    cadde       text,
    apartman_no text,
    daire_no    int,
    primary key (adres_id)
);

create table iskolik.iletisim (
    iletisim_id   serial,
    adres_id      int,
    cep_telefonu  char(10),
    sabit_telefon char(10),
    primary key (iletisim_id),
    constraint fk_iletisim__adres
        foreign key (adres_id)
            references iskolik.adres
);

create table iskolik.kullanici (
    kullanici_id  serial,
    kullanici_adi text unique not null,
    email         text unique not null,
    durum         text        not null, --OnayBekliyor,Aktif,Pasif,Silindi
    kayit_tarihi  date        not null default current_date,
    sifre_hash    text        not null,
    iletisim_id   int,
    primary key (kullanici_id),
    constraint fk_kullanici__iletisim
        foreign key (iletisim_id)
            references iskolik.iletisim
);

create table iskolik.bireysel_kullanici (
    kullanici_id int,
    ad           text not null,
    soyad        text not null,
    cinsiyet     char not null, --K,E
    dogum_tarihi date not null,
    primary key (kullanici_id),
    constraint fk_bireysel_kullanici__kullanici
        foreign key (kullanici_id)
            references iskolik.kullanici
);

create table iskolik.kurumsal_kullanici (
    kullanici_id   int,
    ad             text not null,
    calisan_sayisi int,
    primary key (kullanici_id),
    constraint fk_kurumsal_kullanici__kullanici
        foreign key (kullanici_id)
            references iskolik.kullanici
);

create table iskolik.kategori (
    kategori_id     serial,
    ad              text unique not null,
    aciklama        text,
    ata_kategori_id int,
--    durum           text        not null, --Aktif,Pasif
    primary key (kategori_id),
    constraint fk_kategori__ata_kategori
        foreign key (kategori_id)
            references iskolik.kategori
);

create table iskolik.profil (
    profil_id serial,
    ozgecmis  text,
    primary key (profil_id),
    constraint fk_profil__kullanici
        foreign key (profil_id)
            references iskolik.bireysel_kullanici
);

create table iskolik.yetenek (
    profil_id   int,
    kategori_id int,
    aciklama    text,
    primary key (profil_id, kategori_id),
    constraint fk_yetenek__profil
        foreign key (profil_id)
            references iskolik.profil,
    constraint fk_yetenek__kategori
        foreign key (kategori_id)
            references iskolik.kategori
);

create table iskolik.sinav (
    profil_id    int,
    sinav_adi    text,
    sinav_sonucu text,
    primary key (profil_id,sinav_adi),
    constraint fk_sinav__profil
        foreign key (profil_id)
            references iskolik.profil
);

create table iskolik.sertifika (
    profil_id     int,
    sertifika_adi text,
    primary key (profil_id,sertifika_adi),
    constraint fk_sertifika__profil
        foreign key (profil_id)
            references iskolik.profil
);

create table iskolik.ilan (
    ilan_id             serial,
    kullanici_id        int,
    unvan               text    not null,
    is_part_time        boolean not null default false,
    yer                 text    not null,
    giris_tarihi        date    not null,
    yayin_tarihi        date,
    iptal_tarihi        date,
    son_basvuru_tarihi  date    not null,
    basvuru_limiti      int,
    aciklama            text    not null,
    zorunlu_ozellikler  text,
    tercihen_ozellikler text,
    maas_araligi        text,
    durum               char(10), --Beklemede,Aktif,İptal,Tamamlandi
    primary key (ilan_id),
    constraint fk_ilan__kullanici
        foreign key (kullanici_id)
            references iskolik.kurumsal_kullanici
);

create table iskolik.basvuru (
    basvuru_id     serial,
    kullanici_id   int,
    ilan_id        int,
    basvuru_tarihi date not null,
    durum          char(10), --Aktif,İptal, Kapalı
    primary key (basvuru_id),
    constraint fk_basvuru__bireysel_kullanici
        foreign key (kullanici_id)
            references iskolik.bireysel_kullanici,
    constraint fk_basvuru__ilan
        foreign key (ilan_id)
            references iskolik.ilan
);

create table iskolik.ilan_kategori (
    ilan_id     int,
    kategori_id int,
    aciklama    text,
    primary key (ilan_id, kategori_id),
    constraint fk_ilan_kategori__ilan
        foreign key (ilan_id)
            references iskolik.ilan,
    constraint fk_ilan_kategori__kategori
        foreign key (kategori_id)
            references iskolik.kategori
);
