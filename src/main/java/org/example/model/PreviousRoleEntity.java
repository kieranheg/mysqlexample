package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@TableGenerator(name = "previousRoleEntitySequence", initialValue = 75)
public class PreviousRoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "previousRoleEntitySequence")
    @Column(name = "pk_id")
    private Long id;

    private String jobTitle;
    private String grade;

    // TODO Kieran.Hegarty 02/12/2020 09:07 > NO column for FK WORKS

}

