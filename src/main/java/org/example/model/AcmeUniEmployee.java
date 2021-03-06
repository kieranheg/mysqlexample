package org.example.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcmeUniEmployee {
    private String name;
    private String jobTitle;
    private String grade;

    private List<UniPreviousRole> previousRoles;
}
