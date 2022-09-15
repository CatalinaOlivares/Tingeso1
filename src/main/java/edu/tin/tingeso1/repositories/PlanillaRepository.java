package edu.tin.tingeso1.repositories;

import org.springframework.data.repository.query.Param;
import edu.tin.tingeso1.entities.PlanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaEntity, Long> {

}
