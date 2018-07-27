package faxel.model

import faxel.model.ModelDefinitionFactory
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
    }
}
