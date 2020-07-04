# GreenTrip酒店管理系统

## 说明

1. 本系统是南京大学软件学院2018级软件工程与计算二的大作业
2. 实现功能见文件夹文档/大作业需求v3，额外功能等待探索
3. 我们为本系统编写了用例文档、需求规格描述文档、体系结构描述文档、详细设计文档，均在文件夹文档中
4. 我们为本系统编写了一些前后端的测试用例，并附上测试代码和前后端测试文档

<br>

## 项目部署示例

示例所用为MacBook Pro和IDEA 2020.1.2

<br>

### 下载项目

从本仓库克隆代码至本地文件夹

```bash
git clone https://github.com/Condorcyh/SEECII_cooperation.git
```

<br>

### 导入项目

打开IDEA，选择 Open or Import（低版本选择Import Project），导入项目

<img src="https://i.loli.net/2020/07/04/vrKuai7LZ5VIcyJ.png" alt="1.png" style="zoom: 33%;" />

等待IDEA加载完毕（第一次可能会比较慢）

提示：需要安装Lombok插件

<br>

### 部署数据库

打开终端，启动数据库

```bash
mysql.server start
```

输入密码，登录数据库

```bash
mysql -u root -p
```

<img src="https://i.loli.net/2020/07/04/TFQeX7drRIzP58A.png" alt="2.png" style="zoom: 33%;" />

（我这里因为服务之前启动过了所以终端才提示要启动）

创建数据库hotel

```bash
create databases hotel;
```

创建完后可以检查一下数据库是否创建成功

<img src="https://i.loli.net/2020/07/04/uJQDlWbtP63aVBe.png" alt="3.png" style="zoom:33%;" />

输入exit退出数据库

<br>

### 在IDEA里连接数据库

点击右边的数据库，点击+，选择Data Source—MySQL

<img src="https://i.loli.net/2020/07/04/LCH5PkE4jNYcDUK.png" alt="4.png" style="zoom: 33%;" />



输入Name，User和Password，点击Test Connection测试连接

（可能会让你安装一些文件，安装即可；也可能会保时区错误，按照提示去到Advanced设置serverTimezone为Asia/Shanghai即可）

<img src="https://i.loli.net/2020/07/04/QjMp4EnG5OZW7rt.png" alt="5.png" style="zoom:33%;" />

​	

现在去到hotel.sql文件，在文件内右击，点击Run，选择hotel数据库

<img src="https://i.loli.net/2020/07/05/u7MSshlBgLWpvnJ.png" alt="6.png" style="zoom:33%;" />

这样数据库就连接好了

<br>

现在打开src/main/resources/application.yml文件，将里面的数据库密码改成自己的密码

<img src="https://i.loli.net/2020/07/05/r9a2CHZBb7sEpoM.png" alt="7.png" style="zoom:33%;" />

到这里后端就全部配置完毕了

<br>

### 前端配置

打开IDEA最下面一栏的Terminal，输入以下命令

```bash
cd front_end
npm install
```

这是在安装依赖等文件，只需要执行一次（前提是需要安装node.js）

有可能会安装失败，那就再装一次呗

<br>

安装成功后，执行

```bash
npm run serve
```

前端即开始运行

<br>

### 打开网站

打开浏览器输入网址localhost:8000

去到后端HotelApplication文件，运行后端

项目启动完成



有其他报错是难免的，但没有什么是百度谷歌不能解决的，无非就是配置问题