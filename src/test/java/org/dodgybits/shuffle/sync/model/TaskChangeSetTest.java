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

public class TaskChangeSetTest {

    @Test
    public void testActiveFlag() {
        TaskChangeSet set = TaskChangeSet.newChangeSet();
        assertFalse(set.hasChanges());
        assertEquals(set.getChangeSet(), 0L);
        assertFalse(set.isActiveChanged());

        set.activeChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.getChangeSet() > 0L);
        assertTrue(set.isActiveChanged());
        assertFalse(set.isDeletedChanged());

        set.activeChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.getChangeSet() > 0L);
        assertTrue(set.isActiveChanged());
        assertFalse(set.isDeletedChanged());

        set.reset();

        assertFalse(set.hasChanges());
        assertEquals(set.getChangeSet(), 0L);
        assertFalse(set.isActiveChanged());
        assertFalse(set.isDeletedChanged());
    }

    @Test
    public void testSaveAndRestore() {
        TaskChangeSet set = TaskChangeSet.newChangeSet();
        set.allDayChanged();
        assertTrue(set.activeChanged());
        assertTrue(set.detailsChanged());
        set.descriptionChanged();

        assertTrue(set.hasChanges());
        assertTrue(set.isDetailsChanged());
        assertTrue(set.isDescriptionChanged());
        assertFalse(set.isContextsChanged());
        assertFalse(set.isProjectChanged());
        assertFalse(set.isDueChanged());
        assertFalse(set.isShowFromChanged());
        assertFalse(set.isOrderChanged());
        assertFalse(set.isCompleteChanged());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isAllDayChanged());

        long val = set.getChangeSet();

        TaskChangeSet clonedSet = TaskChangeSet.fromChangeSet(val);

        assertEquals(clonedSet, set);

        assertTrue(clonedSet.hasChanges());
        assertTrue(clonedSet.isDetailsChanged());
        assertTrue(clonedSet.isDescriptionChanged());
        assertFalse(clonedSet.isContextsChanged());
        assertFalse(clonedSet.isProjectChanged());
        assertFalse(clonedSet.isDueChanged());
        assertFalse(clonedSet.isShowFromChanged());
        assertFalse(clonedSet.isOrderChanged());
        assertFalse(clonedSet.isCompleteChanged());
        assertTrue(clonedSet.isActiveChanged());
        assertTrue(clonedSet.isAllDayChanged());
    }

    @Test
    public void onlyOrderChanged() {
        TaskChangeSet set = TaskChangeSet.newChangeSet();
        assertFalse(set.isOrderChanged());

        assertTrue(set.orderChanged());

        assertTrue(set.hasChanges());
        assertFalse(set.isDetailsChanged());
        assertFalse(set.isDescriptionChanged());
        assertFalse(set.isContextsChanged());
        assertFalse(set.isProjectChanged());
        assertFalse(set.isDueChanged());
        assertFalse(set.isShowFromChanged());
        assertTrue(set.isOrderChanged());
        assertFalse(set.isCompleteChanged());
        assertFalse(set.isActiveChanged());
        assertFalse(set.isAllDayChanged());
    }

    @Test
    public void testMarkAll() {
        TaskChangeSet set = TaskChangeSet.newChangeSet();
        set.markAll();

        assertTrue(set.hasChanges());
        assertTrue(set.isDetailsChanged());
        assertTrue(set.isDescriptionChanged());
        assertTrue(set.isContextsChanged());
        assertTrue(set.isProjectChanged());
        assertTrue(set.isDueChanged());
        assertTrue(set.isShowFromChanged());
        assertTrue(set.isOrderChanged());
        assertTrue(set.isCompleteChanged());
        assertTrue(set.isActiveChanged());
        assertTrue(set.isAllDayChanged());

    }

}
