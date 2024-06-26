import './productsTable.css';
import SearchBar from './SearchBar.js'

export default function ProductsTable({ products, searchText, setSearchText }) {
    const productsOnSearch = [];

    products.forEach((product) => {
        if (product.productName.toLowerCase().indexOf(searchText.toLowerCase()) === -1) {
            return;
        }
        productsOnSearch.push(
            <ProductOutput key={product.id} name={product.productName} valor={product.productPrice} />
        );
    });
    return (
        <>
            <h1 id='titulo'>Mania de Maria</h1>
            <SearchBar setSearchText={setSearchText} />
            <table className='productsTable'>{productsOnSearch}</table>
        </>
    );
}

function ProductOutput({ name, valor }) {
    return (
        <>
            <td className='tableCell'>
                <h1>{name}</h1>
                <br />
                <b>Preço: {valor}</b>
                <br />
                <button id='addButton'>Adcionar ao carrinho</button>
            </td>
        </>
    );
}