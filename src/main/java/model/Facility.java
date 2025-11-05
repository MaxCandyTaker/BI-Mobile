package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facilites")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto ID
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private int telephoneNr;

    //@Column(nullable = false);
    //private Manager manager;

    private List<Employee> employeeList;

    public Facility() {}

    public Facility(String address, String mail, int telephoneNr) {
        this.address = address;
        this.mail = mail;
        this.telephoneNr = telephoneNr;
        this.employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelephoneNr() {
        return telephoneNr;
    }

    public void setTelephoneNr(int telephoneNr) {
        this.telephoneNr = telephoneNr;
    }
}
