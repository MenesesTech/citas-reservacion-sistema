// components/Paso3.jsx
import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { buscarFechaPorMedico } from "../../services/CitaEspecialidadService";

export const SeleccionarHoraFechaEsp = () => {
  const [fechasHoras, setFechasHoras] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [fechaHoraSeleccionada, setFechaHoraSeleccionada] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  const { especialidad, medico } = location.state || {};

  useEffect(() => {
    if (!especialidad || !medico) {
      navigate("/paso1");
      return;
    }
    cargarFechasHoras();
  }, [medico]);

  const cargarFechasHoras = async () => {
    try {
      setLoading(true);
      const data = await buscarFechaPorMedico(medico.id);
      setFechasHoras(data);
    } catch (err) {
      setError("Error al cargar las fechas y horas disponibles");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleSeleccionarFechaHora = (fechaHora) => {
    if (fechaHora.disponible) {
      setFechaHoraSeleccionada(fechaHora);
    }
  };

  const handleContinuar = () => {
    if (fechaHoraSeleccionada) {
      navigate("/crear-cita-medica/buscar-por/especialidad/paso-4", {
        state: {
          especialidad: especialidad,
          medico: medico,
          fechaHora: fechaHoraSeleccionada,
        },
      });
    }
  };

  const handleVolver = () => {
    navigate("/crear-cita-medica/buscar-por/especialidad/paso-2", {
      state: { especialidad },
    });
  };

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

  if (!especialidad || !medico) {
    return null;
  }

  if (loading) {
    return (
      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="text-center">
              <div className="spinner-border text-primary" role="status">
                <span className="visually-hidden">Cargando...</span>
              </div>
              <p className="mt-3">Cargando horarios disponibles...</p>
            </div>
          </div>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="alert alert-danger" role="alert">
              {error}
            </div>
            <div className="d-flex gap-2">
              <button className="btn btn-primary" onClick={cargarFechasHoras}>
                Reintentar
              </button>
              <button className="btn btn-secondary" onClick={handleVolver}>
                Volver
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }

  const fechasDisponibles = fechasHoras.filter((fh) => fh.disponible);
  const fechasOcupadas = fechasHoras.filter((fh) => !fh.disponible);

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="card shadow">
            <div className="card-header bg-primary text-white">
              <h3 className="card-title mb-0">
                <i className="fas fa-calendar-alt me-2"></i>
                Paso 3 - Selección de Fecha y Hora
              </h3>
              <small className="text-light">
                Dr(a). {medico.nombreCompleto} - {especialidad.nombre}
              </small>
            </div>
            <div className="card-body">
              <p className="text-muted mb-4">
                Selecciona la fecha y hora de tu preferencia:
              </p>

              {fechasDisponibles.length > 0 ? (
                <>
                  <h5 className="text-success mb-3">
                    <i className="fas fa-check-circle me-2"></i>
                    Horarios Disponibles
                  </h5>
                  <div className="row mb-4">
                    {fechasDisponibles.map((fechaHora) => (
                      <div
                        key={fechaHora.id}
                        className="col-md-4 col-sm-6 mb-3"
                      >
                        <div
                          className={`card cursor-pointer border-2 ${
                            fechaHoraSeleccionada?.id === fechaHora.id
                              ? "border-primary bg-light"
                              : "border-success"
                          }`}
                          style={{ cursor: "pointer" }}
                          onClick={() => handleSeleccionarFechaHora(fechaHora)}
                        >
                          <div className="card-body text-center py-3">
                            <div className="mb-2">
                              <i className="fas fa-calendar text-success fa-2x"></i>
                            </div>
                            <h6 className="card-title text-capitalize">
                              {formatearFecha(fechaHora.fecha)}
                            </h6>
                            <p className="card-text">
                              <span className="badge bg-success fs-6">
                                {formatearHora(fechaHora.hora)}
                              </span>
                            </p>
                            {fechaHoraSeleccionada?.id === fechaHora.id && (
                              <div className="mt-2">
                                <i className="fas fa-check-circle text-primary fa-2x"></i>
                              </div>
                            )}
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </>
              ) : (
                <div className="text-center py-4 mb-4">
                  <i className="fas fa-calendar-times fa-3x text-warning mb-3"></i>
                  <h5>No hay horarios disponibles</h5>
                  <p className="text-muted">
                    No se encontraron horarios disponibles para este médico.
                  </p>
                </div>
              )}

              {fechasOcupadas.length > 0 && (
                <>
                  <h5 className="text-danger mb-3">
                    <i className="fas fa-times-circle me-2"></i>
                    Horarios No Disponibles
                  </h5>
                  <div className="row">
                    {fechasOcupadas.map((fechaHora) => (
                      <div
                        key={fechaHora.id}
                        className="col-md-4 col-sm-6 mb-3"
                      >
                        <div className="card border-danger opacity-50">
                          <div className="card-body text-center py-3">
                            <div className="mb-2">
                              <i className="fas fa-calendar-times text-danger fa-2x"></i>
                            </div>
                            <h6 className="card-title text-capitalize text-muted">
                              {formatearFecha(fechaHora.fecha)}
                            </h6>
                            <p className="card-text">
                              <span className="badge bg-danger">
                                {formatearHora(fechaHora.hora)}
                              </span>
                            </p>
                            <small className="text-muted">Ocupado</small>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </>
              )}
            </div>

            <div className="card-footer bg-light">
              <div className="d-flex justify-content-between align-items-center">
                <button
                  className="btn btn-outline-secondary"
                  onClick={handleVolver}
                >
                  <i className="fas fa-arrow-left me-2"></i>
                  Volver
                </button>

                <div className="text-center flex-grow-1 mx-3">
                  <span className="text-muted">
                    {fechaHoraSeleccionada
                      ? `Seleccionado: ${formatearFecha(
                          fechaHoraSeleccionada.fecha
                        )} a las ${formatearHora(fechaHoraSeleccionada.hora)}`
                      : "Selecciona una fecha y hora para continuar"}
                  </span>
                </div>

                <button
                  className="btn btn-primary btn-lg"
                  disabled={!fechaHoraSeleccionada}
                  onClick={handleContinuar}
                >
                  Continuar
                  <i className="fas fa-arrow-right ms-2"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
