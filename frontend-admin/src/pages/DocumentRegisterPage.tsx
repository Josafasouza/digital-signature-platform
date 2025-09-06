import React, { useState } from 'react';
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/admin';

const DocumentRegisterPage: React.FC = () => {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [message, setMessage] = useState('');

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      setSelectedFile(event.target.files[0]);
    }
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      setMessage('Por favor, selecione um arquivo para upload.');
      return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
      const token = 'seu_jwt_token_aqui'; // Substitua pelo token JWT real do seu usuário
      const response = await axios.post(`${API_URL}/documentos/upload`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': `Bearer ${token}`
        }
      });
      setMessage(`Documento "${response.data.nome}" enviado com sucesso!`);
    } catch (error) {
      setMessage('Erro ao enviar o documento. Por favor, verifique o console para mais detalhes.');
      console.error('Erro de upload:', error);
    }
  };

  return (
    <div>
      <h1>Cadastrar Novo Documento</h1>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload} disabled={!selectedFile}>
        Upload
      </button>
      {message && <p>{message}</p>}
    </div>
  );
};

export default DocumentRegisterPage;