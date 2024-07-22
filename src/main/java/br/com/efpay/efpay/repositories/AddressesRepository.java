package br.com.efpay.efpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.efpay.efpay.entities.AddressEntity;
import br.com.efpay.efpay.entities.CepEntity;

public interface AddressesRepository extends JpaRepository<AddressEntity, Long> {
	@Query("SELECT a FROM AddressEntity a WHERE a.zipCode = :zipCode")
	Optional<AddressEntity> findAddressByCep(@Param("zipCode") CepEntity zipCode);
}
