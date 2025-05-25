import {MantineProvider } from '@mantine/core';
import { HeaderHome } from './Components/HeaderMain.tsx'
import { FooterHome } from './Components/FooterMain.tsx'
import { BrowserRouter} from 'react-router'
import Auth from './Pages/Auth/Index.tsx';
import './App.css'
import './index.css'


function App() {
  return (
    <>
    <header>
        <HeaderHome />
    </header>
    <main className='tw-img-backgroundmain'>
      <MantineProvider>
      <BrowserRouter>
        <div><Auth/></div>
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
