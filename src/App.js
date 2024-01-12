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

function FilterableProductTable({products}) {
    const [searchText, setSearchText] = useState('');

    return (
        <div>
            <SearchBar setSearchText={setSearchText}/>
            <ProductsTable products={products} searchText={searchText}/>
        </div>
    );
}

function SearchBar({setSearchText}) {

    const { 
        register,
        handleSubmit,
        formState:{ errors }
    } = useForm({
        defaultValues:{
            filterText: ''
        }
    });


    return (
        <form id='searchBar' onSubmit={handleSubmit(data => setSearchText(data.filterText))}>
            <b>Pesquisa:</b>
            <input
                type='text'
                id='searchInput' 
                placeHolder='Digite um produto'
                {...register('filterText', { required: 'Preencha esse campo!' })} />
            <button type='submit' id='searchButton'>🔍︎</button>
            <p>{errors.filterText?.message}</p>
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




/*function SearchBar({ onFilterTextChange }) {

    function handleSubmit(e) {
        onFilterTextChange(e.target[0].value);
    }

    return (
        <form id='searchBar' onSubmit={e => {
            e.preventDefault();
            handleSubmit(e);
        }}>
            <b>Pesquisa:</b>
            <input
                type='text'
                id='searchInput'
                placeHolder='Digite um produto'
                required />
            <button type='submit' id='searchButton'>🔍︎</button>
        </form>
    );
}*/
