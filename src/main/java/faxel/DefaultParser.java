package faxel;

class DefaultParser implements FaxelParser {
    DefaultParser(String filePath) {

    }

    public <T> T parseTo(Class<T> clazz) {
        return ClassInitializer.createSilently(clazz);
    }
}
