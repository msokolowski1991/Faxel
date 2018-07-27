package faxel.model;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class DefaultModelDefinition<DEST> implements ModelDefinition<DEST> {
    private static Logger LOG = LoggerFactory.getLogger(DefaultModelDefinition.class);

    private final Collection<SheetDefinition> sheetDefinitions;

    DefaultModelDefinition(Collection<SheetDefinition> sheetDefinitions) {
        this.sheetDefinitions = sheetDefinitions;
        LOG.trace("Creating DefaultModelDefinition with {} sheet definitions", sheetDefinitions.size());
    }

    @Override
    public DEST fill(Workbook workbook, DEST destination) {
        LOG.trace("Filling Model {}", destination.getClass());
        sheetDefinitions.forEach(sheet -> sheet.fill(workbook, destination));
        LOG.trace("Model {} filled", destination.getClass());
        return destination;
    }
}
