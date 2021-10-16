package dung.vm.demo.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import dung.vm.demo.dto.SingerForm;
import dung.vm.demo.entity.Singer;
import dung.vm.demo.service.SingerService;

@RestController
@RequestMapping("/api/v1/singers")
@CrossOrigin("*")
public class SingerController  {

	private SingerService singerService;
 
//	Get all singers
//	@GetMapping("/singers")
//	@CrossOrigin
//	public List<Singer> getAllSingers() {
//		System.out.print("ádasdasdasd");
//		return singerRepository.findAll();
//	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<String>("OK" , HttpStatus.OK);
	}

//	Create singer rest api
	@PostMapping("/singers")
	public Singer createSinger(@RequestBody Singer singer) {
//		return singerRepository.save(singer);
		return singerService.createSinger(singer);
	}

//	Get singer by id rest api
	@GetMapping("/singers/{id}")
	public ResponseEntity<Singer> getSingerById(@PathVariable Long singerId) {
		Singer singer = singerService.findById(singerId);
//		Singer singer = singerRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Singer not exist with id: " + id));
		return ResponseEntity.ok(singer);
	}

//	Update singer rest api
	@PutMapping("/singers")
	public ResponseEntity<Singer> updateSinger(@RequestBody SingerForm singerForm) {
//		Singer singer = singerRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Singer not exist with id: " + id));
		Singer singer = singerService.updateSinger(singerForm);
		return ResponseEntity.ok(singer);
	}

//	Delete singer rest api
	@DeleteMapping("/singers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteSinger(@PathVariable Long singerId) {
//		Singer singer = singerRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Singer not exist with id: " + id));
		singerService.deleteSinger(singerId);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
