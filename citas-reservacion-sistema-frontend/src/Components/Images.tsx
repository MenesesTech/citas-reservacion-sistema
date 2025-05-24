import DoctorLogo from '../assets/doctor.svg'
import Cita from '../assets/fileicon.svg'
import './Style.css'

export function Logo () {
    return(
        <div>
            <img className='tw-img-logo' src='https://images.weare365.io/filters:format(.webp)/1920x0/PE_Mifarma_299e2f303a.png'/>
        </div>
    )
}

export function Intra () {
    return(
        <div className='tw-header-main'>
            <div className='tw-header-main'>
                <img className='tw-svg-logo-sime'  src={Cita}/> <h1 className='tw-text-medium-header'></h1>
            </div>
            <div>
                <img className='tw-svg-logo-sime'  src={DoctorLogo} />
            </div>
        </div>
    )
}