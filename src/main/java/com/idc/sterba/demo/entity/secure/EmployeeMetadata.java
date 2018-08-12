package com.idc.sterba.demo.entity.secure;

import com.idc.sterba.demo.dto.RegisterFormDTO;
import com.idc.sterba.demo.entity.Employee;

import javax.persistence.*;

@Entity
@Table(name = "employee_metadata", schema = "secure")
public class EmployeeMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeMedatadaIdSeq")
    @SequenceGenerator(name = "employeeMedatadaIdSeq", sequenceName = "employee_metadata_id_seq", allocationSize = 1, schema = "secure")
    private Long id;

    @OneToOne
    private Employee employee;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Lob
    @Column
    private byte[] profilePicture;

    public EmployeeMetadata() {
    }

    public EmployeeMetadata(RegisterFormDTO registerFormDTO, Employee employee) {
        this.username = registerFormDTO.getUsername();
        this.password = registerFormDTO.getPassword();
        this.email = registerFormDTO.getEmail();
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
