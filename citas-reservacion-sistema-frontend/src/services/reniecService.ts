import axios from "axios";

export interface ApiPeruDniResponse {
  success: boolean;
  dni: string;
  nombres: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
}

const TOKEN =
  "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1hbmFuaXRvc3Rpa3Rva0BnbWFpbC5jb20ifQ.bSGT9xAhKpnLOYWv_Oy4dya5-8Y-9Go8qm6avFPexsg";

const API_URL = "https://dniruc.apisperu.com/api/v1";

export const validarDni = async (dni: string): Promise<ApiPeruDniResponse> => {
  try {
    const url = `${API_URL}/dni/${dni}`;
    const response = await axios.get<ApiPeruDniResponse>(url, {
      params: { token: TOKEN },
    });

    return response.data;
  } catch (error) {
    throw new Error("Error al consultar API de DNI");
  }
};
