import {MantineProvider } from '@mantine/core';
import { HeaderHome } from './Components/HeaderMain.tsx'
import { FooterHome } from './Components/FooterMain.tsx'
import { BrowserRouter} from 'react-router'
import Auth from './Pages/Auth/Index.tsx';
import './App.css'
import './index.css'
import { useEffect, useState } from 'react';
import LoggedInFlow from './Pages/App/Index.tsx';

function App() {

  const [isLoggedIn, SetIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      SetIsLoggedIn(true);
    }
  }, [])

  return (
    <>
    <header>
        <HeaderHome />
    </header>
    <main className='tw-img-backgroundmain'>
      <MantineProvider>
      <BrowserRouter>
        {isLoggedIn ? <LoggedInFlow /> : <Auth />}
      </BrowserRouter>
      </MantineProvider>
    </main>
    <footer>
        <FooterHome />
    </footer>
    </>
  )
}

export default App
