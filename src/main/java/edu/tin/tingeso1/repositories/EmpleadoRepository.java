package edu.tin.tingeso1.repositories;

import edu.tin.tingeso1.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

//capa de persistencia
@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    public EmpleadoEntity findByRut(String rut);
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Transactional
    @Query(value = "update empleados as e set anios_servicio = :aniosServicio," +
            "bonifica_horas_extras = :horasExtras," +
            "descuentos = :descuento," +
            "bonifica_anios_ser = :bonificacion," +
            "sueldo_fijo = :sueldoFijo," +
            "sueldo_bruto = :sueldoBruto," +
            "cot_prev = :previsional," +
            "cot_sal = :salud," +
            "sueldo_final = :sueldoFinal where e.rut = :rut",
            nativeQuery = true)
    void updateByOne(@Param("rut") String rut, @Param("aniosServicio") Integer aniosServicio, @Param("horasExtras") Integer horasExtras,
                          @Param("descuento") Double descuento, @Param("bonificacion") Double bonificacion,
                          @Param("sueldoFijo") Double sueldoFijo, @Param("sueldoBruto") Double sueldoBruto, @Param("previsional") Double previsional,@Param("salud") Double salud,@Param("sueldoFinal") Double sueldoFinal);


    @Query("select e from EmpleadoEntity e where e.nombre = :nombre")
    EmpleadoEntity findByNameCustomQuery(@Param("nombre") String nombre);


    @Query(value = "select * from empleados as e where e.nombre = :nombre",
            nativeQuery = true)
    EmpleadoEntity findByNameNativeQuery(@Param("nombre") String nombre);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Transactional
    @Query(value = "update empleados set horas_Extras = :horas_extras  where  rut = :rut",
            nativeQuery = true)
    void updateHorasExtras( @Param("rut") String rut,@Param("horas_extras") Integer horas_extras );

}