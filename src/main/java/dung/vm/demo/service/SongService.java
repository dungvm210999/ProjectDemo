package dung.vm.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dung.vm.demo.common.Common;
import dung.vm.demo.common.Constant;
import dung.vm.demo.dto.SongForm;
import dung.vm.demo.entity.Singer;
import dung.vm.demo.entity.Song;
import dung.vm.demo.exception.BusinessException;
import dung.vm.demo.repository.SingerRepository;
import dung.vm.demo.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	SongRepository songRepository;
	
	@Autowired
	SingerRepository singerRepository;

	@Transactional
	public Song createSong(SongForm songForm, Long singerId) {
		if (songForm == null) {
			throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Du lieu truyen vao khong dung!");
		}
		
		singerRepository.findBySingerId(singerId);
		
//		if (singer == null) {
//			throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Ca sĩ không tồn tại!");
//		}
		
		Song song = new Song();
		
		song.setName(songForm.getName());
		song.setCreateAt(Common.getSystemDate());
		song.setUpdateAt(Common.getSystemDate());
		song.setPrice(songForm.getPrice());

		return songRepository.save(song);
	}
	
	public List<Song> findAllSongsIsNotDelete() {
		System.out.println("Song service findAllSongs");
		return songRepository.findAllSongsIsNotDelete();
	}

	public Song findById(Long songId) {
		return songRepository.findBySongId(songId);
	}
	
	public List<Song> findAllSongsOfSinger(Long singerId) {
		return songRepository.findAllSongsOfSingerIdIsNotDelete(singerId);
	}

	@Transactional
	public Song updateSong(SongForm songForm) {
		Song song = songRepository.findBySongId(songForm.getSongId());
		
		song.setName(songForm.getName());
		song.setCreateAt(Common.getSystemDate());
		song.setUpdateAt(Common.getSystemDate());
		song.setPrice(songForm.getPrice());
		
		return songRepository.save(song);
	}

	@Transactional
	public void deleteSong(Long songId) {
		Song song = songRepository.findBySongId(songId);
		
		song.setDelete(true);
		song.setUpdateAt(Common.getSystemDate());
		
		songRepository.save(song);
	}
	
}
