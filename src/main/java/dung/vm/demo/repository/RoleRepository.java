package dung.vm.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dung.vm.demo.common.ERole;
import dung.vm.demo.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(ERole roleName);

}
