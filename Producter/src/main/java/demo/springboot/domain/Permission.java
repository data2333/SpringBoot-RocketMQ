package demo.springboot.domain;



import javax.persistence.*;

@Entity
@Table(name = "t_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String permissionname;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;// 一个权限对应一个角色

    // 省略 get set


    public Integer getId() {
        return id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public Role getRole() {
        return role;
    }
}