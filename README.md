# Sorting and Searching Visualizer

Java 17 / JavaFX 桌面课程项目，支持六种排序算法与二叉搜索树查找动画。

## 运行
安装 JDK 17 和 Maven 3.9，在项目根目录执行 `mvn clean compile`，再执行 `mvn javafx:run`。首次构建需要联网下载依赖。无需本机 JavaFX SDK 绝对路径。

选择算法，输入逗号或空格分隔的整数，再点 Play。支持负数、重复值和 32 位整数；最多 50 个元素。BSTSearch 需要有效整数目标。重新播放会停止上一段动画。

## 本人分工
肖雅薇负责 JavaFX 界面开发、排序模块集成与代码合并。算法与其他模块属于团队成果。

## 结构与限制
`src/main/java/ui`：界面与动画控制；`algorithms`：排序步骤；`datastructures`：树结构。排序柱高按数值范围缩放。树布局采用固定画布，较深的树可能超出视野；尚未进行完整桌面交互回归。

## 验证
`javac -cp target/classes -d target/test-classes tests/SmokeTest.java` 后执行 `java -cp "target/classes;target/test-classes" SmokeTest`（macOS/Linux 将分号改为冒号）。
