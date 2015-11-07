MVVM_Android-CleanArchitecture
=========================

@ https://github.com/zhengxiaopeng/MVVM_Android-CleanArchitecture

此项目由 [Android-CleanArchitecture](https://github.com/android10/Android-CleanArchitecture) fork 而来, 展现层(UI Layer or Presentation layer)重构成由 MVVM 模式实现，并去除 di(Dagger) 和 lambda。  
  
项目对应博客文章：[MVVM_Android-CleanArchitecture](http://rocko.xyz/2015/11/07/MVVM_Android-CleanArchitecture/)


Clean architecture
-----------------
![Clean Architecture](http://rocko-blog.qiniudn.com/MVVM_Android-CleanArchitecture-2.png)

Architectural approach
-----------------
![MVVM_Clean-Architecture 分层结构](http://rocko-blog.qiniudn.com/MVVM_Android-CleanArchitecture-3.png)

Architectural reactive approach
-----------------
![MVVM_Clean-Architecture put all 应用在一起](http://rocko-blog.qiniudn.com/MVVM_Android-CleanArchitecture-4.png)

Local Development
-----------------

Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check.
 * `./gradlew installDebug` - Install the debug apk on the current connected device.
 * `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration).
 * `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests.


License
--------
```
    Copyright 2014 Fernando Cejas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```