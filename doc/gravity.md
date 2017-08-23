# 核心
> 宽度=原来宽度+剩余宽度*所占百分比。先按照没有给weight属性的分配，最后剩余的按照weight来分配 


# 结论：
+ 在layout_width设置为fill_parent的时候,layout_weight所代表的是你的控件要优先尽可能的大,但这个大是有限度的,即fill_parent.
+ 在layout_width设置为wrap_content的时候,layout_weight所代表的是你的控件要优先尽可能的小,但这个小是有限度的,即wrap_content.
+ 官方推荐layout_width设置为0dp,这样是均分