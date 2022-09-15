package edu.tin.tingeso1;
import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.repositories.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.tin.tingeso1.services.OficinaRRH;

import java.sql.Date;
import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class OficinaRRHTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmpleadoRepository empleadoRepository;
    OficinaRRH oficinaRRHH = new OficinaRRH();
    EmpleadoEntity empleado = new EmpleadoEntity();

    @Test
    public void ObtenerSueldoFinal() {
        empleado.setRut("1-9");
        empleado.setNombre("Juan Perez");
        empleado.setFechaIngreso(Date.valueOf(LocalDate.of(2019, 1, 1)));
        empleado.setSueldoFijo(100000);
        empleado.setBonificaAniosSer(0.0);
        entityManager.persist(empleado);
        entityManager.flush();
        assertEquals(100000, oficinaRRHH.calcularSueldoFinal(100000,"1-9"));


    }





}
