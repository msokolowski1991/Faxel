package faxel.definition;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;

final class ModelDefinitionImpl<DEST> implements ModelDefinition<DEST> {

    private final Collection<SheetDefinition> sheetDefinitions;

    ModelDefinitionImpl(Collection<SheetDefinition> sheetDefinitions) {
        this.sheetDefinitions = sheetDefinitions;
    }

    @Override
    public DEST fill(Workbook workbook, DEST destination) {
        // TODO actual filling
        return destination;
    }
}
