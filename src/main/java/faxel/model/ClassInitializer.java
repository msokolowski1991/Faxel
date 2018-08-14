package faxel.model;

import faxel.FaxelException;

final class ClassInitializer {

    private ClassInitializer() {
        throw new FaxelException("You should not create instance of ClassInitializer");
    }

    static <T> T createSilently(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable cause) {
            throw new FaxelException(
                    cause, "Cold not create object of class %s. Probably it does not have public non args constructor", clazz.getName()
            );
        }
    }
}
