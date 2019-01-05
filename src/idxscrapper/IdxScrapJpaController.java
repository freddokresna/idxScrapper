/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idxscrapper;

import entity.IdxScrap;
import idxscrapper.exceptions.NonexistentEntityException;
import idxscrapper.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author freddo
 */
public class IdxScrapJpaController implements Serializable {

    public IdxScrapJpaController() {
    }

    public IdxScrapJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IdxScrap idxScrap) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(idxScrap);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIdxScrap(idxScrap.getKodeSaham()) != null) {
                throw new PreexistingEntityException("IdxScrap " + idxScrap + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IdxScrap idxScrap) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            idxScrap = em.merge(idxScrap);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = idxScrap.getKodeSaham();
                if (findIdxScrap(id) == null) {
                    throw new NonexistentEntityException("The idxScrap with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IdxScrap idxScrap;
            try {
                idxScrap = em.getReference(IdxScrap.class, id);
                idxScrap.getKodeSaham();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The idxScrap with id " + id + " no longer exists.", enfe);
            }
            em.remove(idxScrap);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IdxScrap> findIdxScrapEntities() {
        return findIdxScrapEntities(true, -1, -1);
    }

    public List<IdxScrap> findIdxScrapEntities(int maxResults, int firstResult) {
        return findIdxScrapEntities(false, maxResults, firstResult);
    }

    private List<IdxScrap> findIdxScrapEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IdxScrap.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public IdxScrap findIdxScrap(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IdxScrap.class, id);
        } finally {
            em.close();
        }
    }

    public int getIdxScrapCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IdxScrap> rt = cq.from(IdxScrap.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
