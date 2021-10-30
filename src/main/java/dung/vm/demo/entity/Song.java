package dung.vm.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "song")
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_id")
	private Long songId;

	public Long getSingerId() {
		return singerId;
	}

	public void setSingerId(Long singerId) {
		this.singerId = singerId;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "lyric")
	private String lyric;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_at")
	private Date updateAt;

	@Column(name = "price")
	private Double price;

	@Column(name = "is_delete", columnDefinition = "Boolean default false")
	private boolean isDelete;

//	@Column(name = "song_time")
//	private float songTime;

	@Column(name = "singer_id", nullable = false, unique = true, updatable = false, insertable = false)
	private Long singerId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "singer_id")
	private Singer singer;

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

//	public float getSongTime() {
//		return songTime;
//	}
//
//	public void setSongTime(float songTime) {
//		this.songTime = songTime;
//	}

}
