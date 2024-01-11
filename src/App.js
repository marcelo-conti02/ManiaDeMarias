import { useState } from 'react';

const produtos = [{ name: 'Pijama bolinha', id: 1, type: 'pijama', valor: 40, imgUrl: 'img/pijamaBolinha1.jpg' },
{ name: 'Bermuda azul', id: 2, type: 'bermuda', valor: 50, imgUrl: 'img/bermudaAzul.jpg' },
{ name: 'Shorts listrado', id: 3, type: 'pijama', valor: 60, imgUrl: 'img/shortsListrado.jpg' }
];


export default function ManiaDeMarias() {
    return (
        <>
            <Search />
            <table id='productsTable'>
                <ProductsTable produtos={produtos} />
            </table>
        </>
    );
}

function Search() {
    return (
        <div id='searchBar'>
            <label>Pesquisa</label>
            <input className='searchInput' placeHolder='Digite um produto' />
            <button className='searchInput'>🔍︎</button>
        </div>
    );
}

function ProductsTable({ produtos }) {
    const table = produtos.map(product =>
        <td className='tableCell'>
            <h1>{product.name}</h1>
            <img
                className='productImg'
                src={product.imgUrl}
                alt={product.name}
            />
            <br />
            <b>Preço: R${product.valor}</b>
        </td>
    );
    return (
        <>{table}</>
    );
}





