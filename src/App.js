import { useState } from 'react';


export default function ManiaDeMarias() {
    return (
        <>
            <Multiplica />
            <Multiplica />
        </>
    );
}


function Multiplica(){
    const [valor, setValor] = useState(2);

    function clicou(){
        setValor(valor * 2);
    }

    return(
        <>
            <h1>{valor}</h1>
            <button onClick={clicou}>multiplica por 2</button>
        </>
    );

}
