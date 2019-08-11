package test.LamdaLearning;

import java.util.Arrays;
import java.util.Comparator;

public class FromJava7ToLambda {
    public static class Employee {
        private String firstName;
        private String lastName;
        private Long salary;

        public Employee(String firstName, String lastName, Long salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public Long getSalary() {
            return salary;
        }

        public void setSalary(Long salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee: " + this.lastName + ", " + this.firstName + " (" + this.salary + ")";
        }
    }

    private static Employee[] createSomeEmployees() {
        return new Employee[]{
                new Employee("John", "Smith", 123000L),
                new Employee("Steve", "Johnson", 144000L),
                new Employee("Sally", "Lee", 244000L),
                new Employee("Bob", "Nielsen", 184000L),
                new Employee("Mia", "Chen", 155000L),
                new Employee("Ning", "Shaw", 187000L),
                new Employee("Ronny", "White", 214000L),
                new Employee("Billy", "Kid", 166000L),
                new Employee("Jimmy", "Smith", 196000L),
                new Employee("Bill", "Evans", 142000L),
                new Employee("Mary", "Johnson", 144000L),
        };
    }

    public static void main(String[] args) {
        Employee[] employees = createSomeEmployees();
//        Employee[] sortedByNameJava7 = sortByNameJava7(employees);
//        printEmployees("Sorted by name in Java 7", sortedByNameJava7);
//        Employee[] sortedBySalaryJava7 = sortBySalaryJava7(employees);
//        printEmployees("Sorted by salary in Java 7", sortedBySalaryJava7);
         Employee [] sortedByNameJava8 = sortByNameJava8(employees);
         printEmployees("Sorted by name in Java 8", sortedByNameJava8);
         Employee [] sortedBySalaryJava8 = sortBySalaryJava8(employees);
         printEmployees("Sorted by salary in Java 8", sortedBySalaryJava8);
    }

    private static void printEmployees(String header, Employee[] employees) {
        System.out.println(header);
//        System.out.println(StringUtils.repeat("-", header.length()));
        int i = 1;
        for (Employee e : employees) {

            System.out.println(String.format("%2d. %s", i, e.toString()));
            i++;
        }
//        System.out.println(StringUtils.repeat("=", header.length()));

    }

    private static Employee[] sortByNameJava8(Employee[] employees) {
        Arrays.sort(employees, (o1, o2) -> {
            int firstCompare = o1.getLastName().compareTo(o2.getLastName());
            if (firstCompare == 0) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return firstCompare;

        });
        return employees;
    }

    private static Employee[] sortBySalaryJava8(Employee[] employees) {
        Arrays.sort(employees, (o1,o2)-> Long.compare(o2.getSalary(), o1.getSalary()));
        return employees;
    }
}