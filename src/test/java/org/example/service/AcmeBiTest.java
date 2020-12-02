package org.example.service;

import org.example.model.*;
import org.example.repository.AcmeBiRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AcmeBiTest {

    @Autowired
    private AcmeBiService acmeBiService;

    @Autowired
    private AcmeBiRepo acmeBiRepo;

    @Before
    public void clearDatabase() {
        acmeBiRepo.deleteAll();
    }

    @Test
    public void saveMetricTest() {
        AcmeBiEmployee acmeRequest = buildAcmeBiRequest();

        AcmeBiRequestEntity savedAcmeRequest = acmeBiService.saveAcmeRequest(acmeRequest);

        List<AcmeBiRequestEntity> acmeBiRequestEntities = acmeBiRepo.findAll();
        assertFalse(acmeBiRequestEntities.isEmpty());
        assertEquals(1, acmeBiRequestEntities.size());
        assertEquals(3, acmeBiRequestEntities.get(0).getPreviousRoles().size());
    }

    private AcmeBiEmployee buildAcmeBiRequest() {
        AcmeBiEmployee acmeRequest = new AcmeBiEmployee();
        acmeRequest.setName("James Jones");
        acmeRequest.setJobTitle("Lead Engineer");
        acmeRequest.setGrade("D");
        acmeRequest.setPreviousRoles(buildPreviousRoleList());
        return acmeRequest;
    }

    private List<BiPreviousRole> buildPreviousRoleList() {
        return asList(
                BiPreviousRole.builder().jobTitle("Graduate Trainee").grade("A1").build(),
                BiPreviousRole.builder().jobTitle("Software Engineer").grade("B1").build(),
                BiPreviousRole.builder().jobTitle("Senior Software Engineer").grade("C1").build()
        );
    }
}
