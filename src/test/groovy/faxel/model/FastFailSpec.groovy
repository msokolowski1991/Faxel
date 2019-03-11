package faxel.model

import faxel.FaxelException
import faxel.source.SourceFactory
import faxel.source.SourceType
import faxel.test.data.inrow.fail.*
import spock.lang.Specification
import spock.lang.Unroll

class FastFailSpec extends Specification {

    @Unroll
    def "Should fast fail on #description using Apache Poi V3"(Object model, String description) {
        given: "Default modelDefinition"
          def excelStream = getClass().getResourceAsStream("/fast-fail.xlsx")
          def modelDefinition = ModelDefinitionFactory.get().create(model.class)
        when: "Parser parse source excel"
          modelDefinition.fill(SourceFactory.of(SourceType.POI_V3).create(excelStream), model)
        then: "Should fast fail and throw an exception"
          thrown(FaxelException)
        where:
          model                                   | description
          new UnknownTypeExcel()                  | "Unknown cell type"
          new WrongTypeExcel()                    | "Wrong cell type"
          new UnknownSheetNameExcel()             | "Unknown sheet name"
          new UnknownSheetIndexExcel()            | "Unknown sheet index"
          new ExcelSheetWithoutNameOrIndexExcel() | "ExcelSheet without name or index"
          new NoPublicConstructorExcel()          | "No public argument constructor in model"
    }

    @Unroll
    def "Should fast fail on #description using docx4j V6"(Object model, String description) {
        given: "Default modelDefinition"
          def excelStream = getClass().getResourceAsStream("/fast-fail.xlsx")
          def modelDefinition = ModelDefinitionFactory.get().create(model.class)
        when: "Parser parse source excel"
          modelDefinition.fill(SourceFactory.of(SourceType.DOCX4J_V6).create(excelStream), model)
        then: "Should fast fail and throw an exception"
          thrown(FaxelException)
        where:
          model                                   | description
          new UnknownTypeExcel()                  | "Unknown cell type"
          new WrongTypeExcel()                    | "Wrong cell type"
          new UnknownSheetNameExcel()             | "Unknown sheet name"
          new UnknownSheetIndexExcel()            | "Unknown sheet index"
          new ExcelSheetWithoutNameOrIndexExcel() | "ExcelSheet without name or index"
          new NoPublicConstructorExcel()          | "No public argument constructor in model"
    }
}