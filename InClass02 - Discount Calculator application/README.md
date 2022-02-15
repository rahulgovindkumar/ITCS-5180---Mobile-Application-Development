In this assignment you will build your first Android application. You will get familiar with some common Android components. You will build a discount calculator application.

![image](https://user-images.githubusercontent.com/13596624/153968391-c3ea89a6-b121-4777-a0ba-cb97cffc6f22.png)

**Part 1:**
The interface should be created to match the user interface presented in Figure 1. You will be using layout files, and strings.xml to create the user interface. To build the UI, 
please follow the following tasks:
1. The string values used for all the labels used in this application and should not be hardwired in the layout file. 
2. Use the “Hint” attribute to set the “Enter Ticket Price” grayed out hint in the EditView of the input field for the ticket price.
3. The radio group provides selection of discounts 5%, 10%, 15%, 20% and 50%. 
4.Clicking on the “Calculate” button:

a. If the ticket price is empty or is not a valid number you should show a Toast message indicating that the number should be a valid positive number.

b. If the ticket price is entered correctly, the discounted price should be calculated based on the selected discount percentage, and the result should be shown in 
front of the “Discounted Price” as shown in Figure 1(b). The result should be formatted to 2 decimal places.

5. Clicking the “Clear” button should clear the form and bring it back to the initial state 
shown in Figure 1(a)
