package br.com.addressmanager.jpa;

import br.com.addressmanager.AddressJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryJPA extends CrudRepository<AddressJPA, Long> {
}
