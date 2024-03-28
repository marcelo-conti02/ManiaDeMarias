import './header.css';
import { Link } from 'react-router-dom';

export default function Header() {
    return (
        <>
            <header id='header'>
                <Link to={'/'}>Produtos</Link>
                <Link to={'/info'}>Sobre</Link>
                <Link to={'/contact'}>Fale conosco!</Link>
            </header>
        </>
    );
}
