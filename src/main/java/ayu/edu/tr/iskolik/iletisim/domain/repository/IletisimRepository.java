package ayu.edu.tr.iskolik.iletisim.domain.repository;

import ayu.edu.tr.iskolik.iletisim.domain.model.entity.Iletisim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IletisimRepository extends JpaRepository<Iletisim, Long>, JpaSpecificationExecutor<Iletisim> {

}
