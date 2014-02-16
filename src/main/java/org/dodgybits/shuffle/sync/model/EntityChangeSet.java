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

public abstract class EntityChangeSet {
    private long changeSet;

    // All entities
    private static final long DELETED_MASK = 0b1;
    private static final long ACTIVE_MASK = 0b10;

    protected EntityChangeSet(long changeSet) {
        this.changeSet = changeSet;
    }

    public long getChangeSet() {
        return changeSet;
    }

    public boolean isDeletedChanged() {
        return changed(DELETED_MASK);
    }

    public void deleteChanged() {
        mark(DELETED_MASK);
    }

    public boolean isActiveChanged() {
        return changed(ACTIVE_MASK);
    }

    public void activeChanged() {
        mark(ACTIVE_MASK);
    }

    public void reset() {
        changeSet = 0L;
    }

    public boolean hasChanges() {
        return changeSet != 0L;
    }

    protected boolean changed(long mask) {
        return (changeSet & mask) != 0L;
    }

    protected void mark(long mask) {
        changeSet |= mask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityChangeSet)) return false;

        EntityChangeSet that = (EntityChangeSet) o;

        if (changeSet != that.changeSet) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (changeSet ^ (changeSet >>> 32));
    }
}
