package br.com.efpay.efpay.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String cpf;
	private String cnpj;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<PhoneNumberEntity> phones = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<AddressEntity> address = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "status_id")
	private UserStatusEntity userStatus;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<UserTokenEntity> token = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<SmsLogEntity> smsLog = new ArrayList<>();	

	public UserEntity() {
	}

	public UserEntity(Long id, String email, String firstName, String lastName, String password, String cpf,
			String cnpj, List<PhoneNumberEntity> phones, List<AddressEntity> address) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.phones = phones;
		this.address = address;
		associations();
	}

	public UserEntity(String email, String firstName, String lastName, String password, String cpf, String cnpj,
			List<PhoneNumberEntity> phones, List<AddressEntity> address) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.phones = phones;
		this.address = address;
		associations();
	}

	private void associations() {
		for (PhoneNumberEntity phone : phones) {
			phone.setUser(this);
		}
		for (AddressEntity address : address) {
			address.setUser(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<PhoneNumberEntity> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneNumberEntity> phones) {
		this.phones = phones;
	}

	public List<AddressEntity> getAddress() {
		return address;
	}

	public void setAddress(List<AddressEntity> address) {
		this.address = address;
	}
	
	public UserStatusEntity getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusEntity userStatus) {
		this.userStatus = userStatus;
	}	

	public List<UserTokenEntity> getToken() {
		return token;
	}

	public void setToken(List<UserTokenEntity> token) {
		this.token = token;
	}	

	public List<SmsLogEntity> getSmsLog() {
		return smsLog;
	}

	public void setSmsLog(List<SmsLogEntity> smsLog) {
		this.smsLog = smsLog;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(id, other.id);
	}

}
