package me.remil.springpetclinic.services;

import me.remil.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}