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
public class AcmeUniRequestEntity implements Serializable {
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
    private List<UniPreviousRoleEntity> previousRoles;

    public AcmeUniRequestEntity(final AcmeUniEmployee acmeRequest) {
        this.setName(acmeRequest.getName());
        this.setJobTitle(acmeRequest.getJobTitle());
        this.setGrade(acmeRequest.getGrade());
        this.setPreviousRoles(buildPreviousRoleEntity(acmeRequest.getPreviousRoles()));
    }

    private List<UniPreviousRoleEntity> buildPreviousRoleEntity(final List<UniPreviousRole> previousRoles) {
        return previousRoles.stream().map(
                previousRole -> {
                    UniPreviousRoleEntity previousRoleEntity = new UniPreviousRoleEntity();
                    previousRoleEntity.setJobTitle(previousRole.getJobTitle());
                    previousRoleEntity.setGrade(previousRole.getGrade());
                    return previousRoleEntity;
                }).collect(Collectors.toList());
    }

}
