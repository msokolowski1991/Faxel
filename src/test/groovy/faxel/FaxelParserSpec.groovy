package faxel

import faxel.annotation.Column
import faxel.annotation.Sheet
import spock.lang.Specification

class FaxelParserSpec extends Specification {

    static class PersonDataExcel {
        @Sheet(name = "Person", firstDataRow = 1)
        private Collection<Person> people;

        @Sheet(name = "Address", firstDataRow = 1)
        private Collection<Address> addresses;
    }

    static class Person {
        @Column(index = 0)
        private int number;

        @Column(index = 1)
        private String firstName;

        @Column(index = 2)
        private String lastName;
    }

    static class Address {
        @Column(index = 0)
        private int number;

        @Column(index = 1)
        private int personNumber;

        @Column(index = 2)
        private String address;

        @Column(index = 3)
        private String type;
    }

    def "Should parse given excel to java object"() {
        given: "Default parser"
          def parser = FaxelFactory.create("/person-data.xlsx")
        when: "Parser parse SimplePersonData"
          def result = parser.parseTo(PersonDataExcel.class)
        then: "The result is PersonDataExcel instance"
          result instanceof PersonDataExcel
    }
}
