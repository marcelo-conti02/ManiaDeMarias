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
            <SearchBar setSearchText={setSearchText} />
            <div className='productsTable'>{productsOnSearch}</div>
        </>
    );
}

function ProductOutput({ name, valor }) {
    return (
        <>
            <div className='tableCell'>
                <h1>{name}</h1>
                <br />
                <b>Preço: {valor}</b>
            </div>
        </>
    );
}