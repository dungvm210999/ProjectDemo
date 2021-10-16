package dung.vm.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dung.vm.demo.common.Common;
import dung.vm.demo.dto.SingerForm;
import dung.vm.demo.entity.Singer;
import dung.vm.demo.exception.ResourceNotFoundException;
import dung.vm.demo.repository.SingerRepository;

@Service
public class SingerService {
	
	@Autowired
	private SingerRepository singerRepository;

	public Singer createSinger(Singer singer) {
		return singerRepository.save(singer);
	}

	public Singer findById(Long singerId) {
		Singer singer = singerRepository.findById(singerId)
		.orElseThrow(() -> new ResourceNotFoundException("Singer not exist with id: " + singerId));
		return singer;
	}

	@Transactional
	public Singer updateSinger(SingerForm singerForm) {
		Singer singer = singerRepository.findBySingerId(singerForm.getSingerId());
		
		singer.setName(singerForm.getName());
		singer.setAge(singerForm.getAge());
		singer.setPhoneNumber(singerForm.getPhoneNumber());
		singer.setAddress(singerForm.getAddress());
		singer.setEmail(singerForm.getEmail());
		singer.setDescription(singerForm.getDescription());
		singer.setYearOfOperation(singerForm.getYearOfOperation());
		singer.setFullName(singerForm.getFullName());
		singer.setBirthday(singerForm.getBirthday());
		singer.setCreateBy(singerForm.getCreateBy());
		singer.setCreateAt(singerForm.getCreateAt());
		singer.setUpdateAt(Common.getSystemDate());
		singer.setUpdateBy(singerForm.getUpdateBy());
		
		return singerRepository.save(singer);
	}

	@Transactional
	public void deleteSinger(Long singerId) {
		Singer singer = singerRepository.findBySingerId(singerId);
		
		singer.setDelete(true);
		singer.setUpdateAt(Common.getSystemDate());
		
		singerRepository.save(singer);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
