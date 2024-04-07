package com.example.UsersServices.Entity;
import com.example.UsersServices.Enum.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name= "First_name")
    private String firstName;
    @NonNull
    @Column(name= "Last_name")
    private String lastName;
    @NonNull
    @Column(name = "Genre")
    private String genre;
    @NonNull
    @Column(name = "Email", unique = true)
    private String email;
    @NonNull
    @Column(name = "Phone")
    private String phone;
    @NonNull
    private String password;
    private UserRole userRole;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "delete_at")
    @UpdateTimestamp
    private Date deleteAt;
}
