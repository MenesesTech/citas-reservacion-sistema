import React, { Component } from "react";
import type { ChangeEvent, FormEvent } from "react";
import { Link } from "react-router-dom";
import UsuarioService from "../services/usuarioService";

// Estado del componente
interface LoginPageState {
  dni: string;
  password: string;
  error: string | null;
  loading: boolean;
}

interface LoginPageProps {}

class LoginUsuarioPage extends Component<LoginPageProps, LoginPageState> {
  constructor(props: LoginPageProps) {
    super(props);
    this.state = {
      dni: "",
      password: "",
      error: null,
      loading: false,
    };

    this.changeDni = this.changeDni.bind(this);
    this.changePassword = this.changePassword.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
  }

  changeDni(event: ChangeEvent<HTMLInputElement>) {
    this.setState({ dni: event.target.value });
  }

  changePassword(event: ChangeEvent<HTMLInputElement>) {
    this.setState({ password: event.target.value });
  }

  handleLogin(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    this.setState({ loading: true, error: null });

    UsuarioService.login(this.state.dni, this.state.password)
      .then((res) => {
        alert(`Bienvenido ${res.data.nombre || "usuario"}`);
        this.setState({ loading: false });
        // Aquí puedes redirigir a otra vista usando navigate si usas React Router v6+
      })
      .catch(() => {
        this.setState({
          loading: false,
          error: "Credenciales inválidas",
        });
      });
  }

  render() {
    return (
      <div className="d-flex justify-content-center align-items-center vh-100">
        <div
          className="card shadow-lg p-4"
          style={{
            maxWidth: 400,
            width: "100%",
            borderRadius: 20,
            border: "none",
            background: "#fff",
          }}
        >
          <div className="text-center mb-4">
            <svg
              width="56"
              height="56"
              viewBox="0 0 56 56"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              style={{ marginRight: 16 }}
            >
              <circle cx="28" cy="28" r="28" fill="#E3F6FC" />
              <rect
                x="23"
                y="12"
                width="10"
                height="32"
                rx="5"
                fill="#00BCD4"
              />
              <rect
                x="12"
                y="23"
                width="32"
                height="10"
                rx="5"
                fill="#00BCD4"
              />
              <path
                d="M28 38c-3-2.5-8-5.5-8-10a5 5 0 0 1 8-4 5 5 0 0 1 8 4c0 4.5-5 7.5-8 10z"
                fill="#FF6F91"
                stroke="#fff"
                strokeWidth="2"
              />
            </svg>
            <h2 style={{ color: "#0097a7", fontWeight: 700 }}>
              Iniciar Sesión
            </h2>
            <p className="text-muted" style={{ fontSize: 15 }}>
              Bienvenido al sistema de citas de la clínica
            </p>
          </div>

          {this.state.error && (
            <div className="alert alert-danger py-2" role="alert">
              {this.state.error}
            </div>
          )}

          <form onSubmit={this.handleLogin}>
            <div className="form-group mb-3">
              <label htmlFor="dni" style={{ fontWeight: 500 }}>
                DNI
              </label>
              <input
                type="text"
                id="dni"
                placeholder="Ingrese su DNI"
                className="form-control"
                value={this.state.dni}
                onChange={this.changeDni}
                required
                style={{
                  borderRadius: 10,
                  borderColor: "#b2ebf2",
                  background: "#f1fafd",
                }}
              />
            </div>

            <div className="form-group mb-4">
              <label htmlFor="password" style={{ fontWeight: 500 }}>
                Contraseña
              </label>
              <input
                type="password"
                id="password"
                placeholder="Ingrese su contraseña"
                className="form-control"
                value={this.state.password}
                onChange={this.changePassword}
                required
                style={{
                  borderRadius: 10,
                  borderColor: "#b2ebf2",
                  background: "#f1fafd",
                }}
              />
            </div>

            <button
              type="submit"
              className="btn w-100"
              style={{
                background: "linear-gradient(90deg, #00bcd4 0%, #0097a7 100%)",
                color: "#fff",
                fontWeight: 600,
                borderRadius: 10,
                fontSize: 17,
                boxShadow: "0 2px 8px #b2ebf2",
              }}
              disabled={this.state.loading}
            >
              {this.state.loading ? "Validando..." : "Ingresar"}
            </button>
          </form>

          <div className="text-center mt-3">
            <span>¿No tienes una cuenta? </span>
            <Link to="/register" style={{ color: "#0097a7", fontWeight: 600 }}>
              Registro
            </Link>
          </div>
        </div>
      </div>
    );
  }
}

export default LoginUsuarioPage;
