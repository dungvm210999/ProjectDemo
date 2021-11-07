package dung.vm.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dung.vm.demo.dto.FormSearchSinger;
import dung.vm.demo.dto.SingerForm;
import dung.vm.demo.dto.SongForm;
import dung.vm.demo.entity.Singer;
import dung.vm.demo.entity.Song;
import dung.vm.demo.service.SingerService;
import dung.vm.demo.service.SongService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class SingerController {

	@Autowired
	private SingerService singerService;

	@Autowired
	private SongService songService;

//	Search singers
	@PostMapping("/admin/search-singers/page{pageNumber, pageSize}")
	@ResponseBody
	public ResponseEntity<Page<Singer>> searchSinger(@RequestBody FormSearchSinger formSearchSinger,
			@RequestParam int pageNumber, @RequestParam int pageSize) {

		Page<Singer> singerSearchPage = singerService.searchSinger(formSearchSinger, pageNumber, pageSize);
		return new ResponseEntity<Page<Singer>>(singerSearchPage, HttpStatus.OK);
	}

//	Get all singers
	@GetMapping("/admin/singers")
	public List<Singer> getAllSinger() {
		return singerService.findAllSingers();
	}

//	Create singer rest api
	@PostMapping("/admin/create-singer")
	public ResponseEntity<Map<String, Boolean>> createSinger(@RequestBody SingerForm singerForm) {
		singerService.createSinger(singerForm);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Created", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}

//	Get singer by id rest api
	@GetMapping("/admin/singers/{singerId}")
	public ResponseEntity<Singer> getSingerById(@PathVariable Long singerId) {
		Singer singer = singerService.findById(singerId);

//		Map<String, Boolean> response = new HashMap<>();
//		response.put("result", Boolean.TRUE);
		return ResponseEntity.ok(singer);
	}

//	Update singer rest api
	@PutMapping("/admin/update-singer")
	public ResponseEntity<Singer> updateSinger(@RequestBody SingerForm singerForm) {
		Singer singer = singerService.updateSinger(singerForm);

		return ResponseEntity.ok(singer);
	}

//	Delete singer rest api
	@DeleteMapping("/admin/delete-singer/{singerId}")
	public ResponseEntity<Map<String, Boolean>> deleteSinger(@PathVariable Long singerId) {
		Singer singer = singerService.findById(singerId);
		
		if (singer == null) {
			System.out.println("Singer deleted");
			Map<String, Boolean> resFalse = new HashMap<>();
			resFalse.put("Deleted", Boolean.FALSE);
			return ResponseEntity.ok(resFalse);
		}
		
		if (!singer.isDelete()) {
			System.out.println("Singer not delete");
			singerService.deleteSinger(singerId);

			Map<String, Boolean> resTrue = new HashMap<>();
			resTrue.put("Deleted", Boolean.TRUE);
			return ResponseEntity.ok(resTrue);
		} 
		else {
			System.out.println("Singer deleted");
			Map<String, Boolean> resFalse = new HashMap<>();
			resFalse.put("Deleted", Boolean.FALSE);
			return ResponseEntity.ok(resFalse);
		}
	
	}

//	Pagination
	@GetMapping("/admin/singers/page{pageNumber, pageSize}")
	public ResponseEntity<Page<Singer>> getAllSingers(@RequestParam int pageNumber, @RequestParam int pageSize) {
		Page<Singer> singers = singerService.getAllSingers(pageNumber, pageSize);
		return new ResponseEntity<Page<Singer>>(singers, HttpStatus.OK);
	}

//	Song controller

//	Get all songs of sing isDelete = false
	@GetMapping("/admin/singers/{singerId}/songs")
	public List<Song> getAllSongsOfSinger(@PathVariable Long singerId) {
		return songService.findAllSongsOfSinger(singerId);
	}

//	Get all songs
	@GetMapping("/admin/songs")
	public List<Song> getAllSongs() {
		return songService.findAllSongsIsNotDelete();
	}

//	Create song rest api
	@PostMapping("/admin/singers/{singerId}/create-song")
	public ResponseEntity<Map<String, Boolean>> createSong(@RequestBody SongForm songForm,
			@PathVariable Long singerId) {
		Song song = songService.createSong(songForm, singerId);

		singerService.addSongToSinger(song, singerId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Created", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}

//	Get song by id rest api
	@GetMapping("/admin/songs/{songId}")
//	@GetMapping("singers/{singerId}/songs/{songId}")
	public ResponseEntity<Song> getSongById(@PathVariable Long songId) {
		Song song = songService.findById(songId);

		return ResponseEntity.ok(song);
	}

//	Update song rest api
	@PutMapping("/admin/update-song")
	public ResponseEntity<Song> updateSong(@RequestBody SongForm songForm) {
		Song song = songService.updateSong(songForm);

		return ResponseEntity.ok(song);
	}

//	Delete song rest api
	@DeleteMapping("/admin/delete-song/{songId}")
	public ResponseEntity<Map<String, Boolean>> deleteSong(@PathVariable Long songId) {
		songService.deleteSong(songId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
