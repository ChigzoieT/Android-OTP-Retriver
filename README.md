the sms getter and OTP parser gets the last SMS on the device, assuming it is an SMS coming in with an OTP and gets the code from the file, it was tested up to the point where it gets the SMS, the OTP parsing logic was not tested as i dont have any SMS with OTP, but i am assuming the logic works fine, if for any reason you experience any challenges with the logic, feel free to modify it, the problem couldnt be far off as i belive any person going through this document is a literate in the field of programming.

do not forget to add   <uses-permission android:name="android.permission.READ_SMS" /> to your manifest file

    <uses-feature
        android:name="android.hardware.telephony"
        android:required="false" /> and add this too. -------> GOODLUCK.
