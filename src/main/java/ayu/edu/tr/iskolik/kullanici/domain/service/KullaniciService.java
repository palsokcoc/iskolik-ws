package ayu.edu.tr.iskolik.kullanici.domain.service;

import ayu.edu.tr.iskolik.kullanici.domain.model.dto.ElemanAramaSonucuDTO;
import ayu.edu.tr.iskolik.kullanici.domain.model.dto.KullaniciDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface KullaniciService {

	KullaniciDTO findKullaniciById(Long id);

	List<KullaniciDTO> findAll(Specification specification, Pageable pageable);
	List<ElemanAramaSonucuDTO> findBireyselKullaniciOzet(Specification specification, Pageable pageable);

	KullaniciDTO saveKullanici(KullaniciDTO kullaniciDTO);
	KullaniciDTO updateKullanici(Long id, KullaniciDTO kullaniciDTO);
	KullaniciDTO deleteKullaniciById(Long id);
}
