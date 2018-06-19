package main.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T extends CrudRepository<C, Long>, C> {

    private T dao;

    protected BaseService(T dao) {
        this.dao = dao;
    }

    public List<C> findAll() {
        return (List<C>) this.dao.findAll();
    }

    public Optional<C> findById(Long id) {
        return this.dao.findById(id);
    }

    public C updateEntity(C entity) {
        return this.dao.save(entity);
    }

    public List<C> updateEntities(List<C> entity) {
        return (List<C>) this.dao.saveAll(entity);
    }

    public void deleteById(Long id) {
        this.dao.deleteById(id);
    }

    public void deleteAll() {
        this.dao.deleteAll();
    }

    public C save(C c) {
        return this.dao.save(c);
    }

    public List<C> saveAll(List<C> cs) {
        return (List<C>) this.dao.saveAll(cs);
    }

    public T getDao() {
        return dao;
    }
}
