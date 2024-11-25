/**
 * 
 */
package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    private final Map<String, List<U>> followed;      //The peapole followed by this user are organized in groups, rather than in a singular list

    /*
     * [CONSTRUCTORS]
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
        this.followed = new HashMap<String, List<U>>();
    }
    public SocialNetworkUserImpl(final String name, final String surname, final String user) {
        super(name, surname, user, -1);
        this.followed = new HashMap<String, List<U>>();
    }

    /*
     * [METHODS]
     */
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        if (user == null){
            return false;
        }
        if (this.followed.get(circle) == null){
            //Se non esiste un gruppo chiamato "circle", lo crea.
            this.followed.put(circle, new ArrayList<U>());
        }
        this.followed.get(circle).add(user);
        return true;
    }

    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        final Collection<U> getted = this.followed.get(groupName);
        if (getted == null){
            return Collections.emptyList();
        }
        return new ArrayList<>(getted);
    }

    @Override
    public List<U> getFollowedUsers() {
        final ArrayList<U> fullList = new ArrayList<>();
        for (String gruppo : this.followed.keySet()) {
            for (U u : this.followed.get(gruppo)) {
                fullList.add(u);
            }
        }
        return new ArrayList<>(fullList);
    }
}
