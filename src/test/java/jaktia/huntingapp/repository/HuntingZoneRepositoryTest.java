package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.HuntingZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class HuntingZoneRepositoryTest {

    @Autowired
    HuntingZoneRepository testObject;

    HuntingZone addedZone1;
    HuntingZone addedZone2;

    @BeforeEach
    public void setup() {
        HuntingZone zone1 = new HuntingZone(1, "Forest", "So many trees, vision is limited", "Jönköping", "Forest");
        HuntingZone zone2 = new HuntingZone(2, "Woods", "So many trees, vision is limited", "Växjö", "Forest");

        addedZone1 = testObject.save(zone1);
        addedZone2 = testObject.save(zone2);
        assertNotNull(addedZone1);
        assertNotNull(addedZone2);
    }
    @Test
    public void test_findById(){
        Optional<HuntingZone> huntingZoneOptional = testObject.findById(addedZone1.getId());
        assertTrue(huntingZoneOptional.isPresent());
        HuntingZone actual = huntingZoneOptional.get();
        HuntingZone expected = addedZone1;
        assertEquals(actual, expected);
    }
    @Test
    public void test_findByName(){
        Optional<HuntingZone> huntingZoneOptional = testObject.findByNameIgnoreCase(addedZone1.getName());
        assertTrue(huntingZoneOptional.isPresent());
        HuntingZone actual = huntingZoneOptional.get();
        HuntingZone expected = addedZone1;
        assertEquals(actual, expected);
    }
    @Test
    public void test_findByTerrain(){
        List<HuntingZone> huntingZoneList = testObject.findAllByTerrainIgnoreCase("forest");
        assertEquals(2, huntingZoneList.size());
        assertTrue(huntingZoneList.contains(addedZone1));
        assertTrue(huntingZoneList.contains(addedZone2));
    }
}
