package faxel.source;

import java.io.InputStream;

import faxel.source.poi.ApachePoiSource;

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
            public SourceExcel create(InputStream sourceStream) {
                if (sourceStream == null) {
                    throw new IllegalArgumentException("sourceStream param could not be null");
                }
                try {
                    return new ApachePoiSource(org.apache.poi.ss.usermodel.WorkbookFactory.create(sourceStream));
                } catch (Throwable e) {
                    throw new IllegalStateException("Could not open excel", e);
                }
            }
        };
    }

    public abstract SourceExcel create(InputStream is);
}
