# Arkanoid
Arkanoid Game - written in java.

The game contains four levels with different difficulty.

![](media/Levels.png)

### interfaces
Animation - An Animation interface. Has two functions- "doOneFrame" that put the changes in the drawsurface and and "shouldStop" that indicates to stop the animation.

Sprite: interface of Sprite. An object that can be drawen on a DrawSurface.

HitNotifier: interface of Hit Notifier.

HitListener: interface of Hit Listener.

LevelInformation: An interface of level information.

Collidable: A collidable interface. An object that other objects can collide it.

### Geometry
Line: Represents a line that contains two points one start and one end.

Point: Represents a point that contains x and y.

Rectangle: A Rectangle that contains upperLeft Point, width and height.

### Sprites
Classes that implements Sprite interface:

Block: A block that contains a rectangle and a color, implements Collidable, Sprite, HitNotifier.

Paddle: A paddle that contains a rectangle, color and keyboard sensor.

Ball: A ball that contains his location and radius and functions of set to his fields and move his location.

### Levels
Classes that implements LevelInformation interface:

DirectHit: Level one.

WideEasy: Level two.

Green3: Level three.

FinalFour: Level four.

### Backgrounds
Classes that implements Sprite interface:

BackGround1: A sprites backGround.

BackGround2: A sprites backGround.

BackGround3: A sprites backGround.

BackGround4: A sprites backGround.


### Game Settings
