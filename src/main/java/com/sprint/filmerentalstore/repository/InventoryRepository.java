package com.sprint.filmerentalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.filmerentalstore.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Short> {

	Optional<Inventory> findByInventoryId(Short id);

	 

    List<Inventory> findByStoreId_StoreId(Short storeId);

 

    List<Inventory> findByFilmId_FilmId(Short filmId);

    

    Inventory findFirstByFilmId_FilmId(Short filmId);

 

    List<Inventory> findByFilmId_FilmIdAndStoreId_StoreId(Short filmId, Short storeId);


}
