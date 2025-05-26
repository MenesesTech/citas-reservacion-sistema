// components/Paso1.jsx
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { listarEspecialidades } from "../../services/CitaEspecialidadService";

export const SeleccionarEspecialidadEsp = () => {
  const [especialidades, setEspecialidades] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [especialidadSeleccionada, setEspecialidadSeleccionada] =
    useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    cargarEspecialidades();
  }, []);

  const cargarEspecialidades = async () => {
    try {
      setLoading(true);
      const data = await listarEspecialidades();
      setEspecialidades(data);
    } catch (err) {
      setError("Error al cargar las especialidades");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleSeleccionarEspecialidad = (especialidad) => {
    setEspecialidadSeleccionada(especialidad);
  };

  const handleContinuar = () => {
    if (especialidadSeleccionada) {
      navigate("/crear-cita-medica/buscar-por/especialidad/paso-2", {
        state: { especialidad: especialidadSeleccionada },
      });
    }
  };

  if (loading) {
    return (
      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-md-8">
            <div className="text-center">
              <div className="spinner-border text-primary" role="status">
                <span className="visually-hidden">Cargando...</span>
              </div>
              <p className="mt-3">Cargando especialidades...</p>
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
            <button className="btn btn-primary" onClick={cargarEspecialidades}>
              Reintentar
            </button>
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
                <i className="fas fa-stethoscope me-2"></i>
                Paso 1 - Selección de Especialidad
              </h3>
            </div>
            <div className="card-body">
              <p className="text-muted mb-4">
                Selecciona la especialidad médica para tu cita:
              </p>

              <div className="row">
                {especialidades.map((especialidad) => (
                  <div key={especialidad.id} className="col-md-6 mb-3">
                    <div
                      className={`card h-100 cursor-pointer border-2 ${
                        especialidadSeleccionada?.id === especialidad.id
                          ? "border-primary bg-light"
                          : "border-light"
                      }`}
                      style={{ cursor: "pointer" }}
                      onClick={() =>
                        handleSeleccionarEspecialidad(especialidad)
                      }
                    >
                      <div className="card-body text-center">
                        <div className="mb-3">
                          <i className="fas fa-user-md fa-3x text-primary"></i>
                        </div>
                        <h5 className="card-title">{especialidad.nombre}</h5>

                        {especialidadSeleccionada?.id === especialidad.id && (
                          <div className="mt-2">
                            <i className="fas fa-check-circle text-primary fa-2x"></i>
                          </div>
                        )}
                      </div>
                    </div>
                  </div>
                ))}
              </div>

              {especialidades.length === 0 && (
                <div className="text-center py-5">
                  <i className="fas fa-exclamation-triangle fa-3x text-warning mb-3"></i>
                  <p className="text-muted">
                    No hay especialidades disponibles
                  </p>
                </div>
              )}
            </div>

            <div className="card-footer bg-light">
              <div className="d-flex justify-content-between align-items-center">
                <span className="text-muted">
                  {especialidadSeleccionada
                    ? `Especialidad seleccionada: ${especialidadSeleccionada.nombre}`
                    : "Selecciona una especialidad para continuar"}
                </span>
                <button
                  className="btn btn-primary btn-lg"
                  disabled={!especialidadSeleccionada}
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
