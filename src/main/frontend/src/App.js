import { useEffect, useState } from 'react';
import Header from './Header.js';
import ProductsTable from './pages/produtos/ProductsTable.js';

export default function ManiaDeMarias() {
    const url = "http://localhost:8080/product"
    const [products, setProducts] = useState([]);
    const [searchText, setSearchText] = useState('');

    const fetchProducts = async () => {
        try {
            const response = await fetch(url);
            const data = await response.json();
            setProducts(data);
        } catch (error) {
            console.error('Erro ao buscar produtos', error);
        }
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    return (
        <>
            <Header />
            <ProductsTable products={products} searchText={searchText} setSearchText={setSearchText} />
        </>
    );
}





