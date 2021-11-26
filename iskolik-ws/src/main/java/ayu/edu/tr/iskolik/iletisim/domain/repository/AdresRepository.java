package ayu.edu.tr.iskolik.iletisim.domain.repository;

import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdresRepository extends JpaRepository<Adres, Long>, JpaSpecificationExecutor<Adres> {

}
