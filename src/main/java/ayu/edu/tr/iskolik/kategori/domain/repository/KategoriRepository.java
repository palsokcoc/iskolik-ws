package ayu.edu.tr.iskolik.kategori.domain.repository;

import ayu.edu.tr.iskolik.kategori.domain.model.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KategoriRepository extends JpaRepository<Kategori, Long>, JpaSpecificationExecutor<Kategori> {

}
