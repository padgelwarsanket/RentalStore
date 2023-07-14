package com.sprint.filmerentalstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.InventoryDto;
import com.sprint.filmerentalstore.entity.Message;
import com.sprint.filmerentalstore.service.InventoryService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/add")
	public ResponseEntity<Message> addFilmToStore(@Valid @RequestBody InventoryDto inventory) {
		inventoryService.addInventory(inventory);
		return ResponseEntity.status(HttpStatus.OK).body(new Message("Record created successfully"));
	}

	@GetMapping("/single/film/{id}")

	public ResponseEntity<Short> getSingleInventoryByFilmId(@PathVariable("id") Short filmId) {

		Short id = inventoryService.getSingleInventoryByFilmId(filmId);

		return ResponseEntity.ok(id);

	}

	@GetMapping("/films")
	public ResponseEntity<List<InventoryDto>> getAllInventory() {
		List<InventoryDto> inventoryList = inventoryService.getAllInventory();
		return ResponseEntity.ok(inventoryList);
	}

	@GetMapping("/store/{id}")
	public ResponseEntity<List<InventoryDto>> getInventoryByStoreId(@PathVariable("id") Short storeId) {
		List<InventoryDto> inventoryList = inventoryService.getInventoryByStoreId(storeId);
		return ResponseEntity.ok(inventoryList);
	}

	@GetMapping("/film/{id}")
	public ResponseEntity<List<InventoryDto>> getInventoryByFilmId(@PathVariable("id") Short filmId) {
		List<InventoryDto> inventoryList = inventoryService.getInventoryByFilmId(filmId);
		return ResponseEntity.ok(inventoryList);
	}

//	@GetMapping("/single/film/{id}")
//	public ResponseEntity<InventoryDto> getSingleInventoryByFilmId(@PathVariable("id") Short filmId) {
//		InventoryDto inventoryList = inventoryService.getSingleInventoryByFilmId(filmId);
//		return ResponseEntity.ok(inventoryList);
//	}

	@GetMapping("/film/{filmId}/store/{storeId}")
	public ResponseEntity<List<InventoryDto>> getInventoryByFilmIdAndStoreId(@PathVariable("filmId") Short filmId,
			@PathVariable("storeId") Short storeId) {
		List<InventoryDto> inventory = inventoryService.getInventoryByFilmIdAndStoreId(filmId, storeId);
		return ResponseEntity.ok(inventory);
	}
}
