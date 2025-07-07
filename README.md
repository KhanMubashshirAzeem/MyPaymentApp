
# MyPaymentApp UI

**Note:** This project includes only the UI part.

## Modules

### Host App (mypaymentapp module)
- PhonePeActivity
- ElectricityActivity
- ReceiptActivity

### SDK Module (payment_sdk)
- MainActivity (handles payment logic)

## Flow of the Application
```
PhonePeActivity → ElectricityActivity → MainActivity → ReceiptActivity
```

## Project Structure

```
MyPaymentApp/
├── app/ (mypaymentapp)
│   ├── home_screen/
│   │   ├── PhonePeActivity
│   │   ├── MoneyTransferAdapter
│   │   ├── RechargeBillAdapter, RechargeBillModel, RechargeModel
│   ├── electricity_screen/
│   │   ├── ElectricityActivity
│   │   ├── BillersAdapter, BillersModel
│   └── ReceiptActivity
│
├── payment_sdk/
│   ├── MainActivity
│   └── PaymentProcessor
```

## Prerequisites

- Android Studio (latest recommended)
- Java 11
- Android Gradle Plugin (AGP) version 8.0 or above

## How to Run This Project

- #### Service Number - 12131415
- #### Amount - 5400 
- #### Pin - 0123 

1. **Clone the Repository**
```bash
git clone https://github.com/KhanMubashshirAzeem/MyPaymentApp.git
```

2. **Open in Android Studio**
- Open the root folder `MyPaymentApp` in Android Studio.

3. **Build the Project**
- Gradle will sync automatically.
- If not, click `File > Sync Project with Gradle Files`.

4. **Run the App**
- Select the `mypaymentapp` module.
- Click the Run button to launch the app on your emulator or physical device.
