package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.HuntingZone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HuntingZoneRepository extends CrudRepository<HuntingZone, Integer> {
    Optional<HuntingZone> findByNameIgnoreCase(String name);
    List<HuntingZone> findAllByTerrainIgnoreCase(String terrain);

}
