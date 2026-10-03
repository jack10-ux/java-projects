package Voting;

import java.util.Scanner;

public class Main {

static final String RESET = "\033[0m";
static final String RED = "\033[31m";
static final String GREEN = "\033[32m";
static final String YELLOW = "\033[33m";
static final String BLUE = "\033[34m";
static final String PURPLE = "\033[35m";
static final String CYAN = "\033[36m";
static final String WHITE = "\033[37m";

static final String BOLD = "\033[1m";


public static void main(String[] args) {

VotingService votingService = new VotingService();

Scanner scanner = new Scanner(System.in);

String currentUser = null;

boolean running = true;



System.out.println(CYAN + BOLD
+ "=========================================="
+ RESET);

System.out.println(CYAN + BOLD
+ " WELCOME TO ONLINE VOTING SYSTEM "
+ RESET);

System.out.println(CYAN + BOLD
+ "=========================================="
+ RESET);


//  MAIN MENU 

while (running) {

System.out.println();
System.out.println(BLUE + BOLD
+ "---------------- MAIN MENU ---------------"
+ RESET);


// Login status

if (currentUser != null) {

System.out.println(
GREEN + "Status: Logged in as ["
+ currentUser + "]"
+ RESET
);

} else {

System.out.println(
RED + "Status: Not logged in"
+ RESET
);
}


// Menu options

System.out.println(
CYAN + "1. Register" + RESET
);

System.out.println(
CYAN + "2. Login" + RESET
);

System.out.println(
CYAN + "3. View Candidates" + RESET
);

System.out.println(
CYAN + "4. Cast Vote" + RESET
);

System.out.println(
CYAN + "5. View Total Votes" + RESET
);

System.out.println(
CYAN + "6. View Result" + RESET
);

System.out.println(
YELLOW + "7. Exit" + RESET
);


System.out.print(
PURPLE + "Enter your choice (1-7): "
+ RESET
);


String choice = scanner.nextLine().trim();



switch (choice) {

case "1":

handleRegister(votingService, scanner);

break;


case "2":

currentUser =
handleLogin(votingService, scanner);

break;


case "3":

votingService.displayCandidates();

break;


case "4":

handleCastVote(
votingService,
scanner,
currentUser
);

break;


case "5":

votingService.displayTotalVotes();

break;


case "6":

votingService.displayResult();

break;


case "7":

System.out.println();

System.out.println(
YELLOW + BOLD
+ "Exiting Online Voting System..."
+ RESET
);

System.out.println(
GREEN + "Thank you for using the system!"
+ RESET
);

running = false;

break;


default:

System.out.println(
RED + "Invalid choice!"
+ RESET
);

System.out.println(
YELLOW
+ "Please enter a valid number from 1 to 7."
+ RESET
);

break;
}
}


scanner.close();
}


// REGISTER

private static void handleRegister(
VotingService service,
Scanner scanner) {

System.out.println();

System.out.println(
BLUE + BOLD
+ "--------------- REGISTER ---------------"
+ RESET
);


System.out.print(
CYAN + "Enter username: "
+ RESET
);

String username =
scanner.nextLine().trim();


System.out.print(
CYAN + "Enter password: "
+ RESET
);

String password =
scanner.nextLine().trim();


try {

service.registerUser(
username,
password
);


System.out.println(
GREEN + BOLD
+ "SUCCESS: Registration successful!"
+ RESET
);

System.out.println(
GREEN
+ "You can now login with username '"
+ username
+ "'."
+ RESET
);


} catch (VotingException e) {

System.out.println(
RED + "ERROR: "
+ e.getMessage()
+ RESET
);
}
}


// LOGIN

private static String handleLogin(
VotingService service,
Scanner scanner) {

System.out.println();

System.out.println(
BLUE + BOLD
+ "---------------- LOGIN ----------------"
+ RESET
);


System.out.print(
CYAN + "Enter username: "
+ RESET
);

String username =
scanner.nextLine().trim();


System.out.print(
CYAN + "Enter password: "
+ RESET
);

String password =
scanner.nextLine().trim();


try {

User user =
service.loginUser(
username,
password
);


System.out.println();

System.out.println(
GREEN + BOLD
+ "SUCCESS: Welcome, "
+ user.getUsername()
+ "!"
+ RESET
);

System.out.println(
GREEN
+ "You are logged in successfully."
+ RESET
);


return user.getUsername();


} catch (VotingException e) {

System.out.println(
RED + "ERROR: "
+ e.getMessage()
+ RESET
);


return null;
}
}


// CAST VOTE

private static void handleCastVote(
VotingService service,
Scanner scanner,
String currentUser) {


System.out.println();

System.out.println(
BLUE + BOLD
+ "--------------- CAST VOTE ---------------"
+ RESET
);


// Check login

if (currentUser == null) {

System.out.println(
RED + BOLD
+ "ERROR: You must login first!"
+ RESET
);

System.out.println(
YELLOW
+ "Please select Option 2 (Login)."
+ RESET
);

return;
}


// Display candidates

service.displayCandidates();


System.out.println();

System.out.print(
PURPLE
+ "Enter Candidate ID to cast your vote: "
+ RESET
);


String candidateId =
scanner.nextLine().trim();


try {

service.castVote(
currentUser,
candidateId
);


System.out.println();

System.out.println(
GREEN + BOLD
+ "SUCCESS: Your vote has been recorded!"
+ RESET
);


} catch (VotingException e) {

System.out.println();

System.out.println(
RED + "ERROR: "
+ e.getMessage()
+ RESET
);
}
}
}
