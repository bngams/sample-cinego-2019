package fr.cesi.poec.projetback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.cesi.poec.projetback.entities.Seance;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Long>{

}
