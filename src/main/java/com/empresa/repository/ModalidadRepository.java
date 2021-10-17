package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.Modalidad;

public interface ModalidadRepository extends JpaRepository<Modalidad, Integer>  {

	@Query("Select m from Modalidad m where m.nombre like :param_nom and m.sede = :param_sede")
	public List<Modalidad> listaPorNombreSede(@Param("param_nom") String nom, @Param("param_sede") String sede);
}
