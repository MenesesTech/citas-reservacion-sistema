import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegistroUsuarioPage";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        {/* Redirección de la raíz a /login */}
        <Route path="/" element={<Navigate to="/login" replace />} />

        {/* Ruta para login */}
        <Route path="/login" element={<LoginPage />} />

        {/* Ruta para registro */}
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
    </Router>
  );
};

export default App;
