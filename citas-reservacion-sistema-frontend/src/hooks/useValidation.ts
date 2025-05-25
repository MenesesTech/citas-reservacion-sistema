import { useCallback, useState } from "react";
import type { UserData } from "../types/UserData";
import { validarDni } from "../services/reniecService";

interface ValidationState {
  loading: boolean;
  error: string | null;
}

export function useValidation() {
  const [state, setState] = useState<ValidationState>({
    loading: false,
    error: null,
  });

  const validate = useCallback(
    async (dni: string): Promise<UserData | null> => {
      setState({ loading: true, error: null });

      try {
        const data = await validarDni(dni);

        setState({ loading: false, error: null });

        const userData: UserData = {
          dni: data.dni,
          nombre: data.nombres,
          apellidoPaterno: data.apellidoPaterno,
          apellidoMaterno: data.apellidoMaterno,
          fechaNacimiento: "",
          telefono: "",
          email: "",
          genero: "",
          departamento: "",
          provincia: "",
          distrito: "",
          direccion: "",
          password: "",
        };

        return userData;
      } catch (err) {
        const error = (err as Error).message || "Error al validar el DNI";
        setState({ loading: false, error });
        return null;
      }
    },
    []
  );

  const setError = (error: string | null) => {
    setState((prev) => ({ ...prev, error }));
  };

  const clearError = () => {
    setError(null);
  };

  return { ...state, validate, setError, clearError };
}
