package edu.tin.tingeso1.repositories;


import edu.tin.tingeso1.entities.PlanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaEntity, Long> {

}
