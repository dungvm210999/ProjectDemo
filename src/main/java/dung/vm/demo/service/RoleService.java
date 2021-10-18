package dung.vm.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dung.vm.demo.entity.Role;
import dung.vm.demo.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAdllRole() {
		List<Role> listRole = roleRepository.findAll();
		if (listRole != null) {
			return listRole;
		}
		return new ArrayList<Role>();
	}
	
}
