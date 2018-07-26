package faxel

import faxel.test.data.person.PersonDataExcel
import org.apache.poi.ss.usermodel.WorkbookFactory
import spock.lang.Specification

class FaxelParserSpec extends Specification {

    def "Should parse given excel to java object"() {
        given: "Default parser"
          def excelStream = getClass().getResourceAsStream("/person-data.xlsx")
          def parser = FaxelFactory.create(PersonDataExcel.class)
        when: "Parser parse SimplePersonData"
          def result = parser.parseFrom(WorkbookFactory.create(excelStream))
        then: "The result is PersonDataExcel instance"
          result instanceof PersonDataExcel
        when: "Result is PersonDataExcel"
          result = result as PersonDataExcel
        then: "Result should has correct amount of people and address entries"
          result.people.size() == 4
          result.addresses.size() == 4
    }
}
