package faxel

import spock.lang.Specification

class FaxelParserSpec extends Specification {

    static class PersonDataExcel {
        private Collection<Person> people;
        private Collection<Address> addresses;
    }

    static class Person {
        private int number;
        private String firstName;
        private String lastName;
    }

    static class Address {
        private int number;
        private int personNumber;
        private String address;
        private String type;
    }

    def "Should parse given excel to java object"() {
        given: "Default parser"
          def parser = FaxelFactory.create("/SimplePersonData.xlsx")
        when: "Parser parse SimplePersonData"
          def result = parser.parseTo(PersonDataExcel.class)
        then: "The result is PersonDataExcel instance"
          result instanceof PersonDataExcel
    }
}
