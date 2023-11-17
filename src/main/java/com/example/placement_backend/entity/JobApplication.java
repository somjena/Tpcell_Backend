package com.example.placement_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name="cid")
    private Company company;
    private String status;
    private String remarks;
    private LocalDate applydate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getApplydate() {
        return applydate;
    }

    public void setApplydate(LocalDate applydate) {
        this.applydate = applydate;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", student=" + student +
                ", job=" + job +
                ", company=" + company +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                ", applydate=" + applydate +
                '}';
    }
}
