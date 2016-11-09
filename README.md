# LinkedListView - 0.4.7

[ ![Download](https://api.bintray.com/packages/gensagames/maven/linkedlistview/images/download.svg) ](https://bintray.com/gensagames/maven/linkedlistview/_latestVersion)
 
It's simple view, that hold view and save links in list. Using several implementations - Adapter and AnimationController, LinkedListview allows to animate and change child view in anytime. Library contains several examples, and realization to animate views per scrolling, but LinkedListView only works with coordinates! Feel free to leave comments, suggestions and issues.



<img src="https://raw.githubusercontent.com/GensaGames/LinkedListView/master/screenshots/Screen-SimplePointMoving.png" width="280" height="450" />
<img src="https://raw.githubusercontent.com/GensaGames/LinkedListView/master/screenshots/Screen-ScaleCenter.png" width="280" height="450" />
<img src="https://raw.githubusercontent.com/GensaGames/LinkedListView/master/screenshots/Screen-PointMoving.png" width="280" height="450" />



## Getting started
You can find library in Maven and JCenter. Adding to project: 

#### Gradle
```
dependencies {
    compile 'com.github.gensagames:linkedlistview:0.4.7'
}
```
#### Maven
```
<dependency>
  <groupId>com.github.gensagames</groupId>
  <artifactId>linkedlistview</artifactId>
  <version>0.4.7</version>
  <type>aar</type>
</dependency>
```

## Basic Usage
Main view that extend ScrollView and hold all child views, as simple ListView - LinkedListView. All views managing takes LinkedListView.Adapter, he works as simple Android ArrayAdapter(SimpleAdapter, etc.). Some example of base initializing.

```
LinkedListView listView = new LinkedListView (getContext());
LinkedListView.Adapter adapter = new MyBaseAdapter ();
listview.setAdapter(adapter);
....
```
For updating viesw in any their states, use class LinkedListView.AnimationController. This class provides acces to all views and some usefull methods. **See samples for more information. Some part of methods already documented in library...**
```
....
LinkedListView.AnimationController controller = new MyBaseAnimController ();
listview.setAnimationController(controller);
....
```


## License

MIT License 

Copyright (c) 2016 - 2017 GensaGames

