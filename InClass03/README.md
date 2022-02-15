In this assignment you will be building an application that uses multiple activities and exchanges data among these activities. 

![image](https://user-images.githubusercontent.com/13596624/153970763-ad38ac13-89ec-4d62-ae89-c4d00baa3fa5.png)

**Part 1, Registration activity:**
The main activity displays a form as shown in Figure 1(a). Please follow the steps to build this activity: 
1. The activity requests the user’s name, email, id and department info. The department info is retrieved by clicking the “Select” button. Make appropriate Toasts 
whenever any invalid input is detected after you click Submit. 
2. Clicking the “Select” button should start for result the “Select Department” activity. Upon returning from the from the “Select Department” activity, the department value selected should be displayed as shown in Figure 1(c). 
3. Clicking on the Submit button should start the “Profile” activity to display the current user profile.


**Part 2, Select Department Activity:** 
1. Clicking on the “Select” button in the Registration activity should start the “Select Department” activity for result. This is shown in Figure 1(b). 
2. The user is able to select a department from the displayed Radio Group. Clicking “Submit” should send back the selected department to the “Registration” activity and finish the current activity. Clicking “Cancel” should simply finish the current activity which will show the “Registration” activity. 
3. You should start this activity for result. 

![image](https://user-images.githubusercontent.com/13596624/153970980-3205aec9-eaaa-4f2b-999f-29c7348e44f2.png)


**Part 3, Profile Activity:**
1. After you fill up the form, and click on Submit, the app should take you to the Profile Activity as shown in Figure 2(a). 
2. You should create a Profile class implementing Serializable or Parcelable interface. 
Profile class should have all the elements needed to display them in a different activity. 
3. Use explicit intent to start this activity from the registration activity, and send the Serializable or Parcelable data from the main activity to this activity. 
4. Display the Name, Email, ID and Department.
