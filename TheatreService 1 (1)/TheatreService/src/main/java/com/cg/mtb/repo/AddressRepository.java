
package com.cg.mtb.repo;
import com.cg.mtb.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> 
{
	 
}
