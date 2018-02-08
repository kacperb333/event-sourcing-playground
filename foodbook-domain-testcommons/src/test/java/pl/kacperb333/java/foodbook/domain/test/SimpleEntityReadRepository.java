package pl.kacperb333.java.foodbook.domain.test;

import java.util.concurrent.ConcurrentMap;

class SimpleEntityReadRepository extends TestReadRepository<SimpleEntity, SimpleEntity.Identifier> {

    protected SimpleEntityReadRepository(ConcurrentMap<SimpleEntity.Identifier, SimpleEntity> database) {
        super(database);
    }
}
