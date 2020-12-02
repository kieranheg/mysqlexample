package org.example.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreviousRole {
    private String jobTitle;
    private String grade;
}
