package com.example.oil.db.migration;

import androidx.annotation.Nullable;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class CarModelMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            RealmObjectSchema carSchema = schema.get("Model");


            carSchema
                    .addField("model", String.class, FieldAttribute.REQUIRED)
                    .transform(obj -> obj.set("model", obj.getString("model")));

            oldVersion++;
        }

    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof CarModelMigration;
    }
}
