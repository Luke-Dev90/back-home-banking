package com.lchalela.banking.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = -8179381135241241530L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String detail;
	
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@NotNull
	private Double amount;
	
	@NotEmpty
	private String destinyNumber;
	@NotEmpty
	private String senderNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDestinyNumber() {
		return destinyNumber;
	}

	public void setDestinyNumber(String destinyNumber) {
		this.destinyNumber = destinyNumber;
	}

	public String getSenderNumber() {
		return senderNumber;
	}

	public void setSenderNumber(String senderNumber) {
		this.senderNumber = senderNumber;
	}


	
}
