import './header.css';
import { Link } from 'react-router-dom';

export default function Header() {
    return (
        <>
            <header id='header'>
                <Link className='link' to={'/'}>Produtos</Link>
                <Link className='link' to={'/info'}>Sobre</Link>
                <Link className='link' to={'/contact'}>Fale conosco!</Link>
                <Link className='link' to={'/contact'}>Entre ou cadastre-se</Link>
            </header>
            <h1 id='titulo'>Mania de Maria</h1>
        </>
    );
}
