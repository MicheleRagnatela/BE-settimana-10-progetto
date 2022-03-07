package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.exception.CatalogoLibriException;
import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.CategoriaRepository;
import it.epicode.be.catalogolibri.repository.LibroRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	LibroRepository libroRepository;

	public Optional<Categoria> findCategoriaById(Long id) {
		Optional<Categoria> c = categoriaRepository.findById(id);
		if(c.isPresent())
			return categoriaRepository.findById(id);
		else
			throw new CatalogoLibriException("Categoria non trovata!Inserisci un altro id");

	}
	
	public List<Categoria> findAllCategorie() {
		return categoriaRepository.findAll();
	}

	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria updateCategoria(Long id, Categoria categoria) {
		Optional<Categoria> categoriaResult = categoriaRepository.findById(id);

		if (categoriaResult.isPresent()) {
			Categoria categoriaUpdate = categoriaResult.get();
			categoriaUpdate.setNome(categoria.getNome());
			categoriaRepository.save(categoriaUpdate);
			return categoriaUpdate;
		} else {
			throw new CatalogoLibriException("Categoria non trovata! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");
		}
	}

	public void deleteCategoria(Long id) {
		Optional<Categoria> c = categoriaRepository.findById(id);
		if(c.isPresent()) {
		List<Libro> libri = libroRepository.findByCategorieId(id);
		for(Libro l : libri) 
			l.getCategorie().remove(c.get());
		}
		else {
			throw new CatalogoLibriException("Categoria non trovata! Non è possibile effettuare l'eliminazione. Inserisci un altro id");

		}
		categoriaRepository.deleteById(id);
	}

}
