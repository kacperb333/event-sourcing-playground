package pl.kacperb333.java.eventsourcing;

public class NoExpectedResultException extends Exception {
    private final long expectedLeastAggregateVersion;
    private final long actualAggregateVersion;

    public NoExpectedResultException(long expectedLeastAggregateVersion, long actualAggregateVersion) {
        super(String.format("Aggregate version mismatch. Expected at least: %s, actual: %s",
                expectedLeastAggregateVersion, actualAggregateVersion));
        this.expectedLeastAggregateVersion = expectedLeastAggregateVersion;
        this.actualAggregateVersion = actualAggregateVersion;
    }

    public long getExpectedLeastAggregateVersion() {
        return expectedLeastAggregateVersion;
    }

    public long getActualAggregateVersion() {
        return actualAggregateVersion;
    }
}
