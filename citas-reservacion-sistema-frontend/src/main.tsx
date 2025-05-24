import { HeaderHome } from './Components/HeaderMain.tsx'
import { FooterHome } from './Components/FooterMain.tsx'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import './index.css'


createRoot(document.getElementById('root')!).render(

    <>
    <header>
        <HeaderHome />
    </header>
    <main className='tw-img-backgroundmain'>
        <App />
    </main>
    <footer>
        <FooterHome />
    </footer>
    </>

)
