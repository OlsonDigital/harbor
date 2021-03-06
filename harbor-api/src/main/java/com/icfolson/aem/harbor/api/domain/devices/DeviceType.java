package com.icfolson.aem.harbor.api.domain.devices;

import com.google.common.base.Optional;
import com.icfolson.aem.harbor.api.constants.devices.DeviceTypes;

public enum DeviceType {
    PHONE(DeviceTypes.PHONE),
    TABLET(DeviceTypes.TABLET),
    DESKTOP(DeviceTypes.DESKTOP),
    LARGE_DESKTOP(DeviceTypes.LARGE_DESKTOP);

    private final String typeIdentifier;

    DeviceType(String typeIdentifier) {
        this.typeIdentifier = typeIdentifier;
    }

    public String getTypeIdentifier() {
        return typeIdentifier;
    }

    public static Optional<DeviceType> forIdentifier(String id) {
        if (id.equals(DeviceTypes.PHONE)) {
            return Optional.fromNullable(PHONE);
        }
        if (id.equals(DeviceTypes.TABLET)) {
            return Optional.fromNullable(TABLET);
        }
        if (id.equals(DeviceTypes.DESKTOP)) {
            return Optional.fromNullable(DESKTOP);
        }
        if (id.equals(DeviceTypes.LARGE_DESKTOP)) {
            return Optional.fromNullable(LARGE_DESKTOP);
        }

        return Optional.absent();
    }
}
