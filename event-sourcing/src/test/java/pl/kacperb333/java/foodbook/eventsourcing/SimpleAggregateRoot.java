package pl.kacperb333.java.foodbook.eventsourcing;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class SimpleAggregateRoot extends AggregateRoot<SimpleAggregateIdentifier> {

    enum State {
        ACTIVE, DISABLED
    }

    private SimpleAggregateIdentifier identifier;
    private String name;
    private int balance;
    private State state;

    private SimpleAggregateRoot(SimpleAggregateIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public SimpleAggregateIdentifier getAggregateIdentifier() {
        return identifier;
    }

    public static SimpleAggregateRoot startProcess(SimpleAggregateIdentifier identifier,
                                                   String initialName,
                                                   int initialBalance) {
        SimpleAggregateRoot started = new SimpleAggregateRoot(identifier);
        started.applyEvent(new AggregateProcessStartedEvent(identifier, initialName, initialBalance));
        return started;
    }

    private void apply(AggregateProcessStartedEvent processStartedEvent) {
        this.name = processStartedEvent.getName();
        this.balance = processStartedEvent.getInitialBalance();
        this.state = State.ACTIVE;
    }

    void rename(String newName) {
        applyEvent(new AggregateRenamedEvent(getAggregateIdentifier(), getVersion(), newName));
    }

    private void apply(AggregateRenamedEvent renamedEvent) {
        this.name = renamedEvent.getNewName();
    }

    void credit(int creditAmount) {
        applyEvent(new AggregateCreditedEvent(getAggregateIdentifier(), getVersion(), creditAmount));
    }

    private void apply(AggregateCreditedEvent creditedEvent) {
        this.balance += creditedEvent.getCreditValue();
    }

    void debit(int debitAmount) {
        applyEvent(new AggregateDebitedEvent(getAggregateIdentifier(), getVersion(), debitAmount));
    }

    private void apply(AggregateDebitedEvent debitedEvent) {
        this.balance -= debitedEvent.getDebitValue();
    }

    void disable() {
        applyEvent(new AggregateDisabledEvent(getAggregateIdentifier(), getVersion()));
    }

    void apply(AggregateDisabledEvent disabledEvent) {
        this.state = State.DISABLED;
    }

    void activate() {
        applyEvent(new AggregateActivatedEvent(getAggregateIdentifier(), getVersion()));
    }

    void apply(AggregateActivatedEvent activatedEvent) {
        this.state = State.ACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SimpleAggregateRoot that = (SimpleAggregateRoot) o;

        return new EqualsBuilder()
                .append(balance, that.balance)
                .append(identifier, that.identifier)
                .append(name, that.name)
                .append(state, that.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(identifier)
                .append(name)
                .append(balance)
                .append(state)
                .toHashCode();
    }
}
