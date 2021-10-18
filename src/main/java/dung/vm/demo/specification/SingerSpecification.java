package dung.vm.demo.specification;

import org.springframework.data.jpa.domain.Specification;

import dung.vm.demo.entity.Singer;
import dung.vm.demo.entity.Singer_;


public class SingerSpecification {

	public static Specification<Singer> isDelete(boolean isDelete) {
		return (root, query, cb) -> cb.equal(root.get(Singer_.IS_DELETE), isDelete);
	}

	public static Specification<Singer> likeName(String name) {
		return (root, query, cb) -> cb.like(root.get(Singer_.NAME), "%" + name + "%");
	}
	
	public static Specification<Singer> likeAddress(String address) {
		return (root, query, cb) -> cb.like(root.get(Singer_.ADDRESS), "%" + address + "%");
	}

	public static Specification<Singer> likeEmail(String email) {
		return (root, query, cb) -> cb.like(root.get(Singer_.EMAIL), "%" + email + "%");
	}
	
}
