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

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProjectChangeSetTest {

    @Test
    public void testMultipleChanges() {
        ProjectChangeSet set = ProjectChangeSet.newChangeSet();
        assertEquals(set.getChangeSet(), 0L);

        assertFalse(set.hasChanges());
        assertFalse(set.isActiveChanged());
        assertFalse(set.isDeletedChanged());
        assertFalse(set.isParallelChanged());
        assertFalse(set.isNameChanged());
        assertFalse(set.isDefaultContextChanged());
        assertFalse(set.isOrderChanged());

        set.activeChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertFalse(set.isDeletedChanged());
        assertFalse(set.isParallelChanged());
        assertFalse(set.isNameChanged());
        assertFalse(set.isDefaultContextChanged());
        assertFalse(set.isOrderChanged());

        set.deleteChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertFalse(set.isParallelChanged());
        assertFalse(set.isNameChanged());
        assertFalse(set.isDefaultContextChanged());
        assertFalse(set.isOrderChanged());

        assertTrue(set.parallelChanged());
        assertFalse(set.parallelChanged());

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isParallelChanged());
        assertFalse(set.isNameChanged());
        assertFalse(set.isDefaultContextChanged());
        assertFalse(set.isOrderChanged());

        set.nameChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isParallelChanged());
        assertTrue(set.isNameChanged());
        assertFalse(set.isDefaultContextChanged());
        assertFalse(set.isOrderChanged());

        set.defaultContextChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isParallelChanged());
        assertTrue(set.isNameChanged());
        assertTrue(set.isDefaultContextChanged());
        assertFalse(set.isOrderChanged());

        assertTrue(set.orderChanged());
        assertFalse(set.orderChanged());

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isParallelChanged());
        assertTrue(set.isNameChanged());
        assertTrue(set.isDefaultContextChanged());
        assertTrue(set.isOrderChanged());
    }

    @Test
    public void testSaveAndRestore() {
        ProjectChangeSet set = ProjectChangeSet.newChangeSet();

        set.nameChanged();
        set.parallelChanged();

        long val = set.getChangeSet();

        ProjectChangeSet clonedSet = ProjectChangeSet.fromChangeSet(val);

        assertEquals(set, clonedSet);

        assertTrue(clonedSet.hasChanges());
        assertFalse(clonedSet.isActiveChanged());
        assertFalse(clonedSet.isDeletedChanged());
        assertFalse(clonedSet.isDefaultContextChanged());
        assertTrue(clonedSet.isParallelChanged());
        assertTrue(clonedSet.isNameChanged());
        assertFalse(clonedSet.isOrderChanged());
    }


}
