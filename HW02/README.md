The app provides features to list users, filter and sort. Figure 1 shows the app screens.

![image](https://user-images.githubusercontent.com/13596624/153962152-06bbacef-f9f7-4ff8-95c7-1fc91884a3d8.png)

**Main Requirements and Data:**
1. This app should contain only ONE activity and all the screens should be implemented 
using fragments.
2. You are provided with DataServices class that includes a list of User objects. Each 
user has a name, age, gender, group and state. 
a. You should include the DataServices.java file in your project.
b. You are able to retrieve the full list of users by calling DataServices.getAllUsers()

**Part 1: Users Fragment**
The interface should be created to match the UI presented in Figure 1(a). The 
requirements are as follows:
1. This fragment should display the list of users as shown in Figure 1(a). This fragment 
is the main fragment that should be displayed when the app starts.
2. You are free to use ListView or RecyclerView to implement the users list. Note that for 
each row item you should include the user name, state, age and group. In addition 
the user icon should be selected based on the user’s gender as shown in Figure 1(a).
3. Clicking the “Filter” button should display “Filter By State” fragment and place the 
current fragment on the back stack.
a. Upon returning from the “Filter By State” fragment, the selected filter criteria should be returned to the Users fragment and the list should be filtered based on 
the selected criteria.
b. Filtering should make sure to maintain the preselected sorting order of the users.
4. Clicking the “Sort” button should display Sort fragment and place the current fragment 
on the back stack.
a. Upon returning from the “Sort” fragment, the selected sort criteria should be 
returned to the Users fragment and the list should be sorted based on the 
selected criteria.
b. Note that the “Sort” fragment does not affect the filter criteria selected if any.

**Part 2: Filter By State Fragment**
The interface should be created to match the UI presented in Figure 1(b). The 
requirements are as follows:
1. The list of unique states should be retrieved from the users list in the Data class 
provided. 
2. The list of unique states should be sorted in ascending order and the top row should 
include “All States” as shown in Figure 1(b).
3. Clicking on a state row item:
a. The selected state should be sent back to the Users Fragment, which should filter 
the users to display only the users at the selected state. Then refresh the list to 
display the filtered list of users. Selecting “All States” row should signal the Users 
Fragment to display all the users from all states.
b. The Users Fragment should be popped from the back stack and should be 
displayed to reflect the selected filter criteria.

**Part 3: Sort Fragment**
The interface should be created to match the UI presented in Figure 1(c). The 
requirements are as follows:
1. This fragment allows the user to select the sorting criteria to be used to sort the list 
presented in the Users Fragment. The sort criteria include sort ascending/descending 
based on age or name or state.
2. This fragment should use RecyclerView to present the three rows shown in Fig 1(c). 
Each row should display the name of the sort attribute, and two buttons descending 
and ascending as shown in Fig 1(c).
3. Clicking on the Descending or Ascending buttons on a given row:
a. The selected sort attribute and criteria should be sent back to the Users 
Fragment, which should sort the users based on the selected sort attribute and 
criteria. Then refresh the list to display the sorted list of users.
b. The Users Fragment should be popped from the back stack and should be 
displayed to reflect the selected sort criteria
