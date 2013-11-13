package au.com.jaylin.test.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="T_HELLO")
@NamedQueries( {
	@NamedQuery(name = "allHellos", query = "select h from Hello h"),
	@NamedQuery(name = "helloFromUsername", query = "select h from Hello h where h.username = :username")
})
public class Hello {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "A_USER", unique = true, nullable = false)
	private String username;
	
	@Column(name = "A_COUNTER", nullable = false)
	private Integer counter;
	
	@Version
	@Column(name = "A_TIMESTAMP", nullable = false)
	private Timestamp when;
	
	
	public Hello() {
		this.counter = 1;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Timestamp getWhen() {
		return when;
	}

	public void setWhen(Timestamp when) {
		this.when = when;
	}
}
