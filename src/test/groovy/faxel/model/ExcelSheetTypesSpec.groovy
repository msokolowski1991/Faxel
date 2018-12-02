package faxel.model

import faxel.model.ModelDefinitionFactory
import faxel.source.SourceFactory
import faxel.source.SourceType
import faxel.test.data.inrow.excelsheettypes.*
import spock.lang.Specification

class ExcelSheetTypesSpec extends Specification {

    def sourceFactory = SourceFactory.get(SourceType.POI_V3)

    def "Should parse if ExcelSheet field is java.util.Collection"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(CollectionExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new CollectionExcelSheetExcel())
        then:
          result instanceof CollectionExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is java.util.List"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(ListExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new ListExcelSheetExcel())
        then:
          result instanceof ListExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is child of java.util.List"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(ArrayListExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new ArrayListExcelSheetExcel())
        then:
          result instanceof ArrayListExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is java.util.Set"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(SetExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new SetExcelSheetExcel())
        then:
          result instanceof SetExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is child of java.util.Set"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(HashSetExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new HashSetExcelSheetExcel())
        then:
          result instanceof HashSetExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is custom collection implementation"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(CustomImplementationExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(sourceFactory.create(excelStream), new CustomImplementationExcelSheetExcel())
        then:
          result instanceof CustomImplementationExcelSheetExcel
    }
}