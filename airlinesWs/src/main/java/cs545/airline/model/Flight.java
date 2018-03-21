package cs545.airline.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "A flight object")
@XmlRootElement
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Flight {
	@Id
	@GeneratedValue
	private long id;
	private String flightnr;
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	@Temporal(TemporalType.TIME)
	private Date departureTime;
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airline airline;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airport origin;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airport destination;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airplane airplane;

	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
			Locale.US);
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.US);

	/* Constructors */
	public Flight() {
	}

	public Flight(String flightnr, String departureDate, String departureTime,
			String arrivalDate, String arrivalTime) {
		this.flightnr = flightnr;
		setDepartureDate(departureDate);
		setDepartureTime(departureTime);
		setArrivalDate(arrivalDate);
		setArrivalTime(arrivalTime);
	}

	public Flight(String flightnr, String departureDate, String departureTime,
			String arrivalDate, String arrivalTime, Airline airline,
			Airport origin, Airport destination, Airplane airplane) {
		this.flightnr = flightnr;
		setDepartureDate(departureDate);
		setDepartureTime(departureTime);
		setArrivalDate(arrivalDate);
		setArrivalTime(arrivalTime);
		airline.addFlight(this);
		origin.addDeparture(this);
		destination.addArrival(this);
		airplane.addFlight(this);
	}

	/* Getters & Setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Flight Number is required", example = "DL123A8", required = true)
	public String getFlightnr() {
		return flightnr;
	}

	public void setFlightnr(String flightnr) {
		this.flightnr = flightnr;
	}

	@ApiModelProperty(value = "Departure date is required", example = "30/12/2018", required = true)	
	public String getDepartureDate() {
		return df.format(departureDate);
	}

	public void setDepartureDate(String departureDate) {
		try {
			this.departureDate = df.parse(departureDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ApiModelProperty(value = "Departure time is required", example = "11:10PM", required = true)	
	public String getDepartureTime() {
		return tf.format(departureTime);
	}

	public void setDepartureTime(String departureTime) {
		try {
			this.departureTime = tf.parse(departureTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getArrivalDate() {
		return df.format(arrivalDate);
	}

	public void setArrivalDate(String arrivalDate) {
		try {
			this.arrivalDate = df.parse(arrivalDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getArrivalTime() {
		return tf.format(arrivalTime);
	}

	public void setArrivalTime(String arrivalTime) {
		try {
			this.arrivalTime = tf.parse(arrivalTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

}
