package faxel.source;

import java.io.InputStream;

/**
 * SourceExcel createFactory. Simplifies SourceFactory creation. <br>
 * Example usage:
 * <pre>
 * InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
 * SourceExcel source = SourceFactory.get().create(excelStream);
 * </pre>
 */
public abstract class SourceFactory {

    private static SourceFactory INSTANCE;

    /**
     * Returns SourceFactory instance. The instance is created based on runtime parsing library.
     * For example if an apache poi parser is present in runtime, then ApachePoiSourceFactory will be created.
     * If more than one supported library is present first one find is chosen.
     * Please use {@link #get(SourceType)}  if you want to choose library by your own.
     *
     * @return SourceFactory instance
     */
    public static SourceFactory get() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    /**
     * Returns SourceFactory instance of given source type.
     * @param type source type.
     * @return SourceFactory instance
     */
    public static SourceFactory get(SourceType type) {
        return type.createFactory();
    }

    private static SourceFactory createInstance() {
        return SourceType.findInClasspath()
                .orElseThrow(() -> new IllegalStateException("Not find any known excel parser. Provide one of [Apache POI 3, Docx4j 6]"))
                .createFactory();
    }

    /**
     * Create SourceExcel from given InputStream. Must point to a valid excel document.
     *
     * @param inputStream of the source excel
     * @return new SourceExcel created from given inputStream
     */
    public SourceExcel create(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("sourceStream param could not be null");
        }
        try {
            return doCreate(inputStream);
        } catch (Exception e) {
            throw new IllegalStateException("Could not open excel", e);
        }
    }

    /**
     * This method actually created instance of SourceExcel. {@link #create(InputStream)} do argument checking and exception handling and call this method internally.
     *
     * @param inputStream source input stream - not null
     * @return source excel implementation
     * @throws Exception in case of any error
     */
    protected abstract SourceExcel doCreate(InputStream inputStream) throws Exception;
}
