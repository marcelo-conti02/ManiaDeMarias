import { useState } from 'react';

const produtos = [{ name: 'Pijama bolinha', id: 1, type: 'pijama', valor: 40, imgUrl: 'img/pijamaBolinha1.jpg' },
{ name: 'Bermuda azul', id: 2, type: 'bermuda', valor: 50, imgUrl: 'img/bermudaAzul.jpg' },
{ name: 'Shorts listrado', id: 3, type: 'pijama', valor: 60, imgUrl: 'img/shortsListrado.jpg' }
];


export default function ManiaDeMarias() {
    return (
        <>
            <Search />
            <ProductsTable produtos={produtos} />
        </>
    );
}

function FilterableProductTable({ products }) {
    const [filterText, setFilterText] = useState('');

    return (
        <div>
            <SearchBar
                filterText={filterText} />
            <ProductsTable
                products={products}
                filterText={filterText} />
        </div>
    );
}

function SearchBar() {
    function handleSubmit() {
        return(null);
    }
    return (
        <form id='searchBar' onSubmit={e => {
            e.preventDefault();
            handleSubmit();
        }}>
            <b>Pesquisa:</b>
            <input id='searchInput' placeHolder='Digite um produto' value={filterText} />
            <button id='searchButton'>🔍︎</button>
        </form>
    );
}

function ProductsTable({ products, filterText }) {
    const table = products.map(product =>
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
        <table className='productsTable'>{table}</table>
    );
}





