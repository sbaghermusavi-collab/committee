package com.nicico.committee.service;//package com.nicico.committee.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fgostar.hrm.sdk.client.*;
//import com.fgostar.hrm.sdk.dto.EOperator;
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
//import com.nicico.committee.config.ApplicationException;
//import com.nicico.committee.dto.*;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.temporal.ChronoUnit;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class HrmOrchestrationService {
//
//    private final CompanyClient companyClient;
//    private final PostClient postClient;
//    private final CatalogClient catalogClient;
//    private final PersonClient personClient;
//    private final PersonnelClient personnelClient;
//    private final PostPersonClient postPersonClient;
//    private final CompanyStructureVersionClient companyStructureVersionClient;
//    private final ObjectMapper objectMapper;
//
//    @Transactional(readOnly = true)
//    public CompanyDTO.InfoLight getHrmCompanyByCode(String companyCode) throws IOException {
//        return companyClient.getCompanyByCode(companyCode).execute().body();
//    }
//
//    @Transactional(readOnly = true)
//    public long countPostPersonsByGroupCode(Long companyId, String groupCode) throws IOException {
//        SearchDTO.SearchRq sr = new SearchDTO.SearchRq().setStartIndex(0).setCount(10);
//
//        SearchDTO.CriteriaRq crCompany = new SearchDTO.CriteriaRq()
//                .setFieldName("companyId").setOperator(EOperator.equals).setValue(companyId);
//
//        SearchDTO.CriteriaRq crGroupViaPost = new SearchDTO.CriteriaRq()
//                .setFieldName("post.groupCode").setOperator(EOperator.equals).setValue(groupCode);
//
//
//        sr.setCriteria(new SearchDTO.CriteriaRq().setOperator(EOperator.and)
//                .setCriteria(List.of(crCompany, crGroupViaPost)));
//
//        SearchDTO.SearchRs<PostPersonDTO.Info> result = postPersonClient.search(sr).execute().body();
//        if (result == null || result.getTotalCount() == null) {
//            return 0L;
//        }
//        return result.getTotalCount();
//    }
//
//    @Transactional(readOnly = true)
//    public boolean companyHasPostGroupCode(Long companyId, String groupCode) throws IOException {
//        return countPostPersonsByGroupCode(companyId, groupCode) > 0;
//    }
//
////    private static final String COMMON_ACTION_DATE_STR = "1767225600000"; // "2026-01-01T00:00:00Z"
//    private static final Instant COMMON_BIRTH_DATE_INSTANT = Instant.now().atZone(ZoneId.systemDefault()).minus(35, ChronoUnit.YEARS).toInstant();
//    private static final Instant COMMON_ACTION_DATE_STR = Instant.now().atZone(ZoneId.systemDefault()).minus(20, ChronoUnit.YEARS).toInstant();
//    private static final Instant COMMON_EMP_DATE_INSTANT = Instant.now().atZone(ZoneId.systemDefault()).minus(2, ChronoUnit.YEARS).toInstant();
//    private static final Date COMMON_BIRTH_DATE_UTIL = Date.from(COMMON_BIRTH_DATE_INSTANT);
//    private static final Date COMMON_EMP_DATE_UTIL = Date.from(COMMON_EMP_DATE_INSTANT);
//    private static final Date COMMON_ACTION_DATE_UTIL = Date.from(COMMON_ACTION_DATE_STR);
//
//    // --- Company and Structure Orchestration ---
//
//    @Transactional
//    public CompanyDTO.Info findOrCreateHrmCompany(CreateHrmCompanyRequest request) throws IOException {
//        SearchDTO.SearchRq searchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("code")
//                        .setOperator(EOperator.equals)
//                        .setValue(request.getEconomicCode())
//        );
//        SearchDTO.SearchRs<CompanyDTO.Info> searchResult = companyClient.search(searchRq).execute().body();
//
//        if (searchResult != null && !searchResult.getList().isEmpty()) {
//            log.info("Found existing HRM company with economic code: {}", request.getEconomicCode());
//            return searchResult.getList().get(0);
//        }
//
//        log.info("Creating new HRM company: {}", request.getName());
//        CompanyDTO.Create createRequest = new CompanyDTO.Create()
//                .setName(request.getName())
//                .setCode(request.getEconomicCode());
//        return companyClient.create(createRequest).execute().body();
//    }
//
//    @Transactional
//    public CompanyStructureVersionDTO.Info createCompanyStructureVersion(CreateHrmCompanyStructureRequest request) throws IOException {
//        CompanyStructureVersionDTO.Create createRequest = new CompanyStructureVersionDTO.Create()
//                .setCompanyId(request.getCompanyId())
//                .setCode(request.getCode())
//                .setStartDate(COMMON_ACTION_DATE_UTIL);
//
//        log.info("Creating new company structure version '{}' for company ID {}", request.getName(), request.getCompanyId());
//        return companyStructureVersionClient.create(createRequest, String.valueOf(COMMON_ACTION_DATE_STR.toEpochMilli()), false).execute().body();
//    }
//
//
//    // --- Post Orchestration ---
//
//    @Transactional
//    public PostDTO.Info findOrCreatePost(CreateHrmPostRequest request) throws IOException {
//        return findOrCreatePost(request.getPostRoleCode(), request.getTitle(), request.getCompanyId(), null);
//    }
//
//
//    private PostDTO.Info findOrCreatePost(PostRoleCode roleCode, String title, Long companyId, Long parentId) throws IOException {
//        String postCode = roleCode.getCode();
//        SearchDTO.SearchRq searchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("code")
//                        .setOperator(EOperator.equals)
//                        .setValue(postCode)
//        );
//        SearchDTO.SearchRs<PostDTO.Info> searchResult = postClient.search(searchRq).execute().body();
//        if (searchResult != null && !searchResult.getList().isEmpty()) {
//            log.info("Found existing post with code: {}", postCode);
//            return searchResult.getList().get(0);
//        }
//        log.info("Creating new post with code: {}", postCode);
//        String postTitle = StringUtils.hasText(title) ? title : roleCode.getFaTitle();
//        PostDTO.Create createRequest = new PostDTO.Create()
//                .setTitle(postTitle).setGroupCode(roleCode.getCode())
//                .setCompanyId(companyId).setParentId(parentId);
//        return postClient.create(createRequest, String.valueOf(COMMON_ACTION_DATE_STR.toEpochMilli()), false, 0L, null).execute().body();
//    }
//
//    // --- Personnel Orchestration ---
//
//    @Transactional
//    public PersonnelDTO.Info findOrCreatePersonnel(CreateHrmPersonnelRequest request) throws IOException {
//        PersonDTO.Info person = findOrCreatePerson(request);
//        return findOrCreatePersonnelRecord(request, person.getId());
//    }
//
//    private PersonDTO.Info findOrCreatePerson(CreateHrmPersonnelRequest request) throws IOException {
//        PersonDTO.Info existingPerson = personClient.getByNationalCode(request.getNationalCode()).execute().body();
//        if (Objects.nonNull(existingPerson)) {
//            log.info("Found existing person with national code: {}", request.getNationalCode());
//            return existingPerson;
//        }
//        log.info("Creating new person: {} {}", request.getFirstName(), request.getLastName());
//        String fatherName = StringUtils.hasText(request.getFatherName()) ? request.getFatherName() : "DefaultFather";
//        Long genderId = request.getGenderId() != null ? request.getGenderId() : 162L;
//        PersonDTO.Create createRequest = new PersonDTO.Create().setFirstName(request.getFirstName()).setLastName(request.getLastName()).setNationalCode(request.getNationalCode()).setFatherName(fatherName).setGenderId(genderId).setBirthDate(COMMON_BIRTH_DATE_UTIL);
//        return personClient.create(createRequest).execute().body();
//    }
//    @Transactional
//    public PersonDTO.Info createPerson(PersonDTO.Create request) throws IOException {
//        return personClient.create(request).execute().body();
//    }
//    private PersonnelDTO.Info findOrCreatePersonnelRecord(CreateHrmPersonnelRequest request, Long personId) throws IOException {
//        SearchDTO.SearchRq searchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("personId")
//                        .setOperator(EOperator.equals)
//                        .setValue(personId.toString())
//        );
//        SearchDTO.SearchRs<PersonnelDTO.Info> searchResult = personnelClient.search(searchRq).execute().body();
//        if (searchResult != null && !searchResult.getList().isEmpty()) {
//            log.info("Found existing personnel record for personId: {}", personId);
//            return searchResult.getList().get(0);
//        }
//        log.info("Creating new personnel record for personId: {}", personId);
//        PersonnelDTO.Create createRequest = new PersonnelDTO.Create().setPersonId(personId).setPersonnelCode(request.getPersonnelCode()).setCompanyId(request.getCompanyId()).setStartDate(COMMON_EMP_DATE_UTIL).setEmpStartDate(COMMON_EMP_DATE_UTIL);
//        return personnelClient.create(createRequest).execute().body();
//    }
//
//    @Transactional(readOnly = true)
//    public List<PersonnelDTO.Info> findPersonnelByPostCode(String postCode) throws IOException {
//        SearchDTO.SearchRq postSearchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("code")
//                        .setOperator(EOperator.equals)
//                        .setValue(postCode)
//        );
//        SearchDTO.SearchRs<PostDTO.Info> postSearchResult = postClient.search(postSearchRq).execute().body();
//        if (postSearchResult == null || postSearchResult.getList().isEmpty()) {
//            log.warn("No post found with code: {}", postCode);
//            return Collections.emptyList();
//        }
//        PostDTO.Info post = postSearchResult.getList().get(0);
//        SearchDTO.SearchRq assignmentSearchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("postId")
//                        .setOperator(EOperator.equals)
//                        .setValue(post.getId().toString())
//        );
//        SearchDTO.SearchRs<PostPersonDTO.Info> assignmentResult = postPersonClient.search(assignmentSearchRq).execute().body();
//        if (assignmentResult == null || assignmentResult.getList().isEmpty()) {
//            log.info("No personnel assigned to post with code: {}", postCode);
//            return Collections.emptyList();
//        }
//        List<Long> personnelIds = assignmentResult.getList().stream().map(PostPersonDTO.Info::getPersonnelId).collect(Collectors.toList());
//        SearchDTO.SearchRq personnelSearchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("id")
//                        .setOperator(EOperator.inSet)
//                        .setValue(personnelIds)
//        );
//        SearchDTO.SearchRs<PersonnelDTO.Info> personnelResult = personnelClient.search(personnelSearchRq).execute().body();
//        return personnelResult != null ? personnelResult.getList() : Collections.emptyList();
//    }
//
//    @Transactional(readOnly = true)
//    public TotalResponse<PersonDTO.Info> searchPersons(Map<String,Object> request) throws IOException {
//
//        return personClient.search(
//                request ).execute().body();
//    }
//
//    @Transactional(readOnly = true)
//    public SearchDTO.SearchRs<PostPersonDTO.Info> searchPersonnel(SearchDTO.SearchRq request) throws IOException {
//        return postPersonClient.search(request).execute().body();
//    }
//
//    // --- Assignment Orchestration ---
//
//    @Transactional
//    public PostPersonDTO.Info assignPersonToPost(AssignPersonToPostRequest request) throws IOException {
//        PersonDTO.Info person = personClient.getByNationalCode(request.getNationalCode()).execute().body();
//        if (person == null) {
//            throw new ApplicationException(HttpStatus.NOT_FOUND, "person.not.found.with.national.code", new Object[]{request.getNationalCode()});
//        }
//        SearchDTO.SearchRq postSearchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("code")
//                        .setOperator(EOperator.equals)
//                        .setValue(request.getPostCode())
//        );
//        SearchDTO.SearchRs<PostDTO.Info> postSearchResult = postClient.search(postSearchRq).execute().body();
//        if (postSearchResult == null || postSearchResult.getList().isEmpty()) {
//            throw new ApplicationException(HttpStatus.NOT_FOUND, "post.not.found.with.code", new Object[]{request.getPostCode()});
//        }
//        PostDTO.Info post = postSearchResult.getList().get(0);
//        SearchDTO.SearchRq personnelSearchRq = new SearchDTO.SearchRq().setCriteria(
//                new SearchDTO.CriteriaRq()
//                        .setFieldName("personId")
//                        .setOperator(EOperator.equals)
//                        .setValue(person.getId().toString())
//        );
//        SearchDTO.SearchRs<PersonnelDTO.Info> personnelResult = personnelClient.search(personnelSearchRq).execute().body();
//        if (personnelResult == null || personnelResult.getList().isEmpty()) {
//            throw new ApplicationException(HttpStatus.NOT_FOUND, "personnel.record.not.found.for.person", new Object[]{person.getId()});
//        }
//        PersonnelDTO.Info personnel = personnelResult.getList().get(0);
//        PostPersonDTO.Create createRequest = new PostPersonDTO.Create().setPostId(post.getId())
//                .setPersonnelId(personnel.getId())
//                .setCompanyId(request.getCompanyId()).setStartDate(COMMON_EMP_DATE_UTIL).setEndDate(COMMON_ACTION_DATE_UTIL);
//        log.info("Assigning person {} to post {}", person.getId(), post.getId());
//        return (PostPersonDTO.Info) postPersonClient.create(createRequest, null, false, true).execute().body();
//    }
//
//    // --- Catalog Orchestration ---
//
//    @Transactional(readOnly = true)
//    public List<CatalogDTO.Info> getCatalogChildrenByCode(String code) throws IOException {
//        return catalogClient.getChildrenByCode(code).execute().body();
//    }
//
//    @Transactional(readOnly = true)
//    public SearchDTO.SearchRs<CatalogDTO.Info> searchCatalogs(SearchDTO.SearchRq request) throws IOException {
//        return catalogClient.search(request).execute().body();
//    }
//}
