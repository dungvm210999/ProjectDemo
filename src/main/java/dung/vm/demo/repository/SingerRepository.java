package dung.vm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dung.vm.demo.model.Singer;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {

}
