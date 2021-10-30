package dung.vm.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dung.vm.demo.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>, PagingAndSortingRepository<Song, Long> {

	@Query("SELECT so FROM Song so WHERE so.isDelete = false AND so.songId = :songId")
	public Song findBySongId(Long songId);

	@Query("SELECT so FROM Song so WHERE so.isDelete = false AND singerId = :singerId")
	public List<Song> findAllSongsOfSingerIdIsNotDelete(Long singerId);
	
	@Query("SELECT so FROM Song so WHERE so.isDelete = false")
	public List<Song> findAllSongsIsNotDelete();

}
