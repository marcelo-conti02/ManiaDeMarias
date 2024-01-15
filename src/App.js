import { useState } from 'react';
import Header from './Header.js';
import ProductsTable from './ProductsTable.js';

const produtos = [{ name: 'Pijama bolinha', id: 1, type: 'pijama', valor: 'R$40.00', imgUrl: 'img/pijamaBolinha1.jpg' },
{ name: 'Bermuda azul', id: 2, type: 'bermuda', valor: 'R$50.00', imgUrl: 'img/bermudaAzul.jpg' },
{ name: 'Shorts listrado', id: 3, type: 'pijama', valor: 'R$60.00', imgUrl: 'img/shortsListrado.jpg' }
];


export default function ManiaDeMarias() {
    const [searchText, setSearchText] = useState('');

    return (
        <>
            <Header setSearchText={setSearchText} />
            <ProductsTable products={produtos} searchText={searchText}/>
        </>
    );
}





