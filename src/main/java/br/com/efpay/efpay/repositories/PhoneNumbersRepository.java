package br.com.efpay.efpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.efpay.efpay.entities.PhoneNumberEntity;

public interface PhoneNumbersRepository extends JpaRepository<PhoneNumberEntity, Long> {
	@Query("SELECT p FROM PhoneNumberEntity p WHERE p.number = :number")
	Optional<PhoneNumberEntity> findPhoneNumberByNumber(@Param("number") String number);
}
