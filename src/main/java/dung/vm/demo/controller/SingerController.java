package dung.vm.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import dung.vm.demo.common.Constant;
import dung.vm.demo.dto.FormSearchSinger;
import dung.vm.demo.dto.SingerForm;
import dung.vm.demo.entity.Singer;
import dung.vm.demo.service.SingerService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class SingerController {

	@Autowired
	private SingerService singerService;
	
	@Autowired
	private Environment environment;
	
	public void readValues() {
	    System.out.println("Some Message:"
	            + environment.getProperty("<Property Name>")); 

	}
	
//	Search singers
	@GetMapping("/singers/search-singers/{formSearchSinger}")
	@ResponseBody
	public Page<Singer> searchSinger(@RequestParam FormSearchSinger formSearchSinger) {
		return singerService.searchSinger(formSearchSinger);
	}

//	Get all singers
	@GetMapping("/singers")
	public List<Singer> getAllSinger() {
		return singerService.findAllSingers();
	}

//	Create singer rest api
	@PostMapping("/create-singer")
	public Singer createSinger(@RequestBody SingerForm singerForm) {
		return singerService.createSinger(singerForm);
	}

//	Get singer by id rest api
	@GetMapping("/singers/{singerId}")
	public ResponseEntity<Singer> getSingerById(@PathVariable Long singerId) {
		Singer singer = singerService.findById(singerId);
		return ResponseEntity.ok(singer);
	}

//	Update singer rest api
	@PutMapping("/singers")
	public ResponseEntity<Singer> updateSinger(@RequestBody SingerForm singerForm) {
		Singer singer = singerService.updateSinger(singerForm);
		return ResponseEntity.ok(singer);
	}

//	Delete singer rest api
	@DeleteMapping("/singers/{singerId}")
	public ResponseEntity<Map<String, Boolean>> deleteSinger(@PathVariable Long singerId) {
		singerService.deleteSinger(singerId);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
//	Pagination
	@GetMapping("/singers/page{pageNumber, pageSize}")
	public ResponseEntity<Page<Singer>> getAllSingers(@RequestParam int pageNumber) {
		Page<Singer> singers = singerService.getAllSingers(pageNumber, Constant.RECORD_PER_PAGE);
		return new ResponseEntity<Page<Singer>>(singers, HttpStatus.OK);
	}
	
	
}
