package faxel;

public interface FaxelParser {
    <T> T parseTo(Class<T> clazz);
}
