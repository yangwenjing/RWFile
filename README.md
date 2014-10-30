RWFile
======

Reading and Writing Txt File, 主要思路是使用一个抽象类，实现读操作。具体处理操作为实现 抽象函数 dealwithfile。针对具体代码已经有实现的类。

主要包括3个包operation， main, entity.

operation
=======
主要的操作，实现代码。
AbsReadFile.java 是抽象类，实现了主要的读文件，可以读文件也可以读文件夹，就是一个迭代过程。

包含一个抽象方法 public abstract void dealWithFile(File file);

只需要继承这个抽象方法即可

operation.ReadMatrixFile
=========================
用于读取一个100*100的数组，其中有个参数是threshold,在config配置文件中配置。
定义了一个cell的数据结构。
cell的num值大于threshold, 那么这个cell叫做dense cell
几个相连接的dense cell叫做一个区域dense region.
出去dense region之外的为sparse region。

输入：
<x y num>
1 1 100

输出：cluster为region的id
<x,y,num,cluster>
1 1 100 1

entity
=======
主要定义需要的实体。这里定义了node, segment。

main
======
定义了config函数，制定读写路径。

并实现 *client 含有main函数的类，作为程序入口。


