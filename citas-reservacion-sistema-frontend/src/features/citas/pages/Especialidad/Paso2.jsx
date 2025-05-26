// components/Paso2.jsx
import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { buscarDoctoresPorEspecialidad } from "../../services/CitaEspecialidadService";

export const SeleccionarDoctorEsp = () => {
  const [medicos, setMedicos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [medicoSeleccionado, setMedicoSeleccionado] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  const especialidad = location.state?.especialidad;

  useEffect(() => {
    if (!especialidad) {
      navigate("/crear-cita-medica/buscar-por/especialidad/paso-1");
      return;
    }
    cargarMedicos();
  }, [especialidad]);

  const cargarMedicos = async () => {
    try {
      setLoading(true);
      const data = await buscarDoctoresPorEspecialidad(especialidad.id);
      setMedicos(data);
    } catch (err) {
      setError("Error al cargar los médicos");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleSeleccionarMedico = (medico) => {
    setMedicoSeleccionado(medico);
  };

  const handleContinuar = () => {
    if (medicoSeleccionado) {
      navigate("/crear-cita-medica/buscar-por/especialidad/paso-3", {
        state: {
          especialidad: especialidad,
          medico: medicoSeleccionado,
        },
      });
    }
  };

  const handleVolver = () => {
    navigate("/crear-cita-medica/buscar-por/especialidad/paso-1");
  };

  if (!especialidad) {
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
              <p className="mt-3">Cargando médicos...</p>
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
              <button className="btn btn-primary" onClick={cargarMedicos}>
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

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="card shadow">
            <div className="card-header bg-primary text-white">
              <h3 className="card-title mb-0">
                <i className="fas fa-user-md me-2"></i>
                Paso 2 - Selección de Médico
              </h3>
              <small className="text-light">
                Especialidad: {especialidad.nombre} - S/. {especialidad.monto}
              </small>
            </div>
            <div className="card-body">
              <p className="text-muted mb-4">
                Selecciona el médico de tu preferencia:
              </p>

              {medicos.length > 0 ? (
                <div className="row">
                  {medicos.map((medico) => (
                    <div key={medico.id} className="col-md-6 mb-3">
                      <div
                        className={`card h-100 cursor-pointer border-2 ${
                          medicoSeleccionado?.id === medico.id
                            ? "border-primary bg-light"
                            : "border-light"
                        }`}
                        style={{ cursor: "pointer" }}
                        onClick={() => handleSeleccionarMedico(medico)}
                      >
                        <div className="card-body">
                          <div className="d-flex align-items-center mb-3">
                            <div className="me-3">
                              <i className="fas fa-user-circle fa-3x text-primary"></i>
                            </div>
                            <div>
                              <h5 className="card-title mb-1">
                                {medico.nombreCompleto}
                              </h5>
                              <p className="text-muted mb-0">
                                <small>
                                  <i className="fas fa-hospital me-1"></i>
                                  {medico.nombreSede}
                                </small>
                              </p>
                            </div>
                          </div>

                          <div className="mb-2">
                            <span className="badge bg-info text-dark">
                              {medico.nombreEspecialidad}
                            </span>
                          </div>

                          {medicoSeleccionado?.id === medico.id && (
                            <div className="text-center mt-3">
                              <i className="fas fa-check-circle text-primary fa-2x"></i>
                            </div>
                          )}
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              ) : (
                <div className="text-center py-5">
                  <i className="fas fa-exclamation-triangle fa-3x text-warning mb-3"></i>
                  <h5>No hay médicos disponibles</h5>
                  <p className="text-muted">
                    No se encontraron médicos para la especialidad seleccionada.
                  </p>
                </div>
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
                    {medicoSeleccionado
                      ? `Médico seleccionado: ${medicoSeleccionado.nombreCompleto}`
                      : "Selecciona un médico para continuar"}
                  </span>
                </div>

                <button
                  className="btn btn-primary btn-lg"
                  disabled={!medicoSeleccionado}
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
