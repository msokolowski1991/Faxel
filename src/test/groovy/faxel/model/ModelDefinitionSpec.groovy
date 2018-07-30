package faxel.model

import faxel.test.data.person.Address
import faxel.test.data.person.Person
import faxel.test.data.person.PersonDataExcel
import org.apache.poi.ss.usermodel.WorkbookFactory
import spock.lang.Specification

class ModelDefinitionSpec extends Specification {

    def "Should parse given excel to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/person-data.xlsx")
          def model = ModelDefinitionFactory.get().create(PersonDataExcel)
        when: "Parser parse source excel"
          def result = model.fill(WorkbookFactory.create(excelStream), new PersonDataExcel())
        then: "The result is PersonDataExcel instance"
          result instanceof PersonDataExcel
        when: "Result is PersonDataExcel"
          result = result as PersonDataExcel
        then: "Result should has correct amount of people and address entries"
          result.people.size() == 4
          result.addresses.size() == 4

          result.people[0] == new Person(1, "John", "Smith", true, new Date(90, 0, 1), decimalOf(1000))
          result.people[1] == new Person(2, "Jack", "Nicolson", false, new Date(80, 0, 1), decimalOf(2000))
          result.people[2] == new Person(3, "Will", "Smith", true, new Date(75, 0, 1), decimalOf(5000))
          result.people[3] == new Person(4, "Mike", "Kowalski", true, new Date(85, 0, 1), decimalOf(10000))

          result.addresses[0] == new Address(1, 1, "Kraków Pawia 1", "RESIDENCE")
          result.addresses[1] == new Address(2, 1, "Kraków Pawia 2", "CORESPONDENCE")
          result.addresses[2] == new Address(3, 2, "Kraków Dolna 1", "RESIDENCE")
          result.addresses[3] == new Address(4, 3, "Kraków Al. Pokoju 1", "RESIDENCE")
    }

    private static BigDecimal decimalOf(Long val) {
        BigDecimal.valueOf(val).setScale(1)
    }
}
