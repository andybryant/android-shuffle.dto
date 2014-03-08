/*
 * Copyright (C) 2012 Android Shuffle Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dodgybits.shuffle.sync.model;

public class ContextChangeSet extends EntityChangeSet {

    private static final long NAME_MASK = 0b100;
    private static final long COLOUR_MASK = 0b1000;
    private static final long ICON_MASK = 0b1_0000;

    public static ContextChangeSet newChangeSet() {
        return new ContextChangeSet(0L);
    }

    public static ContextChangeSet fromChangeSet(long changeSet) {
        return new ContextChangeSet(changeSet);
    }

    private ContextChangeSet(long changeSet) {
        super(changeSet);
    }

    public boolean isNameChanged() {
        return changed(NAME_MASK);
    }

    public void nameChanged() {
        mark(NAME_MASK);
    }

    public boolean isColourChanged() {
        return changed(COLOUR_MASK);
    }

    public void colourChanged() {
        mark(COLOUR_MASK);
    }

    public boolean isIconChanged() {
        return changed(ICON_MASK);
    }

    public void iconChanged() {
        mark(ICON_MASK);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ContextChangeSet");
        if (hasChanges()) {
            builder.append(" changed:");
            if (isActiveChanged()) {
                builder.append(" active");
            }
            if (isDeletedChanged()) {
                builder.append(" deleted");
            }
            if (isNameChanged()) {
                builder.append(" name");
            }
            if (isIconChanged()) {
                builder.append(" icon");
            }
            if (isColourChanged()) {
                builder.append(" colour");
            }
        } else {
            builder.append(" unchanged");
        }
        builder.append(']');
        return builder.toString();
    }
}
