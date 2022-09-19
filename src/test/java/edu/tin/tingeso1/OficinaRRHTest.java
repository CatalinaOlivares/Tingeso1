package edu.tin.tingeso1;
import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.entities.MarcaEntity;
import edu.tin.tingeso1.repositories.EmpleadoRepository;
import org.hibernate.type.TrueFalseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import edu.tin.tingeso1.services.OficinaRRH;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class OficinaRRHHTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmpleadoRepository empleadoRepository;
    OficinaRRH oficinaRRHH = new OficinaRRH();
    EmpleadoEntity empleado = new EmpleadoEntity();
    MarcaEntity marca = new MarcaEntity();

    @Test
    void ObtenerAniosServicioTest() {
        empleado.setFechaIngreso(Date.valueOf(LocalDate.of(2019, 1, 1)));
        empleado.setRut("27.134.678-6");
        Integer aniosServicio = oficinaRRHH.obtenerAniosServicio(empleado.getRut(),empleado);
        assertEquals(3, aniosServicio, 0.0);
    }

    @Test
    void ObteneringresarJustificacionTest() throws ParseException {
        empleado.setRut("27.134.678-6");
        marca= new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("010:00:00"), "27.134.678-6");
        Integer justificacion = oficinaRRHH.obteneringresarJustificativo(marca);
        assertEquals(1, justificacion);
    }
    /* public void ObtenerHorasExtrasTest() {
        empleado.setRut("27.134.678-6");
        marca= new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("021:00:00"), "27.134.678-6");
        Integer horasExtras = oficinaRRHH.I(empleado.getRut(),marca);
        assertEquals(10, horasExtras, 0.0);
    }*/
    //VERRRR
    @Test
    void ObtenerDescuentoTest() {
        empleado.setRut("27.134.678-6");
        ArrayList<MarcaEntity> marcas=new ArrayList();
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("10:00:00"), "27.134.678-6",true));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 1)), Time.valueOf("08:00:00"), "27.134.678-6",false));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 3)), Time.valueOf("09:00:00"), "27.134.678-6",false));
        double descuento = oficinaRRHH.obtenerDescuento(empleado.getRut(),marcas);

        assertEquals(0.06, descuento, 0.0);


    }
    @Test
    void ObtenerBonifHoraExtraTest() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("C");
        ArrayList<MarcaEntity> marcas=new ArrayList();
        empleado.setHorasExtras(10);
        double bonifHoraExtra = oficinaRRHH.obtenerBonifHorasExtras(empleado.getRut(),empleado);

        assertEquals(100000, bonifHoraExtra, 0.0);
    }






}
