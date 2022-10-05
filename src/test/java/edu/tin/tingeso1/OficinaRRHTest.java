package edu.tin.tingeso1;
import edu.tin.tingeso1.entities.EmpleadoEntity;
import edu.tin.tingeso1.entities.MarcaEntity;
import edu.tin.tingeso1.repositories.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import edu.tin.tingeso1.services.OficinaRRH;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class OficinaRRHTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmpleadoRepository empleadoRepository;
    OficinaRRH oficinaRRHH = new OficinaRRH();
    EmpleadoEntity empleado = new EmpleadoEntity();
    MarcaEntity marca = new MarcaEntity();

    @Test
    void obtenerAniosServicioTest() {
        empleado.setFechaIngreso(Date.valueOf(LocalDate.of(2019, 1, 1)));
        empleado.setRut("27.134.678-6");
        Integer aniosServicio = oficinaRRHH.obtenerAniosServicio(empleado);
        assertEquals(3, aniosServicio, 0.0);
    }



    @Test
    void obteneringresarJustificativoTest() throws ParseException {
        empleado.setRut("27.134.678-6");
        marca= new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("10:00:00"), "27.134.678-6");
        Integer justificacion = oficinaRRHH.obteneringresarJustificativo(marca);
        assertEquals(1, justificacion);
    }

    @Test
    void obteneringresarJustificativoTest2() throws ParseException {
        empleado.setRut("27.134.678-6");
        marca= new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("08:40:00"), "27.134.678-6");
        Integer justificacion = oficinaRRHH.obteneringresarJustificativo(marca);
        assertEquals(0, justificacion);
    }
    /* public void ObtenerHorasExtrasTest() {
        empleado.setRut("27.134.678-6");
        marca= new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("021:00:00"), "27.134.678-6");
        Integer horasExtras = oficinaRRHH.I(empleado.getRut(),marca);
        assertEquals(10, horasExtras, 0.0);
    }*/
    //VERRRR
    @Test
    void obtenerDescuentoTest() {
        empleado.setRut("27.134.678-6");
        ArrayList<MarcaEntity> marcas=new ArrayList();
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("10:00:00"), "27.134.678-6",true));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 1)), Time.valueOf("08:00:00"), "27.134.678-6",false));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 3)), Time.valueOf("09:00:00"), "27.134.678-6",false));
        double descuento = oficinaRRHH.obtenerDescuento(marcas);
        assertEquals(0.06, descuento, 0.0);
    }
    @Test
    void obtenerDescuentoTest2() {
        empleado.setRut("27.134.678-6");
        ArrayList<MarcaEntity> marcas=new ArrayList();
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("10:00:00"), "27.134.678-6",true));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 1)), Time.valueOf("11:00:00"), "27.134.678-6",false));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 3)), Time.valueOf("09:00:00"), "27.134.678-6",false));
        double descuento = oficinaRRHH.obtenerDescuento(marcas);
        assertEquals(0.21, descuento, 0.0);
    }
    @Test
    void obtenerDescuentoTest3() {
        empleado.setRut("27.134.678-6");
        ArrayList<MarcaEntity> marcas=new ArrayList();
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 1, 1)), Time.valueOf("10:00:00"), "27.134.678-6",false));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 1)), Time.valueOf("8:30:00"), "27.134.678-6",false));
        marcas.add(new MarcaEntity(Date.valueOf(LocalDate.of(2019, 2, 3)), Time.valueOf("8:20:00"), "27.134.678-6",false));
        double descuento = oficinaRRHH.obtenerDescuento(marcas);
        assertEquals(0.28, descuento, 0.0);
    }
    //calcularBonificacionTiempoServicio?????
    @Test
    void obtenerBonifHoraExtraTest() { // REVISAR
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("C");
        empleado.setHorasExtras(10);
        double bonifHoraExtra = oficinaRRHH.obtenerBonifHorasExtras(empleado);
        assertEquals(1000000, bonifHoraExtra, 0.0);
    }
    @Test
    void obtenerBonifHoraExtraTest2() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("A");
        empleado.setHorasExtras(5);
        double bonifHoraExtra = oficinaRRHH.obtenerBonifHorasExtras(empleado);

        assertEquals(125000, bonifHoraExtra, 0.0);
    }
    @Test
    void obtenerBonifHoraExtraTest3() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("B");
        empleado.setHorasExtras(3);
        double bonifHoraExtra = oficinaRRHH.obtenerBonifHorasExtras(empleado);

        assertEquals(60000, bonifHoraExtra, 0.0);
    }
    @Test
    void obtenerBonifHoraExtraTest4() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("F");
        empleado.setHorasExtras(3);
        double bonifHoraExtra = oficinaRRHH.obtenerBonifHorasExtras(empleado);

        assertEquals(0, bonifHoraExtra, 0.0);
    }
    @Test
    void obtenerBonifHoraExtraTest5() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("F");
        double bonifHoraExtra = oficinaRRHH.obtenerBonifHorasExtras(empleado);

        assertEquals(0, bonifHoraExtra, 0.0);
    }
    @Test
    void obtenerSueldoFijoTest() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("C");
        double sueldoFijo = oficinaRRHH.obtenerSueldoFijo(empleado);
        assertEquals(800000, sueldoFijo, 0.0);
    }

    @Test
    void obtenerSueldoFijoTest2() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("A");
        double sueldoFijo = oficinaRRHH.obtenerSueldoFijo(empleado);
        assertEquals(1700000, sueldoFijo, 0.0);
    }
    @Test
    void obtenerSueldoFijoTest3() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("F");
        double sueldoFijo = oficinaRRHH.obtenerSueldoFijo(empleado);
        assertEquals(0, sueldoFijo, 0.0);
    }
    @Test
    void obtenerSueldoFijoTest4() {
        empleado.setRut("27.134.678-6");
        empleado.setCategoria("B");
        double sueldoFijo = oficinaRRHH.obtenerSueldoFijo(empleado);
        assertEquals(1200000, sueldoFijo, 0.0);
    }
    



}
