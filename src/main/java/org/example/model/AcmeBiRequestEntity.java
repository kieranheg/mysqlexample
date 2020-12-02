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
public class AcmeBiRequestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "acmeRequestEntitySequence")
    @Column(name = "are_id")
    private Long id;

    private String name;
    private String jobTitle;
    private String grade;

    // TODO Kieran.Hegarty 02/12/2020 09:07 > UNI-DIRECTIONAL WORKS, NO CHILD FK RETURNED
    @OneToMany(mappedBy="fk_AcmeBiRequestEntity", cascade = CascadeType.PERSIST)
    private List<BiPreviousRoleEntity> previousRoles;

    public AcmeBiRequestEntity(final AcmeBiEmployee acmeRequest) {
        this.setName(acmeRequest.getName());
        this.setJobTitle(acmeRequest.getJobTitle());
        this.setGrade(acmeRequest.getGrade());
        this.setPreviousRoles(buildPreviousRoleEntity(acmeRequest.getPreviousRoles()));
    }

    private List<BiPreviousRoleEntity> buildPreviousRoleEntity(final List<BiPreviousRole> previousRoles) {
        return previousRoles.stream().map(
                previousRole -> {
                    BiPreviousRoleEntity previousRoleEntity = new BiPreviousRoleEntity();
                    previousRoleEntity.setJobTitle(previousRole.getJobTitle());
                    previousRoleEntity.setGrade(previousRole.getGrade());
                    previousRoleEntity.setFk_AcmeBiRequestEntity(this);
                    return previousRoleEntity;
                }).collect(Collectors.toList());
    }

}
