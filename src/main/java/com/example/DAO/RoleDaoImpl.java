package com.example.DAO;

import com.example.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public Role findByRole(String role) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where role = :role", Role.class);
        query.setParameter("role", role);
        return query.getSingleResult();
    }

    @Override
    public Role findByRole(Long id) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where id = :id", Role.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.getReference(Role.class, id);
    }

    @Override
    public Long countRoles(String name) {
        return(Long) entityManager.createQuery("SELECT COUNT(*) FROM Role where name = :name")
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
