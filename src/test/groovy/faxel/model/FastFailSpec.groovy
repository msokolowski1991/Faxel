package faxel.model

import faxel.source.SourceFactory
import faxel.test.data.inrow.fail.NoPublicConstructorExcel
import faxel.test.data.inrow.fail.UnknownTypeExcel
import faxel.test.data.inrow.fail.ExcelSheetWithoutNameOrIndexExcel
import spock.lang.Specification

class FastFailSpec extends Specification {

    def "Should fast fail on unknown type"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/fast-fail.xlsx")
          def model = ModelDefinitionFactory.get().create(UnknownTypeExcel)
        when: "Parser parse source excel"
          model.fill(SourceFactory.get().create(excelStream), new UnknownTypeExcel())
        then: "Should fast fail and throw an exception"
          thrown(IllegalArgumentException)
    }

    def "Should fast fail on no public constructor in model"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/fast-fail.xlsx")
          def model = ModelDefinitionFactory.get().create(NoPublicConstructorExcel)
        when: "Parser parse source excel"
          model.fill(SourceFactory.get().create(excelStream), new NoPublicConstructorExcel())
        then: "Should fast fail and throw an exception"
          thrown(IllegalArgumentException)
    }

    def "Should fast fail when @ExcelSheet has no name or index"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/fast-fail.xlsx")
          def model = ModelDefinitionFactory.get().create(ExcelSheetWithoutNameOrIndexExcel)
        when: "Parser parse source excel"
          model.fill(SourceFactory.get().create(excelStream), new ExcelSheetWithoutNameOrIndexExcel())
        then: "Should fast fail and throw an exception"
          thrown(IllegalStateException)
    }
}