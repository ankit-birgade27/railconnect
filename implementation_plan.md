# Implementation Plan - Student 1: User Registration

Implement the **User Registration** module for the **RailConnect** project according to the official specification (*Student Task Specification - User & Passenger Management Module*).

---

## Architecture Flow
```
Controller (UserController) 
    -> Service Interface (UserService) 
    -> Service Implementation (UserServiceImpl) 
    -> DAO Interface (UserDao) 
    -> DAO Implementation (UserDaoImpl) 
    -> Data Storage (In-memory List<User>)
```

---

## User Review & Technical Decisions

> [!NOTE]
> - **Exception Hierarchy**: All custom domain exceptions (`InvalidUserException`, `DuplicateUsernameException`, `DuplicateEmailException`, `DuplicateMobileException`, `InvalidPasswordException`) extend `RuntimeException`. This ensures compliance with the strict rule: *"Do not modify the existing Model classes or Service method signatures"*, avoiding unhandled checked exception compilation errors across other student modules.
> - **DAO Design**: `UserDao` is an interface backed by `UserDaoImpl` using an in-memory `List<User>` and thread-safe auto-increment ID sequence starting at `1001`. The legacy `getnewMessage(String msg)` method is preserved on the DAO to ensure backward compatibility with the existing `welcome()` feature.
> - **Service Interface Alignment**: `validateUsername(String username)` and `validateEmail(String email)` are added to `UserService.java` as assigned for Student 1. Validations for mobile and password are implemented in `UserServiceImpl` to satisfy the 11-step `registerUser` workflow without stepping on Student 2/3's interface assignments.

---

## Implemented Components

### 1. Custom Exceptions (`com.railconnect.exception`)
- `InvalidUserException.java`: Thrown for null user, or invalid username, email, or mobile format.
- `DuplicateUsernameException.java`: Thrown when a username is already taken.
- `DuplicateEmailException.java`: Thrown when an email is already registered.
- `DuplicateMobileException.java`: Thrown when a mobile number is already registered.
- `InvalidPasswordException.java`: Thrown when password is null, empty, or less than 6 characters.

### 2. Data Access Object Layer (`com.railconnect.controller.dao`)
- `UserDao.java`: Interface defining:
  - `void saveUser(User user);`
  - `User findByUsername(String username);`
  - `User findByEmail(String email);`
  - `User findByMobile(String mobile);`
  - `User findById(int userId);`
  - `List<User> findAllUsers();`
  - `int generateUniqueUserId();`
  - `String getnewMessage(String msg);`
- `UserDaoImpl.java`: In-memory storage using `List<User>` and atomic ID generator.

### 3. Service Layer (`com.railconnect.service` & `com.railconnect.serviceimpl`)
- `UserService.java`: Declares `registerUser(User user)`, `validateUsername(String username)`, `validateEmail(String email)`.
- `UserServiceImpl.java`:
  - 11-step `registerUser(User user)` algorithm:
    1. Null check $\rightarrow$ `InvalidUserException`
    2. Validate username ($\ge 3$ chars, non-blank) $\rightarrow$ `InvalidUserException`
    3. Validate email (regex `^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$`) $\rightarrow$ `InvalidUserException`
    4. Validate mobile (10 digits) $\rightarrow$ `InvalidUserException`
    5. Validate password ($\ge 6$ chars) $\rightarrow$ `InvalidPasswordException`
    6. Duplicate username check $\rightarrow$ `DuplicateUsernameException`
    7. Duplicate email check $\rightarrow$ `DuplicateEmailException`
    8. Duplicate mobile check $\rightarrow$ `DuplicateMobileException`
    9. Unique user ID assignment via `dao.generateUniqueUserId()`
    10. Default values: `role = "PASSENGER"`, `accountLocked = false`, `loginAttempts = 0`
    11. Save via `dao.saveUser(user)`

### 4. Controller & Demonstration (`com.railconnect.controller`)
- `UserController.java`:
  - `public void registerUser(User user)`: Catches specific custom exceptions and reports formatted status.
  - `public static void main(String[] args)`: Full demonstration covering all positive, negative, and edge-case scenarios.

### 5. Standalone Test Suite (`com.railconnect.test`)
- `UserRegistrationTest.java`: Pure Java automated test runner with 30 assertions covering:
  - Valid user registration & defaults verification
  - Null user rejection
  - Username boundary & format validation
  - Email format validation
  - Mobile format validation
  - Password strength validation
  - Duplicate username, email, and mobile rejections
  - Auto-increment sequential ID assignment

---

## Verification & Execution Commands

```powershell
# Compile the project
Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { '"' + $_.FullName.Replace('\', '/') + '"' } | Out-File -Encoding ascii sources.txt
javac -d bin "@sources.txt"
Remove-Item sources.txt

# Run Automated Test Suite (All 30 assertions pass)
java -cp bin com.railconnect.test.UserRegistrationTest

# Run Interactive Controller Demonstration
java -cp bin com.railconnect.controller.UserController
```
