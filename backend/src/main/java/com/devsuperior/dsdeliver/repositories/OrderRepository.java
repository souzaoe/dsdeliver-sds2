package com.devsuperior.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsdeliver.entities.Order;

// repositorey é os objetos que fazem acesso ao banco de dados

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	// metodo para retornar os pedidos pendentes do mais antigo para o mais moderno
	
	// a linguagem aqui não é sql mais sim em linguagem da jpa que é o jpql, para incluir no Query a sua consulta // apelido do seu objeto é obj
	// o from tem que ser igual ao nome da sua classe criada Order, a lista é products 
	@Query("SELECT DISTINCT obj FROM Order obj JOIN FETCH obj.products "
			+ " WHERE obj.status = 0 ORDER BY obj.moment ASC")
	List<Order> findOrdersWithProducts(); 
}
