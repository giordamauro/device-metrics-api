package org.unicen.dmetrics.firebase;

import java.io.Serializable;

public class SimpleFirebaseCrudRepository<E, T extends Serializable> implements FirebaseRepository<E, T> {

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(T arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(E arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Iterable<? extends E> arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean exists(T arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterable<E> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<E> findAll(Iterable<T> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E findOne(T arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends E> S save(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends E> Iterable<S> save(Iterable<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
