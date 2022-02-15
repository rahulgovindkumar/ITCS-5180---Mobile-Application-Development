In this assignment you will get familiar with RecyclerViews and using Firebase Authentication and Firestore. You will develop a forums application which enable you to 
list, create, and delete forums. The app should use Firebase Firestore to store and manage the forums and user accounts. Your application should contain only one 
Activity and multiple fragments to implement the required screens.

![image](https://user-images.githubusercontent.com/13596624/153967497-07d3a979-edea-4d36-a110-8f47d672fc07.png)

Part 1: Login Fragment (5 Points)
The interface should be created to match the UI presented in Figure 1(a). The requirements are as follows:
1. Clicking the “Create New Account” button should replace this fragment with the Create New Account Fragment.
2. Clicking the “Submit” button, the app should check if email and password fields are entered and display an alert dialog if any of the entries is missing indicating that the 
missing field is required. 
a. Use the Firebase Auth to login the user. If the login is successful then replace the current fragment with the Forums Fragment.

b. If the login is not successful then display an alert dialog indicating the reason returned by Firebase Auth.

Part 2: Create New Account Fragment (5 Points)
The interface should be created to match the UI presented in Figure 1(b). The requirements are as follows:
1. This screens allows the user to register a new user account using email and password.
2. Clicking the “Cancel” button should dismiss this Screen and show the Login Fragment
Clicking the “Submit” button, the app should check if email and password fields are entered and display an alert dialog if any of the entries is missing indicating that the 
missing field is required. 
a. Use the Firebase Auth to register a new user. If the registration is successful then replace the current screen with the Forums Fragment.
b. If the registration is not successful then display an alert dialog indicating the reason returned by Firebase Auth

Part 3 : Forums Fragment (40 Points)
This screen enables the user to view the list of forums created by all the users and to 
interact with these forums. As shown in Figure 1(c), The requirements are as follows:
1. This screen should retrieve the list of users from Firestore.
a. You should create a Forum class to store the forum information.
b. Set up realtime updates to retrieve the forums from Firebase.
2. Clicking the “Logout” menu button should logout the currently logged in user and 
replace this fragment with the Login Fragment.
3. Clicking the “New Forum” button should:
a. Replace the current fragment with the New Forum Fragment and Push the current 
fragment on the back stack.
4. The list of forums should be retrieved from firestorm and should setup realtime 
updates.
5. The forums list should be displayed using a RecyclerView as shown in Fig 1(c). Each 
row item should display a forum:
a. Forum title, name of forum creator, the forum description, the date/time the forum 
was created.
b. The trash “Delete” icon should only be displayed for forum items that were 
created by the currently logged in user. 
i. If the user clicks the “Delete” icon the forum should be deleted on Firestore.
ii. You are required to initially setup realtime updates which should trigger when 
the forum is delete and refresh the displayed list after deletion to reflect this 
change.
c. The like or unlike indicator which are represented by un-filled heart icon and filled 
heart respectively. Each Forum should contain a list or map of user IDs that have 
liked this forum. If the currently logged in user has liked a forum then the filled 
heart should be displayed, and otherwise the un-filled heart icon should be 
displayed for that forum list item.
i. The user can like the forum by clicking the un-filled heart icon, which should 
add the currently logged in user’s ID to the list/map on Firestore for this forum. 
Upon as successful update the list of forums should be refreshed to reflect this 
change.
ii. The user can un-like the forum by clicking the filled heart icon, which should 
remove the currently logged in user’s ID to the list/map on Firestore for this 
forum. Upon as successful update the list of forums should be refreshed to 
reflect this change.
6. Clicking on a row item should replace the current fragment with the Forum Fragment 
and Push the current fragment on the back stack

Part 4 : New Forum Fragment (10 Points)
The interface should be created to match Figure 1(d). The requirements are as follows:
1. Clicking the “Cancel” button should:
a. Pop the back stack which should return back to the Forums Fragment.
2. Clicking the “Submit” button, the app should check if all the fields are entered and 
display an alert dialog if any of the entries is missing indicating that the missing field 
is required.
a. If all the fields are entered, the app should store a new forum on Firestore, upon a 
successful update the app should pop the back stack to display the Forums 
fragment.
b. Upon returning to the Forums fragment, the list should be refreshed to show the 
newly added forum.

![image](https://user-images.githubusercontent.com/13596624/153967640-cd9926c7-a0cb-44da-a792-dbbd7d6e4cfa.png)

Part 5 : Forum Fragment (40 Points)
The Forum fragment enables the user to view the forum details and comments made by other users on this forum. In addition, this fragment enables the user 
to post comments on the forum. The requirements are as follows:
1. The Forum screen should display details about the selected forum, number of comments, and show the 
list of comments as shown in Fig 2.
2. At the top of the screen, the forum title, forum creator name, and forum description should be displayed.
3. You should set up realtime updates to retrieve the list comments for this forum from Firestore.
4. The list of comments should be displayed using a RecyclerView as shown in Fig 3. Each row item 
should display a comment:
a. Name of comment creator, comment text, the date/time the comment was created.
b. The trash “Delete” icon should only be displayed for comment items that were created by the currently logged in user.
5. If the user clicks the “Delete” icon the comment should be deleted from the forum’s comment collection from Firestore. The previously setup realtime updates which should be triggered which 
should refresh the comments list to reflect this change.
6. The number of comments should be shown under the description as shown in Fig 3.
7. Creating a comment:

a. The write comment EditText should allow the user to enter a text comment. Clicking the “Post” button should check if there is a comment entered.
b. If there is a comment entered then save the comment on Firestore under the forum’s comment collection. The previously setup realtime updates which should 
be triggered which should refresh the comments list to reflect this change.
8. Pressing the back button should pop the back stack to show the Forums Fragment
