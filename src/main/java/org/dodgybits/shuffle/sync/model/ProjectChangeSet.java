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

public class ProjectChangeSet extends EntityChangeSet {

    private static final long NAME_MASK = 0b100;
    private static final long DEFAULT_CONTEXT_MASK = 0b1000;
    private static final long PARALLEL_MASK = 0b1_0000;
    private static final long ORDER_MASK = 0b10_0000;

    public static ProjectChangeSet newChangeSet() {
        return new ProjectChangeSet(NO_CHANGES);
    }

    public static ProjectChangeSet fromChangeSet(long changeSet) {
        return new ProjectChangeSet(changeSet);
    }

    private ProjectChangeSet(long changeSet) {
        super(changeSet);
    }

    public boolean isNameChanged() {
        return changed(NAME_MASK);
    }

    public boolean nameChanged() {
        return mark(NAME_MASK);
    }

    public boolean isDefaultContextChanged() {
        return changed(DEFAULT_CONTEXT_MASK);
    }

    public boolean defaultContextChanged() {
        return mark(DEFAULT_CONTEXT_MASK);
    }

    public boolean isParallelChanged() {
        return changed(PARALLEL_MASK);
    }

    public boolean parallelChanged() {
        return mark(PARALLEL_MASK);
    }

    public boolean isOrderChanged() {
        return changed(ORDER_MASK);
    }

    public boolean orderChanged() {
        return mark(ORDER_MASK);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ProjectChangeSet");
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
            if (isDefaultContextChanged()) {
                builder.append(" defaultContext");
            }
            if (isParallelChanged()) {
                builder.append(" parallel");
            }
            if (isOrderChanged()) {
                builder.append(" order");
            }
        } else {
            builder.append(" unchanged");
        }
        builder.append(']');
        return builder.toString();
    }

}
