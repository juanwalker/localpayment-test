package com.localpayment.domain.entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties({"person","password"})
@Entity(name="User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name="PERSON_ID",referencedColumnName="id")
	@JsonProperty("person")
	private Person person;
	@Column(nullable = false)
	@JsonProperty("password")
	private String password;

	public User() {

	}

	public User(Integer id, String name, Person person, String password){
		this.id = id;
		this.name = name;
		this.person = person;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
/*
	private Producto producto;
	private Date fechaSolicitudGarantia;
	private Date fechaFinGarantia;
	private double precioGarantia;
	private String nombreCliente;
	
	public GarantiaExtendida(Producto producto,String nombreCliente) {
		this.fechaSolicitudGarantia = new Date();
		this.producto = producto;
		this.nombreCliente = nombreCliente;
		this.calcularPrecioGarantia();
		this.calcularFechaFin();

	}

	public GarantiaExtendida(Producto producto, Date fechaSolicitudGarantia, Date fechaFinGarantia, double precioGarantia,
			String nombreCliente) {
		this.producto = producto;
		this.fechaSolicitudGarantia = fechaSolicitudGarantia;
		this.fechaFinGarantia = fechaFinGarantia;
		this.precioGarantia = precioGarantia;
		this.nombreCliente = nombreCliente;
		this.calcularPrecioGarantia();
		this.calcularFechaFin();
	}

	public Producto getProducto() {
		return producto;
	}

	public Date getFechaSolicitudGarantia() {
		return fechaSolicitudGarantia;
	}

	public Date getFechaFinGarantia() {
		return fechaFinGarantia;
	}

	public double getPrecioGarantia() {
		return precioGarantia;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}


	private void calcularPrecioGarantia() {
		if (this.producto.getPrecio() > 500000) {
			this.precioGarantia =  this.producto.getPrecio() * 0.20;
		} else{
			this.precioGarantia =  this.producto.getPrecio() * 0.10;
		}
	}

	private void calcularFechaFin() {
		LocalDate fechaInicio =  this.localDateCoversor(this.fechaSolicitudGarantia);
		LocalDate fechaFinal;
		if (this.producto.getPrecio() > 500000) {
			fechaFinal= fechaInicio.plusDays(200);
		} else{
			fechaFinal = fechaInicio.plusDays(100);
		}
		int cantidadLunes = this.contarLunes(fechaInicio,fechaFinal);
		fechaFinal = fechaFinal.minusDays(cantidadLunes);
		if (fechaFinal.getDayOfWeek() == DayOfWeek.SUNDAY){
			fechaFinal = fechaFinal.plusDays(1);
		}
		this.fechaFinGarantia =  this.dateCoversor(fechaFinal);
	}

	private int contarLunes(LocalDate fechaInicial, LocalDate fechaFinal) {
		int cantidadLunes = 0;
		while (fechaInicial.isBefore(fechaFinal)) {
			fechaInicial = fechaInicial.plusWeeks(1);
			cantidadLunes++;
		}
		return cantidadLunes;
	}


	private LocalDate localDateCoversor(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private Date dateCoversor(LocalDate localDate){
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
*/
}
