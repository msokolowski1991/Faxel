package faxel.model

import faxel.source.SourceFactory
import faxel.test.data.inrow.types.TypesExcelBoxed
import faxel.test.data.inrow.types.TypesExcelPrimitives
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class AllTypesParsingSpec extends Specification {


    def "Should parse boxed types excel arranged in rows to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesExcelBoxed)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new TypesExcelBoxed())
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

    def "Should parse primitive types excel arranged in rows to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesExcelPrimitives)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new TypesExcelPrimitives())
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

    def "Should parse empty types excel arranged in rows to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types-empty.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesExcelPrimitives)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new TypesExcelPrimitives())
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
}