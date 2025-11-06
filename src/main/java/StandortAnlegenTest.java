import bimobile.controller.FacilityController;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import bimobile.Application;


public class StandortAnlegenTest {
    public static void main(String[] args) {
	    ApplicationContext context = SpringApplication.run(Application.class, args);
	    FacilityController controller = context.getBean(FacilityController.class);

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