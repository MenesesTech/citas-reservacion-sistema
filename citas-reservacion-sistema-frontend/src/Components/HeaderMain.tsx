import {Logo, Intra} from './Images'
import './Style.css'

export function HeaderHome () {
    return(
        <div>
            <div className='tw-line'></div>
            <div className='tw-header-main'>
                <div> <Logo /> </div>
                <div> <Intra/> </div>
            </div>
            <div className='tw-line'></div>
        </div>
    )
}

