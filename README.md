<div align="center">
<img src='logo.png' width='500px' alt="FolkLite logo">

[![Latest Release](https://img.shields.io/github/v/release/matsuzaka-yuki/FolkLite?label=Release&logo=github)](https://github.com/matsuzaka-yuki/FolkLite/releases/latest)
[![Channel](https://img.shields.io/badge/Follow-Telegram-blue.svg?logo=telegram)](https://t.me/FolkPatch)
[![GitHub License](https://img.shields.io/github/license/matsuzaka-yuki/FolkPatch?logo=gnu)](/LICENSE)

</div>

🌏 **README 语言:** [**English**](./README_EN.md) / [**中文**](./README.md) / [**日本語**](./README_JA.md)

**FolkLite** - FolkPatch 的 MIUIX 轻量化版本

基于 [MIUIX](https://github.com/compose-miuix-ui/miuix) 组件库重新打造的 Root 管理工具，在保留 FolkPatch 全部核心功能的同时，带来原生 MIUI/HyperOS 风格的视觉体验与交互设计。

[📚 阅读完整文档](https://fp.mysqil.com/) →

<table>
  <tr>
    <td><img alt="" src="docs/1.png"></td>
    <td><img alt="" src="docs/2.png"></td>
    <td><img alt="" src="docs/3.png"></td>
  <tr>
  <tr>
    <td><img alt="" src="docs/4.png"></td>
    <td><img alt="" src="docs/5.png"></td>
    <td><img alt="" src="docs/6.png"></td>
  <tr>
</table>

---

## ✨ 介绍

FolkLite 是 FolkPatch 的 MIUIX 轻量化分支，专注于为用户提供精致的 MIUI/HyperOS 风格界面体验，同时保持与上游 FolkPatch 完全兼容的内核级功能。

### 🎨 核心功能
- [x] 基于 KernelPatch 的 Root 实现
- [x] 无需重新编译内核即可 Hook 内核函数

### 📱 前置要求

- **必须：** 基于 ARM64 架构且 Linux 内核版本 3.18 至 6.15 的 Android 设备
- **建议：** Android 13+ 以获得完整的视觉效果（模糊、液态玻璃等）

### 🖼️ 界面与设计

- [x] **MIUIX 设计语言** - 基于 MIUIX 组件库，还原原生 MIUI/HyperOS 风格
- [x] **Monet 动态取色** - 6 种主题模式（Monet 跟随系统/浅色/深色 + 标准 跟随系统/浅色/深色），16 种种子颜色可选
- [x] **实时模糊效果** - 导航栏与顶栏的实时背景模糊（需 Android 13+）
- [x] **液态玻璃效果** - 多层光学效果叠加的玻璃拟态设计，包含模糊、色彩振动、镜头折射、高光反射与内阴影（需 Android 13+）
- [x] **悬浮底栏** - 胶囊形状的悬浮导航栏，支持物理弹簧拖拽动画、交互高光、按压形变、自动隐藏
- [x] **三种主页布局** - 列表视图 / 网格视图 / 经典视图
- [x] **页面弹簧过渡动画** - MIUI 风格的阻尼弹簧物理动画
- [x] **自定义导航布局** - 可按需显示/隐藏底部导航栏各标签页
- [x] **App DPI 调节** - 80% ~ 110% 连续滑动缩放
- [x] **备用图标** - 支持切换启动器图标样式
- [x] **预测性返回手势** - Android 14+ 预测性返回动画（可切换）
- [x] 国际化支持

### 📦 模块相关
- [x] APM: 类 Magisk 模块系统，支持批量刷入、全量备份、撤销卸载、桌面快捷方式
- [x] KPM: 内核模块系统（支持 inline-hook 与 syscall-table-hook），支持自动加载
- [x] 通过在线商店下载热门 APM 或 KPM 模块
- [x] 智能 APM 排序 - 高优先级模块优先展示

### ⚡ 技术特性
- [x] 基于 [KernelPatch](https://github.com/bmax121/KernelPatch/)
- [x] UI 框架基于 [MIUIX](https://github.com/compose-miuix-ui/miuix)

## 🚀 下载安装

### 📦 使用指导

1. **下载安装：**
   从 [发布页面](https://github.com/matsuzaka-yuki/FolkLite/releases/latest) 下载最新版安装包

2. **安装应用：**
   安装最新版安装包到你的 Android 设备

3. **开始使用：**
   阅读 https://fp.mysqil.com/

## 🙏 开源致谢

本项目基于以下开源项目：

- [KernelPatch](https://github.com/bmax121/KernelPatch/) - 核心组件
- [FolkPatch](https://github.com/matsuzaka-yuki/FolkPatch/) - 上游项目
- [MIUIX](https://github.com/YuKongA/miuix) - MIUI/HyperOS 设计组件库
- [KernelSU](https://github.com/tiann/KernelSU) - 应用 UI 和类似 Magisk 的模块支持
- [APatch](https://github.com/bmax121/APatch) - 上游分支

## 📄 许可证

- FolkLite 遵循 [GNU General Public License v3 (GPL-3)](http://www.gnu.org/copyleft/gpl.html) 许可证开源，作为二改者或分发者，您需遵守以下标准：
- 若您修改了代码或在项目中集成了 FolkLite 并向第三方分发，您的整个项目必须同样采用 GPLv3 协议开源
- 分发二进制文件时，必须主动提供或承诺提供完整且可读的源代码
- 严禁对软件授权本身收取许可费，您可以针对分发、技术支持或定制开发收费
- 分发行为即代表您授予所有用户使用该项目涉及的您的相关专利
- 本软件"按原样"提供，不含任何担保，原作者不对因使用本软件造成的任何损失负责
- 任何违反上述条款的行为将导致您的 GPLv3 授权自动终止，届时，您将失去分发 FolkLite 的合法权利，原作者保留依法追究著作权侵权责任（包括但不限于申请停止侵权禁令、经济赔偿及下架违规项目）的权利

## 💬 社区交流

### 讨论交流
- Telegram 频道: [@FolkPatch](https://t.me/FolkPatch)
- QQ群: 1049815774
