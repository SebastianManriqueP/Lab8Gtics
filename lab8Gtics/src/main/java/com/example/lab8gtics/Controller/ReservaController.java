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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

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

    @PutMapping("/actualizar")
    public ResponseEntity<HashMap<String, Object>> actualizarReserva(@RequestParam String identificador,
                                                                     @RequestParam String idPelicula,
                                                                     @RequestParam String fechaI,
                                                                     @RequestParam String horaIn,
                                                                     @RequestParam String horaF){




        //Separando la fecha
        String[] fechaI1 = fechaI.split(".");
        String fechaI1d = fechaI1[0].substring(1);
        String fechaI1a = fechaI1[1].substring(1);
        String fechaI1m = fechaI1[2].substring(1);



        //Separando la hora de inicio
        String horaI1 = horaIn.substring(-2);
        //Separando la hora de inicio
        String horaF1 = horaF.substring(-2);

        //parseando la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = fechaI1d+"/"+fechaI1m+"/"+fechaI1a;

        //convert String to LocalDate
        LocalDate dateToEntity = LocalDate.parse(date, formatter);


        //Se utiliza el constructor para crear un nuevo objeto Reserva
        /*Reserva reserva = Reserva();*/




        HashMap<String, Object> responseMap = new HashMap<>();
        int id = Integer.parseInt(identificador);
        if (id > 0) {
            Optional<com.example.lab8gtics.Entity.Reserva> opt = reservaRepository.findById(reserva.getId());
            if (opt.isPresent()) {
                com.example.lab8gtics.Entity.Reserva reservaFromDB = opt.get();
                //validar campo por campo
                if (reserva.getId() != null)
                    reservaFromDB.setId(id);

                reservaRepository.save(reservaFromDB);
                responseMap.put("estado", "actualizado");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("msg", "El producto a actualizar no existe");
            }
        } else {
            responseMap.put("msg", "Debe enviar un ID");
        }
        responseMap.put("estado", "error");
        return ResponseEntity.badRequest().body(responseMap);

    }
}
