package service;

import dao.FacilityDAO;
import model.Facility;

import java.util.List;

public class FacilityService {

    private FacilityDAO facilityDAO = new FacilityDAO();

    public void addFacility(Facility facility){
        facilityDAO.addFacility(facility);
    }

    public List<Facility> getAllFacilities(){
        return facilityDAO.getAllFacilities();
    }

}
