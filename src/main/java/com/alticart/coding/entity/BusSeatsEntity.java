package com.alticart.coding.entity;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "busseats")
@Getter
@Setter

public class BusSeatsEntity implements Serializable{
	@Id
	@Column(length = 10)
	private String seatid ;
	
	private int status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="busnumber",referencedColumnName = "busnumber")
	@JsonIgnore
	private BusEntity bus;


}
