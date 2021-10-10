package dung.vm.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import dung.vm.demo.exception.ResourceNotFoundException;
import dung.vm.demo.model.Singer;
import dung.vm.demo.repository.SingerRepository;

@RestController
@RequestMapping("/api/v1/")
public class SingerController  {

	private SingerRepository singerRepository;
 
//	Get all singers
	@GetMapping("/singers")
	@CrossOrigin
	public List<Singer> getAllSingers() {
		System.out.print("ádasdasdasd");
		return singerRepository.findAll();
	}

//	Create singer rest api
	@PostMapping("/singers")
	public Singer createSinger(@RequestBody Singer singer) {
		return singerRepository.save(singer);
	}

//	Get singer by id rest api
	@GetMapping("/singers/{id}")
	public ResponseEntity<Singer> getSingerById(@PathVariable Long id) {
		Singer singer = singerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Singer not exist with id: " + id));
		return ResponseEntity.ok(singer);
	}

//	Update singer rest api
	@PutMapping("/singers/{id}")
	public ResponseEntity<Singer> updateSinger(@PathVariable Long id, @RequestBody Singer singerDetails) {
		Singer singer = singerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Singer not exist with id: " + id));
		singer.setName(singerDetails.getName());
		singer.setAddress(singerDetails.getAddress());
		singer.setEmail(singerDetails.getEmail());

		Singer updatedSinger = singerRepository.save(singer);
		return ResponseEntity.ok(updatedSinger);
	}

//	Delete singer rest api
	@DeleteMapping("/singers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteSinger(@PathVariable Long id) {
		Singer singer = singerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Singer not exist with id: " + id));
		singerRepository.delete(singer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
