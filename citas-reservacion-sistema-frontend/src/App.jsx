import "./App.css";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import RegisterForm from "./pages/RegistroUsuarioPage";
import LoginPage from "./pages/LoginUsuarioPage";

function App() {
  return (
    <Router>
      <Routes>
        {/* Redirección por defecto a /login */}
        <Route path="/" element={<Navigate to="/login" replace />} />

        {/* Página de Login */}
        <Route path="/login" element={<LoginPage />} />

        {/* Página de Registro */}
        <Route path="/register" element={<RegisterForm />} />
      </Routes>
    </Router>
  );
}

export default App;
