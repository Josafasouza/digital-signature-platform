import React from 'react';
import { assinarDocumento } from '../api/signatureApi';

interface SignaturePageProps {
    documentoId: number;
}
const SignaturePage: React.FC = () => {
      const [documentoId, setDocumentoId] = React.useState<number>(1);
      const [cpf, setCpf] = React.useState<string>('12345678900');
      const [message, setMessage] = React.useState<string>('');

      const handleSignature = async () => {
        try {
          const token = 'seu_jwt_token_aqui';
          const novaAssinatura = await assinarDocumento(documentoId, cpf, token);
          setMessage(`Documento assinado! Hash: ${novaAssinatura.hashAssinatura}`);
        } catch (error) {
          setMessage('Erro ao assinar o documento.');
        }
      };
      return (
        <div>
          <h1>Documento para Assinatura</h1>
          <p>Exibindo o documento...</p>
          <button onClick={handleSignature}>Assinar Documento</button>
          {message && <p>{message}</p>}
        </div>
      );
};
export default SignaturePage;
