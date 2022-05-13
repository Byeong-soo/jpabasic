package hellojpa;

import hellojpa.member.EmbeddedMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class embeddedJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Address address = new Address("city", "street", "1000");

            EmbeddedMember member = new EmbeddedMember();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            EmbeddedMember member2 = new EmbeddedMember();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

//            member.getHomeAddress().setCity("newCity");

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
