package com.example.oil.db.migration;

import androidx.annotation.Nullable;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class CarMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            RealmObjectSchema carSchema = schema.get("Car");


            carSchema
                    .addField("manufacturer", String.class, FieldAttribute.REQUIRED)
                    .addField("model", String.class, FieldAttribute.REQUIRED)
                    .addField("generation", String.class, FieldAttribute.REQUIRED)
                    .addField("carBody", String.class, FieldAttribute.REQUIRED)
                    .addField("yearOfManufacture", String.class, FieldAttribute.REQUIRED)
                    .transform(obj -> {

                        obj.set("manufacturer", obj.getString("manufacturer"));
                        obj.set("model", obj.getString("model"));
                        obj.set("generation", obj.getString("generation"));
                        obj.set("carBody", obj.getString("carBody"));
                        obj.set("yearOfManufacture", obj.getString("yearOfManufacture"));
                    });

            oldVersion++;
        }

    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return (obj instanceof CarMigration);
    }
}
