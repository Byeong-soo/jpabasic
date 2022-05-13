package hellojpa;

import hellojpa.member.EmbeddedMember;
import hellojpa.member.Member;
import hellojpa.member.ValueTypeMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ValueTypeJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            ValueTypeMember member = new ValueTypeMember();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street","10000"));
            member.getAddressHistory().add(new AddressEntity("old2","street","10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("============START=============");
            ValueTypeMember findMember = em.find(ValueTypeMember.class, member.getId());

//            homeCity -> newCity
//            set로 값 변경하면 사이트이펙트 발생할수있음. 좋은방법이 아님.
//            findMember.getHomeAddress().setCity("newCity");
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcode()));

//            치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

//            findMember.getAddressHistory().remove(new Address("old1","street","10000"));
//            findMember.getAddressHistory().add(new AddressEntity("newCity1","street","10000"));



//            조회! 값 타입 조회는 전부 지연로딩!(디폴트)
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

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
