package faxel.source;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class SourceFactory {

    private static SourceFactory INSTANCE;

    public static SourceFactory get() {
        if (INSTANCE == null) {
            if (hasClass("org.apache.poi.ss.usermodel.Workbook")) {
                INSTANCE = apachePoiImpl();
            } else {
                throw new IllegalStateException("Not find any known excel parser. Include one of [Apache POI] as dependency");
            }
        }
        return INSTANCE;
    }

    private static boolean hasClass(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static SourceFactory apachePoiImpl() {
        return new SourceFactory() {
            @Override
            public SourceExcel create(InputStream is) {
                try {
                    return new ApachePoiSource(WorkbookFactory.create(is));
                } catch (IOException | InvalidFormatException e) {
                    throw new IllegalArgumentException("Could not open excel", e);
                }
            }
        };
    }

    public abstract SourceExcel create(InputStream is);
}
