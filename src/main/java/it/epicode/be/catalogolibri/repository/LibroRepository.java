package it.epicode.be.catalogolibri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.catalogolibri.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

	public List<Libro> findByAutoriCognome(String cognome);
	public List<Libro> findByCategorieNome(String nome);
	public List<Libro> findByAutoriId(Long id);
	public List<Libro> findByCategorieId(Long id);
}
