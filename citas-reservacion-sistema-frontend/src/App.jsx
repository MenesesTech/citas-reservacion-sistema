// src/App.jsx
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { LoginUsuarioPage } from "./features/auth/Login";
import { HomePage } from "./features/home/home";
import { AuthProvider } from "./context/AuthContext";
import { PrivateLayout } from "./layouts/PrivateLayout";
import "./App.css";
import { CitaMedicaOptions } from "./features/citas/pages/CitaMedicaPage";
import { CitaMedicaBuscarPor } from "./features/citas/pages/CitaMedicaBuscarPor";
import { SeleccionarEspecialidadEsp } from "./features/citas/pages/Especialidad/Paso1";
import { SeleccionarDoctorEsp } from "./features/citas/pages/Especialidad/Paso2";
import { SeleccionarHoraFechaEsp } from "./features/citas/pages/Especialidad/Paso3";
import MiCuenta from "./features/pacientes/MiCuenta";
import { CrearCitaPorEsp } from "./features/citas/pages/Especialidad/Paso4";
import { RegistroUsuario } from "./features/registroUsuario/RegistroUsuarioPage";

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          {/* Página pública */}
          <Route path="/login" element={<LoginUsuarioPage />} />
          <Route path="/registro-user" element={<RegistroUsuario />} />
          {/* Todas las demás rutas están protegidas */}
          <Route element={<PrivateLayout />}>
            <Route path="/home" element={<HomePage />} />
            <Route path="/Mi-cuenta" element={<MiCuenta />} />
            <Route path="/crear-cita-medica" element={<CitaMedicaOptions />} />
            <Route
              path="/crear-cita-medica/buscar-por"
              element={<CitaMedicaBuscarPor />}
            />
            <Route
              path="/crear-cita-medica/buscar-por/especialidad/paso-1"
              element={<SeleccionarEspecialidadEsp />}
            />
            <Route
              path="/crear-cita-medica/buscar-por/especialidad/paso-2"
              element={<SeleccionarDoctorEsp />}
            />
            <Route
              path="/crear-cita-medica/buscar-por/especialidad/paso-3"
              element={<SeleccionarHoraFechaEsp />}
            />
            <Route
              path="/crear-cita-medica/buscar-por/especialidad/paso-4"
              element={<CrearCitaPorEsp />}
            />
            <Route path="/" element={<HomePage />} />
          </Route>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
