package com.example.placement_backend.controllers;

public class CompanyController {
    @Autowired UsersService uservice;
    @Autowired CompanyService cservice;
    @Autowired JobApplyService jservice;
    @Autowired StudentService sservice;
    @Autowired EmailService email;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CompanyDTO dto) {
        int id=cservice.saveCompany(CompanyDTO.toEntity(dto));
        Users user=new Users(dto.getEmail(),dto.getCname(),dto.getPwd(),id,"Company",true);
        uservice.AddUser(user);
        return Response.success("Company created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CompanyDTO dto) {
        Company cmp=CompanyDTO.toEntity(dto);
        cmp.setId(id);
        cservice.saveCompany(cmp);
        return Response.success("Company updated successfully");
    }

    @GetMapping
    public ResponseEntity<?> listAllCompanies() {
        List<Company> result = cservice.listAll();
        return Response.success(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findCompanyById(@PathVariable("id") int id) {
        Company result = cservice.getDetails(id);
        return Response.success(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteCompanyById(@PathVariable("id") int id) {
        cservice.deleteById(id);
        uservice.deleteUser(id);
        return Response.success("Company deleted successfully");
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> saveJob(@RequestBody JobDTO dto) {
        Job job=JobDTO.toEntity(dto);
        Company cmp=cservice.getDetails(dto.getCid());
        job.setCompany(cmp);
        cservice.saveJob(job);
        return Response.success("Job Created Successfully");
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> listAllJobs() {
        List<Job> result = cservice.getAllJobs();
        return Response.success(result);
    }

    @GetMapping("/jobs/{cid}")
    public ResponseEntity<?> listCompanyJobs(@PathVariable("cid") int cid) {
        List<Job> result = cservice.getCompanyJobs(cid);
        return Response.success(result);
    }

    @GetMapping("/jobs/details/{id}")
    public ResponseEntity<?> getJobDetails(@PathVariable("id") int id) {
        Job result = cservice.getJobDetails(id);
        return Response.success(result);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> DeleteJobById(@PathVariable("id") int id) {
        cservice.deleteJob(id);
        return Response.success("Job deleted successfully");
    }

    @GetMapping("/applications/{cid}")
    public ResponseEntity<?> findApplicationsByStudentId(@PathVariable("cid") int cid) {
        List<JobApplication> result = jservice.listCompanyApplication(cid);
        return Response.success(result);
    }

    @GetMapping("/applications/details/{id}")
    public ResponseEntity<?> findApplicationDetailsById(@PathVariable("id") int jid) {
        JobApplication result = jservice.findDetails(jid);
        return Response.success(result);
    }

    @PostMapping("/applications/update")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusDTO dto) {
        jservice.udpateStatus(dto);
        JobApplication job=jservice.findDetails(dto.getJid());
        if(dto.getStatus().equals("Approved")) {
            String subject=dto.getStatus().equals("Approved")?"Congratulations for Selection":"";
            email.sendMail(job.getStudent().getEmail(), subject,
                    "Dear "+job.getStudent().getSname()+",<br>You have been selected for the job.");
        }
        return Response.success("Status updated successfully");
    }

    @GetMapping("/selected")
    public ResponseEntity<?> getSelectedStudents() {
        List<JobApplication> result = jservice.listSelectedCandidates();
        return Response.success(result);
    }
}
