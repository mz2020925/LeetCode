package bilibili_match.p15;

import java.util.ArrayList;
import java.util.List;

class Employee {
    public int happy;
    public List<Employee> nexts;

    public Employee(int h) {
        happy = h;
        nexts = new ArrayList<>();
    }
}
