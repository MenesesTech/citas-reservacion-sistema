package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.PagoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PagoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Pago;
import com.femt.citas_reservacion_sistema_backend.mapper.PagoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.CitaRepository;
import com.femt.citas_reservacion_sistema_backend.repository.PagoRepository;
import com.femt.citas_reservacion_sistema_backend.service.PagoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImp implements PagoService {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final CitaRepository citaRepository;

    public PagoServiceImp(PagoRepository pagoRepository, PagoMapper pagoMapper, CitaRepository citaRepository) {
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
        this.citaRepository = citaRepository;
    }

    @Override
    public List<PagoResponseDTO> listarPagos() throws Exception {
        return pagoMapper.toResponseDTOList(pagoRepository.findAll());
    }

    @Override
    public Optional<PagoResponseDTO> obtenerPagoPorId(Long id) throws Exception {
        return pagoRepository.findById(id)
                .map(pagoMapper::toResponseDTO);
    }

    @Override
    public PagoResponseDTO guardarPago(PagoRequestDTO pagoRequest) throws Exception {
        Pago pago = pagoMapper.toEntity(pagoRequest);

        // Asignar la cita
        pago.setCita(citaRepository.findById(pagoRequest.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada")));

        Pago pagoGuardado = pagoRepository.save(pago);
        return pagoMapper.toResponseDTO(pagoGuardado);
    }

    @Override
    public void eliminarPago(Long id) throws Exception {
        if (!pagoRepository.existsById(id)) {
            throw new Exception("Pago no encontrado con ID: " + id);
        }
        pagoRepository.deleteById(id);
    }

    @Override
    public PagoResponseDTO actualizarPago(PagoRequestDTO pagoRequest) throws Exception {
        Pago pago = pagoRepository.findById(pagoRequest.getCitaId())
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        pago.setIdTransaccionPasarela(pagoRequest.getIdTransaccionPasarela());
        pago.setProveedor_pasarela(pagoRequest.getProveedorPasarela());

        Pago pagoActualizado = pagoRepository.save(pago);
        return pagoMapper.toResponseDTO(pagoActualizado);
    }

    @Override
    public List<PagoResponseDTO> obtenerPagosPorCita(Long citaId) throws Exception {
        return pagoRepository.findByCitaId(citaId).stream()
                .map(pagoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<PagoResponseDTO> obtenerPagoPorTransaccion(String idTransaccion) throws Exception {
        return pagoRepository.findByIdTransaccionPasarela(idTransaccion)
                .map(pagoMapper::toResponseDTO);
    }
}