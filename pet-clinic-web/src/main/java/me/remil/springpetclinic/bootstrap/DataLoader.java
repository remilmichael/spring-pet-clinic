package me.remil.springpetclinic.bootstrap;

import me.remil.springpetclinic.model.Owner;
import me.remil.springpetclinic.model.Pet;
import me.remil.springpetclinic.model.PetType;
import me.remil.springpetclinic.model.Vet;
import me.remil.springpetclinic.services.OwnerService;
import me.remil.springpetclinic.services.PetTypeService;
import me.remil.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("12312344");
        ownerService.save(owner1);

        Pet pet1 = new Pet();
        pet1.setPetType(savedDogPetType);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());
        pet1.setName("Rosco");
        owner1.getPets().add(pet1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("456 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("12312344");
        ownerService.save(owner2);

        Pet pet2 = new Pet();
        pet2.setName("Meow");
        pet2.setOwner(owner2);
        pet2.setPetType(savedCatPetType);
        pet2.setBirthDate(LocalDate.now());
        owner2.getPets().add(pet2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println(">>>>>>>> Saved Owners and Vets");
    }
}
