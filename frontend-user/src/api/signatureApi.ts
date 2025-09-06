import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const assinarDocumento = async (idDocumento: number, cpf: string, token: string) => {
    try {
        const response = await axios.post(`${API_URL}/assinar`, {
            idDocumento,
            cpf
        }, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
        return response.data;
    } catch (error) {
        console.error("Erro ao assinar documento:", error);
        throw error;
    }
};

export const validarAssinatura = async (idAssinatura: number, cpf: string) => {
    try {
        const response = await axios.post(`${API_URL}/validar-assinatura`, {
            idAssinatura,
            cpf
        });
        return response.data;
    } catch (error) {
        console.error("Erro ao validar assinatura:", error);
        throw error;
    }
};