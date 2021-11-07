package dung.vm.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dung.vm.demo.entity.Singer;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long>, PagingAndSortingRepository<Singer, Long>,
		JpaSpecificationExecutor<Singer> {

	@Query("SELECT s FROM Singer s WHERE s.isDelete = false AND s.singerId = :singerId")
	public Singer findBySingerId(Long singerId);

	@Query("SELECT s FROM Singer s WHERE s.isDelete = false")
	public Page<Singer> findAllSingers(String strFalse, Pageable pageable);

	@Query("SELECT s FROM Singer s WHERE s.isDelete = false")
	public List<Singer> findAllSingersIsNotDelete();

}
