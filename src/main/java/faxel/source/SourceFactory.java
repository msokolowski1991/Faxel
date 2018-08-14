package faxel.source;

import java.io.InputStream;

import faxel.source.apache.poi3.ApachePoi3Source;

/**
 * SourceExcel factory
 */
public abstract class SourceFactory {

    private static SourceFactory INSTANCE;

    /**
     * Returns SourceFactory instance. The instance is created based on runtime parsing library.
     * For example if an apache poi parser is present in runtime, then ApachePoiSourceFactory will be created.
     * @return SourceFactory instance
     */
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
            public SourceExcel create(InputStream inputStream) {
                if (inputStream == null) {
                    throw new IllegalArgumentException("sourceStream param could not be null");
                }
                try {
                    return new ApachePoi3Source(org.apache.poi.ss.usermodel.WorkbookFactory.create(inputStream));
                } catch (Throwable e) {
                    throw new IllegalStateException("Could not open excel", e);
                }
            }
        };
    }

    /**
     * Create SourceExcel from given InputStream. Must point to a valid excel document.
     * @param inputStream of the source excel
     * @return new SourceExcel created from given inputStream
     */
    public abstract SourceExcel create(InputStream inputStream);
}
