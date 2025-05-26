import React, { useEffect, useState } from "react";

import {
  buscarDoctoresPorEspecialidad,
  buscarFechaPorMedico,
  buscarHorarioPorMedicoYFecha,
  construirResumenCita,
} from "../citas/services/CitaEspecialidadService";
import { SidebarComponent } from "../components/sidebar/sidebar";

export const CrearCitaPorEspecialidad = () => {
  const [especialidades, setEspecialidades] = useState([
    { id: 1, nombre: "Cardiología" },
    { id: 2, nombre: "Dermatología" },
    { id: 3, nombre: "Pediatría" },
  ]); // Simulado, o puedes cargarlo desde API

  const [idEspecialidad, setIdEspecialidad] = useState("");
  const [medicos, setMedicos] = useState([]);
  const [idMedico, setIdMedico] = useState("");
  const [fechas, setFechas] = useState([]);
  const [fechaSeleccionada, setFechaSeleccionada] = useState("");
  const [horarios, setHorarios] = useState([]);
  const [horaSeleccionada, setHoraSeleccionada] = useState("");
  const [resumen, setResumen] = useState(null);

  // Función para buscar médicos al pulsar el botón
  const handleBuscarMedicos = () => {
    if (!idEspecialidad) {
      alert("Seleccione una especialidad primero.");
      return;
    }
    buscarDoctoresPorEspecialidad(idEspecialidad)
      .then((data) => {
        setMedicos(data);
        // Reseteamos selecciones previas
        setIdMedico("");
        setFechas([]);
        setFechaSeleccionada("");
        setHorarios([]);
        setHoraSeleccionada("");
        setResumen(null);
      })
      .catch((error) => {
        console.error("Error al buscar médicos:", error);
        alert("No se pudo cargar la lista de médicos.");
      });
  };

  // Cargar fechas cuando se selecciona médico
  useEffect(() => {
    if (!idMedico) {
      setFechas([]);
      setFechaSeleccionada("");
      setHorarios([]);
      setHoraSeleccionada("");
      return;
    }
    buscarFechaPorMedico(idMedico)
      .then((data) => {
        setFechas(data);
        setFechaSeleccionada("");
        setHorarios([]);
        setHoraSeleccionada("");
        setResumen(null);
      })
      .catch((error) => {
        console.error("Error al cargar fechas:", error);
        alert("No se pudo cargar las fechas disponibles.");
      });
  }, [idMedico]);

  // Cargar horarios cuando se selecciona fecha y médico
  useEffect(() => {
    if (!idMedico || !fechaSeleccionada) {
      setHorarios([]);
      setHoraSeleccionada("");
      return;
    }
    buscarHorarioPorMedicoYFecha(idMedico, fechaSeleccionada)
      .then((data) => {
        setHorarios(data);
        setHoraSeleccionada("");
        setResumen(null);
      })
      .catch((error) => {
        console.error("Error al cargar horarios:", error);
        alert("No se pudo cargar los horarios disponibles.");
      });
  }, [idMedico, fechaSeleccionada]);

  const handleConfirmar = async () => {
    try {
      const resumenFinal = await construirResumenCita(
        idEspecialidad,
        idMedico,
        fechaSeleccionada,
        horaSeleccionada
      );
      setResumen(resumenFinal);
    } catch (error) {
      alert("No se pudo construir el resumen.");
    }
  };

  return (
    <div className="container mt-5">
      <SidebarComponent />
      <h2>Solicitar Cita por Especialidad</h2>

      <div className="mb-3">
        <label>Especialidad:</label>
        <select
          className="form-control"
          value={idEspecialidad}
          onChange={(e) => setIdEspecialidad(parseInt(e.target.value))}
        >
          <option value="">-- Seleccione --</option>
          {especialidades.map((esp) => (
            <option key={esp.id} value={esp.id}>
              {esp.nombre}
            </option>
          ))}
        </select>

        <button
          className="btn btn-outline-primary mt-2"
          onClick={handleBuscarMedicos}
        >
          Buscar Médicos
        </button>
      </div>

      {medicos.length > 0 && (
        <div className="mb-3">
          <label>Médico:</label>
          <select
            className="form-control"
            value={idMedico}
            onChange={(e) => setIdMedico(parseInt(e.target.value))}
          >
            <option value="">-- Seleccione --</option>
            {medicos.map((med) => (
              <option key={med.id} value={med.id}>
                {med.nombreCompleto}
              </option>
            ))}
          </select>
        </div>
      )}

      {fechas.length > 0 && (
        <div className="mb-3">
          <label>Fecha:</label>
          <select
            className="form-control"
            value={fechaSeleccionada}
            onChange={(e) => setFechaSeleccionada(e.target.value)}
          >
            <option value="">-- Seleccione --</option>
            {fechas.map((f, idx) => (
              <option key={idx} value={f}>
                {f}
              </option>
            ))}
          </select>
        </div>
      )}

      {horarios.length > 0 && (
        <div className="mb-3">
          <label>Hora:</label>
          <select
            className="form-control"
            value={horaSeleccionada}
            onChange={(e) => setHoraSeleccionada(e.target.value)}
          >
            <option value="">-- Seleccione --</option>
            {horarios.map((h, idx) => (
              <option key={idx} value={h}>
                {h}
              </option>
            ))}
          </select>
        </div>
      )}

      {horaSeleccionada && (
        <button className="btn btn-primary" onClick={handleConfirmar}>
          Confirmar Cita
        </button>
      )}

      {resumen && (
        <div className="mt-4 alert alert-success">
          <h5>Resumen de Cita</h5>
          <p>
            <strong>Especialidad:</strong> {resumen.nombreEspecialidad}
          </p>
          <p>
            <strong>Médico:</strong> {resumen.nombreMedico}
          </p>
          <p>
            <strong>Sede:</strong> {resumen.nombreSede}
          </p>
          <p>
            <strong>Fecha:</strong> {resumen.fecha}
          </p>
          <p>
            <strong>Hora:</strong> {resumen.hora}
          </p>
        </div>
      )}
    </div>
  );
};
