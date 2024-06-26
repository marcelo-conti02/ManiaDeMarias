import './header.css';
import { Link } from 'react-router-dom';

export default function Header() {
    return (
        <>
            <header id='header'>
                <Link className='link' to={'/'}>Produtos</Link>
                <Link className='link' to={'/cart'}>Carrinho</Link>
                <Link className='link' to={'/cart'}>Entre ou cadastre-se</Link>
            </header>
        </>
    );
}
