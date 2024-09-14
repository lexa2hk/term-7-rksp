package org.example;

import io.reactivex.rxjava3.core.Observable;

public class UserFriendService {

    private final UserFriend[] userFriends;

    public UserFriendService(UserFriend[] userFriends) {
        this.userFriends = userFriends;
    }

    public Observable<UserFriend> getFriends(int userId) {
        return Observable.fromArray(userFriends)
                .filter(userFriend -> userFriend.getUserId() == userId);
    }
}

