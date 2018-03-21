package pl.kacperb333.java.eventsourcing.test;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.kacperb333.java.eventsourcing.AggregateRoot;

class SimpleAggregateRoot extends AggregateRoot<SimpleAggregateIdentifier> {

    enum State {
        ACTIVE, DISABLED
    }

    private SimpleAggregateIdentifier identifier;
    private String name;
    private int balance;
    private State state;

    SimpleAggregateRoot(SimpleAggregateIdentifier identifier) {
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
        applyEvent(new AggregateRenamedEvent(getAggregateIdentifier(), getNextVersion(), newName));
    }

    private void apply(AggregateRenamedEvent renamedEvent) {
        this.name = renamedEvent.getNewName();
    }

    void credit(int creditAmount) {
        applyEvent(new AggregateCreditedEvent(getAggregateIdentifier(), getNextVersion(), creditAmount));
    }

    private void apply(AggregateCreditedEvent creditedEvent) {
        this.balance += creditedEvent.getCreditValue();
    }

    void debit(int debitAmount) {
        applyEvent(new AggregateDebitedEvent(getAggregateIdentifier(), getNextVersion(), debitAmount));
    }

    private void apply(AggregateDebitedEvent debitedEvent) {
        this.balance -= debitedEvent.getDebitValue();
    }

    void disable() {
        applyEvent(new AggregateDisabledEvent(getAggregateIdentifier(), getNextVersion()));
    }

    private void apply(AggregateDisabledEvent disabledEvent) {
        this.state = State.DISABLED;
    }

    void activate() {
        applyEvent(new AggregateActivatedEvent(getAggregateIdentifier(), getNextVersion()));
    }

    private void apply(AggregateActivatedEvent activatedEvent) {
        this.state = State.ACTIVE;
    }

    SimpleAggregateIdentifier getIdentifier() {
        return identifier;
    }

    String getName() {
        return name;
    }

    int getBalance() {
        return balance;
    }

    State getState() {
        return state;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("identifier", identifier)
                .append("name", name)
                .append("balance", balance)
                .append("state", state)
                .toString();
    }
}
