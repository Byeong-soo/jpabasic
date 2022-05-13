package hellojpa.member;

import hellojpa.item.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    //매핑
    @Id
    private Long id;
    @Column(name = "name")
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    public Member(){

    }
}

