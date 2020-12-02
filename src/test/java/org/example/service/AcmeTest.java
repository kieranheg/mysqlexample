package org.example.service;

import org.example.model.AcmeRequest;
import org.example.model.AcmeRequestEntity;
import org.example.model.PreviousRole;
import org.example.model.PreviousRoleEntity;
import org.example.repository.AcmeRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AcmeTest {

    @Autowired
    private AcmeService acmeService;

    @Autowired
    private AcmeRepo acmeRepo;

    @Before
    public void clearDatabase() {
        acmeRepo.deleteAll();
    }

    @Test
    public void saveMetricTest() {
        AcmeRequest acmeRequest = buildAcmeRequest();

        AcmeRequestEntity savedAcmeRequestEntity = acmeService.saveAcmeRequest(acmeRequest);

        AcmeRequestEntity expectedMetric = buildAcmeRequestEntity(acmeRequest);
//        assertNotNull(savedAcmeRequestEntity.getAre_id());
//        expectedMetric.setAre_id(savedAcmeRequestEntity.getAre_id());
//        assertEquals(expectedMetric, savedAcmeRequestEntity);

        List<AcmeRequestEntity> acmeRequestEntities = acmeRepo.findAll();
        assertFalse(acmeRequestEntities.isEmpty());
        assertEquals(1, acmeRequestEntities.size());

        assertEquals(3, acmeRequestEntities.get(0).getPreviousRoles().size());
    }

    private AcmeRequest buildAcmeRequest() {
        AcmeRequest acmeRequest = new AcmeRequest();
        acmeRequest.setName("James Jones");
        acmeRequest.setJobTitle("Lead Engineer");
        acmeRequest.setGrade("D");
        acmeRequest.setPreviousRoles(buildPreviousRoleList());
        return acmeRequest;
    }

    private List<PreviousRole> buildPreviousRoleList() {
        return asList(
                PreviousRole.builder().jobTitle("Graduate Trainee").grade("A1").build(),
                PreviousRole.builder().jobTitle("Software Engineer").grade("B1").build(),
                PreviousRole.builder().jobTitle("Senior Software Engineer").grade("C1").build()
        );
    }

    private AcmeRequestEntity buildAcmeRequestEntity(final AcmeRequest acmeRequest) {
        AcmeRequestEntity acmeRequestEntity = new AcmeRequestEntity();
        acmeRequestEntity.setName(acmeRequest.getName());
        acmeRequestEntity.setJobTitle(acmeRequest.getJobTitle());
        acmeRequestEntity.setGrade(acmeRequest.getGrade());
        acmeRequestEntity.setPreviousRoles(buildPreviousRoleEntity(acmeRequest.getPreviousRoles(), acmeRequestEntity));

        return acmeRequestEntity;
    }

    private List<PreviousRoleEntity> buildPreviousRoleEntity(final List<PreviousRole> previousRoles, final AcmeRequestEntity acmeRequestEntity) {
        return previousRoles.stream().map(
                previousRole -> {
                    PreviousRoleEntity previousRoleEntity = new PreviousRoleEntity();
                    previousRoleEntity.setJobTitle(previousRole.getJobTitle());
                    previousRoleEntity.setGrade(previousRole.getGrade());
//                    previousRoleEntity.setXxxxId(acmeRequestEntity.getId());
                    return previousRoleEntity;
                }).collect(Collectors.toList());
    }
}
