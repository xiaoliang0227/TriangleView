# TriangleView

三角角标控件

直角三角角标，左上角、右上角、右下角、左下角

圆角三角角标，左上角、右上角、右下角、左下角

### gradle 引入方式如下
~~~
compile 'com.github.xiaoliang0227:TriangleView:1.0.1'
or
compile 'com.github.xiaoliang0227:app:1.0.1'
~~~

![image](https://github.com/xiaoliang0227/TriangleView/blob/master/screen_shot.png)

### 下面分别介绍两种角标的属性及使用方式
#### TriangleView（直角三角角标）
- **t_fillColor** 三角角标的填充色，默认为红色
- **t_textColor** 角标中文本的颜色，默认为白色
- **t_textSize** 角标中文本的大小，默认为36
- **t_text** 角标中文本内容
- **t_type** 角标的位置，分为四种：leftTop（左上角）、rightTop（右上角）、rightBottom（右下角）、leftBottom（左下角）

######使用样例
~~~
<com.zyl.triangleview.widget.TriangleView
                    android:layout_width="100dp"
                    android:layout_height="100dp"
                    t:t_fillColor="#FF0000"
                    t:t_text="测试"
                    t:t_textColor="#FFF"
                    t:t_textSize="18sp"
                    t:t_type="leftTop" />
~~~



#### RoundTriangleView（圆角三角角标）
- **rt_fillColor** 三角角标的填充色，默认为红色
- **rt_textColor** 角标中文本的颜色，默认为白色
- **rt_textSize** 角标中文本的大小，默认为36
- **rt_text** 角标中文本内容
- **rt_roundRadius** 圆角的大小
- **rt_type** 角标的位置，分为四种：leftTop（左上角）、rightTop（右上角）、rightBottom（右下角）、leftBottom（左下角）

######使用样例
~~~
<com.zyl.triangleview.widget.RoundTriangleView
                    android:layout_width="100dp"
                    android:layout_height="100dp"
                    t:rt_fillColor="#FF0000"
                    t:rt_roundRadius="20"
                    t:rt_text="测试"
                    t:rt_textColor="#FFF"
                    t:rt_textSize="18sp"
                    t:rt_type="leftTop" />
~~~
