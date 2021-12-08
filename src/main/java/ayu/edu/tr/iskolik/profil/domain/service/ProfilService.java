package ayu.edu.tr.iskolik.profil.domain.service;

import ayu.edu.tr.iskolik.profil.domain.model.dto.ProfilDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProfilService {

	ProfilDTO findProfilById(Long id);

	List<ProfilDTO> findAll(Specification specification, Pageable pageable);

	ProfilDTO saveProfil(ProfilDTO profilDTO);
	ProfilDTO updateProfil(Long id, ProfilDTO profilDTO);
	ProfilDTO deleteProfilById(Long id);
}
