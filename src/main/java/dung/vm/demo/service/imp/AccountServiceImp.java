package dung.vm.demo.service.imp;

import java.util.Optional;

public interface AccountServiceImp<T> {
	Iterable<T> findAll();

	Optional<T> findById(Long accountId);

	T save(T t);

	void remove(Long accountId);
}
