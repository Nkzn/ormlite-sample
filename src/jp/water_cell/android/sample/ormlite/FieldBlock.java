package jp.water_cell.android.sample.ormlite;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class FieldBlock {

    @DatabaseField(generatedId = true)
    Integer id;

    @DatabaseField
    String name;

    @ForeignCollectionField
    ForeignCollection<Field> fields;

    public FieldBlock() {
    }

    public FieldBlock(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fields
     */
    public ForeignCollection<Field> getFields() {
        return fields;
    }

}
