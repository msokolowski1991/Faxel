package faxel.source;

import faxel.source.apache.poi.ApachePoi3Source;
import faxel.source.docx4j.v6.Docx4jV6Source;

import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Set of supported excel parsing libraries.
 */
public enum SourceType {
    /**
     * Apache POI 3.x.x source type
     */
    POI_V3("org.apache.poi.ss.usermodel.Workbook") {
        @Override
        SourceFactory createFactory() {
            return new SourceFactory() {
                @Override
                protected SourceExcel doCreate(InputStream inputStream) throws Exception {
                    return new ApachePoi3Source(org.apache.poi.ss.usermodel.WorkbookFactory.create(inputStream));
                }
            };
        }
    },
    /**
     * Docx4j 6.x.x implementation source type
     */
    DOCX4J_V6("org.docx4j.openpackaging.packages.SpreadsheetMLPackage") {
        @Override
        SourceFactory createFactory() {
            return new SourceFactory() {
                @Override
                protected SourceExcel doCreate(InputStream inputStream) throws Exception {
                    return new Docx4jV6Source(org.docx4j.openpackaging.packages.SpreadsheetMLPackage.load(inputStream));
                }
            };
        }
    };

    private final String scanClass;

    SourceType(String scanClass) {
        this.scanClass = scanClass;
    }

    abstract SourceFactory createFactory();

    static Optional<SourceType> findInClasspath() {
        return Stream.of(values())
                .filter(value -> hasClass(value.scanClass))
                .findFirst();
    }

    private static boolean hasClass(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
