package bimobile.views;

import bimobile.controller.FacilityController;
import bimobile.model.Facility;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "standorte", layout = MainLayout.class)
@PageTitle("Standortübersicht")
public class StandortUebersichtView extends VerticalLayout {

	private final FacilityController controller;
	private final Grid<Facility> grid = new Grid<>(Facility.class, false);

	@Autowired
	public StandortUebersichtView(FacilityController controller) {
		this.controller = controller;

		setPadding(true);
		setSizeFull();
		getStyle().set("background", "#f9fafb");
		getStyle().set("min-height", "100vh");

		H2 title = new H2("Standortübersicht");

		// +++ Button "Neuen Standort anlegen"
		Button neu = new Button("Neuen Standort anlegen", new Icon(VaadinIcon.PLUS));
		neu.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		neu.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("standort-anlegen")));

		HorizontalLayout header = new HorizontalLayout(title, neu);
		header.setWidthFull();
		header.setAlignItems(Alignment.CENTER);
		header.setJustifyContentMode(JustifyContentMode.BETWEEN);

		// +++ Tabelle (Grid) mit echten Daten
		grid.addColumn(Facility::getId).setHeader("ID").setAutoWidth(true);
		grid.addColumn(Facility::getAddress).setHeader("Adresse").setAutoWidth(true);
		grid.addColumn(Facility::getMail).setHeader("E-Mail").setAutoWidth(true);
		grid.addColumn(Facility::getTelephoneNr).setHeader("Telefon").setAutoWidth(true);

		grid.addComponentColumn(facility -> {
			Button bearbeiten = new Button(new Icon(VaadinIcon.EDIT));
			bearbeiten.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
			bearbeiten.addClickListener(e ->
					Notification.show("Bearbeiten für: " + facility.getAddress()));

			Button löschen = new Button(new Icon(VaadinIcon.TRASH));
			löschen.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
			löschen.addClickListener(e -> {
				controller.getAllFacilities().remove(facility); // später delete-Methode einbauen
				updateGrid();
				Notification.show("Standort gelöscht: " + facility.getAddress());
			});

			return new HorizontalLayout(bearbeiten, löschen);
		}).setHeader("Aktionen");

		updateGrid();

		add(header, grid);
		setFlexGrow(1, grid);
	}

	private void updateGrid() {
		List<Facility> facilities = controller.getAllFacilities();
		grid.setItems(facilities);
	}
}
