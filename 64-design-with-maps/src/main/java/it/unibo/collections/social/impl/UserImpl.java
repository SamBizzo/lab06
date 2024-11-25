package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.User;

import java.util.Objects;

/**
 * This is a basic implementation of a {@link User}.
 * 
 * This class is completely mplemented and can be used as it is.
 * 
 */
public class UserImpl implements User {

    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String username;
    /*
     * to be "lazily" initialized.
     */
    private int hash;

    public UserImpl(final String name, final String surname, final String user) {
        this(Objects.requireNonNull(name), Objects.requireNonNull(surname), Objects.requireNonNull(user), -1);
    }

    public UserImpl(final String name, final String surname, final String user, final int userAge) {
        this.firstName = Objects.requireNonNull(name);
        this.lastName = Objects.requireNonNull(surname);
        this.age = userAge;
        this.username = Objects.requireNonNull(user);
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public boolean isAgeDefined() {
        return this.age > 0;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {return true;}
        if (o != null && getClass().equals(o.getClass())) {
            final UserImpl user = (UserImpl) o;
            return firstName.equals(user.getFirstName())
                && lastName.equals(user.getLastName())
                && username.equals(user.getUsername())
                && age == user.getAge();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        /*
         * All fields are final and immutable: lazy initialization allowed.
         */
        if (hash == 0) {
            hash = Objects.hash(firstName, lastName, username, age);
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[ " + this.firstName + " " + this.lastName + " " + this.age + " " + this.username + " ]";
    }

}
