import axios from 'axios';

const API_URL = "http://localhost:8080/api/auth/login";

/**
 * 
 * @param {string} dni 
 * @param {string} password 
 * @returns {Promise<Object>} Datos del usuario autenticado
 */
export const loginUser = async (dni, password) => {
    try {
        const response = await axios.post(API_URL, { dni, password });
        if (response.status === 200) {
            const usuario = response.data;
            // Guarda el ID del usuario en localStorage
            localStorage.setItem("usuarioId", usuario.id);
            localStorage.setItem("nombreUsuario", usuario.nombre);
            return usuario;
        } else {
            throw new Error("Credenciales inválidas");
        }
    } catch (error) {
        throw new Error("Error al iniciar sesión");
    }
}