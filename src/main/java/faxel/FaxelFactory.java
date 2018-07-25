package faxel;

public class FaxelFactory {
    static <D> FaxelParser create(Class<D> dClass) {
        return new DefaultParser<>(dClass);
    }
}
