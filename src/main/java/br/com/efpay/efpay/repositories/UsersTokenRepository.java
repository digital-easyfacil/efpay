package br.com.efpay.efpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.efpay.efpay.entities.UserTokenEntity;

public interface UsersTokenRepository extends JpaRepository<UserTokenEntity, Long> {
	@Query("SELECT token FROM UserTokenEntity token WHERE token.token = :token")
	Optional<UserTokenEntity> findToken(@Param("token") String token);
}
