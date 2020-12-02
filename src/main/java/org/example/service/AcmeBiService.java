package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.AcmeBiEmployee;
import org.example.model.AcmeBiRequestEntity;
import org.example.model.AcmeUniEmployee;
import org.example.model.AcmeUniRequestEntity;
import org.example.repository.AcmeBiRepo;
import org.example.repository.AcmeUniRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcmeBiService {

    private final AcmeBiRepo acmeRepo;

    public AcmeBiRequestEntity saveAcmeRequest(final AcmeBiEmployee acmeRequest) {
        AcmeBiRequestEntity acmeRequestEntity = new AcmeBiRequestEntity(acmeRequest);
        return acmeRepo.save(acmeRequestEntity);
    }

//    private AcmeRequestEntity buildAcmeRequestEntity(final AcmeRequest acmeRequest) {
//        AcmeRequestEntity acmeRequestEntity = new AcmeRequestEntity();
//        acmeRequestEntity.setName(acmeRequest.getName());
//        acmeRequestEntity.setJobTitle(acmeRequest.getJobTitle());
//        acmeRequestEntity.setGrade(acmeRequest.getGrade());
//        acmeRequestEntity.setPreviousRoles(buildPreviousRoleEntity(acmeRequest.getPreviousRoles(), acmeRequestEntity));
//
//        return acmeRequestEntity;
//    }
//
//    private List<PreviousRoleEntity> buildPreviousRoleEntity(final List<PreviousRole> previousRoles, final AcmeRequestEntity acmeRequestEntity) {
//        return previousRoles.stream().map(
//                previousRole -> {
//                    PreviousRoleEntity previousRoleEntity = new PreviousRoleEntity();
//                    previousRoleEntity.setJobTitle(previousRole.getJobTitle());
//                    previousRoleEntity.setGrade(previousRole.getGrade());
//                    previousRoleEntity.setAcmeRequestEntity(acmeRequestEntity);
//                    return previousRoleEntity;
//                }).collect(Collectors.toList());
//    }
}
