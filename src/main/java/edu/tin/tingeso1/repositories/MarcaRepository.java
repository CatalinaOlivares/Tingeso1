package edu.tin.tingeso1.repositories;
import edu.tin.tingeso1.entities.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    public MarcaEntity findByRut(String rut);
    public MarcaEntity findByFecha(Date fecha);
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Transactional
    @Query(value = "update marcas set justificativo = true where fecha = :fecha and rut = :rut",
            nativeQuery = true)
    void updateJustificativo(@Param("fecha") Date fecha, @Param("rut") String rut);




    @Query(value = "select * from marcas as m where m.fecha = :fecha and m.rut = :rut ORDER BY m.hora limit 1",
            nativeQuery = true)
    MarcaEntity findByRutAndFecha(@Param("fecha") Date fecha, @Param("rut") String rut);
    @Query(value = "select * from marcas as m where  m.fecha = :fecha and m.rut = :rut and m.hora > '18:00'",
            nativeQuery = true)
    MarcaEntity findByHoraAfterAndRut(@Param("rut") String rut,@Param("fecha") Date fecha);


    @Query(value = "select * from marcas as m where m.rut = :rut and m.hora > '8:10' and m.hora < '18:00'" ,
            nativeQuery = true)
    ArrayList<MarcaEntity> findByHoraIngrAndRut(@Param("rut") String rut);
   /* @Query(value = "SELECT @s\\:=@s+1, m.* FROM (SELECT * from marcas as m where m.rut = :rut order by hora) as m, (SELECT @s\\:= 0) AS s where @s < (SELECT count(*)/2 from (SELECT * from marcas as m where m.rut = :rut order by hora) d2)",
            nativeQuery = true)
    ArrayList<MarcaEntity> findByHoraIngrAndRut(@Param("rut") String rut);
    */
}
