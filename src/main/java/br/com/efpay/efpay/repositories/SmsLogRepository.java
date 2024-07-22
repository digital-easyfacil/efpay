package br.com.efpay.efpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.efpay.efpay.entities.SmsLogEntity;

public interface SmsLogRepository extends JpaRepository<SmsLogEntity, Long>{

}
