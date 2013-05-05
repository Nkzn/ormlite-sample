package jp.water_cell.android.sample.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Field {

    @DatabaseField(generatedId = true)
    Integer id;

    @DatabaseField
    String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    FieldBlock fieldBlock;

    public Field() {
    }

    public Field(String name, FieldBlock fieldBlock) {
        this.name = name;
        this.fieldBlock = fieldBlock;
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
     * @return the fieldBlock
     */
    public FieldBlock getFieldBlock() {
        return fieldBlock;
    }

    /**
     * @param fieldBlock
     *            the fieldBlock to set
     */
    public void setFieldBlock(FieldBlock fieldBlock) {
        this.fieldBlock = fieldBlock;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(name);

        if (fieldBlock != null) {
            sb.append("[" + fieldBlock.getName() + "]");
        }

        return sb.toString();
    }
}
