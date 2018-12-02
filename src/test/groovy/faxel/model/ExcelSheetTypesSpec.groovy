package faxel.model

import faxel.source.SourceFactory
import faxel.source.SourceType
import faxel.test.data.inrow.excelsheettypes.*
import spock.lang.Specification
import spock.lang.Unroll

class ExcelSheetTypesSpec extends Specification {

    static poi3SourceFactory = SourceFactory.get(SourceType.POI_V3)
    static docx4jSourceFactory = SourceFactory.get(SourceType.DOCX4J_V6)

    @Unroll
    def "Should parse if ExcelSheet field is java.util.Collection. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(CollectionExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new CollectionExcelSheetExcel())
        then:
          result instanceof CollectionExcelSheetExcel
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }

    @Unroll
    def "Should parse if ExcelSheet field is java.util.List. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(ListExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new ListExcelSheetExcel())
        then:
          result instanceof ListExcelSheetExcel
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }

    @Unroll
    def "Should parse if ExcelSheet field is child of java.util.List. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(ArrayListExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new ArrayListExcelSheetExcel())
        then:
          result instanceof ArrayListExcelSheetExcel
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }

    @Unroll
    def "Should parse if ExcelSheet field is java.util.Set. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(SetExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new SetExcelSheetExcel())
        then:
          result instanceof SetExcelSheetExcel
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }

    @Unroll
    def "Should parse if ExcelSheet field is child of java.util.Set. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(HashSetExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new HashSetExcelSheetExcel())
        then:
          result instanceof HashSetExcelSheetExcel
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }

    @Unroll
    def "Should parse if ExcelSheet field is custom collection implementation. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/types.xlsx")
          def model = ModelDefinitionFactory.get().create(CustomImplementationExcelSheetExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new CustomImplementationExcelSheetExcel())
        then:
          result instanceof CustomImplementationExcelSheetExcel
        where:
          factory             | description
          poi3SourceFactory   | "Apache poi"
          docx4jSourceFactory | "docx4j"
    }
}