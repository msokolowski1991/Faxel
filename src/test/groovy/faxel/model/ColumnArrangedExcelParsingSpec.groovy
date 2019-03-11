package faxel.model

import faxel.source.SourceFactory
import faxel.source.SourceType
import faxel.test.data.incolumn.Vehicle
import faxel.test.data.incolumn.VehicleExcel
import faxel.test.data.incolumn.VehicleExcelWithMaxLimit
import spock.lang.Specification
import spock.lang.Unroll

class ColumnArrangedExcelParsingSpec extends Specification {

    static poi3SourceFactory = SourceFactory.of(SourceType.POI_V3)
    static docx4j6SourceFactory = SourceFactory.of(SourceType.DOCX4J_V6)

    @Unroll
    def "Should parse vehicles excel arranged in columns to java object. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/vehicles.xlsx")
          def model = ModelDefinitionFactory.get().create(VehicleExcel)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new VehicleExcel())
        then: "The result is VehicleExcel instance"
          result instanceof VehicleExcel
        when: "Result is VehicleExcel"
          result = result as VehicleExcel
        then: "Result should has two positions"
          result.vehicles.size() == 3
          result.vehicles[0] == new Vehicle("Honda", "Accord")
          result.vehicles[1] == new Vehicle("Volkswagen", "Polo")
          result.vehicles[2] == new Vehicle("Ferrari", "Enzo")
        where:
          factory              | description
          poi3SourceFactory    | "Apache poi"
          docx4j6SourceFactory | "docx4j"
    }

    @Unroll
    def "Should parse vehicles excel arranged in columns limited to third position to java object. Using #description."(SourceFactory factory, String description) {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/vehicles.xlsx")
          def model = ModelDefinitionFactory.get().create(VehicleExcelWithMaxLimit)
        when: "Parser parse source excel"
          def result = model.fill(factory.create(excelStream), new VehicleExcelWithMaxLimit())
        then: "The result is VehicleExcelWithMaxLimit instance"
          result instanceof VehicleExcelWithMaxLimit
        when: "Result is VehicleExcelWithMaxLimit"
          result = result as VehicleExcelWithMaxLimit
        then: "Result should has 2 positions, 1 should be ignored"
          result.vehicles.size() == 2
          result.vehicles[0] == new Vehicle("Honda", "Accord")
          result.vehicles[1] == new Vehicle("Volkswagen", "Polo")
        where:
          factory              | description
          poi3SourceFactory    | "Apache poi"
          docx4j6SourceFactory | "docx4j"
    }
}