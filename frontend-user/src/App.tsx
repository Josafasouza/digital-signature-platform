import React, { useState } from 'react';
import SignaturePage from './pages/SignaturePage.tsx';
import LoginPage from './pages/LoginPage.tsx';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [currentPage, setCurrentPage] = useState('login');

    const handleLoginSuccess = () => {
      setIsAuthenticated(true);
      setCurrentPage('signature');
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
        case 'signature':
          return <SignaturePage />;
        default:
          return <SignaturePage />;
      }
    };

    return (
      <div className="flex flex-col min-h-screen">
        {isAuthenticated && (
          <nav className="navigation-menu">
            <button onClick={handleLogout}>Sair</button>
          </nav>
        )}
        <div className="flex-1 flex items-center justify-center">
          {renderContent()}
        </div>
      </div>
    );
}

export default App;