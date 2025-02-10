#Android OTP Retriever

The SMS Getter and OTP Parser extracts the latest SMS, assuming it contains an OTP, and processes the code accordingly. While SMS retrieval has been validated, OTP parsing has yet to be tested with actual messages. However, the logic is designed for reliability and can be adjusted if needed.

do not forget to add   <uses-permission android:name="android.permission.READ_SMS" /> to your manifest file

    <uses-feature
        android:name="android.hardware.telephony"
        android:required="false" /> and add this too. -------> GOODLUCK.
