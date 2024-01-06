package pavils.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pavils.springframework.sfgpetclinic.model.*;
import pavils.springframework.sfgpetclinic.services.PetTypeService;
import pavils.springframework.sfgpetclinic.services.SpecialityService;
import pavils.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import pavils.springframework.sfgpetclinic.services.map.PetServiceMap;
import pavils.springframework.sfgpetclinic.services.map.VetServiceMap;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerServiceMap ownerServiceMap;
    private final VetServiceMap vetServiceMap;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerServiceMap ownerServiceMap, VetServiceMap vetServiceMap, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerServiceMap = ownerServiceMap;
        this.vetServiceMap = vetServiceMap;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {
        if (petTypeService.findAll().size() == 0)
            loadData();
    }

    private void loadData() {
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);


        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Pavils");
        owner1.setLastName("Kuzmins");
        owner1.setAddress("Meza prospekts 4");
        owner1.setCity("Ogre");
        owner1.setTelephone("29907444");

        Pet pavilsPet = new Pet();
        pavilsPet.setPetType(savedCatPetType);
        pavilsPet.setName("Boriss");
        pavilsPet.setOwner(owner1);
        pavilsPet.setBirthDay(LocalDate.now());
        owner1.getPets().add(pavilsPet);

        ownerServiceMap.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Solvita");
        owner2.setLastName("Kuzmina");
        owner2.setAddress("Meza prospekts 4");
        owner2.setCity("Ogre");
        owner2.setTelephone("2645745");

        Pet solvitasPet = new Pet();
        solvitasPet.setPetType(savedDogPetType);
        solvitasPet.setName("Tobi");
        solvitasPet.setOwner(owner2);
        solvitasPet.setBirthDay(LocalDate.now());
        owner2.getPets().add(solvitasPet);

        ownerServiceMap.save(owner2);

        System.out.println("Owners loaded..");

        Vet vet1 = new Vet();
        vet1.setFirstName("Stive");
        vet1.setLastName("Jobs");
        vet1.getSpecialities().add(surgery);
        vet1.getSpecialities().add(radiology);

        vetServiceMap.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bill");
        vet2.setLastName("Gates");
        vet2.getSpecialities().add(dentistry);

        vetServiceMap.save(vet2);

        System.out.println("Vets loaded..");
    }
}
