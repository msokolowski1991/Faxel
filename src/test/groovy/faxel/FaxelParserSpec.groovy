package faxel

import faxel.annotation.Column
import faxel.annotation.SheetRows
import org.apache.poi.ss.usermodel.WorkbookFactory
import spock.lang.Specification

class FaxelParserSpec extends Specification {

    static class PersonDataExcel {
        @SheetRows(sheetName = "Person")
        private Collection<Person> people;

        @SheetRows(sheetName = "Address")
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
          def excelStream = getClass().getResourceAsStream("/test-files/person-data.xlsx")
          def parser = FaxelFactory.create(PersonDataExcel.class)
        when: "Parser parse SimplePersonData"
          def result = parser.parseFrom(WorkbookFactory.create(excelStream))
        then: "The result is PersonDataExcel instance"
          result instanceof PersonDataExcel
    }
}
