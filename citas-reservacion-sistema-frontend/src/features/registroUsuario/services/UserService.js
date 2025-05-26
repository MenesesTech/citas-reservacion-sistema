// services/UserService.js
import axios from "axios";

const API_BASE_URL = 'http://localhost:8080/api';
const TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1hbmFuaXRvc3Rpa3Rva0BnbWFpbC5jb20ifQ.bSGT9xAhKpnLOYWv_Oy4dya5-8Y-9Go8qm6avFPexsg";
const RENIEC_API_URL = "https://dniruc.apisperu.com/api/v1";

// Interfaz para la respuesta de la API de DNI
export const validarDni = async (dni) => {
  try {
    const url = `${RENIEC_API_URL}/dni/${dni}`;
    const response = await axios.get(url, {
      params: { token: TOKEN },
    });
    return response.data;
  } catch (error) {
    console.error('Error al consultar API de DNI:', error);
    throw new Error("Error al consultar API de DNI");
  }
};

// Registrar nuevo usuario
export const registrarUsuario = async (userData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/users`, userData);

    if (response.status === 200 || response.status === 201) {
      return response.data;
    }
    throw new Error("Error al registrar el usuario");
  } catch (error) {
    if (error.response && error.response.data && error.response.data.message) {
      throw new Error(error.response.data.message);
    }
    throw new Error(error.message || "Error en el servidor");
  }
};

// Validaciones
export const validarEmail = (email) => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
};

export const validarTelefono = (telefono) => {
  const telefonoRegex = /^[0-9]{9}$/;
  return telefonoRegex.test(telefono);
};

export const validarDNI = (dni) => {
  const dniRegex = /^[0-9]{8}$/;
  return dniRegex.test(dni);
};

export const validarPassword = (password) => {
  return password.length >= 6;
};