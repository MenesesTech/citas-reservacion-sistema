
import axios from 'axios';

const API_URL_ESPECIALIDADES = "http://localhost:8080/api/especialidades";
const API_URL_ESPECIALIDAD_MEDICO = "http://localhost:8080/api/especialidades/medicos";
const API_URL_FECHAS = "http://localhost:8080/api/fechas-horas/medico";
const API_URL_HORARIO = "http://localhost:8080/api/fechas-horas/disponibles/medico";
const API_URL_CITAS = "http://localhost:8080/api/citas";
const API_URL_FECHA_HORA = "http://localhost:8080/api/fechas-horas";

export const listarEspecialidades = async () => {
    try {
        const especialidades = await axios.get(API_URL_ESPECIALIDADES);
        if (especialidades.status === 200) {
            return especialidades.data;
        }
        throw new Error("Error al traer especialidades del API");
    } catch (error) {
        throw new Error("Error en el servidor");
    }
};

export const buscarDoctoresPorEspecialidad = async (idEspecialidad) => {
    try {
        const response = await axios.get(`${API_URL_ESPECIALIDAD_MEDICO}/${idEspecialidad}`);
        if (response.status === 200) {
            return response.data;
        }
        throw new Error("Error al traer médicos del API");
    } catch (error) {
        console.error("Error real:", error.message);
        throw new Error("Error en el servidor");
    }
};

export const buscarFechaPorMedico = async (idMedico) => {
    try {
        const response = await axios.get(`${API_URL_FECHAS}/${idMedico}`);
        if (response.status === 200) {
            return response.data;
        }
        throw new Error("Error al traer fechas del API");
    } catch (error) {
        throw new Error("Error en el servidor");
    }
};

export const buscarFechaHoraPorId = async (idFechaHora) => {
    try {
        const response = await axios.get(`${API_URL_FECHA_HORA}/${idFechaHora}`);
        if (response.status === 200) {
            return response.data;
        }
        throw new Error("Error al traer fechas y horas del API");
    } catch (error) {
        throw new Error("Error en el servidor");
    }
};

export const buscarHorarioPorMedicoYFecha = async (idMedico, fecha) => {
    try {
        const response = await axios.get(`${API_URL_HORARIO}?medico=${idMedico}&fecha=${fecha}`);
        if (response.status === 200) {
            return response.data;
        }
        throw new Error("Error al traer horarios del API");
    } catch (error) {
        throw new Error("Error en el servidor");
    }
};

export const construirResumenCita = async (
    idEspecialidad,
    idMedico,
    idFechaHora = null
) => {
    try {
        const usuarioId = localStorage.getItem("usuarioId");
        if (!usuarioId) throw new Error("Usuario no autenticado.");

        const medicos = await buscarDoctoresPorEspecialidad(idEspecialidad);
        const medicoSeleccionado = medicos.find((m) => m.id === idMedico);
        if (!medicoSeleccionado) throw new Error("Médico no encontrado.");

        let fechaSeleccionada = null;
        if (idFechaHora !== null) {
            const fechaHora = await buscarFechaHoraPorId(idFechaHora);
            // Aquí asumo que la respuesta es un objeto único, no una lista
            fechaSeleccionada = fechaHora;
            if (!fechaSeleccionada || fechaSeleccionada.id !== idFechaHora) {
                throw new Error("Fecha y hora no encontrado.");
            }
        }

        return {
            usuarioId: parseInt(usuarioId, 10),
            nombreUsuario: medicoSeleccionado.nombreCompleto,
            especialidadId: medicoSeleccionado.especialidadId,
            nombreEspecialidad: medicoSeleccionado.nombreEspecialidad,
            medicoId: medicoSeleccionado.id,
            nombreMedico: medicoSeleccionado.nombreCompleto,
            sedeId: medicoSeleccionado.sedeId,
            nombreSede: medicoSeleccionado.nombreSede,
            fecha: fechaSeleccionada ? fechaSeleccionada.fecha : null,
            hora: fechaSeleccionada ? fechaSeleccionada.hora : null,
            fechaHoraId: fechaSeleccionada ? fechaSeleccionada.id : null,
        };
    } catch (error) {
        console.error("Error al construir el resumen de cita:", error.message);
        throw new Error("No se pudo construir el resumen de cita.");
    }
};

export const registrarCita = async (resumenCita) => {
    try {
        const payload = {
            pacienteId: parseInt(resumenCita.pacienteId),
            fechaHoraId: parseInt(resumenCita.fechaHoraId),
            estado: resumenCita.estado || "PENDIENTE"
        };

        console.log("🚀 Payload a enviar:", payload);

        const response = await axios.post(API_URL_CITAS, payload);

        if (response.status === 200 || response.status === 201) {
            if (resumenCita.fechaHoraId) {
                const updatePayload = {
                    fecha: resumenCita.fecha,
                    hora: resumenCita.hora,
                    disponible: false,
                    medicoId: parseInt(resumenCita.medicoId),
                };

                await axios.put(`${API_URL_FECHA_HORA}/${resumenCita.fechaHoraId}`, updatePayload);
            }

            return response.data;
        }

        throw new Error("Error al registrar la cita");
    } catch (error) {
        console.error("❌ Error en registrarCita:", error.response?.data || error.message);
        throw new Error(error.response?.data?.message || error.message || "Error en el servidor");
    }
};

export const obtenerPacienteId = async (usuarioId) => {
    const res = await axios.get(`http://localhost:8080/api/pacientes/usuario/${usuarioId}`);
    return res.data.id;
};

