import { HeaderHome } from './Components/HeaderMain.tsx'
import { FooterHome } from './Components/FooterMain.tsx'
import { BrowserRouter} from 'react-router'
import Auth from './Pages/Index.tsx'
import './App.css'
import './index.css'


function App() {
  return (
    <>
    <header>
        <HeaderHome />
    </header>
    <main className='tw-img-backgroundmain'>
      <BrowserRouter>
        <div><Auth/></div>
      </BrowserRouter>
    </main>
    <footer>
        <FooterHome />
    </footer>
    </>
  )
}

export default App
