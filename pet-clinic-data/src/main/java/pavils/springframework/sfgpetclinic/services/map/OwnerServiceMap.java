package pavils.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import pavils.springframework.sfgpetclinic.model.Owner;
import pavils.springframework.sfgpetclinic.model.Pet;
import pavils.springframework.sfgpetclinic.services.OwnerService;
import pavils.springframework.sfgpetclinic.services.PetService;
import pavils.springframework.sfgpetclinic.services.PetTypeService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    PetService petService;
    PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {


        if (object != null) {
            object.getPets().forEach(pet -> {
                if (pet.getPetType() != null) {
                    if (pet.getPetType().getId() == null) {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                } else {
                    throw new RuntimeException("Pet type is required");
                }

                if (pet.getId() == null) {
                    Pet savedPet  = petService.save(pet);
                    pet.setId(savedPet.getId());
                }
            });
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
