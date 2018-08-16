package faxel.model

import faxel.source.SourceFactory
import faxel.test.data.inrow.excelsheettypes.CollectionExcelSheetExcel
import faxel.test.data.inrow.excelsheettypes.CustomImplementationExcelSheetExcel
import faxel.test.data.inrow.excelsheettypes.ListExcelSheetExcel
import faxel.test.data.inrow.excelsheettypes.SetExcelSheetExcel
import spock.lang.Specification

class ExcelSheetTypesSpec extends Specification {

    def "Should parse if ExcelSheet field is java.util.Collection"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(CollectionExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new CollectionExcelSheetExcel())
        then:
          result instanceof CollectionExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is java.util.List"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(ListExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new ListExcelSheetExcel())
        then:
          result instanceof ListExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is java.util.Set"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(SetExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new SetExcelSheetExcel())
        then:
          result instanceof SetExcelSheetExcel
    }

    def "Should parse if ExcelSheet field is custom collection implementation"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(CustomImplementationExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new CustomImplementationExcelSheetExcel())
        then:
          result instanceof CustomImplementationExcelSheetExcel
    }
}