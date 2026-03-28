<div align="center">
<img src='logo.png' width='500px' alt="FolkLite logo">

[![Latest Release](https://img.shields.io/github/v/release/matsuzaka-yuki/FolkLite?label=Release&logo=github)](https://github.com/matsuzaka-yuki/FolkLite/releases/latest)
[![Channel](https://img.shields.io/badge/Follow-Telegram-blue.svg?logo=telegram)](https://t.me/FolkPatch)
[![GitHub License](https://img.shields.io/github/license/matsuzaka-yuki/FolkPatch?logo=gnu)](/LICENSE)

</div>

🌏 **README Language:** [**English**](./README_EN.md) / [**中文**](./README.md) / [**日本語**](./README_JA.md)

**FolkLite** - A MIUIX Lightweight Edition of FolkPatch

A Root management tool rebuilt with the [MIUIX](https://github.com/YuKongA/miuix) component library, delivering a native MIUI/HyperOS visual experience and interaction design while retaining all core functionality of FolkPatch.

[📚 Read Full Documentation](https://fp.mysqil.com/) →

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

## ✨ Introduction

FolkLite is a MIUIX lightweight branch of FolkPatch, focused on delivering a refined MIUI/HyperOS-style interface experience while maintaining full kernel-level compatibility with upstream FolkPatch.

### 🎨 Core Features
- [x] Root implementation based on KernelPatch
- [x] Hook kernel functions without recompiling the kernel

### 📱 Prerequisites

- **Required:** ARM64 architecture Android device with Linux kernel version 3.18 to 6.15
- **Recommended:** Android 13+ for full visual effects (blur, liquid glass, etc.)

### 🖼️ Interface & Design

- [x] **MIUIX Design Language** - Built on the MIUIX component library for native MIUI/HyperOS style
- [x] **Monet Dynamic Colors** - 6 theme modes (Monet System/Light/Dark + Standard System/Light/Dark) with 16 seed colors
- [x] **Real-time Blur** - Live background blur on navigation and top bars (Android 13+)
- [x] **Liquid Glass Effect** - Multi-layered glassmorphism with blur, vibrancy, lens distortion, specular highlights, and inner shadows (Android 13+)
- [x] **Floating Bottom Bar** - Capsule-shaped floating navigation with physics-based spring drag, interactive highlights, press deformation, and auto-hide
- [x] **Three Home Layouts** - List View / Grid View / Classic View
- [x] **Damped Spring Page Transitions** - MIUI-style physics-based animations
- [x] **Custom Navigation Layout** - Show/hide bottom navigation tabs as needed
- [x] **App DPI Scaling** - Continuous 80% ~ 110% slider adjustment
- [x] **Alternative Launcher Icon** - Switch between two icon styles
- [x] **Predictive Back Gesture** - Android 14+ predictive back animation (toggleable)
- [x] Internationalization support

### 📦 Module Related
- [x] APM: Magisk-like module system with batch flashing, full backup, undo-uninstall, and launcher shortcuts
- [x] KPM: Kernel module system (supports inline-hook and syscall-table-hook) with auto-loading
- [x] Download popular APM or KPM modules through the online store
- [x] Smart APM sorting - Higher priority modules displayed first

### ⚡ Technical Features
- [x] Based on [KernelPatch](https://github.com/bmax121/KernelPatch/)
- [x] UI framework based on [MIUIX](https://github.com/YuKongA/miuix)

## 🚀 Download & Install

### 📦 Installation Guide

1. **Download & Install:**
   Download the latest installation package from the [Releases page](https://github.com/matsuzaka-yuki/FolkLite/releases/latest)

2. **Install App:**
   Install the latest installation package to your Android device

3. **Get Started:**
   Read https://fp.mysqil.com/

## 🙏 Open Source Credits

This project is based on the following open source projects:

- [KernelPatch](https://github.com/bmax121/KernelPatch/) - Core component
- [FolkPatch](https://github.com/matsuzaka-yuki/FolkPatch/) - Upstream project
- [MIUIX](https://github.com/YuKongA/miuix) - MIUI/HyperOS design component library
- [Magisk](https://github.com/topjohnwu/Magisk) - magiskpolicy
- [KernelSU](https://github.com/tiann/KernelSU) - App UI and Magisk-like module support
- [Sukisu-Ultra](https://github.com/SukiSU-Ultra/SukiSU-Ultra) - Referenced some interface designs
- [APatch](https://github.com/bmax121/APatch) - Upstream branch

## 📄 License

- FolkLite is open sourced under the [GNU General Public License v3 (GPL-3)](http://www.gnu.org/copyleft/gpl.html) license. As a modifier or distributor, you must comply with the following standards:
- If you modify the code or integrate FolkLite into your project and distribute it to a third party, your entire project must also be open sourced under the GPLv3 license
- When distributing binary files, you must actively provide or promise to provide complete and readable source code
- Strictly prohibit charging licensing fees for the software license itself. You may charge for distribution, technical support, or customized development
- Distribution implies that you grant all users the relevant patents involved in the use of the project
- This software is provided "as is", without any warranty. The original author is not responsible for any losses caused by using this software
- Any violation of the above terms will automatically terminate your GPLv3 license. At that time, you will lose the legal right to distribute FolkLite. The original author reserves the right to pursue copyright infringement liability (including but not limited to applying for injunctions to stop infringement, economic compensation, and removing infringing projects)

## 💬 Community & Discussion

### Discussion & Communication
- Telegram Channel: [@FolkPatch](https://t.me/FolkPatch)
