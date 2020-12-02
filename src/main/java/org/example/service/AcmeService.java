package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.AcmeRequest;
import org.example.model.AcmeRequestEntity;
import org.example.repository.AcmeRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcmeService {

    private final AcmeRepo acmeRepo;

    public AcmeRequestEntity saveAcmeRequest(final AcmeRequest acmeRequest) {
        AcmeRequestEntity acmeRequestEntity = new AcmeRequestEntity(acmeRequest);
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
