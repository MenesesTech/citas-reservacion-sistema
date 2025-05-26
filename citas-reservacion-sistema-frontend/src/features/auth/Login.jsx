// src/pages/LoginUsuarioPage.jsx
import React, { useState, useContext } from "react"; // <- Añadido useContext aquí
import { useNavigate, Link } from "react-router-dom";
import { loginUser } from "./authAPI";
import { AuthContext } from "../../context/AuthContext";
import "./Login.module.css";

export const LoginUsuarioPage = () => {
  const [dni, setDni] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const { login } = useContext(AuthContext); // <- Esto ya no rompe

  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const data = await loginUser(dni, password);

      // Actualiza el contexto con el usuario autenticado
      login(data.id, data.nombre);

      setLoading(false);
      navigate("/home");
    } catch (err) {
      setLoading(false);
      setError("Error al iniciar sesión. Verifique sus credenciales.");
    }
  };

  return (
    <>
      <div
        className="d-flex justify-content-center align-items-start"
        style={{
          paddingTop: "120px",
          minHeight: "100vh",
          backgroundColor: "var(--colors-primary)",
        }}
      >
        <div className="card shadow-lg p-4" style={{ width: "400px" }}>
          <div className="text-center mb-4">
            <h2 className="mb-3 h2">Iniciar Sesión</h2>
            <p className="text-muted" style={{ fontSize: 15 }}>
              Ingrese sus credenciales para continuar
            </p>
          </div>

          <form onSubmit={handleLogin}>
            <div className="form-group mb-3" style={{ textAlign: "left" }}>
              <label className="mb-1 label" htmlFor="dni">
                Ingresa el documento *
              </label>
              <input
                type="text"
                id="dni"
                className="form-control"
                placeholder="Ingrese su DNI"
                value={dni}
                onChange={(e) => setDni(e.target.value)}
                required
              />
            </div>

            <div className="form-group mb-4" style={{ textAlign: "left" }}>
              <label className="mb-1 label" htmlFor="password">
                Contraseña
              </label>
              <input
                type="password"
                id="password"
                className="form-control"
                placeholder="Ingrese su contraseña"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>

            {error && <div className="alert alert-danger">{error}</div>}

            <button
              type="submit"
              className="btn w-100 btn-dni"
              style={{
                background: "linear-gradient(90deg, #00bcd4 0%, #0097a7 100%)",
                color: "#fff",
                fontWeight: 600,
                borderRadius: 10,
                fontSize: 17,
                boxShadow: "0 2px 8px #b2ebf2",
              }}
              disabled={loading}
            >
              {loading ? (
                <span className="spinner-border spinner-border-sm" />
              ) : (
                "INGRESAR"
              )}
            </button>
          </form>

          <div className="text-center mt-3">
            <span>¿No tienes una cuenta? </span>
            <Link
              to="/registro-user"
              style={{ color: "#0097a7", fontWeight: 600 }}
            >
              Registro
            </Link>
          </div>
        </div>
      </div>
    </>
  );
};
