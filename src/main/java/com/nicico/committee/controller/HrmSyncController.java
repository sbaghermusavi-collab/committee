package com.nicico.committee.controller;//package com.nicico.committee.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fgostar.hrm.sdk.dto.SearchDTO;
//import com.fgostar.hrm.sdk.dto.TotalResponse;
//import com.fgostar.hrm.sdk.dto.base.CatalogDTO;
//import com.fgostar.hrm.sdk.dto.personnel.CompanyDTO;
//import com.fgostar.hrm.sdk.dto.personnel.PersonDTO;
//import com.fgostar.hrm.sdk.dto.personnel.PersonnelDTO;
//import com.fgostar.hrm.sdk.dto.tashkilat.CompanyStructureVersionDTO;
//import com.fgostar.hrm.sdk.dto.tashkilat.PostDTO;
//import com.fgostar.hrm.sdk.dto.tashkilat.PostPersonDTO;
//
//import com.nicico.committee.dto.*;
////import com.nicico.committee.service.HrmOrchestrationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * A centralized controller for "smart" synchronization with the HRM system.
// * Provides idempotent "find or create" endpoints that abstract away the complexity of the HRM SDK.
// */
//@RestController
//@RequestMapping("/api/hrm-sync")
//@RequiredArgsConstructor
//public class HrmSyncController {
//
//    private final HrmOrchestrationService orchestrationService;
//
//    // --- Company and Structure ---
//
//    @PostMapping("/company/find-or-create")
//    public ResponseEntity<CompanyDTO.Info> findOrCreateHrmCompany(@Valid @RequestBody CreateHrmCompanyRequest request) throws IOException {
//        CompanyDTO.Info result = orchestrationService.findOrCreateHrmCompany(request);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @PostMapping("/company-structure/find-or-create")
//    public ResponseEntity<CompanyStructureVersionDTO.Info> createCompanyStructureVersion(@Valid @RequestBody CreateHrmCompanyStructureRequest request) throws IOException {
//        CompanyStructureVersionDTO.Info result = orchestrationService.createCompanyStructureVersion(request);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//
//
//    // --- Post ---
//
//    @PostMapping("/post/find-or-create")
//    public ResponseEntity<PostDTO.Info> findOrCreatePost(@Valid @RequestBody CreateHrmPostRequest request) throws IOException {
//        PostDTO.Info result = orchestrationService.findOrCreatePost(request);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//
//    // --- Personnel and Person ---
//
//    @PostMapping("/personnel/find-or-create")
//    public ResponseEntity<PersonnelDTO.Info> findOrCreatePersonnel(@Valid @RequestBody CreateHrmPersonnelRequest request) throws IOException {
//        PersonnelDTO.Info result = orchestrationService.findOrCreatePersonnel(request);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @GetMapping("/personnel/by-post-code/{postCode}")
//    public ResponseEntity<List<PersonnelDTO.Info>> findPersonnelByPostCode(@PathVariable String postCode) throws IOException {
//        List<PersonnelDTO.Info> result = orchestrationService.findPersonnelByPostCode(postCode);
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping("/person/search")
//    public ResponseEntity<TotalResponse<PersonDTO.Info>> searchPersons(@RequestBody SearchDTO.SearchRq request) throws IOException {
//        return ResponseEntity.ok(orchestrationService.searchPersons(toSmartGwtQueryParams(request)));
//    }
//    @PostMapping("/person/create")
//    public ResponseEntity<PersonDTO.Info> createPerson(@Valid @RequestBody PersonDTO.Create request) throws IOException {
//        PersonDTO.Info result = orchestrationService.createPerson(request);
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//    public Map<String, Object> toSmartGwtQueryParams(SearchDTO.SearchRq rq) throws JsonProcessingException {
//        Map<String, Object> params = new LinkedHashMap<>();
//
//        // Pagination – SmartGWT style
//        if (rq.getStartIndex() != null) {
//            params.put("_startRow",
//                    rq.getStartIndex()
//            );
//        }
//
//        if (rq.getCount() != null) {
//            int start = rq.getStartIndex() != null ? rq.getStartIndex() : 0;
//            int endRow = start + rq.getCount();
//            params.put("_endRow",
//                    endRow
//            );
//        }
//
//
//        // Distinct (optional – rarely used)
//        if (Boolean.TRUE.equals(rq.getDistinct())) {
//            params.put("distinct", "true");
//        }
//
//        // Criteria – multiple entries if multiple conditions
//        if (rq.getCriteria() != null) {
//            // Assumption: CriteriaRq has a way to get list of individual conditions
//            // You need to adapt this part to your real CriteriaRq structure
//            params.put("criteria",new ObjectMapper().writeValueAsString(rq.getCriteria()));
//        }
//
//        // Optional: SmartGWT often sends these (backend usually ignores)
//        params.put("_operationType", "fetch");
//        // params.put("_operationId", Collections.singletonList("isc_MyRestDataSource_xx_fetch")); // usually dynamic
//
//        return params;
//    }
//    @PostMapping("/personnel/search")
//    public ResponseEntity<SearchDTO.SearchRs<PostPersonDTO.Info>> searchPersonnel(@RequestBody SearchDTO.SearchRq request) throws IOException {
//        return ResponseEntity.ok(orchestrationService.searchPersonnel(request));
//    }
//
//    // --- Assignment ---
//
//    @PostMapping("/assignment/assign")
//    public ResponseEntity<PostPersonDTO.Info> assignPersonToPost(@Valid @RequestBody AssignPersonToPostRequest request) throws IOException {
//        PostPersonDTO.Info result = orchestrationService.assignPersonToPost(request);
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//
//    // --- Catalog ---
//
//    @GetMapping("/catalogs/children-by-code/{code}")
//    public ResponseEntity<List<CatalogDTO.Info>> getCatalogChildrenByCode(@PathVariable String code) throws IOException {
//        return ResponseEntity.ok(orchestrationService.getCatalogChildrenByCode(code));
//    }
//
//    @PostMapping("/catalogs/search")
//    public ResponseEntity<SearchDTO.SearchRs<CatalogDTO.Info>> searchCatalogs(@RequestBody SearchDTO.SearchRq request) throws IOException {
//        return ResponseEntity.ok(orchestrationService.searchCatalogs(request));
//    }
//}
