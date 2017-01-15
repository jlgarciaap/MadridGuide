package es.styleapps.madridguide.manager.db;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public interface DAOPersistable<T> {
    long insert(final @NonNull T data);
    void update(final long id, final @NonNull T data);
    int delete(final long id);
    void deleteAll();
    @Nullable Cursor queryCursor();
    T query(final long id);
    @Nullable List<T> query();
}
