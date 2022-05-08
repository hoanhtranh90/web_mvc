package com.cnpm.webadmin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author DELL
 */
@Entity
@Table(name = "user")
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 6174016284429444079L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "phone_number"/* , unique = true */, length = 25)
    private String phoneNumber;

    @Column(name = "email" , unique = true ,nullable = false, length = 40)
    private String email;

    @Column(name = "username", unique = true,  updatable = false, length = 25)
    private String userName;

    @Column(name = "fullname", length = 100)
    private String fullName;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "status", nullable = false, columnDefinition = "bigint default 1")
    private Long status = 1L;

    @Column(name = "is_delete", columnDefinition = "bigint default 0")
    private Long isDelete = 0L;

    @Comment("Giới tính")
    @Column(name = "sex")
    private Integer sex;



    @Column(name = "address", length = 255, columnDefinition = "nvarchar(255)")
    private String address;


//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRole> userRoleList;


    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;


    @Transient
    private String checkPassword;

    public User(String phoneNumber, String email, String userName) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
    }

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User(Long userId, String userName, String fullName) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
    }

    public User(Long userId, String username, Long status,
                Date createdDate) {
        super();
        this.userId = userId;
        this.userName = username;
        this.status = status;
        this.createdDate = createdDate;
    }

}
