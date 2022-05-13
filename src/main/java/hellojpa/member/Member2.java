package hellojpa.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;

    @Column(name = "name" , nullable = false)
    private String username;

    public Member2(){
    }

}
