package faxel.model

import faxel.test.data.person.Address
import faxel.test.data.person.Person
import faxel.test.data.person.PersonDataExcel
import faxel.test.data.types.TypesExcelBoxed
import faxel.test.data.types.TypesExcelPrimitives
import org.apache.poi.ss.usermodel.WorkbookFactory
import spock.lang.Specification

import java.time.*

class ModelDefinitionSpec extends Specification {

    def "Should parse boxed types excel to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesExcelBoxed)
        when: "Parser parse source excel"
          def result = model.fill(WorkbookFactory.create(excelStream), new TypesExcelBoxed())
        then: "The result is PersonDataExcel instance"
          result instanceof TypesExcelBoxed
        when: "Result is PersonDataExcel"
          result = result as TypesExcelBoxed
        then: "Result should has one row"
          result.types.size() == 1
          with(result.types[0]) {
              aString == "String"
              aInteger == 1
              aLong == 333333333333333L
              aShort == 8
              aFloat == 1.1f
              aDouble == 1.2d
              aBoolean
              aLocalDate == LocalDate.of(2020, 5, 5)
              aLocalDateTime == LocalDateTime.of(2020, 5, 5, 10, 0, 10)
              aLocalTime == LocalTime.of(5, 0, 0)
          }
    }

    def "Should parse primitive types excel to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesExcelPrimitives)
        when: "Parser parse source excel"
          def result = model.fill(WorkbookFactory.create(excelStream), new TypesExcelPrimitives())
        then: "The result is PersonDataExcel instance"
          result instanceof TypesExcelPrimitives
        when: "Result is PersonDataExcel"
          result = result as TypesExcelPrimitives
        then: "Result should has one row"
          result.types.size() == 1
          with(result.types[0]) {
              aString == "String"
              aInteger == 1
              aLong == 333333333333333L
              aShort == (short) 8
              aFloat == 1.1f
              aDouble == 1.2d
              aBoolean
              aLocalDate == new Date(120, 4, 5)
              aLocalDateTime == new Date(120, 4, 5, 10, 0, 10)
              aLocalTime == new Date(-1, 11, 31, 5, 0, 0)
          }
    }

    def "Should parse empty types excel to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types-empty.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesExcelPrimitives)
        when: "Parser parse source excel"
          def result = model.fill(WorkbookFactory.create(excelStream), new TypesExcelPrimitives())
        then: "The result is PersonDataExcel instance"
          result instanceof TypesExcelPrimitives
        when: "Result is PersonDataExcel"
          result = result as TypesExcelPrimitives
        then: "Result should has one row"
          result.types.size() == 1
          with(result.types[0]) {
              aString == ""
              aInteger == 0
              aLong == 0L
              aShort == (short) 0
              aFloat == 0f
              aDouble == 0d
              (!aBoolean)
              aLocalDate == null
              aLocalDateTime == null
              aLocalTime == null
          }
    }

    def "Should parse person excel to java object"() {
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

          result.people[0] == new Person(1, "John", "Smith", true, new Date(90, 0, 1), decimalOf(1000), LocalTime.of(9, 0, 0), 1.0f)
          result.people[1] == new Person(2, "Jack", "Nicolson", false, new Date(80, 0, 1), decimalOf(2000), LocalTime.of(10, 0, 0), 0.75f)
          result.people[2] == new Person(3, "Will", "Smith", true, new Date(75, 0, 1), decimalOf(5000), LocalTime.of(8, 0, 0), 0.85f)
          result.people[3] == new Person(4, "Mike", "Kowalski", true, new Date(85, 0, 1), decimalOf(10000), LocalTime.of(7, 30, 0), 0.4f)

          result.addresses[0] == new Address(1, 1, "Kraków Pawia 1", "RESIDENCE", LocalDate.of(2020, 12, 31), LocalDateTime.of(2010, 1, 10, 12, 45))
          result.addresses[1] == new Address(2, 1, "Kraków Pawia 2", "CORESPONDENCE", LocalDate.of(2024, 12, 31), LocalDateTime.of(2010, 1, 11, 9, 45))
          result.addresses[2] == new Address(3, 2, "Kraków Dolna 1", "RESIDENCE", LocalDate.of(2030, 12, 31), LocalDateTime.of(2010, 1, 12, 18, 45, 15))
          result.addresses[3] == new Address(4, 3, "Kraków Al. Pokoju 1", "RESIDENCE", LocalDate.of(2019, 12, 31), LocalDateTime.of(2010, 1, 13, 20, 0))
    }

    private static BigDecimal decimalOf(Long val) {
        BigDecimal.valueOf(val).setScale(1)
    }
}
