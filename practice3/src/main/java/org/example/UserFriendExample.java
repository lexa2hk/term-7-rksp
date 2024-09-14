package org.example;

import io.reactivex.rxjava3.core.Observable;

import java.util.Random;

public class UserFriendExample {
    public static void runCode() {
        UserFriend[] userFriendsArray = UserFriend.generateRandomUserFriends(50, 10, 10);

        UserFriendService service = new UserFriendService(userFriendsArray);

        Integer[] randomUserIds = generateRandomUserIds(5, 10);

        Observable.fromArray(randomUserIds)
                .flatMap(service::getFriends)
                .subscribe(userFriend -> System.out.println("UserFriend: " + userFriend));
    }

    private static Integer[] generateRandomUserIds(int size, int maxUserId) {
        Random random = new Random();
        Integer[] userIds = new Integer[size];
        for (int i = 0; i < size; i++) {
            userIds[i] = random.nextInt(maxUserId) + 1;  // Random userId between 1 and maxUserId
        }
        return userIds;
    }
}
