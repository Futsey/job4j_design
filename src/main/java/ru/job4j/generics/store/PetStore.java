package ru.job4j.generics.store;

public class PetStore implements Store<Pet> {

    private final Store<Pet> petStore = new MemStore<>();

    @Override
    public void add(Pet pet) {
        petStore.add(pet);
    }

    @Override
    public boolean replace(String id, Pet pet) {
        return petStore.replace(id, pet);
    }

    @Override
    public boolean delete(String id) {
        return petStore.delete(id);
    }

    @Override
    public Pet findById(String id) {
        return petStore.findById(id);
    }

    public Pet findUserById(String id) {
        return petStore.findById(id);
    }
}
