package com.example.oil.db.migration;

import androidx.annotation.Nullable;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class CarEngineMarkMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            RealmObjectSchema carSchema = schema.get("Engine_marks");


            carSchema
                    .addField("engine_marks", String.class, FieldAttribute.REQUIRED)
                    .transform(obj -> obj.set("engine_marks", obj.getString("engine_marks")));

            oldVersion++;
        }

    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof CarEngineMarkMigration;
    }
}
