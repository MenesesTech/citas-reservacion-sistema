import axios from "axios";

const API_URL = "http://localhost:8080/api/auth";

interface LoginRequestDTO {
  dni: string;
  password: string;
}

interface UsuarioResponseDTO {
  id: number;
  nombre: string;
  email: string;
}

class UsuarioService {
  login(dni: string, password: string) {
    const payload: LoginRequestDTO = { dni, password };
    return axios.post<UsuarioResponseDTO>(`${API_URL}/login`, payload);
  }
}

export default new UsuarioService();
