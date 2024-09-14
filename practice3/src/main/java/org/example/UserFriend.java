package org.example;

import java.util.Random;

public class UserFriend {
    private final int userId;
    private final int friendId;

    public UserFriend(int userId, int friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public int getFriendId() {
        return friendId;
    }

    @Override
    public String toString() {
        return "UserFriend{userId=" + userId + ", friendId=" + friendId + '}';
    }

    public static UserFriend[] generateRandomUserFriends(int size, int maxUserId, int maxFriendsId) {
        Random random = new Random();
        UserFriend[] userFriends = new UserFriend[size];
        for (int i = 0; i < size; i++) {
            int userId = random.nextInt(maxUserId) + 1;
            int friendId = random.nextInt(maxFriendsId) + 1;
            userFriends[i] = new UserFriend(userId, friendId);
        }
        return userFriends;
    }

}

