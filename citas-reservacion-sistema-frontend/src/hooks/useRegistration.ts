import { useCallback, useState } from "react";
import type { UserData } from "../types/UserData";

interface RegistrationState {
  loading: boolean;
  error: string | null;
  success: string | null;
}

export const useRegistration = () => {
  const [state, setState] = useState<RegistrationState>({
    loading: false,
    error: null,
    success: null,
  });

  const register = useCallback(async (userData: UserData): Promise<boolean> => {
    setState({ loading: true, error: null, success: null });
    try {
      // await axios.post("http://localhost:8080/api/usuarios", userData);
      setState({
        loading: false,
        success: "Usuario registrado exitosamente",
        error: null,
      });
      return true;
    } catch (err) {
      setState({
        loading: false,
        error: "Error al registrar usuario.",
        success: null,
      });
      return false;
    }
  }, []);

  const clearMessages = useCallback(() => {
    setState((prev) => ({ ...prev, error: null, success: null }));
  }, []);

  return { ...state, register, clearMessages };
};
