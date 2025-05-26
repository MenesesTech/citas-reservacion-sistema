import axios from 'axios';;

export const reniecResponse = {
    dni: "",
    nombres: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
}

const TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1hbmFuaXRvc3Rpa3Rva0BnbWFpbC5jb20ifQ.bSGT9xAhKpnLOYWv_Oy4dya5-8Y-9Go8qm6avFPexsg";

export const validarDNI = async (dni) => {
    try {
        const URL = "https://api.reniec.cloud/v1/dni/" + dni;
        const response = await axios.get(URL, { params: { token: TOKEN } });
        return response.data;
    } catch (error) {
        throw new Error("Error al consultar API de DNI");
    }
}