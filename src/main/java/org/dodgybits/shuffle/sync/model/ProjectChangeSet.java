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

    public static ProjectChangeSet newChangeSet() {
        return new ProjectChangeSet(0L);
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

    public void nameChanged() {
        mark(NAME_MASK);
    }

    public boolean isDefaultContextChanged() {
        return changed(DEFAULT_CONTEXT_MASK);
    }

    public void defaultContextChanged() {
        mark(DEFAULT_CONTEXT_MASK);
    }

    public boolean isParallelChanged() {
        return changed(PARALLEL_MASK);
    }

    public void parallelChanged() {
        mark(PARALLEL_MASK);
    }

}
