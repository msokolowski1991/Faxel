package faxel.source;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class SourceFactory {

    private static final SourceFactory INSTANCE = new SourceFactory() {
        @Override
        public SourceExcel create(InputStream is) {
            try {
                return new ApachePoiSource(WorkbookFactory.create(is));
            } catch (IOException | InvalidFormatException e) {
                throw new IllegalArgumentException("Could not open excel", e);
            }
        }
    };

    public static SourceFactory get() {
        return INSTANCE;
    }

    public abstract SourceExcel create(InputStream is);
}
