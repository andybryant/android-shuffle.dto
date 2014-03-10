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

public class ContextChangeSetTest {

    @Test
    public void testMultipleChanges() {
        ContextChangeSet set = ContextChangeSet.newChangeSet();
        assertEquals(set.getChangeSet(), 0L);

        assertFalse(set.hasChanges());
        assertFalse(set.isActiveChanged());
        assertFalse(set.isDeletedChanged());
        assertFalse(set.isColourChanged());
        assertFalse(set.isIconChanged());
        assertFalse(set.isNameChanged());

        assertTrue(set.activeChanged());

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertFalse(set.isDeletedChanged());
        assertFalse(set.isColourChanged());
        assertFalse(set.isIconChanged());
        assertFalse(set.isNameChanged());

        assertTrue(set.deleteChanged());
        assertFalse(set.deleteChanged());

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertFalse(set.isColourChanged());
        assertFalse(set.isIconChanged());
        assertFalse(set.isNameChanged());

        set.colourChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isColourChanged());
        assertFalse(set.isIconChanged());
        assertFalse(set.isNameChanged());

        set.iconChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isColourChanged());
        assertTrue(set.isIconChanged());
        assertFalse(set.isNameChanged());

        set.nameChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isColourChanged());
        assertTrue(set.isIconChanged());
        assertTrue(set.isNameChanged());
    }

    @Test
    public void testSaveAndRestore() {
        ContextChangeSet set = ContextChangeSet.newChangeSet();

        assertTrue(set.nameChanged());
        assertTrue(set.iconChanged());

        long val = set.getChangeSet();

        ContextChangeSet clonedSet = ContextChangeSet.fromChangeSet(val);

        assertEquals(set, clonedSet);

        assertTrue(clonedSet.hasChanges());
        assertFalse(clonedSet.isActiveChanged());
        assertFalse(clonedSet.isDeletedChanged());
        assertTrue(clonedSet.isIconChanged());
        assertFalse(clonedSet.isColourChanged());
        assertTrue(clonedSet.isNameChanged());
    }

    @Test
    public void testMarkAll() {
        ContextChangeSet set = ContextChangeSet.newChangeSet();
        set.markAll();

        assertTrue(set.hasChanges());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isDeletedChanged());
        assertTrue(set.isColourChanged());
        assertTrue(set.isIconChanged());
        assertTrue(set.isNameChanged());
    }


}
