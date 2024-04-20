package com.example.ProductsServices.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "sous-categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SousCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

   // @JsonManagedReference
   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;

}
