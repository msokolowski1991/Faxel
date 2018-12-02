package faxel.model

import faxel.model.ModelDefinitionFactory
import faxel.source.SourceFactory
import faxel.source.SourceType
import faxel.test.data.inrow.types.TypesBoxedIndexdSheetExcel
import faxel.test.data.inrow.types.TypesBoxedNamedSheetExcel
import faxel.test.data.inrow.types.TypesPrimitivesExcel
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TypesParsingSpec extends Specification {

    def sourceFactory = SourceFactory.get(SourceType.POI_V3)

    def "Should parse boxed types excel arranged in rows with named sheets to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesBoxedNamedSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new TypesBoxedNamedSheetExcel())
        then: "The result is TypesBoxedNamedSheetExcel instance"
          result instanceof TypesBoxedNamedSheetExcel
        when: "Result is TypesBoxedNamedSheetExcel"
          result = result as TypesBoxedNamedSheetExcel
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

    def "Should parse boxed types excel arranged in rows with indexed sheets to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(TypesBoxedIndexdSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new TypesBoxedIndexdSheetExcel())
        then: "The result is TypesBoxedIndexdSheetExcel instance"
          result instanceof TypesBoxedIndexdSheetExcel
        when: "Result is TypesBoxedIndexdSheetExcel"
          result = result as TypesBoxedIndexdSheetExcel
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
          def model = ModelDefinitionFactory.get().create(TypesPrimitivesExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new TypesPrimitivesExcel())
        then: "The result is TypesPrimitivesExcel instance"
          result instanceof TypesPrimitivesExcel
        when: "Result is TypesPrimitivesExcel"
          result = result as TypesPrimitivesExcel
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
          def model = ModelDefinitionFactory.get().create(TypesPrimitivesExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new TypesPrimitivesExcel())
        then: "The result is TypesPrimitivesExcel instance"
          result instanceof TypesPrimitivesExcel
        when: "Result is TypesPrimitivesExcel"
          result = result as TypesPrimitivesExcel
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