# Olife

Olife is an app that works like your private secretary. You can book terms, make notes, even record your thoughts or set alarms. All this for only one purpose - to help you to organise your life.

The application is built in MVVM architecture. The main features of this project are:<br>
-Room Database<br>
-Coroutines<br>
-View Model<br>
-Dependency Injection - Hilt<br>

The home screen of the application contains two RecyclerViews which are responsible for displaying references to voice recordings and short notes. Short notes are saved in Room Database directly. In the case of voice notes, their memory address is stored in Room Database. RecyclerViews are linked with the database through ViewModel.

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/257442256_1056712801772353_6751693274998899807_n.jpg" width="200"/>

The calendar screen will present a multifunctional calendar. Further plans assume adding events and notifications.

<img src="https://github.com/karolkadlubowski/Olife/blob/main/AppScreens/257279191_345392477351506_7447171880428410513_n.jpg" width="200"/>

Third screen will include forthcoming events and alarms.

App is currently under construction.
