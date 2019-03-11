package faxel.source.docx4j.v6;

import faxel.FaxelException;
import faxel.source.SourceExcel;
import faxel.source.SourceSheet;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.xlsx4j.exceptions.Xlsx4jException;
import org.xlsx4j.sml.Sheet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Docx4j 6+ SourceExcel implementation.
 * To use this implementation you need to provide Docx4j 6+ or later dependency.
 */
public class Docx4jV6Source implements SourceExcel {

    private final SpreadsheetMLPackage source;
    private SheetNameToIndexMap sheetNameToIndexMap;

    public Docx4jV6Source(SpreadsheetMLPackage source) {
        this.source = source;
    }

    @Override
    public SourceSheet sheetOf(String sheetName) {
        if (sheetName == null) {
            throw new FaxelException("SheetName can not be null");
        }
        final int sheetIndex = sheetIndex(sheetName);
        return sheetOf(sheetIndex);
    }

    private int sheetIndex(String sheetName) {
        if (sheetNameToIndexMap == null) {
            sheetNameToIndexMap = SheetNameToIndexMap.from(source);
        }
        return sheetNameToIndexMap.indexOf(sheetName);
    }

    @Override
    public SourceSheet sheetOf(int index) {
        if (index < 0) {
            throw new FaxelException("Index should be >= 0");
        }
        try {
            if (index >= source.getWorkbookPart().getContents().getSheets().getSheet().size()) {
                throw new FaxelException("Sheet at %d index does not exist", index);
            }
            final WorksheetPart worksheet = source.getWorkbookPart().getWorksheet(index);
            return new Docx4jSheet(worksheet);
        } catch (Xlsx4jException | Docx4JException e) {
            throw new FaxelException("Could not parse spreadsheet", e);
        }
    }

    private static class SheetNameToIndexMap {
        private Map<String, Long> sheetNameToIndexMap;

        SheetNameToIndexMap(Map<String, Long> sheetNameToIndexMap) {
            this.sheetNameToIndexMap = sheetNameToIndexMap;
        }

        int indexOf(String sheetName) {
            final Long sheetId = sheetNameToIndexMap.get(sheetName.toLowerCase());
            if (sheetId == null) {
                throw new FaxelException("Could not find sheet " + sheetName);
            }
            return sheetId.intValue() - 1;
        }

        static SheetNameToIndexMap from(SpreadsheetMLPackage source)  {
            try {
                List<Sheet> sheets = source.getWorkbookPart().getContents().getSheets().getSheet();
                Map<String, Long> sheetNameToIndexMap = sheets.stream()
                        .collect(Collectors.toMap(s -> s.getName().toLowerCase(), Sheet::getSheetId));
                return new SheetNameToIndexMap(sheetNameToIndexMap);
            } catch (Docx4JException e) {
                throw new FaxelException("Could not parse spreadsheet");
            }
        }
    }
}
