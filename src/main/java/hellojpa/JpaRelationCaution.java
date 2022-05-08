package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaRelationCaution {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 역방향(주인이 아닌방향) 만 연관관계 설정
            // 양방향 매핑시 연관관계의 주인에 값을 입력해야한다.
            // (순수한 객체 관계를 교려한다면 항상 양쪽에 값을 입력해야 한다.)

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member3 member = new Member3();
            member.setUsername("member1");
            member.setTeam(team);
//            member.changeTeam(team);
            em.persist(member);

            //객체에 편의메소드 넣어둠.
            //편의메소드는 한쪽에서만 넣는게 좋음. 무한루프에 빠질수 있다.
            //N이나 1 어느쪽에 넣어도 상관없음 개인이 정하면됌.
            //상황에 맞게 정하기.
            //team.getMembers().add(member);
            team.addMember(member);
            
            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member3> members = findTeam.getMembers();
            for (Member3 m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
