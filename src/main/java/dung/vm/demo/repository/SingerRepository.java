package dung.vm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dung.vm.demo.entity.Singer;


@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {

	@Query("SELECT s FROM Singer s WHERE s.isDelete = false AND s.singerId = :singerId")
	public Singer findBySingerId(Long singerId);

}
