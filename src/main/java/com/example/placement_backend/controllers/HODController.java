package com.example.placement_backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/HOD")
public class HODController {

    @Autowired UsersService uservice;
    @Autowired HodService hservice;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody HodDto dto) {
        int id=hservice.saveHOD(HodDto.toEntity(dto));
        Users user=new Users(dto.getEmail(),dto.getName(),dto.getPwd(),id,"HOD",true);
        uservice.AddUser(user);
        return Response.success("HOD Created Successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HodDto dto) {
        HOD hod=HodDto.toEntity(dto);
        hod.setId(id);
        hservice.saveHOD(hod);
        return Response.success("HoD updated successfully");
    }

    @GetMapping
    public ResponseEntity<?> listAllHODs() {
        List<HOD> result = hservice.listAll();
        return Response.success(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findHODById(@PathVariable("id") int id) {
        HOD result = hservice.getDetails(id);
        return Response.success(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteHODById(@PathVariable("id") int id) {
        hservice.deleteById(id);
        uservice.deleteUser(id);
        return Response.success("HOD deleted successfully");
    }
}
