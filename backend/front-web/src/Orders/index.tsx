import { useEffect, useState } from 'react';
import './styles.css';
import { fetchProducts } from '../api';
import StepsHeader from './StepsHeader';
import {OrderLocationdata, Product } from './types'; 
import ProductsList from './ProductsList';
import OrderLocation from './OrderLocation';



function Orders(){

    const [products, setProducts] = useState<Product[]>([]);
    
    const[orderLocation, setOrderLocation] = useState<OrderLocationdata>();

    useEffect(() => {
        fetchProducts()
        .then(response => setProducts(response.data))
        .catch(erro => console.log(erro))
    }, []); 

    
    return (
        <div className="orders-container">
            <StepsHeader />
            <ProductsList products={products}/>
            <OrderLocation onChangeLocation={location => setOrderLocation(location)} />
        </div>    
    )
}

export default Orders;