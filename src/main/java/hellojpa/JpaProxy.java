package hellojpa;

import hellojpa.item.Team;
import hellojpa.member.Member3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaProxy {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member3 member1 = new Member3();
            member1.setUsername("hello1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member3 refMember = em.getReference(Member3.class, member1.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass());
            refMember.getUsername();
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            //Hibernate.initialize(refMember); //강제초기화

//
//            Member3 member2 = new Member3();
//            member2.setUsername("hello2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member3 m1 = em.find(Member3.class, member1.getId());
//            Member3 m2 = em.getReference(Member3.class, member2.getId());
//
//            System.out.println("m1 == m2: "+(m1 instanceof Member3));
//            System.out.println("m1 == m2: "+(m2 instanceof Member3));

//            Member3 findMember = em.find(Member3.class, member.getId());
//            Member3 findMember = em.getReference(Member3.class, member.getId());

//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());


//            Member3 member = em.find(Member3.class, 1L);
//            printMemberAndTeam(member);
//            printMember(member);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member3 member) {
        System.out.println("member.getUsername() = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member3 member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
    }
}
