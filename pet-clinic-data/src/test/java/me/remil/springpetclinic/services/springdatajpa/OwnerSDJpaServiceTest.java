package me.remil.springpetclinic.services.springdatajpa;

import me.remil.springpetclinic.model.Owner;
import me.remil.springpetclinic.repositories.OwnerRepository;
import me.remil.springpetclinic.repositories.PetRepository;
import me.remil.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    String LAST_NAME = "Smith";
    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = new Owner();
    }

    @Test
    void findByLastName() {
        returnOwner.setId(1L);
        returnOwner.setLastName(LAST_NAME);

        when(ownerSDJpaService.findByLastName(any())).thenReturn(returnOwner);

        Owner owner = ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, owner.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);
        returnOwnersSet.add(owner1);
        Owner owner2 = new Owner();
        owner2.setId(2L);
        returnOwnersSet.add(owner2);

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        returnOwner.setId(1L);

        when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));
        Owner someOwner = ownerSDJpaService.findById(1L);

        assertNotNull(someOwner);
        assertEquals(1, someOwner.getId());
    }

    @Test
    void save() {

        Owner newOwner = new Owner();
        newOwner.setId(2L);

        when(ownerSDJpaService.save(any())).thenReturn(newOwner);
        Owner savedOwner = ownerSDJpaService.save(newOwner);

        assertNotNull(savedOwner);
        assertEquals(2, savedOwner.getId());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}