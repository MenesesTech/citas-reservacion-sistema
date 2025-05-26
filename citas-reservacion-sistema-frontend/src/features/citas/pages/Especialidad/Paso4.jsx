// components/Paso4.jsx
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  registrarCita,
  obtenerPacienteId,
} from "../../services/CitaEspecialidadService";

export const CrearCitaPorEsp = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [citaReservada, setCitaReservada] = useState(false);
  const location = useLocation();
  const navigate = useNavigate();

  const { especialidad, medico, fechaHora } = location.state || {};

  React.useEffect(() => {
    if (!especialidad || !medico || !fechaHora) {
      navigate("/paso1");
    }
  }, []);

  const formatearFecha = (fecha) => {
    const opciones = {
      weekday: "long",
      year: "numeric",
      month: "long",
      day: "numeric",
    };
    return new Date(fecha).toLocaleDateString("es-ES", opciones);
  };

  const formatearHora = (hora) => {
    return hora.substring(0, 5); // "09:30:00" -> "09:30"
  };

  const handleReservarCita = async () => {
    try {
      setLoading(true);
      setError(null);

      // Obtener el ID del usuario
      const usuarioId = localStorage.getItem("usuarioId");
      if (!usuarioId) {
        throw new Error("No se encontró información del usuario.");
      }

      // Obtener el pacienteId desde el backend usando usuarioId
      const pacienteId = await obtenerPacienteId(usuarioId);

      // Construir el resumen de cita
      const resumenCita = {
        pacienteId,
        fechaHoraId: Number(fechaHora.id),
        estado: "PENDIENTE",
        fecha: fechaHora.fecha,
        hora: fechaHora.hora,
        medicoId: Number(medico.id),
      };

      console.log("🚀 Datos a enviar:", resumenCita);

      // Registrar cita
      const result = await registrarCita(resumenCita);
      console.log("✅ Respuesta del servidor:", result);

      setCitaReservada(true);
    } catch (err) {
      console.error("❌ Error completo:", err);
      setError(err.message || "Error al reservar la cita");
    } finally {
      setLoading(false);
    }
  };

  const handleVolver = () => {
    navigate("/crear-cita-medica/buscar-por/especialidad/paso-3", {
      state: { especialidad, medico },
    });
  };

  const handleNuevaCita = () => {
    navigate("/home");
  };

  if (!especialidad || !medico || !fechaHora) {
    return null;
  }

  if (citaReservada) {
    return (
      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="card shadow border-success">
              <div className="card-header bg-success text-white text-center">
                <h3 className="card-title mb-0">
                  <i className="fas fa-check-circle me-2"></i>
                  ¡Cita Reservada Exitosamente!
                </h3>
              </div>
              <div className="card-body text-center py-5">
                <div className="mb-4">
                  <i className="fas fa-calendar-check fa-4x text-success"></i>
                </div>
                <h4 className="text-success mb-4">
                  Tu cita ha sido confirmada
                </h4>

                <div className="row">
                  <div className="col-md-6 offset-md-3">
                    <div className="card bg-light">
                      <div className="card-body">
                        <h6 className="card-title">Detalles de tu cita:</h6>
                        <hr />
                        <p>
                          <strong>Especialidad:</strong> {especialidad.nombre}
                        </p>
                        <p>
                          <strong>Médico:</strong> {medico.nombreCompleto}
                        </p>
                        <p>
                          <strong>Sede:</strong> {medico.nombreSede}
                        </p>
                        <p>
                          <strong>Fecha:</strong>{" "}
                          {formatearFecha(fechaHora.fecha)}
                        </p>
                        <p>
                          <strong>Hora:</strong> {formatearHora(fechaHora.hora)}
                        </p>
                        <p>
                          <strong>Costo:</strong> S/. {especialidad.monto}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>

                <div className="mt-4">
                  <p className="text-muted mb-4">
                    Recibirás una confirmación en tu correo electrónico con
                    todos los detalles.
                  </p>
                  <button
                    className="btn btn-primary btn-lg"
                    onClick={handleNuevaCita}
                  >
                    <i className="fas fa-plus me-2"></i>
                    Agendar Nueva Cita
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="card shadow">
            <div className="card-header bg-primary text-white">
              <h3 className="card-title mb-0">
                <i className="fas fa-clipboard-check me-2"></i>
                Paso 4 - Resumen de Cita
              </h3>
            </div>
            <div className="card-body">
              <p className="text-muted mb-4">
                Revisa los detalles de tu cita antes de confirmar:
              </p>

              <div className="row">
                <div className="col-md-8">
                  <div className="card bg-light h-100">
                    <div className="card-body">
                      <h5 className="card-title text-primary mb-4">
                        <i className="fas fa-info-circle me-2"></i>
                        Detalles de la Cita
                      </h5>

                      <div className="row mb-3">
                        <div className="col-sm-4">
                          <strong>Especialidad:</strong>
                        </div>
                        <div className="col-sm-8">
                          <span className="badge bg-info text-dark fs-6">
                            {especialidad.nombre}
                          </span>
                        </div>
                      </div>

                      <div className="row mb-3">
                        <div className="col-sm-4">
                          <strong>Médico:</strong>
                        </div>
                        <div className="col-sm-8">
                          <i className="fas fa-user-md text-primary me-2"></i>
                          {medico.nombreCompleto}
                        </div>
                      </div>

                      <div className="row mb-3">
                        <div className="col-sm-4">
                          <strong>Sede:</strong>
                        </div>
                        <div className="col-sm-8">
                          <i className="fas fa-hospital text-primary me-2"></i>
                          {medico.nombreSede}
                        </div>
                      </div>

                      <div className="row mb-3">
                        <div className="col-sm-4">
                          <strong>Fecha:</strong>
                        </div>
                        <div className="col-sm-8">
                          <i className="fas fa-calendar text-primary me-2"></i>
                          <span className="text-capitalize">
                            {formatearFecha(fechaHora.fecha)}
                          </span>
                        </div>
                      </div>

                      <div className="row mb-3">
                        <div className="col-sm-4">
                          <strong>Hora:</strong>
                        </div>
                        <div className="col-sm-8">
                          <i className="fas fa-clock text-primary me-2"></i>
                          {formatearHora(fechaHora.hora)}
                        </div>
                      </div>

                      <hr />

                      <div className="row">
                        <div className="col-sm-4">
                          <strong>Costo Total:</strong>
                        </div>
                        <div className="col-sm-8">
                          <h5 className="text-success mb-0">
                            <i className="fas fa-dollar-sign me-1"></i>
                            S/. {especialidad.monto}
                          </h5>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div className="col-md-4">
                  <div className="card border-warning">
                    <div className="card-header bg-warning text-dark">
                      <h6 className="card-title mb-0">
                        <i className="fas fa-exclamation-triangle me-2"></i>
                        Importante
                      </h6>
                    </div>
                    <div className="card-body">
                      <ul className="list-unstyled mb-0">
                        <li className="mb-2">
                          <i className="fas fa-check text-success me-2"></i>
                          <small>Llega 15 minutos antes</small>
                        </li>
                        <li className="mb-2">
                          <i className="fas fa-check text-success me-2"></i>
                          <small>Trae tu documento de identidad</small>
                        </li>
                        <li className="mb-2">
                          <i className="fas fa-check text-success me-2"></i>
                          <small>Trae tu historial médico si tienes</small>
                        </li>
                        <li className="mb-0">
                          <i className="fas fa-check text-success me-2"></i>
                          <small>Pago se realiza en el centro médico</small>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>

              {error && (
                <div className="alert alert-danger mt-4" role="alert">
                  <i className="fas fa-exclamation-triangle me-2"></i>
                  {error}
                </div>
              )}
            </div>

            <div className="card-footer bg-light">
              <div className="d-flex justify-content-between align-items-center">
                <button
                  className="btn btn-outline-secondary"
                  onClick={handleVolver}
                  disabled={loading}
                >
                  <i className="fas fa-arrow-left me-2"></i>
                  Volver
                </button>

                <div className="text-center flex-grow-1 mx-3">
                  <p className="text-muted mb-0">
                    Al confirmar, tu cita será reservada inmediatamente
                  </p>
                </div>

                <button
                  className="btn btn-success btn-lg"
                  onClick={handleReservarCita}
                  disabled={loading}
                >
                  {loading ? (
                    <>
                      <span
                        className="spinner-border spinner-border-sm me-2"
                        role="status"
                      >
                        <span className="visually-hidden">Cargando...</span>
                      </span>
                      Reservando...
                    </>
                  ) : (
                    <>
                      <i className="fas fa-check me-2"></i>
                      Reservar Cita
                    </>
                  )}
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
