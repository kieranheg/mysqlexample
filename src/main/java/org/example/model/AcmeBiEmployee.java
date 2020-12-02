package org.example.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcmeBiEmployee {
    private String name;
    private String jobTitle;
    private String grade;

    private List<BiPreviousRole> previousRoles;
}
