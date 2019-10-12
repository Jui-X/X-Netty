# X-Netty

### chatroom目录下

多人聊天室-后台系统Demo-无前端UI-命令行指令操作

------

### 命令行指令

#### client/console目录下

##### 登录

> 无需指令：输入当前用户用户名

##### 登出

> 输入**logout**指令

##### 单聊

> 输入**sendToUser**指令：后接 #用户名 + “分割符”（换行符） + #消息信息

##### 创建群聊

> 输入**create**指令：后接#群名称 + “分隔符” + #群成员（一定是以英文半角逗号分隔）

##### 加入群聊

> 输入**join**指令：后接#群名称

##### 列出成员列表

> 输入list指令：后接#群名称

##### 发送群消息

> 输入**sendToGroup**指令：后接#群名称 + “分隔符” + #消息信息

##### 退出群聊

> 输入**quit**指令：后接#群名称

##### 上传文件

> 输入**uploadFile**指令：后接#文件路径（完整路径）

------

#### codec目录：编解码器

##### PacketCodeCHandler：各ChannelHandler之间共享的编解码器

------

#### enums目录：枚举类型实例目录

------

#### protocol目录：自定义协议目录

##### request目录：自定义请求协议Packet目录

##### response目录：自定义响应协议Packet目录

##### Packet类：所有自定义协议Packet父类

##### PacketCodeC：定义自定义协议的格式以及编解码方式

------

#### serialize目录：编解码中用到的序列化方式及其实现

- [ ] 此处可扩展其他高效序列化方式

------

#### client/XClient：客户端启动类

#### client/ClientInitializer：客户端Channel Initializer

#### client/handler：客户端下各种服务相应的Channel Handler

------

#### server/XClient：服务端启动类

#### server/ClientInitializer：服务端Channel Initializer

#### server/handler：服务端下各种服务相应的Channel Handler

------

#### util目录：工具类目录