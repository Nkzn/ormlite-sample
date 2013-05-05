package jp.water_cell.android.sample.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    public DbHelper(Context context) {
        super(context, "sample.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

}
