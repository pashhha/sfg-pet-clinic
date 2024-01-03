package pavils.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pavils.springframework.sfgpetclinic.model.Owner;
import pavils.springframework.sfgpetclinic.model.PetType;
import pavils.springframework.sfgpetclinic.model.Vet;
import pavils.springframework.sfgpetclinic.services.PetTypeService;
import pavils.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import pavils.springframework.sfgpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerServiceMap ownerServiceMap;
    private final VetServiceMap vetServiceMap;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerServiceMap ownerServiceMap, VetServiceMap vetServiceMap, PetTypeService petTypeService) {
        this.ownerServiceMap = ownerServiceMap;
        this.vetServiceMap = vetServiceMap;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

         Owner owner1 = new Owner();
         owner1.setFirstName("Pavils");
         owner1.setLastName("Kuzmins");

         ownerServiceMap.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Solvita");
        owner2.setLastName("Kuzmina");

        ownerServiceMap.save(owner2);

        System.out.println("Owners loaded..");

        Vet vet1 = new Vet();
        vet1.setFirstName("Stive");
        vet1.setLastName("Jobs");

        vetServiceMap.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bill");
        vet2.setLastName("Gates");

        vetServiceMap.save(vet2);

        System.out.println("Vets loaded..");

    }
}
