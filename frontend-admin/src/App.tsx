import React, { useState } from 'react';
import DocumentRegisterPage from './pages/DocumentRegisterPage.tsx';
import SignedDocumentsPage from './pages/SignedDocumentsPage.tsx';
import LoginPage from './pages/LoginPage.tsx';
import './Navbar.css';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [currentPage, setCurrentPage] = useState('login');

    const handleLoginSuccess = () => {
      setIsAuthenticated(true);
      setCurrentPage('register');
    };

    const handleLogout = () => {
      setIsAuthenticated(false);
      setCurrentPage('login');
    };

    const renderContent = () => {
      if (!isAuthenticated) {
        return <LoginPage onLoginSuccess={handleLoginSuccess} />;
      }

      switch (currentPage) {
        case 'register':
          return <DocumentRegisterPage />;
        case 'signed':
          return <SignedDocumentsPage />;
        default:
          return <DocumentRegisterPage />;
      }
    };

    return (
      <div className="app-container">
        {isAuthenticated && (
          <nav className="navigation-menu">
            <button onClick={() => setCurrentPage('register')}>Cadastrar Documento</button>
            <button onClick={() => setCurrentPage('signed')}>Documentos Assinados</button>
            <button onClick={handleLogout}>Sair</button>
          </nav>
        )}
        <div className="main-content">
          {renderContent()}
        </div>
      </div>
    );
}

export default App;