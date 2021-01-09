import { useEffect, useState } from 'react';
import './styles.css';
import { fetchProducts } from '../api';
import { Product } from './types';
import StepsHeader from './StepsHeader';
import ProductsList from './ProductsList';


function Orders(){

    const [products, setProducts] = useState<Product[]>([]);
    

    useEffect(() => {
        fetchProducts()
        .then(response => setProducts(response.data))
        .catch(erro => console.log(erro))
    }, []); 

    
    return (
        <div className="orders-container">
            <StepsHeader />
            <ProductsList products={products}/>
        </div>    
    )
}

export default Orders;