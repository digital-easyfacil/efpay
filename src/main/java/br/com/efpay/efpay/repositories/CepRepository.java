package br.com.efpay.efpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.efpay.efpay.entities.CepEntity;

public interface CepRepository extends JpaRepository<CepEntity, String>{

}
