import controller.FacilityController;

public class StandortAnlegenTest {
    public static void main(String[] args) {
        FacilityController controller = new FacilityController();

        // Standort 1
        System.out.println(controller.standortAnlegen(
                "Hauptstraße 1, 51105 Köln",
                "koeln@carrental.de",
                221123456
        ));


        // Test: Ungültige Eingabe
        System.out.println("\n--- Test ungültige E-Mail ---");
        System.out.println(controller.standortAnlegen(
                "Teststraße 1",
                "keine-email",  // Ungültig!
                123456
        ));
    }
}