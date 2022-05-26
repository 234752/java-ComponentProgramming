package pl.cp.dao;

import pl.cp.exception.DaoException;

public interface Dao<T> extends AutoCloseable {

    T read() throws DaoException;

    void write(T obj) throws DaoException;

}
