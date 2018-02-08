package pl.kacperb333.java.foodbook.domain.test;

import java.util.concurrent.ConcurrentMap;

class SimpleEntityWriteRepository extends TestWriteRepository<SimpleEntity, SimpleEntity.Identifier> {

    public SimpleEntityWriteRepository(ConcurrentMap<SimpleEntity.Identifier, SimpleEntity> database) {
        super(database);
    }
}