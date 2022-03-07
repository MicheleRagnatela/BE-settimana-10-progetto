package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.exception.CatalogoLibriException;
import it.epicode.be.catalogolibri.model.Autore;
import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.AutoreRepository;
import it.epicode.be.catalogolibri.repository.LibroRepository;

@Service
public class AutoreService {

	@Autowired
	AutoreRepository autoreRepository;

	@Autowired
	LibroRepository libroRepository;

	public Optional<Autore> findByIdAutori(Long id) {
		Optional<Autore> autore = autoreRepository.findById(id);
		if(autore.isPresent())
			return autoreRepository.findById(id);
		else
			throw new CatalogoLibriException("Autore non trovato!Inserisci un altro id");
	}

	public List<Autore> findAllAutori() {
		return autoreRepository.findAll();
	}

	public Autore saveAutore(Autore autore) {
		return autoreRepository.save(autore);
	}

	public Autore updateAutore(Long id, Autore autore) {
		Optional<Autore> autoreResult = autoreRepository.findById(id);

		if (autoreResult.isPresent()) {
			Autore autoreUpdate = autoreResult.get();
			autoreUpdate.setNome(autore.getNome());
			autoreUpdate.setCognome(autore.getCognome());
			autoreRepository.save(autoreUpdate);
			return autoreUpdate;
		} else {
			throw new CatalogoLibriException(
					"Autore non trovato! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");
		}
	}

	public void deleteAutore(Long id) {
		Optional<Autore> a = autoreRepository.findById(id);
		if (a.isPresent()) {
			List<Libro> libri = libroRepository.findByAutoriId(id);

			for (Libro l : libri)

				l.getAutori().remove(a.get());
		} else {
			throw new CatalogoLibriException(
					"Autore non trovato! Non è possibile effettuare l'eliminazione. Inserisci un altro id");
		}
		autoreRepository.deleteById(id);
	}
}
