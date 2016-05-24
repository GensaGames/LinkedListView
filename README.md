# LinkedListView - 0.2.0
======================

[ ![Download](https://api.bintray.com/packages/gensagames/maven/linkedlistview/images/download.svg) ](https://bintray.com/gensagames/maven/linkedlistview/_latestVersion)
 
It's simple view, that hold view and save links in list. Using several implementations - Adapter and AnimationController, LinkedListview allows to animate and change child view in anytime. Library contains several examples, and realization to animate views per scrolling, but LinkedListView only works with coordinates! Feel free to live comments, suggestions and issues.

Note! This is alpha verision. I will update this project with the new interesting samples and features in first free time. 



<img src="https://raw.githubusercontent.com/GensaGames/LinkedListView/master/screenshots/Screen-SimplePointMoving.gif" width="280" height="450" />
<img src="https://raw.githubusercontent.com/GensaGames/LinkedListView/master/screenshots/Screen-ScaleCenter.gif" width="280" height="450" />
<img src="https://raw.githubusercontent.com/GensaGames/LinkedListView/master/screenshots/Screen-PointMoving.gif" width="280" height="450" />



## Getting started
You can find library in Maven and JCenter. Adding to project: 

#### Gradle
```
dependencies {
    compile 'net.gotev:uploadservice:2.1'
}
```
#### Maven
```
<dependency>
  <groupId>net.gotev</groupId>
  <artifactId>uploadservice</artifactId>
  <version>2.1</version>
  <type>aar</type>
</dependency>
```
Main view that extend ScrollView and hold all child views, as simple ListView - LinkedListView. All views managing takes LinkedListView.Adapter, that works as simple Android ArrayAdapter(SimpleAdapter, etc.). Some example of base initializing.

```
LinkedListView listView = new LinkedListView (getContext());
LinkedListView.Adapter adapter = new MyBaseAdapter ();
listview.setViewPager(adapter);
....
```
For updating viesw in any their states, use class LinkedListView.AnimationController. This class provides acces to all views and some usefull methods. **Se samples for more information. Some part of methods already documented in library...**
```
....
LinkedListView.AnimationController controller = new MyBaseAnimController ();
listview.setAnimationController(controller);
....
```


## License

MIT License 

Copyright (c) 2016 GensaGames

