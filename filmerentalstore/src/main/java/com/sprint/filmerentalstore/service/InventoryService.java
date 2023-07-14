package com.sprint.filmerentalstore.service;

import java.util.List;

import com.sprint.filmerentalstore.dto.InventoryDto;
import com.sprint.filmerentalstore.entity.Inventory;

public interface InventoryService {

	List<InventoryDto> getAllInventory();

	void addInventory(InventoryDto inventory);

	List<InventoryDto> getInventoryByStoreId(Short storeId);

	List<InventoryDto> getInventoryByFilmId(Short filmId);

	List<InventoryDto> getInventoryByFilmIdAndStoreId(Short filmId, Short storeId);

	//InventoryDto getSingleInventoryByFilmId(Short filmId);
	 public Short getSingleInventoryByFilmId(Short filmId);

}
