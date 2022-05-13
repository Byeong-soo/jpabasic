package hellojpa.member;

import hellojpa.Address;
import hellojpa.Period;
import hellojpa.item.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
public class EmbeddedMember{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //기간 Period
    @Embedded
    private Period workPeriod;
   //주소
    @Embedded
    private Address homeAddress;
    }
