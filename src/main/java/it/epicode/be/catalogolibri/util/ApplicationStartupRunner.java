package it.epicode.be.catalogolibri.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.catalogolibri.model.Autore;
import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.AutoreRepository;
import it.epicode.be.catalogolibri.repository.CategoriaRepository;
import it.epicode.be.catalogolibri.repository.LibroRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {
	
	@Autowired
	LibroRepository libroRepository;

	@Autowired
	AutoreRepository autoreRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Caricamento dati nel db...");
	
		Categoria categoria = new Categoria();
		categoria.setNome("horror");
		categoriaRepository.save(categoria);
		
		Categoria categoria1 = new Categoria();
		categoria1.setNome("romanzo giallo");
		categoriaRepository.save(categoria1);
		
		Categoria categoria2 = new Categoria();
		categoria2.setNome("thriller");
		categoriaRepository.save(categoria2);
		
		Categoria categoria3 = new Categoria();
		categoria3.setNome("fantascienza");
		categoriaRepository.save(categoria3);
		
		Autore autore = new Autore();
		autore.setNome("Loriano");
		autore.setCognome("Macchiavelli");
		autoreRepository.save(autore);
		
		Autore autore1 = new Autore();
		autore1.setNome("Francesco");
		autore1.setCognome("Guccini");
		autoreRepository.save(autore1);
		
		Autore autore2 = new Autore();
		autore2.setNome("Stephen");
		autore2.setCognome("King");
		autoreRepository.save(autore2);
		
		Autore autore3 = new Autore();
		autore3.setNome("Peter");
		autore3.setCognome("Straub");
		autoreRepository.save(autore3);
		
		Libro libro1 = new Libro();
		List<Categoria> categ = new ArrayList<>();
		categ.add(categoria1);
		categ.add(categoria2);
		List<Autore> aut = new ArrayList<>();
		aut.add(autore2);
		aut.add(autore3);
		libro1.setTitolo("Il talismano");
		libro1.setPrezzo(29.50);
		libro1.setAnnoDiPubblicazione("1984");
		libro1.setAutori(aut);
		libro1.setCategorie(categ);
		
		Libro libro2 = new Libro();
		List<Categoria> categ1 = new ArrayList<>();
		categ1.add(categoria);
		categ1.add(categoria2);
		List<Autore> aut1 = new ArrayList<>();
		aut1.add(autore);
		aut1.add(autore1);
		libro2.setTitolo("Macaroni");
		libro2.setPrezzo(29.50);
		libro2.setAnnoDiPubblicazione("1990");
		libro2.setAutori(aut1);
		libro2.setCategorie(categ1);
		

		libroRepository.save(libro1);
		libroRepository.save(libro2);
		
		log.info("Libro salvato: " + libro1.toString());
		log.info("Libro salvato: " + libro2.toString());
	
	}

}
