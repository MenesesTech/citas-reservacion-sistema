package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.FechaHoraMapper;
import com.femt.citas_reservacion_sistema_backend.repository.FechaHoraRepository;
import com.femt.citas_reservacion_sistema_backend.service.FechaHoraService;

@Service
public class FechaHoraServiceImp implements FechaHoraService {

    @Autowired
    private FechaHoraRepository fechaHoraRepository;
    private FechaHoraMapper fechaHoraMapper;

    @Override
    public String obtenerFechaHoraActual() throws Exception {
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        return "Fecha actual: " + fechaActual + ", Hora actual: " + horaActual;
    }

    @Override
    public List<FechaHoraDTO> obtenerHorariosDisponiblesPorMedico(Long medicoId, LocalDate fecha, LocalTime hora,
            Boolean disponible) {
        return fechaHoraRepository.findByMedicoIdAndFechaAndHoraAndDisponibleTrue(medicoId, fecha, hora, disponible)
                .stream()
                .map(fechaHoraMapper::toDTO)
                .collect(Collectors.toList());
    }

}
