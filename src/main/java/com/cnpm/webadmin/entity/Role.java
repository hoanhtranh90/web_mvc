package com.cnpm.webadmin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author sadfsafbhsaid
 */
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class Role extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2679118992937555761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "description")
    private String description;

    @Column(name = "role_code", nullable = false, length = 150,
            columnDefinition = "varchar(150)", updatable = false)
    private String roleCode;

    @Column(name = "role_name", nullable = false, length = 150,
            columnDefinition = "nvarchar(150)", updatable = false)
    private String roleName;

    @Column(name = "status", nullable = false, updatable = false, columnDefinition = "bigint default 1")
    private Long status = 1L;

    @Column(name = "role_level")
    private Integer roleLevel;

    @JsonBackReference
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserRole> userRoleList;

    @Column(name = "is_delete", columnDefinition = "bigint default 0")
    private Long isDelete = 0L;

    public Role(Long roleId) {
        this.roleId = roleId;
    }

    public Role(Long roleId, String description, String roleCode, String roleName, Long status, Date createdDate, Date modifiedDate, String createdBy, String modifiedBy) {
        this.roleId = roleId;
        this.description = description;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public Role(Long roleId, String description, String roleCode, String roleName, Long status) {
        this.roleId = roleId;
        this.description = description;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.status = status;
    }

}
