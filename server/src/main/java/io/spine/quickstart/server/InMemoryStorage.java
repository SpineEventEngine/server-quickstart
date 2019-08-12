/*
 * Copyright 2019, TeamDev. All rights reserved.
 *
 * Redistribution and use in source and/or binary forms, with or without
 * modification, must retain the above copyright notice and the following
 * disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.spine.quickstart.server;

import io.spine.server.ContextSpec;
import io.spine.server.aggregate.Aggregate;
import io.spine.server.aggregate.AggregateStorage;
import io.spine.server.delivery.InboxStorage;
import io.spine.server.entity.Entity;
import io.spine.server.projection.Projection;
import io.spine.server.projection.ProjectionStorage;
import io.spine.server.storage.RecordStorage;
import io.spine.server.storage.StorageFactory;
import io.spine.server.storage.memory.InMemoryStorageFactory;

//TODO:2019-08-12:alex.tymchenko: This is a workaround, as in-memory cannot be used in `production`.
public class InMemoryStorage implements StorageFactory {

    private final InMemoryStorageFactory factory = InMemoryStorageFactory.newInstance();

    @Override
    public <I> AggregateStorage<I> createAggregateStorage(ContextSpec context,
                                                          Class<? extends Aggregate<I, ?, ?>> aggregateClass) {
        return factory.createAggregateStorage(context, aggregateClass);
    }

    @Override
    public <I> RecordStorage<I> createRecordStorage(ContextSpec context,
                                                    Class<? extends Entity<I, ?>> entityClass) {
        return factory.createRecordStorage(context, entityClass);
    }

    @Override
    public <I> ProjectionStorage<I> createProjectionStorage(ContextSpec context,
                                                            Class<? extends Projection<I, ?, ?>> projectionClass) {
        return factory.createProjectionStorage(context, projectionClass);
    }

    @Override
    public InboxStorage createInboxStorage(boolean multitenant) {
        return factory.createInboxStorage(multitenant);
    }

    @Override
    public void close() throws Exception {
        factory.close();
    }
}
