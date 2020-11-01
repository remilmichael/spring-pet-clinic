package me.remil.springpetclinic.services.map;

import me.remil.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private final Long ownerId = 1L;
    private final String lastName = "Michael";

    @BeforeEach
    void setUp() {
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void save() {
        Owner owner2 = new Owner();
        owner2.setId(2L);

        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(new Owner());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner fetchedOwner = ownerMapService.findByLastName(lastName);
        assertNotNull(fetchedOwner);
        assertEquals(ownerId, fetchedOwner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner fetchedOwner = ownerMapService.findByLastName("foo");
        assertNull(fetchedOwner);
    }
}