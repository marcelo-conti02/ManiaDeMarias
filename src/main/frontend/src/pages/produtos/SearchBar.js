import { useForm } from 'react-hook-form';

export default function SearchBar({ setSearchText }) {

    const {
        register,
        handleSubmit,
        reset,
        formState: { isSubmitted }
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