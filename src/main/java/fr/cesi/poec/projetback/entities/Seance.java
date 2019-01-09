package fr.cesi.poec.projetback.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// name (facultatif) => nom de la table
// constructeur sans argument
// creer des getters/setters
@Entity(name="seances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
}
