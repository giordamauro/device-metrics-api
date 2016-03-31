package org.unicen.dmetrics.firebase;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

public interface FirebaseRepository<E, T extends Serializable> extends CrudRepository<E, T> {

}
