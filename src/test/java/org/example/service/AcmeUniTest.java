package org.example.service;

import org.example.model.AcmeUniEmployee;
import org.example.model.AcmeUniRequestEntity;
import org.example.model.UniPreviousRole;
import org.example.repository.AcmeUniRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AcmeUniTest {

    @Autowired
    private AcmeUniService acmeUniService;

    @Autowired
    private AcmeUniRepo acmeUniRepo;

    @Before
    public void clearDatabase() {
        acmeUniRepo.deleteAll();
    }

    @Test
    public void saveMetricTest() {
        AcmeUniEmployee acmeRequest = buildAcmeUniRequest();

        AcmeUniRequestEntity saveAcmeUniRequest = acmeUniService.saveAcmeRequest(acmeRequest);

        List<AcmeUniRequestEntity> acmeUniRequestEntities = acmeUniRepo.findAll();
        assertFalse(acmeUniRequestEntities.isEmpty());
        assertEquals(1, acmeUniRequestEntities.size());
        assertEquals(3, acmeUniRequestEntities.get(0).getPreviousRoles().size());
    }

    private AcmeUniEmployee buildAcmeUniRequest() {
        AcmeUniEmployee acmeRequest = new AcmeUniEmployee();
        acmeRequest.setName("James Jones");
        acmeRequest.setJobTitle("Lead Engineer");
        acmeRequest.setGrade("D");
        acmeRequest.setPreviousRoles(buildPreviousRoleList());
        return acmeRequest;
    }

    private List<UniPreviousRole> buildPreviousRoleList() {
        return asList(
                UniPreviousRole.builder().jobTitle("Graduate Trainee").grade("A1").build(),
                UniPreviousRole.builder().jobTitle("Software Engineer").grade("B1").build(),
                UniPreviousRole.builder().jobTitle("Senior Software Engineer").grade("C1").build()
        );
    }
}
