package faxel;

public class FaxelFactory {
    static FaxelParser create(String filePath) {
        return new DefaultParser(filePath);
    }
}
