package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TableGenerator(name = "acmeRequestEntitySequence", initialValue = 99)
@JsonIgnoreProperties({"previousRoles"})
public class AcmeRequestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "acmeRequestEntitySequence")
    @Column(name = "are_id")
    private Long id;

    private String name;
    private String jobTitle;
    private String grade;

    // TODO Kieran.Hegarty 02/12/2020 09:07 > UNI-DIRECTIONAL WORKS, NO CHILD FK RETURNED
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_are_id")
    private List<PreviousRoleEntity> previousRoles;

    public AcmeRequestEntity(final AcmeRequest acmeRequest) {
        this.setName(acmeRequest.getName());
        this.setJobTitle(acmeRequest.getJobTitle());
        this.setGrade(acmeRequest.getGrade());
        this.setPreviousRoles(buildPreviousRoleEntity(acmeRequest.getPreviousRoles()));
    }

    private List<PreviousRoleEntity> buildPreviousRoleEntity(final List<PreviousRole> previousRoles) {
        return previousRoles.stream().map(
                previousRole -> {
                    PreviousRoleEntity previousRoleEntity = new PreviousRoleEntity();
                    previousRoleEntity.setJobTitle(previousRole.getJobTitle());
                    previousRoleEntity.setGrade(previousRole.getGrade());
//                    previousRoleEntity.setAcmeRequestFK(this.getId());
                    return previousRoleEntity;
                }).collect(Collectors.toList());
    }

}
