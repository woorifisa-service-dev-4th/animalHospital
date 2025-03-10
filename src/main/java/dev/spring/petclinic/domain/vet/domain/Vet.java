package dev.spring.petclinic.domain.vet.domain;

import lombok.Getter;
import lombok.ToString;
import javax.persistence.*;



@Getter
@Table(name = "vets")
@Entity
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}
