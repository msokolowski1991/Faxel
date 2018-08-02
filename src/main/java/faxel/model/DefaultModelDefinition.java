package faxel.model;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import faxel.source.SourceExcel;

final class DefaultModelDefinition<DEST> implements ModelDefinition<DEST> {
    private static Logger LOG = LoggerFactory.getLogger(DefaultModelDefinition.class);

    private final Collection<SheetDefinition> sheetDefinitions;

    DefaultModelDefinition(Collection<SheetDefinition> sheetDefinitions) {
        this.sheetDefinitions = sheetDefinitions;
        LOG.trace("Creating DefaultModelDefinition with {} sheet definitions", sheetDefinitions.size());
    }

    @Override
    public DEST fill(SourceExcel source, DEST destination) {
        LOG.trace("Filling Model {}", destination.getClass());
        sheetDefinitions.forEach(sheet -> sheet.fill(source, destination));
        LOG.trace("Model {} filled", destination.getClass());
        return destination;
    }
}
