# MoodTracker2
## Introduction 
MoodTracker is a mood tracker app where the user is able to enter the current mood, add a note to describe why he is feeling that way and see the mood history of the past seven days. Any note left can also be consulted. This app has the requisite of Android 4.4 or above, which covers 95% of all Android users. 
 
## Homepage 
When the user enters the app, it is displayed the homepage:<br/><br/>
<img src="https://i.ibb.co/R2KyHTw/1.png" alt="1" border="0">

On the homepage, it is possible to select the mood for the day, add a note and go to history of moods.
By sliding towards the top, it will be shown the next mood, which is happier. By sliding towards the bottom, it will be shown the previous mood, which is less happy.  Each mood has a different background color and a different image:<br/><br/>

<img src="https://i.ibb.co/h1NNFhm/2.png" alt="2" border="0">

It is possible to choose between five moods:<br/>
* Red background: Really bad mood;
* Grey background: Bad mood;
* Blue background: Decent mood;
* Green background: Good mood;
* Yellow background: Great mood;<br/>

Besides the different background and image, each mood has a sound associated that is played when that particular mood is selected. This feature was implemented because it creates a more dynamic mood choosing.
When the user enters the app for the first time each day, it will be pre-selected the “Great mood” as a default. When the app is closed the mood and note is stored and it will be set for an entire day, but at any point during the day, it is possible reopen the app to change the mood and note. At midnight, the mood at that point in time will be saved as the mood of the day.
On the bottom left of the screen there is a button to add/edit a note, after clicking it will be displayed a dialog to enter the note:<br/><br/>

<img src="https://i.ibb.co/gvqM1pv/3.png" alt="3" border="0">

On the bottom right of the screen there is a bottom that when clicked shows to the use the mood history screen:<br/><br/>

<img src="https://i.ibb.co/Msx9jPc/4.png" alt="4" border="0">

## Mood history screen

On the mood history screen, it will be displayed the moods from the past 7 days. It  will be shown as horizontal bars arranged vertically from oldest to newest. An icon indicates when a mood has an associated note. If a mood has a note and you click in it, the note appears briefly at the bottom of the screen:<br/><br/>

<img src="https://i.ibb.co/5nD8mD5/5.png" alt="5" border="0">

If there is any day where the user didn’t enters a mood, it will be displayed as following:

<img src="https://i.ibb.co/ZGBK8sT/6.png" alt="6" border="0">
