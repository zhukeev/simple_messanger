package com.example.oil.db.migration;

import androidx.annotation.Nullable;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class OilTypeMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            RealmObjectSchema carSchema = schema.get("Oil_type");


            carSchema
                    .addField("oil_type", String.class, FieldAttribute.REQUIRED)
                    .transform(obj -> obj.set("oil_type", obj.getString("oil_type")));

            oldVersion++;
        }

    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof OilTypeMigration;
    }
}
