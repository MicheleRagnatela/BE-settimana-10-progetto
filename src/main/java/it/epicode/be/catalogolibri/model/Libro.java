package it.epicode.be.catalogolibri.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id;
	@Column(nullable=false)
	private String titolo;
	@Column(nullable=false)
	private String annoDiPubblicazione;
	@Column(nullable=false)
	private double prezzo;
	
	@ManyToMany
    @JoinTable(name = "libri_autori",
    joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "autori_id", referencedColumnName = "id"))
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Autore> autori = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "libri_categorie",
    joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "categorie_id", referencedColumnName = "id"))
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Categoria> categorie = new ArrayList<>();
	
}
