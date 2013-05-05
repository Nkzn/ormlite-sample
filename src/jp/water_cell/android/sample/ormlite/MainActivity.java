package jp.water_cell.android.sample.ormlite;

import java.sql.SQLException;
import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

public class MainActivity extends ListActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DbHelper mDbHelper = null;

    private List<Field> mFields;
    private ArrayAdapter<Field> mAdapter;

    private View waiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        waiting = getLayoutInflater().inflate(R.layout.waiting, null);
        getListView().addHeaderView(waiting);
        waiting.setVisibility(View.GONE);

        try {
            TableUtils.dropTable(getHelper().getConnectionSource(), FieldBlock.class, true);
            TableUtils.dropTable(getHelper().getConnectionSource(), Field.class, true);
            TableUtils.createTableIfNotExists(getHelper().getConnectionSource(), FieldBlock.class);
            TableUtils.createTableIfNotExists(getHelper().getConnectionSource(), Field.class);

            final Dao<FieldBlock, Integer> fieldBlockDAO = getHelper().getDao(FieldBlock.class);
            final Dao<Field, Integer> fieldDAO = getHelper().getDao(Field.class);

            // データ作成
            initData(fieldBlockDAO, fieldDAO);

            // テーブル内データでリストを初期化
            mFields = fieldDAO.queryForAll();
            mAdapter = new ArrayAdapter<Field>(this, android.R.layout.simple_list_item_1, mFields);

            setListAdapter(mAdapter);
        } catch (SQLException e) {
            Log.w(TAG, e);
        }

        new AsyncDataRefresh().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDbHelper != null) {
            OpenHelperManager.releaseHelper();
            mDbHelper = null;
        }
    }

    private DbHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DbHelper.class);
        }
        return mDbHelper;
    }

    private void initData(Dao<FieldBlock, Integer> fieldBlockDAO, Dao<Field, Integer> fieldDAO) throws SQLException {

        {
            final FieldBlock fieldBlock = new FieldBlock("ブロック１");
            final Field field1 = new Field("圃場１−１", fieldBlock);
            final Field field2 = new Field("圃場１−２", fieldBlock);
            final Field field3 = new Field("圃場１−３", fieldBlock);

            fieldBlockDAO.create(fieldBlock);
            fieldDAO.create(field1);
            fieldDAO.create(field2);
            fieldDAO.create(field3);
        }

        {
            final FieldBlock fieldBlock = new FieldBlock("ブロック２");
            final Field field1 = new Field("圃場２−１", fieldBlock);
            final Field field2 = new Field("圃場２−２", fieldBlock);
            final Field field3 = new Field("圃場２−３", fieldBlock);

            fieldBlockDAO.create(fieldBlock);
            fieldDAO.create(field1);
            fieldDAO.create(field2);
            fieldDAO.create(field3);
        }

        {
            final FieldBlock fieldBlock = new FieldBlock("ブロック３");
            final Field field1 = new Field("圃場３−１", fieldBlock);
            final Field field2 = new Field("圃場３−２", fieldBlock);
            final Field field3 = new Field("圃場３−３", fieldBlock);

            fieldBlockDAO.create(fieldBlock);
            fieldDAO.create(field1);
            fieldDAO.create(field2);
            fieldDAO.create(field3);
        }
    }

    class AsyncDataRefresh extends AsyncTask<Void, Void, List<Field>> {

        public AsyncDataRefresh() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            waiting.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(List<Field> result) {
            super.onPostExecute(result);

            mFields.clear();
            mFields.addAll(result);

            getListView().removeHeaderView(waiting);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<Field> doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.w(TAG, e);
            }

            try {
                final Dao<FieldBlock, Integer> fieldBlockDAO = getHelper().getDao(FieldBlock.class);
                final Dao<Field, Integer> fieldDAO = getHelper().getDao(Field.class);

                FieldBlock block = new FieldBlock("ブロック４");
                Field field = new Field("圃場４−１", block);

                fieldBlockDAO.create(block);
                fieldDAO.create(field);

                return fieldDAO.queryForAll();
            } catch (SQLException e) {
                Log.w(TAG, e);
            }

            return null;
        }

    }
}
