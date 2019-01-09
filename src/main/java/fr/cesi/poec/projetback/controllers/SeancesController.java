package fr.cesi.poec.projetback.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cesi.poec.projetback.entities.Seance;
import fr.cesi.poec.projetback.repositories.SeanceRepository;


// Permet de mapper des méthodes à des urls
@RestController
@CrossOrigin(origins = "http://localhost:4200")
// Permet de spécifier la racine de toutes les routes de ce controller
@RequestMapping("/seances")
public class SeancesController {
	
	private SeanceRepository seanceRepository;
	
	// Injection de dépendances
	@Autowired
	public SeancesController(SeanceRepository seanceRepository) {
		this.seanceRepository = seanceRepository;
	}
	
	// http://locahost:8282/seances
	// Permet de lier la méthode à une requete HTTP GET
	@GetMapping
	public ResponseEntity<List<Seance>> getSeances() {	
		List<Seance> seances = (List<Seance>) this.seanceRepository.findAll();
		return new ResponseEntity<List<Seance>>(
				seances,
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Seance> getSeanceById(@PathVariable Long id) {
		Seance s = this.seanceRepository.findById(id).orElse(null);
		if(s != null) {
			return new ResponseEntity<Seance>(s, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<Seance> createSeance(@RequestBody Seance s) {
		// json from postman {"id":null,"title":"mon film"}
		s = this.seanceRepository.save(s);
		System.out.println("Seance created " + s.getId());
		return new ResponseEntity<Seance>(s, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Seance> updateSeance(@RequestBody Seance s) {
		if(this.seanceRepository.existsById(s.getId())) {
			this.seanceRepository.save(s);
	        return new ResponseEntity<Seance>(s, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSeance(@PathVariable Long id) {
		Seance s = this.seanceRepository.findById(id).orElse(null);
		if(s != null) {
			this.seanceRepository.delete(s);
	        return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}

}
