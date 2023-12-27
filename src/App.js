import { useState } from 'react';


export default function ManiaDeMarias() {
    const [nome, setNome] = useState('');
    const [aux, setAux] = useState('');

    function handleClick() {
       setAux(nome);
    }

    return (
        <>
            <div className='header'>
                <Input nome={nome} setNome={setNome} onClick={handleClick} />
                <h1 className='mensagem'>Olá, {aux}</h1>
            </div>
        </>
    );
}


function Input({ nome, setNome, onClick }) {

    return (
        <>
            <div className='input'>
                <label>Nome: </label>
                <input
                    type='text'
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    placeholder='Insira seu nome'
                    required />
                <button onClick={onClick}>Submit</button>
            </div>
        </>
    );
}

function Multiplica() {
    const [valor, setValor] = useState(2);

    function clicou() {
        setValor(valor * 2);
    }

    return (
        <>
            <h1>{valor}</h1>
            <button onClick={clicou}>multiplica por 2</button>
        </>
    );

}


