In this assignment you will be building an application that uses as single activity and multiple fragments and exchanges data among these fragments. Structure and 
important setup are listed below:
- In this app you will have only one Activity and 4 fragments, all communication between activities should be managed by the activity.
- You are provided with a DataServices class file which you need to import in your project. Make sure to update the package name to match your project’s. 
• The DataServices class will emulate the account management functions such as login, register, and update functions required to login, register and update 
accounts. All the provided functions return an AccountRequest object, below is an example code snippet showing how to use the login function

![image](https://user-images.githubusercontent.com/13596624/153971289-59446a7e-9f01-4d08-8be3-837ef6768437.png)


**Part 1 : Login Fragment**
The interface should be created to match the UI presented in Figure 2(a). The requirements are as follows:
1. Upon entering the email and password, clicking the submit button should:
a. Clicking “Login” button, if all the inputs are not empty, you should attempt to login the user by calling the DataServices.login function. 
b. If there is missing input, show a Toast indicating missing input.
c. If the login is successful, then communicate the returned account with the activity and replace the current fragment with the Account Fragment.
d. If login is not successful, show a Toast message indicating that the login was not successful.
2. Clicking the “Create New Account” should replace this fragment with the Register Fragment.


**Part 2 Register Fragment**
The interface should be created to match the UI presented in Figure 2(b). The requirements are as follows:
1. This fragment should allow a user to create a new account. Upon entering the name, email and password, clicking the Submit button should:
a. If all the inputs are not empty, you should attempt to signup the user by calling the DataServices.register function. 
b. If the registration is successful then communicate the returned account with the activity and replace the current fragment with the Account Fragment.
c. If the registration is not successful, show a Toast message indicating that the login was not successful.
d. If there is missing input, show a Toast indicating missing input.
2. Clicking “Cancel” should replace this fragment with the Login Fragment.
Part 3 : Account Fragment (30 Points)
This greets the logged in user as shown in Figure 2(c), The requirements are as follows:
1. This fragment should receive the currently logged in user’s account from the Activity, and should use this account to display the user’s name in the greeting as shown in Figure 2(c).
2. Clicking the “Edit Profile” button should perform the following tasks:
a. Replace the current fragment with the Update Account Fragment.
b. Push the current fragment on the back stack.
c. This should all be performed through the Main Activity.
3. Clicking the “Logout” button should delete the account stored in the Main Activity, and replace this fragment with the Login Fragment.

**Part 4 : Update Account Fragment**
The interface should be created to match Figure 3(a). The requirements are as follows:
1. This fragment should receive the currently logged in user’s account from the Activity, and should allow the user to update the user name, and password.
2. Clicking the “Cancel” button should dismiss this fragment and should pop the Account fragment from the back stack.
3. Clicking the “Submit” button, the app should check if all the fields are not empty and should display a Toast if there are any errors 

a. If all the fields are entered, the account should be updated using the DataServices.update function. If the update is successful, the newly updated 
account should be communicated with the Main Activity, should dismiss this fragment and should pop the Account fragment from the back stack. Upon 
returning to the Account fragment, the updated name should be displayed to reflect the account change.

4. Figure 3(b) shows the expected data flow between the different fragments and the Main Activity

![image](https://user-images.githubusercontent.com/13596624/153971488-9044dd9a-b307-48be-8fa9-f283a9af2417.png)

