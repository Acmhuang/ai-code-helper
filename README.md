# AI 编程小助手

一个基于 LangChain4j 和 Spring Boot 的智能编程助手，专注于为程序员提供编程学习、项目指导和求职面试相关的帮助。

## 📋 项目简介

AI 编程小助手是一个全栈 Web 应用，旨在帮助程序员在学习和职业发展过程中获得智能化的指导和建议。该项目由 Java Spring Boot 后端和 Vue.js 前端组成，提供实时聊天功能和专业的编程咨询服务。

### 🎯 核心功能

- **编程学习路线规划** - 为不同技术栈提供清晰的学习路径
- **项目学习建议** - 提供项目实战指导和最佳实践
- **求职指南** - 涵盖简历优化、投递技巧等求职全流程
- **面试题分享** - 高频面试题解析和面试技巧指导

### 🛠️ 技术特性

- **流式响应** - 基于 Server-Sent Events (SSE) 的实时对话体验
- **智能对话** - 集成阿里云通义千问 (Qwen) 大语言模型
- **安全防护** - 内置输入安全检查和防护机制
- **现代前端** - Vue 3 + Vite 构建的响应式用户界面
- **知识库** - 内置编程学习和求职相关文档资源

## 🏗️ 项目架构

```
ai-code-helper/
├── src/main/java/                     # Java 后端源码
│   ├── ai/                           # AI 相关核心功能
│   │   ├── AiCodeHelperService.java  # AI 服务接口
│   │   ├── guardrail/               # 安全防护
│   │   ├── model/                   # 模型配置
│   │   ├── rag/                     # RAG 检索增强
│   │   └── tools/                   # AI 工具集成
│   ├── controller/                   # REST API 控制器
│   └── config/                      # 应用配置
├── src/main/resources/
│   ├── docs/                        # 知识库文档
│   ├── application.yml               # 应用配置
│   └── system-prompt.txt            # AI 系统提示词
├── ai-code-helper-frontend/          # Vue.js 前端
│   ├── src/
│   │   ├── components/              # Vue 组件
│   │   ├── api/                     # API 调用
│   │   └── utils/                   # 工具函数
│   ├── package.json
│   └── vite.config.js
└── pom.xml                          # Maven 项目配置
```

## 🚀 快速开始

### 前置要求

- Java 17+
- Node.js 16+
- Maven 3.6+
- 阿里云通义千问 API Key

### 后端启动

1. **克隆项目**
```bash
git clone <repository-url>
cd ai-code-helper
```

2. **配置 API Key**
编辑 `src/main/resources/application-local.yml`，配置您的 API Key：
```yaml
langchain4j:
  community:
    dashscope:
      chat-model:
        api-key: your-dashscope-api-key
        model-name: qwen-plus
```

3. **启动后端服务**
```bash
mvn spring-boot:run
```

后端服务将在 `http://localhost:8081/api` 启动。

### 前端启动

1. **进入前端目录**
```bash
cd ai-code-helper-frontend
```

2. **安装依赖**
```bash
npm install
```

3. **启动开发服务器**
```bash
npm run dev
```

前端应用将在 `http://localhost:3000` 启动。

## 🔧 技术栈

### 后端技术
- **框架**: Spring Boot 3.5.5
- **AI 框架**: LangChain4j 1.1.0
- **大语言模型**: 阿里云通义千问 (Qwen Plus)
- **构建工具**: Maven
- **Java 版本**: 17

### 前端技术
- **框架**: Vue.js 3.3.4
- **构建工具**: Vite 4.4.9
- **HTTP 客户端**: Axios 1.5.0
- **Markdown 渲染**: Marked 16.0.0

### 核心依赖

#### 后端依赖
```xml
<!-- LangChain4j AI 框架 -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-community-dashscope-spring-boot-starter</artifactId>
    <version>1.1.0-beta7</version>
</dependency>

<!-- Spring Boot Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- 响应式编程支持 -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-reactor</artifactId>
    <version>1.1.0-beta7</version>
</dependency>
```

#### 前端依赖
```json
{
  "dependencies": {
    "vue": "^3.3.4",
    "axios": "^1.5.0",
    "marked": "^16.0.0"
  }
}
```

## 🔌 API 接口

### 聊天接口
```
GET /ai/chat
```

**参数:**
- `memoryId` (int): 会话内存ID，用于维持对话上下文
- `message` (string): 用户输入的消息内容

**响应:** Server-Sent Events 流式响应

**示例:**
```javascript
const eventSource = new EventSource(
  `http://localhost:8081/api/ai/chat?memoryId=12345&message=如何学习Java？`
);

eventSource.onmessage = function(event) {
  console.log('AI回复:', event.data);
};
```

## 📚 知识库

项目内置了丰富的编程学习和求职相关文档：

- `Java 编程学习路线.md` - Java 技术栈完整学习路径
- `程序员常见面试题.md` - 精选高频面试题和答案
- `鱼皮的求职指南.md` - 程序员求职全流程指导
- `鱼皮的项目学习建议.md` - 项目实战学习建议

## 🛡️ 安全特性

- **输入安全检查**: 集成 `SafeInputGuardrail` 对用户输入进行安全验证
- **CORS 配置**: 允许跨域访问，支持前后端分离部署
- **API Key 保护**: 敏感配置信息通过配置文件管理

## 🎨 用户界面

- **现代化设计**: 采用简洁的 Material Design 风格
- **响应式布局**: 支持桌面端和移动端访问
- **实时对话**: 支持流式消息显示和 Markdown 渲染
- **错误处理**: 友好的错误提示和连接状态显示

## 📝 开发指南

### 添加新的 AI 工具
1. 在 `src/main/java/com/acmhuang/ai/aicodehlper/ai/tools/` 下创建新的工具类
2. 实现对应的功能方法
3. 在 AI 服务中注册新工具

### 扩展知识库
1. 在 `src/main/resources/docs/` 目录下添加新的 Markdown 文档
2. 更新 RAG 配置以包含新文档

### 前端组件开发
1. 在 `ai-code-helper-frontend/src/components/` 下创建新组件
2. 遵循 Vue 3 Composition API 最佳实践
3. 保持组件的可复用性和响应式设计

## 📄 许可证

本项目基于 MIT 许可证开源 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

- [LangChain4j](https://github.com/langchain4j/langchain4j) - 强大的 Java AI 应用框架
- [阿里云通义千问](https://dashscope.aliyun.com/) - 提供智能对话能力
- [Vue.js](https://vuejs.org/) - 渐进式 JavaScript 框架
- [Spring Boot](https://spring.io/projects/spring-boot) - 企业级 Java 应用框架
- 以及李鱼皮大佬
