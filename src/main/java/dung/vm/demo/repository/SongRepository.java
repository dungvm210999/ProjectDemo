package dung.vm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dung.vm.demo.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>, PagingAndSortingRepository<Song, Long> {

	@Query("SELECT so FROM Song so WHERE so.isDelete = false AND so.songId = :songId")
	public Song findBySongId(Long songId);

}
