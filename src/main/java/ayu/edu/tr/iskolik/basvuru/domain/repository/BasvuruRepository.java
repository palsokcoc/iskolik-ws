package ayu.edu.tr.iskolik.basvuru.domain.repository;

import ayu.edu.tr.iskolik.basvuru.domain.model.entity.Basvuru;
import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import ayu.edu.tr.iskolik.kullanici.domain.model.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BasvuruRepository extends JpaRepository<Basvuru, Long>, JpaSpecificationExecutor<Basvuru> {

	Basvuru findByKullaniciAndIlan(Kullanici kullanici, Ilan ilan);
}
