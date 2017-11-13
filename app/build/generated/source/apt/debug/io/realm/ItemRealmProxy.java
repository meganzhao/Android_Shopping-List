package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemRealmProxy extends hu.ait.android.shoppinglist.data.Item
    implements RealmObjectProxy, ItemRealmProxyInterface {

    static final class ItemColumnInfo extends ColumnInfo
        implements Cloneable {

        public long itemIdIndex;
        public long nameIndex;
        public long categoryIndex;
        public long priceIndex;
        public long noteIndex;
        public long isPurchasedIndex;

        ItemColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(6);
            this.itemIdIndex = getValidColumnIndex(path, table, "Item", "itemId");
            indicesMap.put("itemId", this.itemIdIndex);
            this.nameIndex = getValidColumnIndex(path, table, "Item", "name");
            indicesMap.put("name", this.nameIndex);
            this.categoryIndex = getValidColumnIndex(path, table, "Item", "category");
            indicesMap.put("category", this.categoryIndex);
            this.priceIndex = getValidColumnIndex(path, table, "Item", "price");
            indicesMap.put("price", this.priceIndex);
            this.noteIndex = getValidColumnIndex(path, table, "Item", "note");
            indicesMap.put("note", this.noteIndex);
            this.isPurchasedIndex = getValidColumnIndex(path, table, "Item", "isPurchased");
            indicesMap.put("isPurchased", this.isPurchasedIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final ItemColumnInfo otherInfo = (ItemColumnInfo) other;
            this.itemIdIndex = otherInfo.itemIdIndex;
            this.nameIndex = otherInfo.nameIndex;
            this.categoryIndex = otherInfo.categoryIndex;
            this.priceIndex = otherInfo.priceIndex;
            this.noteIndex = otherInfo.noteIndex;
            this.isPurchasedIndex = otherInfo.isPurchasedIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final ItemColumnInfo clone() {
            return (ItemColumnInfo) super.clone();
        }

    }
    private ItemColumnInfo columnInfo;
    private ProxyState<hu.ait.android.shoppinglist.data.Item> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("itemId");
        fieldNames.add("name");
        fieldNames.add("category");
        fieldNames.add("price");
        fieldNames.add("note");
        fieldNames.add("isPurchased");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<hu.ait.android.shoppinglist.data.Item>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$itemId() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemIdIndex);
    }

    public void realmSet$itemId(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'itemId' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$category() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.categoryIndex);
    }

    public void realmSet$category(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.categoryIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.categoryIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.categoryIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.categoryIndex, value);
    }

    @SuppressWarnings("cast")
    public double realmGet$price() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.priceIndex);
    }

    public void realmSet$price(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.priceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.priceIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$note() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.noteIndex);
    }

    public void realmSet$note(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.noteIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.noteIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.noteIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.noteIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$isPurchased() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isPurchasedIndex);
    }

    public void realmSet$isPurchased(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isPurchasedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isPurchasedIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Item")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Item");
            realmObjectSchema.add(new Property("itemId", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("category", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("price", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("note", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("isPurchased", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Item");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Item")) {
            Table table = sharedRealm.getTable("class_Item");
            table.addColumn(RealmFieldType.STRING, "itemId", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "category", Table.NULLABLE);
            table.addColumn(RealmFieldType.DOUBLE, "price", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "note", Table.NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "isPurchased", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("itemId"));
            table.setPrimaryKey("itemId");
            return table;
        }
        return sharedRealm.getTable("class_Item");
    }

    public static ItemColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Item")) {
            Table table = sharedRealm.getTable("class_Item");
            final long columnCount = table.getColumnCount();
            if (columnCount != 6) {
                if (columnCount < 6) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 6 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 6 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 6 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ItemColumnInfo columnInfo = new ItemColumnInfo(sharedRealm.getPath(), table);

            if (!table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'itemId' in existing Realm file. @PrimaryKey was added.");
            } else {
                if (table.getPrimaryKey() != columnInfo.itemIdIndex) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field itemId");
                }
            }

            if (!columnTypes.containsKey("itemId")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'itemId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'itemId' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.itemIdIndex) && table.findFirstNull(columnInfo.itemIdIndex) != Table.NO_MATCH) {
                throw new IllegalStateException("Cannot migrate an object with null value in field 'itemId'. Either maintain the same type for primary key field 'itemId', or remove the object with null value before migration.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("itemId"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'itemId' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("category")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'category' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("category") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'category' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.categoryIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'category' is required. Either set @Required to field 'category' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("price")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'price' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("price") != RealmFieldType.DOUBLE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'double' for field 'price' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.priceIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'price' does support null values in the existing Realm file. Use corresponding boxed type for field 'price' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("note")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'note' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("note") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'note' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.noteIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'note' is required. Either set @Required to field 'note' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isPurchased")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isPurchased' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isPurchased") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isPurchased' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.isPurchasedIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isPurchased' does support null values in the existing Realm file. Use corresponding boxed type for field 'isPurchased' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Item' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Item";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static hu.ait.android.shoppinglist.data.Item createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        hu.ait.android.shoppinglist.data.Item obj = null;
        if (update) {
            Table table = realm.getTable(hu.ait.android.shoppinglist.data.Item.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("itemId")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("itemId"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(hu.ait.android.shoppinglist.data.Item.class), false, Collections.<String> emptyList());
                    obj = new io.realm.ItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("itemId")) {
                if (json.isNull("itemId")) {
                    obj = (io.realm.ItemRealmProxy) realm.createObjectInternal(hu.ait.android.shoppinglist.data.Item.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.ItemRealmProxy) realm.createObjectInternal(hu.ait.android.shoppinglist.data.Item.class, json.getString("itemId"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'itemId'.");
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((ItemRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("category")) {
            if (json.isNull("category")) {
                ((ItemRealmProxyInterface) obj).realmSet$category(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$category((String) json.getString("category"));
            }
        }
        if (json.has("price")) {
            if (json.isNull("price")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'price' to null.");
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$price((double) json.getDouble("price"));
            }
        }
        if (json.has("note")) {
            if (json.isNull("note")) {
                ((ItemRealmProxyInterface) obj).realmSet$note(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$note((String) json.getString("note"));
            }
        }
        if (json.has("isPurchased")) {
            if (json.isNull("isPurchased")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isPurchased' to null.");
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$isPurchased((boolean) json.getBoolean("isPurchased"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static hu.ait.android.shoppinglist.data.Item createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        hu.ait.android.shoppinglist.data.Item obj = new hu.ait.android.shoppinglist.data.Item();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("itemId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemId(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemId((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("category")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$category(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$category((String) reader.nextString());
                }
            } else if (name.equals("price")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'price' to null.");
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$price((double) reader.nextDouble());
                }
            } else if (name.equals("note")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$note(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$note((String) reader.nextString());
                }
            } else if (name.equals("isPurchased")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isPurchased' to null.");
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$isPurchased((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'itemId'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static hu.ait.android.shoppinglist.data.Item copyOrUpdate(Realm realm, hu.ait.android.shoppinglist.data.Item object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (hu.ait.android.shoppinglist.data.Item) cachedRealmObject;
        } else {
            hu.ait.android.shoppinglist.data.Item realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(hu.ait.android.shoppinglist.data.Item.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstString(pkColumnIndex, ((ItemRealmProxyInterface) object).realmGet$itemId());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(hu.ait.android.shoppinglist.data.Item.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.ItemRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static hu.ait.android.shoppinglist.data.Item copy(Realm realm, hu.ait.android.shoppinglist.data.Item newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (hu.ait.android.shoppinglist.data.Item) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            hu.ait.android.shoppinglist.data.Item realmObject = realm.createObjectInternal(hu.ait.android.shoppinglist.data.Item.class, ((ItemRealmProxyInterface) newObject).realmGet$itemId(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((ItemRealmProxyInterface) realmObject).realmSet$name(((ItemRealmProxyInterface) newObject).realmGet$name());
            ((ItemRealmProxyInterface) realmObject).realmSet$category(((ItemRealmProxyInterface) newObject).realmGet$category());
            ((ItemRealmProxyInterface) realmObject).realmSet$price(((ItemRealmProxyInterface) newObject).realmGet$price());
            ((ItemRealmProxyInterface) realmObject).realmSet$note(((ItemRealmProxyInterface) newObject).realmGet$note());
            ((ItemRealmProxyInterface) realmObject).realmSet$isPurchased(((ItemRealmProxyInterface) newObject).realmGet$isPurchased());
            return realmObject;
        }
    }

    public static long insert(Realm realm, hu.ait.android.shoppinglist.data.Item object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(hu.ait.android.shoppinglist.data.Item.class);
        long tableNativePtr = table.getNativeTablePointer();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.schema.getColumnInfo(hu.ait.android.shoppinglist.data.Item.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((ItemRealmProxyInterface) object).realmGet$itemId();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((ItemRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$category = ((ItemRealmProxyInterface)object).realmGet$category();
        if (realmGet$category != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.priceIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$price(), false);
        String realmGet$note = ((ItemRealmProxyInterface)object).realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isPurchasedIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$isPurchased(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(hu.ait.android.shoppinglist.data.Item.class);
        long tableNativePtr = table.getNativeTablePointer();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.schema.getColumnInfo(hu.ait.android.shoppinglist.data.Item.class);
        long pkColumnIndex = table.getPrimaryKey();
        hu.ait.android.shoppinglist.data.Item object = null;
        while (objects.hasNext()) {
            object = (hu.ait.android.shoppinglist.data.Item) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((ItemRealmProxyInterface) object).realmGet$itemId();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((ItemRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                String realmGet$category = ((ItemRealmProxyInterface)object).realmGet$category();
                if (realmGet$category != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
                }
                Table.nativeSetDouble(tableNativePtr, columnInfo.priceIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$price(), false);
                String realmGet$note = ((ItemRealmProxyInterface)object).realmGet$note();
                if (realmGet$note != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isPurchasedIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$isPurchased(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, hu.ait.android.shoppinglist.data.Item object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(hu.ait.android.shoppinglist.data.Item.class);
        long tableNativePtr = table.getNativeTablePointer();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.schema.getColumnInfo(hu.ait.android.shoppinglist.data.Item.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((ItemRealmProxyInterface) object).realmGet$itemId();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((ItemRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$category = ((ItemRealmProxyInterface)object).realmGet$category();
        if (realmGet$category != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.categoryIndex, rowIndex, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.priceIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$price(), false);
        String realmGet$note = ((ItemRealmProxyInterface)object).realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isPurchasedIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$isPurchased(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(hu.ait.android.shoppinglist.data.Item.class);
        long tableNativePtr = table.getNativeTablePointer();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.schema.getColumnInfo(hu.ait.android.shoppinglist.data.Item.class);
        long pkColumnIndex = table.getPrimaryKey();
        hu.ait.android.shoppinglist.data.Item object = null;
        while (objects.hasNext()) {
            object = (hu.ait.android.shoppinglist.data.Item) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((ItemRealmProxyInterface) object).realmGet$itemId();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((ItemRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                String realmGet$category = ((ItemRealmProxyInterface)object).realmGet$category();
                if (realmGet$category != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.categoryIndex, rowIndex, false);
                }
                Table.nativeSetDouble(tableNativePtr, columnInfo.priceIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$price(), false);
                String realmGet$note = ((ItemRealmProxyInterface)object).realmGet$note();
                if (realmGet$note != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isPurchasedIndex, rowIndex, ((ItemRealmProxyInterface)object).realmGet$isPurchased(), false);
            }
        }
    }

    public static hu.ait.android.shoppinglist.data.Item createDetachedCopy(hu.ait.android.shoppinglist.data.Item realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        hu.ait.android.shoppinglist.data.Item unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (hu.ait.android.shoppinglist.data.Item)cachedObject.object;
            } else {
                unmanagedObject = (hu.ait.android.shoppinglist.data.Item)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new hu.ait.android.shoppinglist.data.Item();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((ItemRealmProxyInterface) unmanagedObject).realmSet$itemId(((ItemRealmProxyInterface) realmObject).realmGet$itemId());
        ((ItemRealmProxyInterface) unmanagedObject).realmSet$name(((ItemRealmProxyInterface) realmObject).realmGet$name());
        ((ItemRealmProxyInterface) unmanagedObject).realmSet$category(((ItemRealmProxyInterface) realmObject).realmGet$category());
        ((ItemRealmProxyInterface) unmanagedObject).realmSet$price(((ItemRealmProxyInterface) realmObject).realmGet$price());
        ((ItemRealmProxyInterface) unmanagedObject).realmSet$note(((ItemRealmProxyInterface) realmObject).realmGet$note());
        ((ItemRealmProxyInterface) unmanagedObject).realmSet$isPurchased(((ItemRealmProxyInterface) realmObject).realmGet$isPurchased());
        return unmanagedObject;
    }

    static hu.ait.android.shoppinglist.data.Item update(Realm realm, hu.ait.android.shoppinglist.data.Item realmObject, hu.ait.android.shoppinglist.data.Item newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((ItemRealmProxyInterface) realmObject).realmSet$name(((ItemRealmProxyInterface) newObject).realmGet$name());
        ((ItemRealmProxyInterface) realmObject).realmSet$category(((ItemRealmProxyInterface) newObject).realmGet$category());
        ((ItemRealmProxyInterface) realmObject).realmSet$price(((ItemRealmProxyInterface) newObject).realmGet$price());
        ((ItemRealmProxyInterface) realmObject).realmSet$note(((ItemRealmProxyInterface) newObject).realmGet$note());
        ((ItemRealmProxyInterface) realmObject).realmSet$isPurchased(((ItemRealmProxyInterface) newObject).realmGet$isPurchased());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Item = [");
        stringBuilder.append("{itemId:");
        stringBuilder.append(realmGet$itemId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{category:");
        stringBuilder.append(realmGet$category() != null ? realmGet$category() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{price:");
        stringBuilder.append(realmGet$price());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{note:");
        stringBuilder.append(realmGet$note() != null ? realmGet$note() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isPurchased:");
        stringBuilder.append(realmGet$isPurchased());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemRealmProxy aItem = (ItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
