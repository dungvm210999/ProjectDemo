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
	@PostMapping("/search-singers/page{pageNumber, pageSize}")
	@ResponseBody
	public ResponseEntity<Page<Singer>> searchSinger(@RequestBody FormSearchSinger formSearchSinger, @RequestParam int pageNumber, @RequestParam int pageSize) {
//		singerService.searchSinger(formSearchSinger);
		
//		Page<Singer> singerPage = singerService.getAllSingers(pageNumber, pageSize);
		Page<Singer> singerSearchPage = singerService.searchSinger(formSearchSinger, pageNumber, pageSize);
		return new ResponseEntity<Page<Singer>>(singerSearchPage, HttpStatus.OK);
	}

//	Get all singers
	@GetMapping("/singers")
	public List<Singer> getAllSinger() {
		return singerService.findAllSingers();
	}

//	Create singer rest api
	@PostMapping("/create-singer")
	public ResponseEntity<Map<String, Boolean>> createSinger(@RequestBody SingerForm singerForm) {
		singerService.createSinger(singerForm);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Created", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}

//	Get singer by id rest api
	@GetMapping("/singers/{singerId}")
	public ResponseEntity<Singer> getSingerById(@PathVariable Long singerId) {
		Singer singer = singerService.findById(singerId);
		
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("result", Boolean.TRUE);
		return ResponseEntity.ok(singer);
	}

//	Update singer rest api
	@PutMapping("/update-singer")
	public ResponseEntity<Singer> updateSinger(@RequestBody SingerForm singerForm) {
		Singer singer = singerService.updateSinger(singerForm);
		
		return ResponseEntity.ok(singer);
	}

//	Delete singer rest api
	@DeleteMapping("/delete-singer/{singerId}")
	public ResponseEntity<Map<String, Boolean>> deleteSinger(@PathVariable Long singerId) {
		singerService.deleteSinger(singerId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
//	Pagination
	@GetMapping("/singers/page{pageNumber, pageSize}")
	public ResponseEntity<Page<Singer>> getAllSingers(@RequestParam int pageNumber, @RequestParam int pageSize) {
		Page<Singer> singers = singerService.getAllSingers(pageNumber, pageSize);
		return new ResponseEntity<Page<Singer>>(singers, HttpStatus.OK);
	}
	
//	Song controller
	
//	Create song rest api
	@PostMapping("/singers/{singerId}/create-song")
	public ResponseEntity<Map<String, Boolean>> createSong(@RequestBody SongForm songForm, @PathVariable Long singerId) {
		songService.createSong(songForm);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Created", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
	
//	Get song by id rest api
	@GetMapping("/songs/{songId}")
	public ResponseEntity<Song> getSongById(@PathVariable Long songId) {
		Song song = songService.findById(songId);
		
		return ResponseEntity.ok(song);
	}
	
//	Update song rest api
	@PutMapping("/update-song")
	public ResponseEntity<Song> updateSong(@RequestBody SongForm songForm) {
		Song song = songService.updateSong(songForm);
		
		return ResponseEntity.ok(song);
	}
	
//	Delete song rest api
	@DeleteMapping("/delete-song/{songId}")
	public ResponseEntity<Map<String, Boolean>> deleteSong(@PathVariable Long songId) {
		songService.deleteSong(songId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
