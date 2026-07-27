# MotionWatch User Manual

MotionWatch is an Android-based health monitoring application that records motion sensor data from your phone and smartwatch. This manual covers everything you need to get started, record sessions, and review your data.

## Important Medical Disclaimer

MotionWatch is not intended to provide a medical diagnosis, replace medical care, or confirm that a seizure, tic, tremor, or fall has occurred. The application is designed as a monitoring and data collection tool that records motion sensor data for review and analysis. Always consult a qualified medical professional for health concerns.

## What You Need

- Android phone running Android 8.0 or newer
- Internet connection for account and Firebase features
- Sufficient local storage for session CSV files
- Compatible smartwatch (optional but recommended)
- Bluetooth enabled if using a smartwatch

## Getting Started

Open the app. You can use all features without an account, but your session data will not be saved to the database. Creating an account is recommended if you want your data backed up and accessible over time.

### Creating an Account

1. Tap Settings from the bottom navigation bar
2. Tap Sign Up
3. Enter your first name, last name, email, and password
4. Tap Create Account

### Logging In

1. Tap Settings from the bottom navigation bar
2. Tap Log In
3. Enter your email and password
4. Tap Sign In

If your credentials are incorrect an error message will appear. Double check your email and password and try again.

### Logging Out

1. Tap the drawer menu icon at the top left of the screen
2. Tap Logout

## Setting Up Your Profile

1. Tap Settings from the bottom navigation bar
2. Enter your name, age, height, weight, sex, and blood type
3. Toggle Allow Data Sharing on or off based on your preference

Your profile information is saved to your account and can be updated at any time.

## Navigating the App

The app has two ways to navigate.

**Bottom navigation bar** appears at the bottom of every screen. Tap to switch between Home, Sessions, Analytics, and Settings.

**Drawer menu** is accessed by tapping the three lines at the top left of any screen. From here you can access Sessions, Dashboard, Analytics, Settings, and Log Out.

## Recording a Session on Your Phone

1. Open the app and navigate to the Home page
2. Tap the dropdown and select the activity type you want to record (for example: Walking, Tremor, Tonic, Falling)
3. Tap Start
4. The app begins collecting motion data and displays live accelerometer and gyroscope readings
5. When you are done, tap Stop
6. The session is saved automatically as a CSV file on your device

## Recording a Session on Your Smartwatch

1. Make sure your watch is connected to your phone via Bluetooth
2. On the watch, select your activity type
3. Tap Start on the watch to begin recording
4. Move normally during the session
5. Tap Stop on the watch to end the session
6. The session data is automatically sent to your phone and saved

## Using the Analytics Page

The Analytics page shows live motion data and ML predictions during an active session.

1. Navigate to Analytics using the bottom navigation bar
2. The Live Monitor displays in idle state with a Start Session button
3. Tap Start Session to begin
4. During recording you will see the current predicted motion, a confidence score, a live scrolling sensor chart, and real-time duration, sample count, and event count
5. Toggle between ACC, GYRO, and ALL tabs to change the chart view
6. Tap Stop Session when done
7. The app displays a Session Summary showing the date, duration, device used, a motion breakdown chart, and a detection log per activity type

## Viewing Past Sessions

Select Sessions from the bottom navigation bar to see your Top 3 Activities bar graph and Detection Totals for each activity type. Tap History to view the full list of past sessions. Each session shows the date, time, activity type, and whether it was recorded on your phone or watch. Tap a session to preview it. Long press to select it for batch actions.

## Filtering Sessions

1. On the History page tap the filter icon at the top right
2. Select a source (All, Phone, or Watch) and a month
3. Tap Close to apply or Reset All to clear the filter

## Exporting and Sharing Session Data

To export a session to your Downloads folder, tap Export CSV on the Analytics page after stopping a session.

To share a session from History, long press the session, tap Share, and choose an app from the share sheet. The CSV file is sent to your chosen destination.

## Deleting a Session

1. Go to the History page
2. Long press the session you want to delete
3. Tap Delete

The session is permanently removed from your device.

## FAQ

**Do I need an account to use the app?**
No. You can use all features without an account. However your session data will not be saved to the database. An account is recommended if you want your data stored over time.

**Where is my data stored?**
Session data is saved locally on your device as CSV files. If you are logged in, your profile information is also stored in Firebase.

**Can I turn off data sharing?**
Yes. Go to Settings and toggle off Allow Data Sharing.

**What does the confidence score mean?**
It shows how certain the machine learning model is about the current detection. The app only records an event if confidence is 90% or higher to avoid false alarms.

**Why is my watch not syncing?**
Make sure Bluetooth is on and your watch shows as connected to your phone. Check your watch's Bluetooth settings to confirm.

**How do I share data with a doctor?**
Go to the History page, select a session, tap Share, and send the CSV file via email or your preferred method.

## Troubleshooting

| Problem | What to do |
|---|---|
| App won't log in | Check your email and password and make sure you have an internet connection |
| Session data not showing | Make sure you tapped Start before moving and that sensors are active |
| Watch not syncing | Confirm Bluetooth is on and the watch shows as connected to your phone |
| CSV file not found | Check your Downloads folder and make sure you tapped Export CSV after stopping |
| App crashes | Restart the app. If the issue continues reinstall it |

## About

MotionWatch was developed by Heartland Computing as a Computer Science capstone project at Central Washington University, 2026. For more information about the development team open the app and navigate to Settings → About.
