package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.InventoryDto;
import com.sprint.filmerentalstore.entity.Inventory;
import com.sprint.filmerentalstore.repository.FilmRepository;
import com.sprint.filmerentalstore.repository.InventoryRepository;
import com.sprint.filmerentalstore.repository.StoreRepository;
import com.sprint.filmerentalstore.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	public void addInventory(InventoryDto dto) {
		Inventory inv = new Inventory();
		inv.setFilmId(filmRepository.findById(dto.getFilmId()).get());
		inv.setStoreId(storeRepository.findById(dto.getStoreId()).get());
		inv.setLastUpdate(LocalDateTime.now());
		inventoryRepository.save(inv);
	}

	public List<InventoryDto> getAllInventory() {
		List<Inventory> findAll = inventoryRepository.findAll();
		List<InventoryDto> dtos = new ArrayList<>();
		for (Inventory inventory : findAll) {
			InventoryDto dto = copyEntityToDto(inventory);
			dtos.add(dto);
		}
		return dtos;
	}

	public List<InventoryDto> getInventoryByStoreId(Short storeId) {
		 List<Inventory> invs = inventoryRepository.findByStoreId_StoreId(storeId);
		List<InventoryDto> dtos = new ArrayList<>();
		 for(Inventory inv: invs) {
			 InventoryDto dto = copyEntityToDto(inv);
			 dtos.add(dto);
		 }
		 return dtos;
	}

	public List<InventoryDto> getInventoryByFilmId(Short filmId) {
		 List<Inventory> invs = inventoryRepository.findByFilmId_FilmId(filmId);
		List<InventoryDto> dtos = new ArrayList<>();
		 for(Inventory inv: invs) {
			 InventoryDto dto = copyEntityToDto(inv);
			 dtos.add(dto);
		 }
		 return dtos;
	}

	public List<InventoryDto> getInventoryByFilmIdAndStoreId(Short filmId, Short storeId) {
		 List<Inventory> invs = inventoryRepository.findByFilmId_FilmIdAndStoreId_StoreId(filmId, storeId);
		 List<InventoryDto> dtos = new ArrayList<>();
		 for(Inventory inv: invs) {
			 InventoryDto dto = copyEntityToDto(inv);
			 dtos.add(dto);
		 }
		 return dtos;
	}
	
//	@Override
//	public InventoryDto getSingleInventoryByFilmId(Short filmId) {
//		Inventory inventory = inventoryRepository.findFirstByFilmId_FilmId(filmId);
//		InventoryDto dto = copyEntityToDto(inventory);
//		return dto;
//	}

	public InventoryDto copyEntityToDto(Inventory inv){
		InventoryDto dto = new InventoryDto();
		dto.setStoreId(inv.getStoreId().getStoreId());
		dto.setFilmId(inv.getFilmId().getFilmId());
		dto.setInventoryId(inv.getInventoryId());
		dto.setLastUpdate(inv.getLastUpdate());
		
		return dto;
	}
	
	@Override

    public Short getSingleInventoryByFilmId(Short filmId) {

        Inventory inventory = inventoryRepository.findFirstByFilmId_FilmId(filmId);

        InventoryDto dto = copyEntityToDto(inventory);

        return inventory.getInventoryId();

    }

	
}
