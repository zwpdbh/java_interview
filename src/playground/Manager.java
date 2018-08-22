package playground;

import java.util.ArrayList;

public class Manager extends Employee {
    private ArrayList<Employee> minions;

    public Manager(String name, Manager manager, ArrayList<Employee> _minions) {
        super(name, manager);
        minions = _minions;
    }

    public Manager(Manager copy) {
        super(copy.getName(), copy.getManager());
        minions = copy.minions;
    }

    public void setMinions(ArrayList<Employee> _minions) {
        if (minions.size() != _minions.size()) {
            minions = new ArrayList<>(_minions.size());
        }

        for (int i = 0; i != _minions.size(); ++i) {
            if (_minions.get(i) == null) throw new NullPointerException();
            minions.set(i, _minions.get(i));
        }
    }

    public ArrayList<Employee> getMinions() {
        return minions;
    }

    public boolean equals(Object other) {
        return ((Manager) other).minions == minions;
    }
}
