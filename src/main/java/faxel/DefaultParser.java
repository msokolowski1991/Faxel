package faxel;

import org.apache.poi.ss.usermodel.Workbook;

import faxel.definition.ModelDefinition;
import faxel.definition.ModelDefinitionFactory;

class DefaultParser<D> implements FaxelParser<D> {
    private final Class<D> clazz;

    DefaultParser(Class<D> clazz) {
        this.clazz = clazz;
    }

    @Override
    public D parseFrom(Workbook workbook) {

        final ModelDefinition<D> definition = ModelDefinitionFactory.get().create(clazz);

        return definition.fill(workbook, ClassInitializer.createSilently(clazz));
    }

    private class ColumnParser {

    }

}
