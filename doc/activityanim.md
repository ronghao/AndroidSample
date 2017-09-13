# activity 专场动画问题研究
+ overridePendingTransition
+ Activity的theme



### theme 
    <style name="ActAnimTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="android:windowAnimationStyle">@style/Animation</item>
    </style>
    
    <!--动画-->
    <style name="Animation">
        <!--A打开B时，B的出现动画-->
        <item name="android:activityOpenEnterAnimation">@anim/right_to_center</item>
        <!--A打开B时，A的消失动画-->
        <item name="android:activityOpenExitAnimation">@anim/center_to_left</item>
        <!--A打开B后，关闭B时，A的出现动画-->
        <item name="android:activityCloseEnterAnimation">@anim/left_to_center</item>
        <!--A打开B后，关闭B时，B的消失动画-->
        <item name="android:activityCloseExitAnimation">@anim/center_to_right</item>
    </style>

### 如果设置了 windowIsTranslucent 为true
+ activityOpenExitAnimation 失效
+ activityCloseExitAnimation 失效
+ 当Activity A启动Activity B， A不透明，B透明，那么 A的onStop不会被调用，只会调用onPause


### 解决方案
+ 设置windowIsTranslucent为false
+ 反之
    + activityOpenExitAnimation 失效
    + activityCloseExitAnimation 失效
    + 动画能执行

      <style name="animation_translucent" parent="@android:style/Animation.Translucent">
        <item name="android:windowEnterAnimation">@anim/right_to_center</item>
        <item name="android:windowExitAnimation">@anim/center_to_right</item>
      </style>