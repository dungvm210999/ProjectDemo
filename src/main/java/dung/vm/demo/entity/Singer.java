package dung.vm.demo.entity;

import java.util.ArrayList;
import java.util.Date;
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

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "year_of_operation")
	private int yearOfOperation;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "is_delete", columnDefinition = "Boolean default false")
	private boolean isDelete;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_at")
	private Date updateAt;

	@JsonManagedReference
	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
	private List<Song> listSong;

	@Column(name = "image")
	private String image;

	public Singer() {
		super();
	}

	public Singer(long singerId, String name, String phoneNumber, String address, String email,
			List<Song> listSong, String image) {
		super();
		this.singerId = singerId;
		this.name = name;
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
		List<Song> listSongCoppy = new ArrayList<>();
		for(Song song : listSong) {
			if(song.isDelete() == false) {
				listSongCoppy.add(song);
			}
		}
		return listSongCoppy;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYearOfOperation() {
		return yearOfOperation;
	}

	public void setYearOfOperation(int yearOfOperation) {
		this.yearOfOperation = yearOfOperation;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

}
