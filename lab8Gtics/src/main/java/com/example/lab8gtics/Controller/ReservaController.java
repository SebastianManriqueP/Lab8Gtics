package com.example.lab8gtics.controller;

import com.example.lab8gtics.entity.Reserva;
import com.example.lab8gtics.entity.Reservasin;
import com.example.lab8gtics.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    ReservaRepository reservaRepository;


    @PostMapping("/insertar")
    public ResponseEntity<HashMap<String,String>> insertarReserva(@RequestBody Reservasin reservasin){
        HashMap<String,String> hashMap = new HashMap<>();

        String[] fechas = reservasin.getFecha().split(".");

        String anho = new String();
        String dia = new String();
        String mes = new String();

        for(int i = 0; i<3;i++){
            char dato = fechas[i].charAt(0);
            if(String.valueOf(dato).equals("a")){
                anho =fechas[i].replace("a","");
            }else if(String.valueOf(dato).equals("d")) {
                dia = fechas[i].replace("d","");
            } else if (String.valueOf(dato).equals("m")) {
                mes = fechas[i].replace("m","");
            }
        }

        String formatoFecha = anho+'-'+mes+'-'+dia;

        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");

        Date fechaDB = null;
        try {
            fechaDB = formato.parse(formatoFecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Reserva reserva = new Reserva();

        reserva.setFecha((java.sql.Date) fechaDB);

        reserva.setPeliculaId(reservasin.getPeliculaId());

        reservaRepository.save(reserva);

        hashMap.put("idReservaCreado", String.valueOf(reserva.getNumero()));
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }
}
