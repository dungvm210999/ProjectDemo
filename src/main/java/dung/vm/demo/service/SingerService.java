package dung.vm.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import dung.vm.demo.common.Common;
import dung.vm.demo.common.Constant;
import dung.vm.demo.dto.FormSearchSinger;
import dung.vm.demo.dto.SingerForm;
import dung.vm.demo.entity.Singer;
import dung.vm.demo.entity.Song;
import dung.vm.demo.exception.BusinessException;
import dung.vm.demo.repository.SingerRepository;
import dung.vm.demo.specification.SingerSpecification;

@Service
public class SingerService {

	@Autowired
	private SingerRepository singerRepository;

	public List<Singer> findAllSingers() {
		System.out.println("Singer service 23");
		return singerRepository.findAllSingersIsNotDelete();
	}

	@Transactional
	public Singer createSinger(SingerForm singerForm) {
		if (singerForm == null) {
			throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng!");
		}

		Singer singer = new Singer();
		
//		singer.setSingerId(2);
		singer.setName(singerForm.getName());
		singer.setAddress(singerForm.getAddress());
		singer.setPhoneNumber(singerForm.getPhoneNumber());
		singer.setEmail(singerForm.getEmail());
		singer.setFullName(singerForm.getFullName());
		singer.setYearOfOperation(singerForm.getYearOfOperation());
		singer.setCreateAt(Common.getSystemDate());
		singer.setUpdateAt(Common.getSystemDate());

		return singerRepository.save(singer);
	}

	public Singer findById(Long singerId) {
		Singer singer = singerRepository.findBySingerId(singerId);
		return singer;
	}

	@Transactional
	public Singer updateSinger(SingerForm singerForm) {
		Singer singer = singerRepository.findBySingerId(singerForm.getSingerId());

		singer.setName(singerForm.getName());
		singer.setPhoneNumber(singerForm.getPhoneNumber());
		singer.setAddress(singerForm.getAddress());
		singer.setEmail(singerForm.getEmail());
		singer.setDescription(singerForm.getDescription());
		singer.setYearOfOperation(singerForm.getYearOfOperation());
		singer.setFullName(singerForm.getFullName());
		singer.setBirthday(singerForm.getBirthday());
		singer.setCreateAt(Common.getSystemDate());
		singer.setUpdateAt(Common.getSystemDate());

		return singerRepository.save(singer);
	}

	@Transactional
	public void deleteSinger(Long singerId) {
		Singer singer = singerRepository.findBySingerId(singerId);

		singer.setDelete(true);
		singer.setUpdateAt(Common.getSystemDate());

		singerRepository.save(singer);
	}

	public Page<Singer> getAllSingers(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Singer> singers = singerRepository.findAllSingers(Constant.STR_FALSE, pageable);
		return singers;
	}

	public Page<Singer> searchSinger(FormSearchSinger formSearchSinger, int pageNumber, int pageSize) {
		if (formSearchSinger.getPageNumber() == null) {
			formSearchSinger.setPageNumber(0);
		}

		Specification<Singer> conditions = Specification.where(SingerSpecification.isDelete(false));
		if (formSearchSinger != null) {
			if (StringUtils.isNotBlank(formSearchSinger.getName())) {
				conditions = conditions.and(SingerSpecification.likeName(formSearchSinger.getName()));
			}

			if (StringUtils.isNotBlank(formSearchSinger.getAddress())) {
				conditions = conditions.and(SingerSpecification.likeAddress(formSearchSinger.getAddress()));
			}

			if (StringUtils.isNotBlank(formSearchSinger.getEmail())) {
				conditions = conditions.and(SingerSpecification.likeEmail(formSearchSinger.getEmail()));
			}
		}

		Pageable pageable = PageRequest.of(formSearchSinger.getPageNumber(), pageSize);
		Page<Singer> listChapter = singerRepository.findAll(conditions, pageable);
		return listChapter;

	}

	@Transactional
	public void addSongToSinger(Song song, Long singerId) {
		Singer singer = singerRepository.findBySingerId(singerId);
		
		singer.getListSong().add(song);
		song.setSinger(singer);
		
		singerRepository.save(singer);
	}

	public Singer findBySingerId(Long singerId) {
		return singerRepository.findBySingerId(singerId);
	}

}
