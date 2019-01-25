package faxel.model

import faxel.source.SourceFactory
import faxel.source.SourceType
import faxel.test.data.inrow.person.Address
import faxel.test.data.inrow.person.Person
import faxel.test.data.inrow.person.PersonDataExcel
import faxel.test.data.inrow.person.PersonDataExcelWithMaxLimit
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class RowArrangedExcelParsingSpec extends Specification {

    static poi3SourceFactory = SourceFactory.get(SourceType.POI_V3)
    static docx4jSourceFactory = SourceFactory.get(SourceType.DOCX4J_V6)

    @Unroll
    def "Should parse person excel arranged in rows to java object. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/person-data.xlsx")
          def model = ModelDefinitionFactory.get().create(PersonDataExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new PersonDataExcel())
        then: "The result is PersonDataExcel instance"
          result instanceof PersonDataExcel
        when: "Result is PersonDataExcel"
          result = result as PersonDataExcel
        then: "Result should has 4 people and addresses"
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
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }

    @Unroll
    def "Should parse person excel arranged in rows with max limit to java object. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/person-data.xlsx")
          def model = ModelDefinitionFactory.get().create(PersonDataExcelWithMaxLimit)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new PersonDataExcelWithMaxLimit())
        then: "The result is PersonDataExcelWithMaxLimit instance"
          result instanceof PersonDataExcelWithMaxLimit
        when: "Result is PersonDataExcelWithMaxLimit"
          result = result as PersonDataExcelWithMaxLimit
        then: "Result should has 2 people and addresses, rest should be ignored"
          result.people.size() == 2
          result.addresses.size() == 2

          result.people[0] == new Person(1, "John", "Smith", true, new Date(90, 0, 1), decimalOf(1000), LocalTime.of(9, 0, 0), 1.0f)
          result.people[1] == new Person(2, "Jack", "Nicolson", false, new Date(80, 0, 1), decimalOf(2000), LocalTime.of(10, 0, 0), 0.75f)

          result.addresses[0] == new Address(2, 1, "Kraków Pawia 2", "CORESPONDENCE", LocalDate.of(2024, 12, 31), LocalDateTime.of(2010, 1, 11, 9, 45))
          result.addresses[1] == new Address(3, 2, "Kraków Dolna 1", "RESIDENCE", LocalDate.of(2030, 12, 31), LocalDateTime.of(2010, 1, 12, 18, 45, 15))
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }

    private static BigDecimal decimalOf(Long val) {
        BigDecimal.valueOf(val).setScale(1)
    }
}
