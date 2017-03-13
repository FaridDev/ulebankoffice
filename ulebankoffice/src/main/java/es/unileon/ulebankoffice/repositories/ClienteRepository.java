/**
 * 
 */
package es.unileon.ulebankoffice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.unileon.ulebankoffice.service.Cliente;

/**
 * @author Razvan Raducu
 *
 */
@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String>{

		List<Cliente> findByDni(String dni);
	
}
