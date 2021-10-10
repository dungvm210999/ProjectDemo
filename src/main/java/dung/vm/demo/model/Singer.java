package dung.vm.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "singer")
public class Singer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "singer_id")
	private long singerId;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@JsonManagedReference
	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
	private List<Song> listSong;

	@Column(name = "image")
	private String image;

	public Singer() {
		super();
	}

	public Singer(long singerId, String name, int age, String phoneNumber, String address, String email,
			List<Song> listSong, String image) {
		super();
		this.singerId = singerId;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.listSong = listSong;
		this.image = image;
	}

	public long getSingerId() {
		return singerId;
	}

	public void setSingerId(long singerId) {
		this.singerId = singerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Song> getListSong() {
		return listSong;
	}

	public void setListSong(List<Song> listSong) {
		this.listSong = listSong;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
