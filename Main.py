def create_user(user_id, pin, balance=0, transaction_history=None):
    if transaction_history is None:
        transaction_history = []
    return {
        "user_id": user_id,
        "pin": pin,
        "balance": balance,
        "transaction_history": transaction_history
    }

def authenticate(user_data, entered_pin):
    return user_data["pin"] == entered_pin

def change_pin(user_data):
    current_pin = input("Enter your current PIN: ")
    if current_pin != user_data["pin"]:
        print("Incorrect PIN. Cannot change PIN.")
        return
    
    new_pin = input("Enter your new PIN: ")
    confirm_pin = input("Confirm your new PIN: ")
    
    if new_pin != confirm_pin:
        print("PINs do not match. Try again.")
        return

    user_data["pin"] = new_pin
    print("PIN changed successfully.")

def perform_transaction(user_data, choice, amount=None, recipient_user_id=None):
    if choice == "1":
        print(f"Your current balance: {user_data['balance']}")

    elif choice == "2":
        if amount is None or amount <= 0:
            print("Invalid amount. Please enter a positive value.")
            return
        if amount > user_data["balance"]:
            print("Insufficient funds")
        else:
            user_data["balance"] -= amount
            user_data["transaction_history"].append(f"Withdrawal of {amount}")
            print(f"Withdrawal successful. Current balance: {user_data['balance']}")

    elif choice == "3":
        if amount is None or amount <= 0:
            print("Invalid amount. Please enter a positive value.")
            return
        user_data["balance"] += amount
        user_data["transaction_history"].append(f"Deposit of {amount}")
        print(f"Deposit successful. Current balance: {user_data['balance']}")

    elif choice == "4":
        if not recipient_user_id:
            print("Invalid recipient user ID")
            return
        recipient_atm = users.get(recipient_user_id)

        if not recipient_atm:
            print("Recipient's account not found.")
            return

        if amount is None or amount <= 0:
            print("Invalid amount. Please enter a positive value.")
            return
        if amount > user_data["balance"]:
            print("Insufficient funds")
        else:
            user_data["balance"] -= amount
            user_data["transaction_history"].append(f"Transfer of {amount} to {recipient_user_id}")
            recipient_atm["balance"] += amount
            recipient_atm["transaction_history"].append(f"Transfer received from {user_data['user_id']}")
            print(f"Transfer successful. Current balance: {user_data['balance']}")

    elif choice == "5":
        if user_data["transaction_history"]:
            print("\nTransaction History:")
            print("\n".join(user_data["transaction_history"]))
        else:
            print("No transactions yet.")

    else:
        print("Invalid choice. Please try again.")

def main():
    global users
    users = {
        "user1": create_user("user1", "12345", 0),
        "user2": create_user("user2", "67890", 1000),
        "user3": create_user("user3", "01234", 32000),
        "user4": create_user("user4", "56789", 400000),
    }

    user_id = input("Enter your user ID: ")
    pin = input("Enter your PIN: ")

    user_data = users.get(user_id)

    if user_data and authenticate(user_data, pin):
        while True:
            print("\nATM Menu:")
            print("1. View Balance")
            print("2. Withdraw")
            print("3. Deposit")
            print("4. Transfer")
            print("5. View Transaction History")
            print("6. Quit")
            print("7. Change PIN")

            choice = input("Enter your choice: ")

            if choice == "6":
                print("Thank you for using the ATM.\n ----- Have a nice day! -----")
                break
            
            elif choice == "7":
                change_pin(user_data)

            else:
                amount = None  
                recipient_user_id = None

                if choice in ("2", "3", "4"):
                    try:
                        amount = float(input("Enter amount: "))
                        if amount <= 0:
                            print("Amount must be greater than zero.")
                            continue
                    except ValueError:
                        print("Invalid input. Please enter a numeric amount.")
                        continue

                if choice == "4":
                    recipient_user_id = input("Enter recipient's user ID: ")

                perform_transaction(user_data, choice, amount, recipient_user_id)
    else:
        print("Invalid user ID or PIN. Please try again.")

if __name__ == "__main__":
    main()
