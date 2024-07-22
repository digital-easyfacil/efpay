package br.com.efpay.efpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.efpay.efpay.entities.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, Long>{
	@Query("SELECT user FROM UserEntity user WHERE user.cpf = :cpf")
	Optional<UserEntity> findUserByCpf(@Param("cpf") String cpf);
	
	@Query("SELECT user FROM UserEntity user WHERE user.cnpj = :cnpj")
	Optional<UserEntity> findUserByCnpj(@Param("cnpj") String cnppj);
	
	@Query("SELECT user FROM UserEntity user WHERE user.email = :email")
	Optional<UserEntity> findUserByEmail(@Param("email") String email);	
}
