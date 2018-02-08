package pl.kacperb333.java.foodbook.domain.test;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kacperb333.java.foodbook.domain.commontype.DomainEntity;
import pl.kacperb333.java.foodbook.domain.commontype.UniqueIdentifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositoryTest {

    private SimpleEntityReadRepository readRepository;
    private SimpleEntityWriteRepository writeRepository;

    @BeforeMethod
    void before() {
        initialize();
    }

    private void initialize(Long... ids) {
        Map<SimpleEntity.Identifier, SimpleEntity> initialDatabase = new HashMap<>();
        for(Long id : ids) {
            SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(id);
            SimpleEntity initialization = new SimpleEntity(identifier);
            initialDatabase.put(initialization.getId(), initialization);
        }

        readRepository = new SimpleEntityReadRepository(initialDatabase);
        writeRepository = new SimpleEntityWriteRepository(readRepository);
    }

    @Test
    void addedEntityShouldBeRead() {
        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);

        SimpleEntity toSave = new SimpleEntity(identifier);
        writeRepository.save(toSave);

        Optional<SimpleEntity> read = readRepository.find(identifier);
        Assert.assertTrue(read.isPresent());
    }

    @Test
    void nonexistentEntityShouldNotBeFound() {
        SimpleEntity.Identifier nonexistentIdentifier = new SimpleEntity.Identifier(1L);

        Optional<SimpleEntity> notFound = readRepository.find(nonexistentIdentifier);

        Assert.assertFalse(notFound.isPresent());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    void shouldThrowExceptionWhenAddingDuplicateEntity() {
        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);

        SimpleEntity toInsertTwoTimes = new SimpleEntity(identifier);

        writeRepository.save(toInsertTwoTimes);
        writeRepository.save(toInsertTwoTimes);
    }

    @Test
    void shouldReadEntityFromInitializedRepository() {
        initialize(1L);

        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);
        Optional<SimpleEntity> toRead = readRepository.find(identifier);

        Assert.assertTrue(toRead.isPresent());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    void shouldThrowWhenInsertingEntityAlreadyPresentInInitializedRepository() {
        initialize(1L);

        SimpleEntity.Identifier identifier = new SimpleEntity.Identifier(1L);
        SimpleEntity toWrite = new SimpleEntity(identifier);

        writeRepository.save(toWrite);
    }

    private static class SimpleEntity implements DomainEntity<SimpleEntity.Identifier> {
        static class Identifier implements UniqueIdentifier {
            private final Long id;

            public Identifier(Long id) {
                this.id = id;
            }

            @Override
            public Long getId() {
                return id;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;

                if (o == null || getClass() != o.getClass()) return false;

                Identifier that = (Identifier) o;

                return new EqualsBuilder()
                        .append(id, that.id)
                        .isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37)
                        .append(id)
                        .toHashCode();
            }
        }

        private final Identifier id;

        public SimpleEntity(Identifier id) {
            this.id = id;
        }

        @Override
        public Identifier getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            SimpleEntity that = (SimpleEntity) o;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .toHashCode();
        }
    }


    private static class SimpleEntityReadRepository extends TestReadRepository<SimpleEntity, SimpleEntity.Identifier> {

        SimpleEntityReadRepository() {
            super();
        }

        protected SimpleEntityReadRepository(Map<SimpleEntity.Identifier, SimpleEntity> initialDatabase) {
            super(initialDatabase);
        }

        @Override
        public SimpleEntity.Identifier provideNewIdentifier() {
            return new SimpleEntity.Identifier(sequence.getAndIncrement());
        }
    }

    private static class SimpleEntityWriteRepository extends TestWriteRepository<SimpleEntity, SimpleEntity.Identifier> {


        SimpleEntityWriteRepository(SimpleEntityReadRepository underlyingReadRepository) {
            super(underlyingReadRepository);
        }
    }
}
