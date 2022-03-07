package it.epicode.be.catalogolibri.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.service.LibroService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@GetMapping("/findall/libri")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findAllLibri() {
		List<Libro> findAll = libroService.findAllLibro();
		if (!findAll.isEmpty())
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findByid/libro/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Libro> findById(@PathVariable(required = true) Long id) {
		Optional<Libro> findById = libroService.findByIdLibro(id);

		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/findbyautore/{cognome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findByAutoreNome(@PathVariable(required = true) String cognome) {
		Optional<List<Libro>> findById = Optional.ofNullable(libroService.findByAutoreCognome(cognome));

		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/findbycategoria/{nome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findByCategorieNome(@PathVariable(required = true) String nome) {
		Optional<List<Libro>> findByCategoriaNome = Optional.ofNullable(libroService.findByCategoriaNome(nome));

		if (findByCategoriaNome.isPresent()) {
			return new ResponseEntity<>(findByCategoriaNome.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/save/libro")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> save(@RequestBody Libro libro) {
		Libro save = libroService.saveLibro(libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping("/update/libro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> update(@PathVariable(required=true) Long id, @RequestBody Libro libro) {
		Libro save = libroService.updateLibro(id, libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping("/delete/libro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		libroService.deleteLibro(id);
		return new ResponseEntity<>("Libro eliminato", HttpStatus.OK);

	}

}
