package com.example.placement_backend.controllers;

public class StudentController {
    @Autowired UsersService uservice;
    @Autowired StudentService sservice;
    @Autowired JobApplyService jservice;

    @PostMapping
    public ResponseEntity<?> save(StudentDTO dto) {
        System.out.println(dto);
        Student std=StudentDTO.toEntity(dto);
        int cid=sservice.saveStudent(std, dto.getPhoto());
        Users user=new Users(dto.getEmail(),dto.getSname(),dto.getPwd(),cid,"Student",true);
        uservice.AddUser(user);
        return Response.success("Student Created Successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,StudentDTO dto) {
        Student std=StudentDTO.toEntity(dto);
        Student existing=sservice.findById(id);
        std.setId(id);
        std.setPhoto(existing.getPhoto());
        sservice.updateStudent(std);
        return Response.success("Student updated successfully");
    }

    @GetMapping
    public ResponseEntity<?> listAllStudents() {
        List<Student> result = sservice.findAll();
        return Response.success(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findStudentById(@PathVariable("id") int id) {
        Student result = sservice.findById(id);
        return Response.success(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteStudentById(@PathVariable("id") int id) {
        sservice.deleteById(id);
        uservice.deleteUser(id);
        return Response.success("Student deleted successfully");
    }

    @PostMapping("upload")
    public ResponseEntity<?> saveDetails(StudentDetailsDTO dto) {
        System.out.println(dto);
        sservice.updateDetails(dto);
        return Response.success("Student details updated Successfully");
    }

    @PostMapping("apply")
    public ResponseEntity<?> applyJob(@RequestBody JobApplyDTO dto) {
        jservice.saveApply(dto);
        return Response.success("Job applied Successfully");
    }

    @GetMapping("/applied/{sid}")
    public ResponseEntity<?> findApplicationsByStudentId(@PathVariable("sid") int sid) {
        List<JobApplication> result = jservice.listStudentApplications(sid);
        return Response.success(result);
    }
}
