package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 연관관계에 쓰일 Member 객체
@Entity
@Getter @Setter
public class Member3 extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

//    public void changeTeam(Team team) {
//        this.team = team;
//
//        team.getMembers().add(this);
//    }

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @Override
    public String toString() {
        return "Member3{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
