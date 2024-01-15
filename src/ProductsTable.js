import './productsTable.css';

export default function ProductsTable({ products, searchText }) {
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