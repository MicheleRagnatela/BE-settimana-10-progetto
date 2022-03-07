package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.epicode.be.catalogolibri.exception.CatalogoLibriException;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	LibroRepository libroRepository;

	public Optional<Libro> findByIdLibro(Long id) {
		Optional<Libro> libro = libroRepository.findById(id);
		if(libro.isPresent())
			return libroRepository.findById(id);
		else
			throw new CatalogoLibriException("Libro non trovato!Inserisci un altro id");

	}

	public List<Libro> findAllLibro() {
		return libroRepository.findAll();
	}
	
	public Libro saveLibro(Libro libro) {
		return libroRepository.save(libro);
	}
	
	public Libro updateLibro(Long id, Libro libro) {
		Optional<Libro> libroResult = libroRepository.findById(id);
		if(libroResult.isPresent()) {
			Libro libroUpdate = new Libro();
			libroUpdate.setId(id);
			libroUpdate.setTitolo(libro.getTitolo());
			libroUpdate.setPrezzo(libro.getPrezzo());
			libroUpdate.setAnnoDiPubblicazione(libro.getAnnoDiPubblicazione());
			libroUpdate.setAutori(libro.getAutori());
			libroUpdate.setCategorie(libro.getCategorie());
			libroRepository.save(libroUpdate);
			return libroUpdate;
		}
			else
				throw new CatalogoLibriException("Libro non trovato! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");
		
	}
	
	public void deleteLibro(Long id) {
		Optional<Libro> libro = libroRepository.findById(id);
		if (libro.isPresent()) {
			List<Libro> libri = libroRepository.findAll();
			libri.remove(libro.get());
		}

		else {
			throw new CatalogoLibriException(
					"Libro non trovato! Non è possibile effettuare l'eliminazione. Inserisci un altro id");

		}
		libroRepository.deleteById(id);
	}
	
	public List<Libro> findByAutoreCognome(String cognome) {
		return libroRepository.findByAutoriCognome(cognome);
	}
	
	public List<Libro> findByCategoriaNome(String nome) {
		return libroRepository.findByCategorieNome(nome);
	}
	
	
}
