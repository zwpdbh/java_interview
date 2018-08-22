package playground;


public class Employee {
    private String name;
    private Manager manager;

    public Employee(String name, Manager manager) {
        if (name == null) throw new NullPointerException();
        if (manager == null) throw new NullPointerException();
        this.name = name;
        this.manager = manager;
    }

    public Employee(Employee copy) {
        name = copy.name;
        manager = copy.manager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public boolean equals(Object other) {
        Employee employee = (Employee) other;
        return employee.name == name && employee.manager == manager;
    }
}
