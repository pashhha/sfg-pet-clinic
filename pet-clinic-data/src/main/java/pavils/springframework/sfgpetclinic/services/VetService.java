package pavils.springframework.sfgpetclinic.services;

import pavils.springframework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    public interface PetService {
        Vet findById(Long id);
        Vet save(Vet vet);
        Set<Vet> findAll();
    }
}
