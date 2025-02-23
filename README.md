# ATM-MACHINE-SIMULATION

## Overview
This is a simple ATM system implemented in Python that allows users to perform basic banking operations such as checking their balance, withdrawing and depositing money, transferring funds to other users, viewing transaction history, and changing their PIN.

## Features
- **User Authentication:** Users must enter their user ID and PIN to access the system.
- **Balance Inquiry:** Users can check their current balance.
- **Withdraw Money:** Users can withdraw a specified amount if they have sufficient funds.
- **Deposit Money:** Users can deposit money into their account.
- **Transfer Money:** Users can transfer funds to another user's account.
- **View Transaction History:** Users can see a list of their past transactions.
- **Change PIN:** Users can update their PIN for security.
- **Exit Option:** Users can quit the ATM system at any time.

## How It Works
1. The system initializes with predefined users and their account details.
2. The user logs in using their `user_id` and `PIN`.
3. Once authenticated, they are presented with an ATM menu to choose from different banking operations.
4. Based on the user's choice, they can perform transactions, check their balance, transfer money, or change their PIN.
5. The program runs in a loop until the user chooses to exit.

## User Data Structure
Each user account is stored as a dictionary containing:
- `user_id`: Unique identifier for the user
- `pin`: Personal Identification Number for authentication
- `balance`: Account balance
- `transaction_history`: List of past transactions

## Functions
### 1. `create_user(user_id, pin, balance=0, transaction_history=None)`
Creates a user account with the given `user_id`, `pin`, optional `balance`, and `transaction_history`.

### 2. `authenticate(user_data, entered_pin)`
Checks if the entered PIN matches the stored PIN for authentication.

### 3. `change_pin(user_data)`
Allows a user to change their PIN after verifying the current PIN.

### 4. `perform_transaction(user_data, choice, amount=None, recipient_user_id=None)`
Handles different banking operations including balance inquiry, withdrawals, deposits, transfers, and viewing transaction history.

### 5. `main()`
The main function that initializes the ATM system, authenticates users, and provides the ATM menu interface.

## Running the Program
To run the ATM system, save the following code as `atm_system.py` and execute it in a Python environment:

```python
if __name__ == "__main__":
    main()
```

The user will be prompted to enter their user ID and PIN to log in and interact with the ATM menu.

## Example Users
The program initializes with the following users:

| User ID | PIN   | Balance  |
|---------|------|---------|
| user1   | 12345 | 0       |
| user2   | 67890 | 1000    |
| user3   | 01234 | 32000   |
| user4   | 56789 | 400000  |

## Notes
- Ensure that `__name__ == "__main__"` is correctly used for proper script execution.
- The transaction operations must have valid inputs to proceed successfully.
- User data is stored temporarily in memory and does not persist after the program exits.

## Future Enhancements
- Implement file/database storage for persistent user data.
- Add an admin interface to manage users.
- Improve security by encrypting PINs.
- Implement a graphical user interface (GUI).

## License
This project is open-source and available for modification and distribution.
