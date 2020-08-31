import Models.Person;

public interface PersonRelatedAPI {

     Person getProfile();
     void getCommonInterestsOfMyFriends();
     void getCommonFriends();
     void getPersonsWithMostCommonInterests();
     void getJobRecommendation();
     void getShorthestFriendshipPath();

}
