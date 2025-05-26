// src/context/AuthContext.jsx
import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [usuario, setUsuario] = useState(null);
  const [loading, setLoading] = useState(true); // importante para evitar parpadeos

  useEffect(() => {
    const id = localStorage.getItem("usuarioId");
    const nombre = localStorage.getItem("nombreUsuario");

    if (id && nombre) {
      setUsuario({ id, nombre });
    }
    setLoading(false); // ya terminó la verificación
  }, []);

  const login = (id, nombre) => {
    localStorage.setItem("usuarioId", id);
    localStorage.setItem("nombreUsuario", nombre);
    setUsuario({ id, nombre });
  };

  const logout = () => {
    localStorage.clear();
    setUsuario(null);
  };

  return (
    <AuthContext.Provider value={{ usuario, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
};
