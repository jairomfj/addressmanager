package br.com.addressmanager.jpa;

import br.com.addressmanager.AddressJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepositoryJPA extends CrudRepository<AddressJPA, Long> {
    List<AddressJPA> findAllByUserId(Long userId);
}
