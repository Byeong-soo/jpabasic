package hellojpa.member;

import hellojpa.item.BaseEntity;
import hellojpa.item.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ProxyMember extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //@ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @ManyToOne(fetch = FetchType.EAGER) // 즉시로딩
    @JoinColumn
    private Team team;

}
