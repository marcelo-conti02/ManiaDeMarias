import { useState } from 'react';
import { useForm } from 'react-hook-form';

const produtos = [{ name: 'Pijama bolinha', id: 1, type: 'pijama', valor: 'R$40.00', imgUrl: 'img/pijamaBolinha1.jpg' },
{ name: 'Bermuda azul', id: 2, type: 'bermuda', valor: 'R$50.00', imgUrl: 'img/bermudaAzul.jpg' },
{ name: 'Shorts listrado', id: 3, type: 'pijama', valor: 'R$60.00', imgUrl: 'img/shortsListrado.jpg' }
];


export default function ManiaDeMarias() {
    return (
        <>
            <FilterableProductTable products={produtos} />
        </>
    );
}

function FilterableProductTable({ products }) {
    const [searchText, setSearchText] = useState('');

    return (
        <div>
            <Header setSearchText={setSearchText} />
            <ProductsTable products={products} searchText={searchText} />
        </div>
    );
}

function Header({ setSearchText }) {
    return (
        <>
            <div id='header'>
                <SearchBar setSearchText={setSearchText} />
            </div>
            <h1 id='titulo'>Mania de Marias</h1>
        </>
    );
}

function SearchBar({ setSearchText }) {

    const {
        register,
        handleSubmit,
        reset,
        formState: { errors, isSubmitted }
    } = useForm({
        defaultValues: {
            filterText: ''
        }
    });

    if (isSubmitted) {
        reset(undefined, { keepErrors: true });
    }

    return (
        <form id='searchBar' onSubmit={handleSubmit(data => setSearchText(data.filterText))}>
            <b>Pesquisa:</b>
            <input
                type='text'
                id='searchInput'
                placeHolder='Digite um produto'
                {...register('filterText')} />
            <button type='submit' id='searchButton'>🔍︎</button>
        </form>
    );
}

function ProductsTable({ products, searchText }) {
    const productsOnSearch = [];

    products.forEach((product) => {
        if (product.name.toLowerCase().indexOf(searchText.toLowerCase()) === -1) {
            return;
        }
        productsOnSearch.push(
            <ProductOutput name={product.name} imgUrl={product.imgUrl} valor={product.valor} />
        );
    });
    return (
        <table className='productsTable'>{productsOnSearch}</table>
    );
}

function ProductOutput({ name, imgUrl, valor }) {
    return (
        <td className='tableCell'>
            <h1>{name}</h1>
            <img
                className='productImg'
                src={imgUrl}
                alt={name}
            />
            <br />
            <b>Preço: {valor}</b>
        </td>
    );
}



