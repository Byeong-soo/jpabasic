package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaLazy {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            ProxyMember member1 = new ProxyMember();
            member1.setUsername("hello1");
            member1.setTeam(team);
            em.persist(member1);


            em.flush();
            em.clear();

            ProxyMember refMember = em.find(ProxyMember.class, member1.getId());

            System.out.println("refMember = "+refMember.getTeam().getClass());


            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }

}
