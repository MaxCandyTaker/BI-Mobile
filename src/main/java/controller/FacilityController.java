package controller;

import model.Facility;
import service.FacilityService;
import dao.FacilityDAO;

import java.util.List;

public class FacilityController {

    private FacilityService facilityService;
    private FacilityDAO facilityDAO;

    public FacilityController() {
        this.facilityService = new FacilityService();
        this.facilityDAO = new FacilityDAO();
    }

    public String standortAnlegen(String address, String mail, int telephoneNr) {
        if (address == null || address.trim().isEmpty()) {
            return "Fehler: Adresse ist ein Pflichtfeld";
        }

        if (address.length() > 200) {
            return "Fehler: Adresse ist zu lang (maximal 200 Zeichen)";
        }

        if (mail == null || mail.trim().isEmpty()) {
            return "Fehler: E-Mail ist ein Pflichtfeld";
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!mail.matches(emailRegex)) {
            return "Fehler: Ungültiges E-Mail-Format";
        }

        if (telephoneNr <= 0) {
            return "Fehler: Telefonnummer muss eine positive Zahl sein";
        }

        if (String.valueOf(telephoneNr).length() > 15) {
            return "Fehler: Telefonnummer ist zu lang";
        }

        try {
            Facility facility = new Facility(address.trim(), mail.trim(), telephoneNr);
            facilityService.addFacility(facility);
            return "Erfolg: Standort '" + address + "' wurde erfolgreich angelegt";

        } catch (Exception e) {
            return "Fehler: Standort konnte nicht gespeichert werden - " + e.getMessage();
        }
    }


    public String standortBearbeiten(Long id, String address, String mail, int telephoneNr) {
        if (id == null || id <= 0) {
            return "Fehler: Ungültige Standort-ID";
        }

        Facility facility = facilityDAO.getFacilityById(id);
        if (facility == null) {
            return "Fehler: Standort mit ID " + id + " wurde nicht gefunden";
        }

        if (address == null || address.trim().isEmpty()) {
            return "Fehler: Adresse ist ein Pflichtfeld";
        }

        if (address.length() > 200) {
            return "Fehler: Adresse ist zu lang (maximal 200 Zeichen)";
        }

        if (mail == null || mail.trim().isEmpty()) {
            return "Fehler: E-Mail ist ein Pflichtfeld";
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!mail.matches(emailRegex)) {
            return "Fehler: Ungültiges E-Mail-Format";
        }

        if (telephoneNr <= 0) {
            return "Fehler: Telefonnummer muss eine positive Zahl sein";
        }

        if (String.valueOf(telephoneNr).length() > 15) {
            return "Fehler: Telefonnummer ist zu lang";
        }

        try {
            facility.setAddress(address.trim());
            facility.setMail(mail.trim());
            facility.setTelephoneNr(telephoneNr);

            facilityDAO.updateFacility(facility);

            return "Erfolg: Standort wurde aktualisiert (Adresse kann noch nicht geändert werden)";

        } catch (Exception e) {
            return "Fehler: Standort konnte nicht aktualisiert werden - " + e.getMessage();
        }
    }

    public String standortDeaktivieren(Long id) {
        if (id == null || id <= 0) {
            return "Fehler: Ungültige Standort-ID";
        }

        Facility facility = facilityDAO.getFacilityById(id);
        if (facility == null) {
            return "Fehler: Standort mit ID " + id + " wurde nicht gefunden";
        }

        return "Hinweis: Deaktivierung noch nicht vollständig implementiert. " ;
    }

    public String alleStandorteAnzeigen() {
        try {
            List<Facility> facilities = facilityService.getAllFacilities();

            if (facilities.isEmpty()) {
                return "Keine Standorte vorhanden";
            }

            StringBuilder result = new StringBuilder("=== ALLE STANDORTE ===\n\n");

            for (Facility f : facilities) {
                result.append(String.format(
                        "ID: %d | Adresse: %s | E-Mail: %s | Tel: %d\n",
                        f.getId(),
                        f.getAddress(),
                        f.getMail(),
                        f.getTelephoneNr()
                ));
            }

            return result.toString();

        } catch (Exception e) {
            return "Fehler beim Abrufen der Standorte: " + e.getMessage();
        }
    }

    public String standortDetails(Long id) {
        if (id == null || id <= 0) {
            return "Fehler: Ungültige ID";
        }

        try {
            Facility facility = facilityDAO.getFacilityById(id);

            if (facility == null) {
                return "Fehler: Standort mit ID " + id + " nicht gefunden";
            }

            return String.format(
                    "=== STANDORT-DETAILS ===\n" +
                            "ID: %d\n" +
                            "Adresse: %s\n" +
                            "E-Mail: %s\n" +
                            "Telefon: %d\n",
                    facility.getId(),
                    facility.getAddress(),
                    facility.getMail(),
                    facility.getTelephoneNr()
            );

        } catch (Exception e) {
            return "Fehler: " + e.getMessage();
        }
    }
}