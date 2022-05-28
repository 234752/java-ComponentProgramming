package pl.cp.exception;

import java.util.ResourceBundle;

public class DaoException extends Exception {

    public static DaoException getDaoException(ResourceBundle bundle, String resourceBundleKey) {
        return new DaoException(bundle.getString(resourceBundleKey));
    }

    private DaoException(String message) {
        super(message);
    }
}
