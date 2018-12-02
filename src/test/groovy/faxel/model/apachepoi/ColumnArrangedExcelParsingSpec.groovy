package faxel.model.apachepoi

import faxel.model.ModelDefinitionFactory
import faxel.source.SourceFactory
import faxel.test.data.incolumn.Vehicle
import faxel.test.data.incolumn.VehicleExcel
import faxel.test.data.incolumn.VehicleExcelWithMaxLimit
import spock.lang.Specification


class ColumnArrangedExcelParsingSpec extends Specification {
    def "Should parse vehicles excel arranged in columns to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/vehicles.xlsx")
          def model = ModelDefinitionFactory.get().create(VehicleExcel)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new VehicleExcel())
        then: "The result is VehicleExcel instance"
          result instanceof VehicleExcel
        when: "Result is VehicleExcel"
          result = result as VehicleExcel
        then: "Result should has two positions"
          result.vehicles.size() == 3
          result.vehicles[0] == new Vehicle("Honda", "Accord")
          result.vehicles[1] == new Vehicle("Volkswagen", "Polo")
          result.vehicles[2] == new Vehicle("Ferrari", "Enzo")
    }

    def "Should parse vehicles excel arranged in columns limited to third position to java object"() {
        given: "Default model"
          def excelStream = getClass().getResourceAsStream("/vehicles.xlsx")
          def model = ModelDefinitionFactory.get().create(VehicleExcelWithMaxLimit)
        when: "Parser parse source excel"
          def result = model.fill(SourceFactory.get().create(excelStream), new VehicleExcelWithMaxLimit())
        then: "The result is VehicleExcelWithMaxLimit instance"
          result instanceof VehicleExcelWithMaxLimit
        when: "Result is VehicleExcelWithMaxLimit"
          result = result as VehicleExcelWithMaxLimit
        then: "Result should has 2 positions, 1 should be ignored"
          result.vehicles.size() == 2
          result.vehicles[0] == new Vehicle("Honda", "Accord")
          result.vehicles[1] == new Vehicle("Volkswagen", "Polo")
    }
}