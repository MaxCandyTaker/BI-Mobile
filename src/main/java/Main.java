import dao.EmployeeDAO;
import model.Employee;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        Employee employee = new Employee("Max", "Mustermann", "01.01.2000");

        dao.addEmployee(employee);

        dao.getAllEmployees().forEach(e ->
                System.out.println(employee.getName() + " " + employee.getLastname() + " - " + employee.getBirthday())
        );

        dao.close();
    }


}
