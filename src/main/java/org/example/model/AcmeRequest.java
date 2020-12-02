package org.example.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcmeRequest {
    private String name;
    private String jobTitle;
    private String grade;

    private List<PreviousRole> previousRoles;
}
