package ayu.edu.tr.iskolik.ilan.domain.repository;

import ayu.edu.tr.iskolik.ilan.domain.model.entity.Ilan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IlanRepository extends JpaRepository<Ilan, Long>, JpaSpecificationExecutor<Ilan> {

}
