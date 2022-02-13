# Olife

Olife is an app that works like your private secretary. You can book terms, make notes, even record your thoughts or set alarms. All this for only one purpose - to help you to organise your life.

The application is built in MVVM architecture. The main issues of the project are:<br>
-Room Database<br>
-Coroutines<br>
-View Model<br>
-Dependency Injection - Hilt<br>
-Notifications

Navigation graph:

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/nav_graph.png" width="1200"/>

The home screen of the application contains two RecyclerViews which are responsible for displaying references to voice recordings and short notes. Short notes are saved in Room Database directly. In the case of voice notes, their memory address is stored in a Room Database. RecyclerViews are linked with the database through a ViewModel.

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/home_fragment.png" width="200"/>

The calendar fragment displays scheduled events. To add a new event pick a date and tap the button in the middle of the screen.

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/calendar_fragment.png" width="200"/>

We can adjust information about event in the event fragment. Here we also choose time of the notification.

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/event_fragment.png" width="200"/>

The forthcoming fragment consists of saved alarms section and forthcoming events section.

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/forthcoming_fragment.png" width="200"/>

Alarm details could be adjusted in the alarm fragment.

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/alarm_fragment.png" width="200"/>


The application is currently under testing process.
