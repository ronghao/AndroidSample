# AndroidSample
android 示例 分析,使用,应用场景,性能,源码

### 生命周期
+ 启动
    + onCreate -> onStart -> onResume
+ 启动另一个activity2
    + onPause(1) -> onCreate(2) -> onStart(2) - onResume(2) -> onSaveInstanceState(1) -> onStop(1)
+ 再返回activity1
    + onPause(2) -> onRestart(1) -> onStart(1) -> onResume(1) -> onStop(2) -> onDestroy(2)
+ BACK键
    + onPause -> onSaveInstanceState -> onStop -> onDestory
+ HOME键
    + onPause -> onSaveInstanceState -> onStop
+ 再启动
    + onRestart -> onStart -> onResume
+ 长按HOME键，选择运行其他的程序
    + onPause -> onSaveInstanceState -> onStop
+ 锁屏
    + onPause -> onSaveInstanceState -> onStop
+ 解锁
    + onRestart -> onStart -> onResume
+ 横竖屏切换（不处理）
    + onPause -> onStop -> onDestory -> onCreate -> onStart -> onResume
+ 横竖屏切换 处理
    + android:configChanges="keyboardHidden|orientation|screenSize"