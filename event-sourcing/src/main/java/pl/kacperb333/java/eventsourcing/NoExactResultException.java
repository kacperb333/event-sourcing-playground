package pl.kacperb333.java.eventsourcing;

public class NoExactResultException extends Exception {
    private final long expectedAggregateVersion;
    private final long actualAggregateVersion;

    public NoExactResultException(long expectedAggregateVersion, long actualAggregateVersion) {
        super(String.format("Aggregate version mismatch. Expected: %s, actual: %s",
                expectedAggregateVersion, actualAggregateVersion));
        this.expectedAggregateVersion = expectedAggregateVersion;
        this.actualAggregateVersion = actualAggregateVersion;
    }

    public long getExpectedAggregateVersion() {
        return expectedAggregateVersion;
    }

    public long getActualAggregateVersion() {
        return actualAggregateVersion;
    }
}
