package edu.tin.tingeso1.services;

import edu.tin.tingeso1.entities.MarcaEntity;
import edu.tin.tingeso1.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.sql.Time;
import java.text.ParseException;
import java.sql.Date;

@Service
public class MarcaService {
    @Autowired
    MarcaRepository marcaRepository;

    public void leerData() throws IOException, ParseException {
        File doc = new File("DATA.txt");
        BufferedReader obj = new BufferedReader(new FileReader(doc));
        String strng;
        while ((strng = obj.readLine()) != null){
            String[] parts = strng.split(";");
            String part1 = parts[0].replace("/","-"); // date
            String part2 = parts[1]+":00";
            MarcaEntity marca= new MarcaEntity(Date.valueOf(part1), Time.valueOf(part2), parts[2]);
            marcaRepository.save(marca);
        }
    }




}
