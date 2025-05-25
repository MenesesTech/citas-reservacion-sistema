import React, { Component } from "react";
import type { ChangeEvent, FormEvent } from "react";

import UsuarioService from "../services/usuarioService";

// Definimos el tipo del estado del componente
interface LoginPageState {
  dni: string;
  password: string;
  error: string | null;
  loading: boolean;
}

// Si tuviera props, las defines aquí (ahora es vacío)
interface LoginPageProps {}

class LoginPage extends Component<LoginPageProps, LoginPageState> {
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
        console.log("Login exitoso:", res.data);
        alert(`Bienvenido ${res.data.nombre || "usuario"}`);
        this.setState({ loading: false });
      })
      .catch((error) => {
        this.setState({
          loading: false,
          error: "Credenciales inválidas",
        });
      });
  }

  render() {
    return (
      <>
        <div className="container d-flex justify-content-center align-items-center vh-100">
          <div
            className="card p-4 shadow"
            style={{ maxWidth: "400px", width: "100%" }}
          >
            <h2 className="text-center mb-4">Iniciar Sesión</h2>

            {this.state.error && (
              <div className="alert alert-danger" role="alert">
                {this.state.error}
              </div>
            )}

            <form onSubmit={this.handleLogin}>
              <div className="form-group mb-3">
                <label htmlFor="dni">DNI:</label>
                <input
                  type="text"
                  id="dni"
                  placeholder="Ingrese DNI"
                  className="form-control"
                  value={this.state.dni}
                  onChange={this.changeDni}
                  required
                />
              </div>

              <div className="form-group mb-3">
                <label htmlFor="password">Contraseña:</label>
                <input
                  type="password"
                  id="password"
                  placeholder="Ingrese contraseña"
                  className="form-control"
                  value={this.state.password}
                  onChange={this.changePassword}
                  required
                />
              </div>

              <button
                type="submit"
                className="btn btn-primary w-100"
                disabled={this.state.loading}
              >
                {this.state.loading ? "Validando..." : "Ingresar"}
              </button>
            </form>
          </div>
        </div>
      </>
    );
  }
}

export default LoginPage;
