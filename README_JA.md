<div align="center">
<img src='logo.png' width='500px' alt="FolkLite logo">

[![Latest Release](https://img.shields.io/github/v/release/matsuzaka-yuki/FolkLite?label=Release&logo=github)](https://github.com/matsuzaka-yuki/FolkLite/releases/latest)
[![Channel](https://img.shields.io/badge/Follow-Telegram-blue.svg?logo=telegram)](https://t.me/FolkPatch)
[![GitHub License](https://img.shields.io/github/license/matsuzaka-yuki/FolkPatch?logo=gnu)](/LICENSE)

</div>

🌏 **README の言語:** [**English**](./README_EN.md) / [**中文**](./README.md) / [**日本語**](./README_JA.md)

**FolkLite** - FolkPatch の MIUIX 軽量版

[MIUIX](https://github.com/YuKongA/miuix) コンポーネントライブラリで再構築された Root 管理ツール。FolkPatch の全コア機能を維持しながら、ネイティブな MIUI/HyperOS スタイルのビジュアル体験とインタラクションデザインを提供します。

[📚 完全なドキュメントを読む](https://fp.mysqil.com/) →

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

## ✨ 紹介

FolkLite は FolkPatch の MIUIX 軽量ブランチです。洗練された MIUI/HyperOS スタイルのインターフェース体験を提供しながら、アップストリームの FolkPatch との完全なカーネルレベルの互換性を維持しています。

### 🎨 コア機能
- [x] KernelPatch ベースの Root 実装
- [x] カーネルの再コンパイルなしでカーネル関数をフック可能

### 📱 前提条件

- **必須：** ARM64 アーキテクチャベースで Linux カーネルバージョン 3.18 から 6.15 の Android デバイス
- **推奨：** Android 13+ で完全な視覚効果（ブラー、液態ガラスなど）を享受

### 🖼️ インターフェースとデザイン

- [x] **MIUIX デザイン言語** - MIUIX コンポーネントライブラリによるネイティブ MIUI/HyperOS スタイル
- [x] **Monet ダイナミックカラー** - 6つのテーマモード（Monet システム/ライト/ダーク + 標準 システム/ライト/ダーク）、16種のシードカラー
- [x] **リアルタイムブラー** - ナビゲーションバーとトップバーのリアルタイム背景ブラー（Android 13+）
- [x] **液態ガラス効果** - ブラー、ビブランシー、レンズ歪曲、鏡面反射、内蔵シャドウを含む多層グラスモーフィズム（Android 13+）
- [x] **フローティングボトムバー** - 物理ベースのスプリングドラッグ、インタラクティブハイライト、押圧変形、自動非表示を備えたカプセル型フローティングナビゲーション
- [x] **3つのホームレイアウト** - リストビュー / グリッドビュー / クラシックビュー
- [x] **ダンプドスプリングページ遷移** - MIUI スタイルの物理ベースアニメーション
- [x] **カスタムナビゲーションレイアウト** - 必要に応じてボトムナビゲーションタブの表示/非表示を切り替え
- [x] **アプリ DPI スケーリング** - 80% ~ 110% の連続スライダー調整
- [x] **代替ランチャーアイコン** - 2つのアイコンスタイルを切り替え可能
- [x] **予測型戻るジェスチャー** - Android 14+ の予測型戻るアニメーション（切り替え可能）
- [x] 国際化サポート

### 📦 モジュール関連
- [x] APM: Magisk ライクなモジュールシステム、一括フラッシュ、フルバックアップ、アンドゥアンインストール、ランチャーショートカットをサポート
- [x] KPM: カーネルモジュールシステム（inline-hook と syscall-table-hook をサポート）、自動ロードをサポート
- [x] オンラインストアから人気の APM または KPM モジュールをダウンロード
- [x] スマート APM ソート - 高優先度モジュールを上位に表示

### ⚡ 技術的特徴
- [x] [KernelPatch](https://github.com/bmax121/KernelPatch/) に基づいています
- [x] UI フレームワークは [MIUIX](https://github.com/YuKongA/miuix) に基づいています

## 🚀 ダウンロードとインストール

### 📦 インストールガイド

1. **ダウンロードとインストール:**
   [リリースページ](https://github.com/matsuzaka-yuki/FolkLite/releases/latest)から最新のインストールパッケージをダウンロード

2. **アプリをインストール:**
   最新のインストールパッケージをあなたの Android デバイスにインストール

3. **使用開始:**
   https://fp.mysqil.com/ を読んでください

## 🙏 オープンソースクレジット

このプロジェクトは以下のオープンソースプロジェクトに基づいています:

- [KernelPatch](https://github.com/bmax121/KernelPatch/) - コアコンポーネント
- [FolkPatch](https://github.com/matsuzaka-yuki/FolkPatch/) - アップストリームプロジェクト
- [MIUIX](https://github.com/YuKongA/miuix) - MIUI/HyperOS デザインコンポーネントライブラリ
- [Magisk](https://github.com/topjohnwu/Magisk) - magiskpolicy
- [KernelSU](https://github.com/tiann/KernelSU) - アプリ UI と Magisk ライクなモジュールサポート
- [Sukisu-Ultra](https://github.com/SukiSU-Ultra/SukiSU-Ultra) - 一部のインターフェースデザインを参照
- [APatch](https://github.com/bmax121/APatch) - 上流ブランチ

## 📄 ライセンス

- FolkLite は [GNU General Public License v3 (GPL-3)](http://www.gnu.org/copyleft/gpl.html) ライセンスの下でオープンソースされています。変更者または配布者として、以下の基準を遵守する必要があります:
- コードを変更した場合、またはプロジェクトに FolkLite を統合して第三者に配布する場合、プロジェクト全体も GPLv3 ライセンスの下でオープンソースする必要があります
- バイナリファイルを配布する場合、完全かつ読み取り可能なソースコードを積極的に提供するか、提供することを約束する必要があります
- ソフトウェアライセンス自体に対するライセンス料の徴収を厳禁します。配布、技術サポート、カスタム開発に対して料金を請求できます
- 配布行為は、プロジェクトに関連するすべてのユーザーにあなたの関連特許の使用権を付与することを意味します
- 本ソフトウェアは「現状のまま」提供され、いかなる保証もありません。原作者は本ソフトウェアの使用による損失について責任を負いません
- 上記の条項に違反すると GPLv3 ライセンスは自動的に終了します。その際、FolkLite を配布する正当な権利を失い、原作者は著作権侵害の責任を追求する権利（侵害停止命令の申請、経済的賠償、違反プロジェクトの削除を含むがこれらに限定されない）を留保します

## 💬 コミュニティとディスカッション

### ディスカッションとコミュニケーション
- Telegram チャンネル: [@FolkPatch](https://t.me/FolkPatch)
